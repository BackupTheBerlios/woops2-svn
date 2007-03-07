#ifndef _Network_H
#define	_Network_H

#include <sys/socket.h>
#include <netinet/in.h>
#include <string>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <netdb.h>

using namespace std;

namespace Network{
    
    class NetworkManager;
    
    class AbstractSystemObject;
    
    class Interpretor {
        NetworkManager * networkManager;
        AbstractSystemObject * abstractSystemObject;
        
        public:
            NetworkManager* getNetworkManager(){ return networkManager;}
            void setNetworkManager(NetworkManager* nm){ networkManager = nm;}
            AbstractSystemObject* getAbstractSystemObject() { return abstractSystemObject; }
            void setAbstractSystemObject(AbstractSystemObject* aso ) { abstractSystemObject = aso; }
            
            void buildAbstractSystemObject(string buffer){} // met a jours l'attribut
            string interpreteCurrentAbstractSystemObject(){} // prepare buffer AbstractSystemObject courant
    };
    
    
    class AbstractSystemObject {
        Interpretor* interpretor;
        string buffer;
        
        public:
            Interpretor* getInterpretor(){return interpretor;}
            void setInterpretor(Interpretor* _i){interpretor = _i;}
            string getBuffer(){return buffer;}
            void setBuffer(string _buff){buffer = _buff;}
            
            AbstractSystemObject(string _buff){buffer = _buff;}
            virtual void applyToSystem() = 0; // methode abstraite
    };
    
    class NetworkManager {
        
        // --- static ---
        static const int DATA_PORT = 3391;
        static const int COMMAND_PORT = 3390;
        
        static const int MAX_BUFFER_SIZE = 128;
        static const int MAX_CONNEXIONS = 10;
        
        Interpretor interpretor;
        char* remoteHost;
        
        int commandSocket, acceptedCommandSocket;
        int numbytes;
        
        char buf[MAX_BUFFER_SIZE];
        char* buf2;
        //char* buf;
        
        struct sockaddr_in my_addr;
        struct sockaddr_in their_addr;
        struct hostent *he;
        socklen_t sin_size;
        
        int dataSocket, dataBytes;
        
        public:
            
            Interpretor getInterpretor(){return interpretor;}
            void setInterpretor(Interpretor _i){interpretor = _i;}
            
            
            char* getRemoteHost(){return remoteHost;}
            void setRemoteHost(char* _rh){ remoteHost = _rh;}
            
            NetworkManager(){
                remoteHost = "localhost";
                
                printf("prepareCommandSocket \n");
                prepareCommandSocket();
                printf("prepareDataSocket \n");
                prepareDataSocket();
                printf("runListenServer \n");
                runListenServer();
            }
            
            void sendBuffer(char* _buffer){
                printf("dataSocket sending : %s \n", buf);
                
                if ((dataBytes=send(dataSocket, buf, strlen(buf), 0)) == -1) {
                    perror("send");
                    exit(1);
                }
            }
            
            void closeSockets(){
                close(dataSocket);
                close(commandSocket);
            }
            
            private:
                
                void prepareCommandSocket(){
                    
                    if ((commandSocket = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
                        perror("socket");
                        exit(1);
                    }
                    
                    my_addr.sin_family = AF_INET;
                    my_addr.sin_port = htons(COMMAND_PORT);
                    my_addr.sin_addr.s_addr = INADDR_ANY;
                    bzero(&(my_addr.sin_zero), 8);
                    
                    if (bind(commandSocket, (struct sockaddr *)&my_addr, sizeof(struct sockaddr)) \
                    == -1) {
                        perror("bind");
                        exit(1);
                    }
                    
                    if (listen(commandSocket, MAX_CONNEXIONS) == -1) {
                        perror("listen");
                        exit(1);
                    }
                }
                
                void prepareDataSocket(){
                    
                    if ((he=gethostbyname(remoteHost)) == NULL) {
                        herror("gethostbyname");
                        exit(1);
                    }
                    
                    if ((dataSocket = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
                        perror("socket");
                        exit(1);
                    }
                    
                    their_addr.sin_family = AF_INET;
                    their_addr.sin_port = htons(DATA_PORT);
                    their_addr.sin_addr = *((struct in_addr *)he->h_addr);
                    bzero(&(their_addr.sin_zero), 8);
                    
                    
                    // boucle de tentative de connexion
                    printf("try to connect to client ... ");
                    while(connect(dataSocket, (struct sockaddr *)&their_addr, sizeof(struct sockaddr))) {
                        //perror("connect");
                        //exit(1);
                    }
                    // dataSocket connecté
                    printf("connected !");
                }
                
                void runListenServer(){
                    if (!fork()) {
                        while(1) {
                            printf("serveur en attente de connexions ... \n");
                            sin_size = sizeof(struct sockaddr_in);
                            
                            if ((acceptedCommandSocket = accept(commandSocket, (struct sockaddr *)&their_addr, \
                            &sin_size)) == -1) {
                                perror("accept");
                                continue;
                            }
                            //printf("serveur: connection de %s\n", inet_ntoa(their_addr.sin_addr));
                            
                            printf("serveur: connection acceptee\n");
                            
                            
                            while(1) {
                                printf("receiving commands ...\n");
                                
                                if ((numbytes=recv(acceptedCommandSocket, buf, MAX_BUFFER_SIZE, 0)) == -1) {
                                    perror("recv");
                                }
                                
                                buf[numbytes] = '\0';
                                
                                printf("Reçu : %s \n", buf);
                                sleep(2);
                                
                                // echo
                                buf2 = "test";
                                this->sendBuffer(buf2);
                                
                            }
                            
                            //printf("serveur: fin connection de %s\n", inet_ntoa(their_addr.sin_addr));
                            printf("serveur: fin de connexion \n");
                            close(acceptedCommandSocket);
                            exit(0);
                            
                            close(acceptedCommandSocket);
                            printf("serveur: fin de acceptedCommandSocket \n");
                            while(waitpid(-1, NULL, WNOHANG) > 0);
                        }
                    }
                }
    };
    
    class SystemObject {
    };
    
    class DataSystemObject : public AbstractSystemObject {
        SystemObject systemObject;
        
        public:
            SystemObject getSystemObject(){return systemObject;}
            void setSystemObject(SystemObject _s){systemObject = _s;}
            
            virtual void applyToSystem() = 0; // methode abstraite
            DataSystemObject(string _buff) : AbstractSystemObject(_buff){};
    };
    
    class CommandSystemObject : public AbstractSystemObject {
        public:
            
            virtual void applyToSystem() = 0; // methode abstraite
            CommandSystemObject(string _buff) : AbstractSystemObject(_buff){};
    };
    
    
};


#endif
