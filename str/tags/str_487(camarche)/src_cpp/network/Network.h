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
