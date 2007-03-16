#ifndef _Network_Manager_H
#define _Network_Manager_H

#include <iostream>
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

class NetworkManager {
       
private :
	static NetworkManager* networkManager;

        // --- static ---
        static const int DATA_PORT = 3391;
        static const int COMMAND_PORT = 3390;
        
        static const int MAX_BUFFER_SIZE = 128;
        static const int MAX_CONNEXIONS = 10;
        
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
            
           void sendBuffer(char* _buffer);
           void closeSockets();
	   static NetworkManager* getInstance();

	void initNetwork(); 
       
private:
	 	NetworkManager();
                void prepareCommandSocket();
		
                void prepareDataSocket();
                void runListenServer();
    };


#endif
