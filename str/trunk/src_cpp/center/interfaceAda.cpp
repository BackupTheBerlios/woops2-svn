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
	void p_startBus(int busId, int lineId);
	void p_stopBus(int busId, int lineId);
	void p_accelerateBus(int busId, int lineId);
	void p_adadecelerateBus(int busId, int lineId);
	void p_receivePosition(t_position *pos, int speed, int busId);
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
	cout<<"hello"<<endl;
	OperatingCenter::getInstance()->receivePosition(structPosition);
}

//------------------------------------- Methode qui se trouve dans Ada et appelé par le C++ ---------------------

void adainit_busStop(int nombre, int ligne)
{
	p_initBusStop(nombre,ligne);
}

void adainit_bus(int nombre, int ligne)
{
	p_initBus(5,1);
	//NetworkManager::getInstance()->sendBuffer("@pos:1,45,2,500;");
}

void adastart_bus(int busId, int lineId);
{
	//p_startBus(busId,ligneId);
}

void adastop_bus(int busId, int lineId);
{
	//p_stopBus(busId,ligneId);
}

void adaaccelerate_bus(int busId, int lineId);
{
	//p_accelerateBus(busId,ligneId);
}

void adadecelerate_bus(int busId, int lineId);
{
	//p_decelerateBus(busId,ligneId);
}


//-------------------------------------- Méthode que Ada appelle ---------------------------------------
//None.


//main qui initialise le system
int main ()
{
	cout<<"main c"<<endl;
	adainit();
	
	NetworkManager::getInstance()->initNetwork();
	
    while(1){}
	adafinal();
}
