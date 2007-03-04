//
// File:   NetworkManager.h
// Author: garwind
//
// Created on 2 mars 2007, 16:57
//
#ifndef _NetworkManager_H
#define	_NetworkManager_H

#include <string>
#include <netdb.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/wait.h>
#include <sys/socket.h>

using namespace std;

class NetworkManager {
    // --- static ---
    static const int REMOTE_CLIENT_PORT = 3390;
    static const int LOCAL_CLIENT_PORT = 3391;
    static const int MAX_BUFFER_SIZE = 128;
    
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
        
        void sendToRemoteClient(char* _buffer) 
        {
            printf(" size = %d \n", strlen(_buffer));
            
            if ((send(sockfd, _buffer, strlen(_buffer) /*leng*/, 0)) == -1) {
                perror("send");
                exit(1);
                printf("buffer = %s \n", _buffer );
            }
        }
        
        void prepareSocket() 
        {
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
        
        void closeServer()
        {
            printf("fin serveur c++\n");
            close(sockfd);
        }
};


#endif

