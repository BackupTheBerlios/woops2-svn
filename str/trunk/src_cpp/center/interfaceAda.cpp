// 
// File:   main.cc
// Author: soosuske
// Created on 28 février 2007, 01:34
//

/**
Fichier qui sert d'interface entre ada et C qui permet a l'ada d'appeler des fonctions C++
Ce fichier contient aussi le main du projet
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
	t_structReceivePosition *structPosition = new t_structReceivePosition();

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

/**
methode qui permet dinitialiser des busStop
*/
void adainit_busStop(int nombre, int ligne)
{
	p_initBusStop(nombre,ligne);
}

/**
methode qui permet d'initialiser des bus
*/
void adainit_bus(int nombre, int ligne)
{
	p_initBus(nombre,ligne);
}

/**
methode qui permet de startBus
*/
void ada_startBus(int busId)
{
	p_startBus(busId);
}

/**
methode qui permet d'arreter un bus stop
*/
void ada_stopBus(int busId)
{
	p_stopBus(busId);
}

/**
methode qui permet d'accelerer un bus
*/
void ada_accelerateBus(int busId)
{
	p_accelerateBus(busId);
}

/**
methode qui permet deccelerer un bus
*/
void ada_decelerateBus(int busId)
{
	p_decelerateBus(busId);
}
/**
methode qui envoie a ADA des priorityMessage
param = le busId et le tcode code
*/
void ada_managePriorityMessage(int busId, t_code code)
{
	//creation de la structure qui va etre envoyé au centre
	t_priorityMessage* pm =new t_priorityMessage();
	pm->busId = busId;
	pm->code = new t_code(code);
	p_simulateProblem(pm);
}


//-------------------------------------- Méthode que Ada appelle ---------------------------------------
//main qui initialise le system
int main ()
{
	adainit();
	//démarrage des sockets
	NetworkManager::getInstance()->initNet();
    	while(1){	/*sleep(4);  p_accelerateBus(88);*/ }
	adafinal();
}
