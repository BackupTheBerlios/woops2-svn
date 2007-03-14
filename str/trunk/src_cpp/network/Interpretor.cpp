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
	int index = buf.find(";",0);
	if(index != -1)
	{
		while(index != -1)
		{
			int indexaroba = buf.find("@",0);
			cout<<"index de l'@" << indexaroba <<endl;
			
			string commande = buf.substr(0,index);
			cout<<"commande :"<<commande<<endl;
			int i = commande.find(":",0);
			cout << "receiveInformation : " << commande.substr(1,i) << endl;
	
			//traitement des différents cas
			if(commande.substr(1,i) == "createBusStop:")
			{
				createBusStop(commande.substr(i+1,commande.length()-1));
			}
			if(commande.substr(1,i) == "createLine:")
			{
				createLine(commande.substr(i+1,commande.length()-1));
			}
			if(commande.substr(1,i) == "createBus:")
			{
				createBus(commande.substr(i+1,commande.length()-1));
			}
			buf = buf.substr(index+2, buf.length());
			cout<<"buf"<<buf<<endl;
			index = 0;
			index = buf.find(";",index);
			cout<<"index:"<<index<<endl;
		}
	}
	else
	{
		cout<<"le forme du paquet recu est incorrect"<<endl;
	}
}

/*
méthode qui va créer les bus stop.
*/
void Interpretor::createBusStop(string buffer)
{
	cout<<"Appel a la methode create bus stop avec comme param"<<buffer<<endl;
	//OperatingCenter::getInstance()->java_init_busStop(atoi(buffer.c_str()));
}

void Interpretor::createLine(string buffer)
{
	cout<<"Appel a la methode create line avec comme param"<<buffer<<endl;
	//OperatingCenter::getInstance()->java_init_line(atoi(buffer.c_str()));
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

