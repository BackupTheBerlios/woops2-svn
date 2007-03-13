#include "Interpretor.h"
#include "../center/OperatingCenter.h"
#include "NetworkManager.h"

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
void Interpretor::receiveInformation(char* buffer){
	string buf = (string)buffer;
	int i = buf.find(":",0);
	cout << "receiveInformation : " << buf.substr(1,i) << endl;
	if(buf.substr(1,i) == "createBusStop:"){

	//traitement des différents cas

	//traitement des différents cas
	if(buf.substr(1,i) == "createBusStop:")
	{
		createBusStop(buf.substr(i+1,buf.length()-1));
		createBus("12,4");
	}
	if(buf.substr(1,i) == "createLine:"){
		//createLine(buf.substr(i+1,buf.length()-1));
	if(buf.substr(1,i) == "createLine:")
	{
		createLine(buf.substr(i+1,buf.length()-1));
	}
	if(buf.substr(1,i) == "createBus:")
	{
		createBus(buf.substr(i+1,buf.length()-1));
	}
}

void Interpretor::sendPosition(int lineId, int busId, int busStopId, int time){
	string buffer = "buffer to build";
	NetworkManager::getInstance()->sendBuffer(buffer);
}

/*
méthode qui va créer les bus stop.
*/
void Interpretor::createBusStop(string buffer){
	cout<<"Appel a la methode ada avec comme param"<<buffer<<endl;
	OperatingCenter::getInstance()->java_init_bus(atoi(buffer.c_str()));
}

void Interpretor::createLine(string buffer)
{
	cout<<"Appel a la methode ada avec comme param"<<buffer<<endl;
	OperatingCenter::getInstance()->java_init_line(atoi(buffer.c_str()));
}

void Interpretor::createBus(string buffer)
{
	cout<<"Appel a la methode qui va créer des bus  ada avec comme param"<<buffer<<endl;
	int index = buffer.find(",",0);
	int nombre = atoi(buffer.substr(0,index).c_str());
	int num_ligne = atoi(buffer.substr(index+1,buffer.length()-1).c_str());
	cout<<"valeur "<<nombre<<"Num ligne:"<<num_ligne<<"buffer"<<buffer<<endl;	
	OperatingCenter::getInstance()->java_init_bus(nombre,num_ligne);
}


Interpretor  *Interpretor::interpretor = NULL;



/* Private methods */

