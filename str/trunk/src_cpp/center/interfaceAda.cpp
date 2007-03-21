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
#include <fstream>
#include <unistd.h>


//déclaration des fonctions externes
extern "C"{ 
	void adainit();	
	void adafinal();
	void p_initBusStop(int nombre,int ligne);
	void p_initBus(int nombre, int ligne);
	void p_startBus(int busId);
	void p_stopBus(int busId);
	void p_accelerateBus(int busId);
	void p_adadecelerateBus(int busId);
	void receivePosition(t_position *pos, int speed, int busId);
	void receiveMessage(char* message);
}

/**
methode qui permet de recevoir la position d'un bus
param : la position pos qui est un pointeur sur la struture position
*/
void receivePosition(t_position *pos, int speed, int busId)
{
	t_structReceivePosition *structPosition = (t_structReceivePosition *)malloc(sizeof(t_structReceivePosition));
	structPosition->position = pos;
	structPosition->busId = busId;
	structPosition->speed = speed;
	OperatingCenter::getInstance()->receivePosition(structPosition);
}

void receiveMessage(char* message)
{
	cout<<message<<endl;
}
//------------------------------------- Methode qui se trouve dans Ada et appelé par le C++ ---------------------

void adainit_busStop(int nombre, int ligne)
{
	p_initBusStop(nombre,ligne);
}

void adainit_bus(int nombre, int ligne)
{
	p_initBus(nombre,ligne);
}

void ada_startBus(int busId)
{
	p_startBus(busId);
}

void adastop_bus(int busId)
{
	//p_stopBus(busId);
}

void adaaccelerate_bus(int busId)
{
	//p_accelerateBus(busId);
}

void adadecelerate_bus(int busId)
{
	//p_decelerateBus(busId);
}


//-------------------------------------- Méthode que Ada appelle ---------------------------------------
//None.


//main qui initialise le system
int main ()
{
	cout<<"main c"<<endl;
	adainit();
	//p_initBusStop(1,12);
	//p_initBusStop(2,12);
	//p_initBus(88,12);
	//p_startBus(88);

	
	NetworkManager::getInstance()->initNet();
	
    while(1){	/*sleep(4);  p_accelerateBus(88);*/ }
	adafinal();
}
