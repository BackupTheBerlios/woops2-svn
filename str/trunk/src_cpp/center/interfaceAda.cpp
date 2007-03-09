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
#include "../network/Network.h"
#include <stdlib.h>


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

void adainit_bus()
{
	cout<<"hello"<<endl;
	p_initBusStop(2);
}


//-------------------------------------- Méthode que Ada appelle ---------------------------------------
void returnInitBusStop()
{
	//OperatingCenter* oc = new OperatingCenter();
	//oc->returnInitBusStop();
	cout<<"Retour dans le C++"<<endl;
}



//main qui initialise le system
int main ()
{
	cout<<"main c"<<endl;
	adainit();
	
  	NetworkManager* nm = NetworkManager::getInstance();

        while(1){}
	adafinal();
}
