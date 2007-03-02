/* 
 * File:   OperatingCenter.h
 * Author: soosuske
 *
 */

#include "OperatingCenter.h"

extern  void initBus(void);
extern  void p_sendPosition(t_position* position);
extern  void p_initBusStop(int nombre);


OperatingCenter::OperatingCenter()
{
       cout<<"hello"<<endl;
}

OperatingCenter::~OperatingCenter()
{
}


/**fonction de thread pour l'initialisation des Bus
*@Param : 
*/
void* OperatingCenter::thread_function_initBus(void *a){

	//appel de la fonction ADA
	//TODO lors de la mise en place de l'ADA	
	//initBus();
        int nombre = 1;
        p_initBusStop(nombre);
	cout<<"Appel a la fonction d'initialisation des bus \n"<<endl;
	
}

/**fonction de thread pour l'écoute de la réception de bus.
*@Param : 
*/
void* OperatingCenter::thread_function_receive_position(void *a){

	cout<<"Appel des fonctions pour le traitement de la position"<<endl;
	t_position* maposition = (t_position *)a;
        cout<<"valeur :"<<maposition->busStopId<<endl;
	
}

/**fonction de thread pour l'écoute de la réception d'information.
*@Param : 
*/
void* OperatingCenter::thread_function_receive_information(void *a){

	cout<<"Appel des fonctions pour le traitement de l'information"<<endl;
	
	
}

void OperatingCenter::initializeSystem(){
//déclaration
	int etat;
	pthread_t bus_thread;
	
	cout<<"hello";
	//Création des différents thread.
	// Mise en place du Thread pour le bus
	etat = pthread_create(&bus_thread,NULL,thread_function_initBus, NULL);
	if (etat != 0) cout<<"Echec creation de thread pour l'initialisation des bus: %d"<<endl;
}

/**
Méthode qui permet de recevoir la position d'un bus
*/
void OperatingCenter::receivePosition(){
    
    
	int etat;
        string lol;
        
        //création de la structure test de la position
        t_position* maposition = new t_position;
        
        //p_sendPosition(&maposition);
        
	//création du thread pour traiter la position du BUS
	pthread_t receive_position_thread;

	// creation du mutex
    	//etat = pthread_mutex_init (&mutex_position, NULL);	
    	//if (etat != 0) perror("Erreur lors de l'initialisation du mutex pour la position: %d\n");

	
	//création du thread
	etat = pthread_create(&receive_position_thread,NULL,thread_function_receive_position, (void *)maposition);
	if (etat != 0) perror("Echec creation de thread pour la réception des positions: %d\n");

	//pthread_mutex_destroy(&mutex_position);
    
}


/**
Méthode qui permet de recevoir les informations sur le busStop
*/
void OperatingCenter::receiveInformation(t_information* t_ptr_t_information){
    
    
	int etat;

	//création du thread pour traiter l'information
	pthread_t receive_information_thread;

	//création du thread
	etat = pthread_create(&receive_information_thread,NULL,thread_function_receive_information, NULL);
	if (etat != 0) perror("Echec creation de thread pour la réception des positions: %d\n");
    
}

//void OperatingCenter::p_sendPriorityMessage
