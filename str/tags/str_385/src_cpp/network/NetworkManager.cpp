#include "NetworkManager.h"
#include "../center/OperatingCenter.h"
#include "Interpretor.h"

NetworkManager * NetworkManager::instance = NULL;
int commandSocket, acceptedCommandSocket;

NetworkManager::NetworkManager(){
                	
            	}

void NetworkManager::initNet(){
			remoteHost = "localhost";
	                printf("prepareCommandSocket \n");
	                prepareCommandSocket();
	                printf("runListenServer \n");
	                runListenServer();
}

 void NetworkManager::prepareCommandSocket(){
                    
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

void NetworkManager::runListenServer(){
					if ((acceptedCommandSocket = accept(commandSocket, (struct sockaddr *)&their_addr, &sin_size)) == -1) {
                                perror("accept");
                    }

                    if (!fork()) {
                        while(1) {
                            printf("serveur en attente de connexions ... \n");
                            sin_size = sizeof(struct sockaddr_in);

                            printf("serveur: connection acceptee\n");
							cout << "acceptedCommandSocket : " << acceptedCommandSocket << endl;

							while(1) {
                                printf("receiving commands ...\n");

                                if ((numbytes=recv(acceptedCommandSocket, buf, MAX_BUFFER_SIZE, 0)) == -1) {
                                    perror("recv");
                                }
                                
                                buf[numbytes] = '\0';
                                
                                printf("Reçu : %s \n", buf);
				Interpretor::getInstance()->receiveInformation(buf);
                                usleep(300);
								// echo
                                //this->sendBuffer(buf);
                            }

                            printf("serveur: fin de connexion \n");
                            close(acceptedCommandSocket);
                            printf("serveur: fin de acceptedCommandSocket \n");
                            while(waitpid(-1, NULL, WNOHANG) > 0);
							exit(0);
                        }
                    }
                }


NetworkManager* NetworkManager::getInstance() {
				if (instance == NULL){
						cout << "creation nm" << endl;
					 instance = new NetworkManager();
				}
				else cout << "nm recup instance " << endl;
				
				return instance;
}

void NetworkManager::sendBuffer(char* _buffer){
                printf("sending : %s \n", _buffer);
				
                if ((send(acceptedCommandSocket, _buffer, strlen(_buffer), 0)) <= 0 ) {
                    perror("send");
                }
				printf("sent\n");
            }
