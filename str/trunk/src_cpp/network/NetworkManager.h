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



class NetworkManager {
        
        // --- static ---
        static const int COMMAND_PORT = 3390;
        static const int MAX_BUFFER_SIZE = 512;
        static const int MAX_CONNEXIONS = 10;

	static NetworkManager * instance;
		
        char* remoteHost;
        
        //int commandSocket, acceptedCommandSocket;
        int numbytes,dataBytes;
		int i;

		char buf[MAX_BUFFER_SIZE];
        char* buf2;

        struct sockaddr_in my_addr;
        struct sockaddr_in their_addr;
        struct hostent *he;
        socklen_t sin_size;

		private:
				
				NetworkManager();
				~NetworkManager(){}                
				
                void prepareCommandSocket();
                void runListenServer();
        
        public:
	void initNet();
   	   static NetworkManager* getInstance() ;
            void sendBuffer(char* _buffer);

};




#endif
