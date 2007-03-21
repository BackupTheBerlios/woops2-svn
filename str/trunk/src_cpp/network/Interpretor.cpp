#include "Interpretor.h"
#include "../center/OperatingCenter.h"
#include "NetworkManager.h"
#include "../center/util.h"

Interpretor::Interpretor()
{
	//création du thread qui va lire la queue
	pthread_t thread_traitement;
	int etat = pthread_create(&thread_traitement,NULL,threadTraitementInformation,NULL);
	if (etat != 0) cout<<"Echec creation de thread pour le traitement de la queue"<<endl;
}

/*
Getteur et setteur pour la queue
*/
queue<string>* Interpretor::getMessageFromNetwork()
{
	return messageFromNetwork;
}

void Interpretor::setMessageFromNetwork(queue<string>*  _messageFromNetwork)
{
	messageFromNetwork  = _messageFromNetwork;
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
    
        return interpretor;
} 

/*
méthode qui parse les informations reçues
*/
void* Interpretor::threadTraitementInformation(void* a)
{
	while(true)
	{
		while(messageFromNetwork->size()>0)
		{
			string buf = messageFromNetwork->front();
			cout<<"----------------------------------------------------------------"<<endl;
			cout<<endl<<" Buffer lu dans la queue: "<< buf <<endl;
			cout<<"----------------------------------------------------------------"<<endl;
			int index = buf.find(";",0);
			int depart = 0;
			if(index != -1)
			{
				while(index != -1 && buf.length()>0)
				{
					
					int indexaroba = buf.find("@",0);
					
					string commande = buf.substr(depart,index);
					int i = commande.find(":",0);
		
					//traitement des différents cas
					if(commande.substr(1,i) == "createBusStop:")
					{
						createBusStop(commande.substr(i+1,commande.length()-1));
					}
					if(commande.substr(1,i) == "createBus:")
					{
						createBus(commande.substr(i+1,commande.length()-1));
					}
					if(commande.substr(1,i) == "startBus:")
					{
						startBus(commande.substr(i+1,commande.length()-1));
					}
					cout<<"ICICIIII :"<<buf<<endl;
					buf = buf.substr(index+1, buf.length());
					cout<<"buf"<<buf<<endl;
					depart = 1;
					index = buf.find(";",index);
					if(buf.length()>2 && index == -1)
					{
						cout<<"le forme du paquet recu est incorrect"<<endl;
					}
				}
			}
			else
			{
				cout<<"le forme du paquet recu est incorrect"<<endl;
			}
			messageFromNetwork->pop();
		}
		usleep(500);
	}
}

/*
méthode qui va créer les bus stop.
*/
void Interpretor::createBusStop(string buffer)
{
	cout<<"Appel a la methode create bus stop avec comme param"<<buffer<<endl;
	int index = buffer.find(",",0);
	int nombre = atoi(buffer.substr(0,index).c_str());
	int num_ligne = atoi(buffer.substr(index+1,buffer.length()-1).c_str());
	cout<<"valeur "<<nombre<<"Num ligne:"<<num_ligne<<"buffer"<<buffer<<endl;
	OperatingCenter::getInstance()->java_init_busStop(nombre,num_ligne);
}
/**
méthode qui va starter les bus
*/

void Interpretor::startBus(string buffer)
{
	cout<<"Appel a la methode start bus avec comme param"<<buffer<<endl;
	int index = buffer.find(";",0);
	int num_bus = atoi(buffer.substr(0,index).c_str());
	cout<<"valeur "<<num_bus<<endl;
	OperatingCenter::getInstance()->java_start_bus(num_bus);
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

/**
methode qui permet d'envoyer la position d'un bus a java.
*/
void Interpretor::sendPosition(int lineId, int busId, int busStopId, int position)
{
	string chaine = "@position:"+ stringify((double)lineId);
	chaine += ","+stringify((double)busId) ;
	chaine += ","+stringify((double)busStopId);
	chaine += ","+stringify((double)position);
	chaine += ";\n";
	char * chainechar = (char*)chaine.c_str();
	NetworkManager::getInstance()->sendBuffer(chainechar);
}

/**
methode qui permet d'envoyer les informations sur un bus a java
*/
void Interpretor::sendInformation(int lineId, int busId, int busStopId, int time)
{
	string chaine = "@info:"+ stringify((double)lineId);
	chaine += ","+stringify((double)busId) ;
	chaine += ","+stringify((double)busStopId);
	chaine += ","+stringify((double)time);
	chaine += ";\n";
	char * chainechar = (char*)chaine.c_str();
	cout<<"Chaine char"<<chainechar<<endl;
	NetworkManager::getInstance()->sendBuffer(chainechar);
}

Interpretor  *Interpretor::interpretor = NULL;
queue<string>* Interpretor::messageFromNetwork = new queue<string>();



/* Private methods */

