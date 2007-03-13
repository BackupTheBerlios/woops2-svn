// 
// File:   main.cc
// Author: soosuske
//
// Created on 28 février 2007, 01:34
//

/**
Fichier qui sert d'interface entre ada et C qui permet a l'ada d'appeler des fonctions C++
*/

#include "OperatingCenter.h"
#include "../network/NetworkManager.h"
#include <stdlib.h>
#include <unistd.h>


//déclaration des fonctions externes
extern "C"{ 
	void adainit();	
	void adafinal();
	void p_initBusStop(int nombre);
	void receivePosition(t_position *pos);
	void returnInitBusStop();
}

/**
methode qui permet de recevoir la position d'un bus
param : la position pos qui est un pointeur sur la struture position
*/
void receivePosition(t_position *pos)
{
	OperatingCenter* oc = OperatingCenter::getInstance();
	oc->receivePosition(pos);
}

//------------------------------------- Methode qui se trouve dans Ada et appelé par le C++ ---------------------

void adainit_bus(int nombre)
{
	p_initBusStop(nombre);
}


//-------------------------------------- Méthode que Ada appelle ---------------------------------------
void returnInitBusStop()
{
	//OperatingCenter* oc = new OperatingCenter();
	//oc->returnInitBusStop();
	cout<<"Retour dans le C++"<<endl;

	cout<<"Pid"<<getpid()<<endl;
	NetworkManager::getInstance();
}



//main qui initialise le system
int main ()
{
	cout<<"main c"<<endl;
	adainit();
	cout<<"Pid"<<getpid()<<endl;
	
  	NetworkManager* nm = NetworkManager::getInstance();
	NetworkManager* nm1 = NetworkManager::getInstance();
	nm->initNetwork();
        while(1){}
	adafinal();
}
