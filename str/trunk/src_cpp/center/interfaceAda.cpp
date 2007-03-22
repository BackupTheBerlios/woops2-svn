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
#include "ControllerMalloc.h"
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
	void p_decelerateBus(int busId);
	void p_simulateProblem(t_priorityMessage* ptr_mes);
	void receivePosition(t_position *pos, int speed, int busId);
	void receiveMessage(char* message);
	void receivePriorityMessage(t_priorityMessage* ptr_mes);
}

/**
methode qui permet de recevoir la position d'un bus
param : la position pos qui est un pointeur sur la struture position
*/
void receivePosition(t_position *pos, int speed, int busId)
{
	ControllerMalloc::getInstance()->prendre_jeton();
	t_structReceivePosition *structPosition = new t_structReceivePosition();
	ControllerMalloc::getInstance()->rendre_jeton();
	structPosition->position = pos;
	structPosition->busId = busId;
	structPosition->speed = speed;
	OperatingCenter::getInstance()->receivePosition(structPosition);
}

void receiveMessage(char* message){
}

void receivePriorityMessage(t_priorityMessage* ptr_mes)
{
	OperatingCenter::getInstance()->receivePriorityMessage(ptr_mes);
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

void ada_stopBus(int busId)
{
	p_stopBus(busId);
}

void ada_accelerateBus(int busId)
{
	p_accelerateBus(busId);
}

void ada_decelerateBus(int busId)
{
	p_decelerateBus(busId);
}
void ada_managePriorityMessage(int busId, t_code code)
{
	t_priorityMessage* pm =new t_priorityMessage();
	pm->busId = busId;
	pm->code = new t_code(code);
	p_simulateProblem(pm);
}


//-------------------------------------- Méthode que Ada appelle ---------------------------------------
//None.


//main qui initialise le system
int main ()
{
	adainit();

	NetworkManager::getInstance()->initNet();
	
    while(1){	/*sleep(4);  p_accelerateBus(88);*/ }
	adafinal();
}
