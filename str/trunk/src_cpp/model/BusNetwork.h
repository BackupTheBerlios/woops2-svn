#ifndef _BusNetwork_H
#define	_BusNetwork_H

#include <string>
#include <stdio.h>
#include <stdlib.h>

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
