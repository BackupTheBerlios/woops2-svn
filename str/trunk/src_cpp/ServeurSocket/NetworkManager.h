//
// File:   NetworkManager.h
// Author: garwind
//
// Created on 2 mars 2007, 16:57
//
#ifndef _NetworkManager_H
#define	_NetworkManager_H

#include <sys/socket.h>
#include <netinet/in.h>
#include <string>
#include <stdio.h> 
#include <stdlib.h> 
#include <errno.h>
#include <sys/types.h> 
#include <sys/socket.h> 
#include <sys/wait.h> 
#include <netdb.h>

using namespace std;

class NetworkManager {
    
    // --- static ---
    static const int REMOTE_CLIENT_PORT = 3390;
    static const int LOCAL_SERVER_PORT = 3391;
    static const int MAX_BUFFER_SIZE = 128;
    static const int MAX_CONNEXIONS = 10;
    
    // Interpretor interpretor;
    char* remoteHost;
    
    int sockfd;
    struct hostent *he;
    struct sockaddr_in their_addr; /* Adresse de celui qui se connecte */
    
    public:
        /*
         Interpretor getInterpretor(){return interpretor;}
         void setInterpretor(Interpretor _i){interpretor = _i;}
         */
        
        char* getRemoteHost(){return remoteHost;}
        void setRemoteHost(char* _rh){ remoteHost = _rh;}
        
        NetworkManager(){}
        
        void sendToRemoteClient(char* _buffer) {
            printf("sending to remote host & buffer size = %d \n", strlen(_buffer));
            
            if ((send(sockfd, _buffer, strlen(_buffer) /*leng*/, 0)) == -1) {
                perror("send");
                exit(1);
                printf("buffer = %s \n", _buffer );
            }
            
            printf("buffer sent to remote host ! \n");
        }
        
        void prepareSocket() {
            printf("preparing local socket ... \n");
            
            if ((he=gethostbyname(remoteHost)) == NULL){
                herror("gethostbyname");
                exit(1);
            }
            
            if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
                perror("socket");
                exit(1);
            }
            
            their_addr.sin_family = AF_INET;      /* host byte order */
            their_addr.sin_port = htons(REMOTE_CLIENT_PORT);    /* short, network byte order */
            their_addr.sin_addr = *((struct in_addr *)he->h_addr);
            bzero(&(their_addr.sin_zero), 8);     /* zero pour le reste de struct */
            
            if (connect(sockfd, (struct sockaddr *)&their_addr, sizeof(struct sockaddr)) == -1) {
                perror("connect");
                exit(1);
            }
            
        }
        
        void closeSocket() {
            printf("shutdowning server ... \n");
            close(sockfd);
            printf("server closed ! \n");
        }
        
        void startListening() {
            printf("starting listening ...\n");
            switch(fork()) {
                case -1 :  perror("fork");
                exit(1);
                case 0:	listenLocalSocket();
                default: break;
            }
        }
        
        void listenLocalSocket() {
            
            int sockfd, new_fd;  /* Ecouter sock_fd, nouvelle connection sur new_fd */
            int numbytes;
            char buf[MAX_BUFFER_SIZE];
            
            struct sockaddr_in local_addr;    
            /*
            struct sockaddr_in remote_addr; 
            */
            socklen_t sin_size;
            
            if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
                perror("socket");
                exit(1);
            }
            
            local_addr.sin_family = AF_INET;
            local_addr.sin_port = htons(LOCAL_SERVER_PORT);
            local_addr.sin_addr.s_addr = INADDR_ANY; /* auto-remplissage IP */
            bzero(&(local_addr.sin_zero), 8);
            
            if (bind(sockfd, (struct sockaddr *)&local_addr, sizeof(struct sockaddr)) \
            == -1) {
                perror("bind");
                exit(1);
            }
            
            if (listen(sockfd, MAX_CONNEXIONS) == -1) {
                perror("listen");
                exit(1);
            }
            
            while(1) {  /* main accept() loop */
                printf("server is listening ... \n");
                sin_size = sizeof(struct sockaddr_in);
                if ((new_fd = accept(sockfd, (struct sockaddr *) &their_addr, &sin_size)) == -1) {
                    perror("accept");
                    continue;
                }
                
                printf("connection required ... \n");
                
                if (!fork()) { /* processus fils */
                    /*if (send(new_fd, "Hello World ! \n", 14, 0) == -1)
                     perror("send");*/
                    while(1) {
                        if ((numbytes=recv(new_fd, buf, MAX_BUFFER_SIZE, 0)) == -1) {
                            perror("recv");
                        }
                        
                        buf[numbytes] = '\0';
                        
                        printf("buffer received : %s \n", buf);
                        
                    }
                    
                    printf("closing listening \n");
                    close(new_fd);
                    printf("server listening stopped ! \n");
                    exit(0);
                }
                printf("closing listening \n");
                close(new_fd);
                printf("server listening stopped ! \n");
                
                while(waitpid(-1, NULL, WNOHANG) > 0);
            }
        }
};


#endif

