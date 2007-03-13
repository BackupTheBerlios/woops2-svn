#include "Interpretor.h"
#include "../center/OperatingCenter.h"

Interpretor::Interpretor()
{

}

/*
méthode pour le singleton qui retourne l'instance
*/
Interpretor* Interpretor::getInstance()
{
	if (NULL == interpretor)
      	{
        	std::cout << "creating singleton for interpretor." << std::endl;
        	interpretor =  new Interpretor();
	}
	else
	{
		cout<<"singleton for Interpretor already creating"<<endl;
	}
    
        return interpretor;
} 

/*
méthode qui parse les informations reçues
*/
void Interpretor::receiveInformation(char* buffer)
{

	string buf = (string)buffer;
	int i = buf.find(":",0);
	cout << "receiveInformation : " << buf.substr(1,i) << endl;
	if(buf.substr(1,i) == "createBusStop:")
	{
		createBusStop(buf.substr(i+1,buf.length()-1));
	}
	if(buf.substr(1,i) == "createLine:")
	{
		//createLine(buf.substr(i+1,buf.length()-1));
	}

}

/*
méthode qui va créer les bus stop.
*/
void Interpretor::createBusStop(string buffer)
{
	cout<<"Appel a la methode ada avec comme param"<<buffer<<endl;
	OperatingCenter::getInstance()->java_init_bus(atoi(buffer.c_str()));
}


Interpretor  *Interpretor::interpretor = NULL;



/* Private methods */

