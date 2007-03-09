#ifndef _Network_H
#define _Network_H

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
    
    class NetworkManager;
    
    class AbstractSystemObject;
    
class Interpretor {
      
        AbstractSystemObject * abstractSystemObject;
        
        public:
          
            AbstractSystemObject* getAbstractSystemObject();
            void setAbstractSystemObject(AbstractSystemObject* aso );
            
            void buildAbstractSystemObject(string buffer); // met a jours l'attribut
            string interpreteCurrentAbstractSystemObject(); // prepare buffer AbstractSystemObject courant
    };
    
    
    class AbstractSystemObject {
        Interpretor* interpretor;
        string buffer;
        
        public:
            Interpretor* getInterpretor();
            void setInterpretor(Interpretor* _i);
            string getBuffer();
            void setBuffer(string _buff);
            
            AbstractSystemObject(string _buff);
            virtual void applyToSystem() = 0; // methode abstraite
    };
    
    class NetworkManager {
       
	static NetworkManager* networkManager;

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
            
            Interpretor getInterpretor();
            void setInterpretor(Interpretor _i);
            
            
            char* getRemoteHost();
            void setRemoteHost(char* _rh);
           
            
            void sendBuffer(char* _buffer);
            
            void closeSockets();

	   static NetworkManager* getInstance();
           
       private:
	 	 NetworkManager();
                void prepareCommandSocket();
                void prepareDataSocket();
                void runListenServer();
    };
	

    class SystemObject {
    };
    
    class DataSystemObject : public AbstractSystemObject {
        SystemObject * systemObject;
        
        public:
            SystemObject * getSystemObject();
            void setSystemObject(SystemObject * _s);
            
            void applyToSystem();
             DataSystemObject(string _buff) : AbstractSystemObject(_buff){};
    };
    
    class CommandSystemObject : public AbstractSystemObject {
        public:
            
            void applyToSystem();
            CommandSystemObject(string _buff) : AbstractSystemObject(_buff){
			
			
	    };
    };


#endif
