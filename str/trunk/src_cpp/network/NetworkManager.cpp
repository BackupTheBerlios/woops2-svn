#include "NetworkManager.h"
#include "Interpretor.h"



void NetworkManager::sendBuffer(char* _buffer){
                printf("dataSocket sending : %s \n", buf);
                
                if ((dataBytes=send(dataSocket, buf, strlen(buf), 0)) == -1) {
                    perror("send");
                    exit(1);
                }
}

 void NetworkManager::closeSockets(){
	close(dataSocket);
	close(commandSocket);
}

NetworkManager* NetworkManager::getInstance()
{
	if (NULL == networkManager)
      	{
        	std::cout << "creating singleton." << std::endl;
        	networkManager =  new NetworkManager();
	}
else
{
	cout<<"deja créer"<<endl;
}
    
        return networkManager;
} 



NetworkManager::NetworkManager(){
	prepareCommandSocket();
	
}

void NetworkManager::initNetwork(){
	prepareDataSocket();
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
                
void NetworkManager::prepareDataSocket(){
                    
                    if ((he=gethostbyname("localhost")) == NULL) {
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
                
void NetworkManager::runListenServer(){
               //   if (!fork()) {
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
				Interpretor::getInstance()->receiveInformation(buf);
				//OperatingCenter* op = OperatingCenter::getInstance();
                                //op->java_init_bus(buf);
				printf("init bus");
                                 // echo
                                buf2 = "test";

				
                                
                            }
                            
                            //printf("serveur: fin connection de %s\n", inet_ntoa(their_addr.sin_addr));
                            printf("serveur: fin de connexion \n");
                            close(acceptedCommandSocket);
                            exit(0);
                            
                            close(acceptedCommandSocket);
                            printf("serveur: fin de acceptedCommandSocket \n");
                            while(waitpid(-1, NULL, WNOHANG) > 0);
                        }
                  //  }
}

NetworkManager  *NetworkManager::networkManager = NULL;
