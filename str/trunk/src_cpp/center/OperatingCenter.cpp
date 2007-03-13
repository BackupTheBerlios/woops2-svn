/* 
 * File:   OperatingCenter.h
 * Author: soosuske
 *
 */

#include "OperatingCenter.h"


void initSystem()
{
    cout<<"hello"<<endl;
}

OperatingCenter::OperatingCenter()
{
 
}

OperatingCenter::~OperatingCenter()
{
}

/*----------------------------------------------------------------------------------
Mise en place des threads

----------------------------------------------------------------------------------*/

/**fonction de thread pour l'initialisation des Bus
*@Param : 
*/
 void* OperatingCenter::thread_function_initBus(void *a){

	//appel de la fonction ADA
	//TODO lors de la mise en place de l'ADA	
	//initBus();
	cout<<"Appel a la fonction d'initialisation des bus \n"<<endl;
	
}

/**fonction de thread pour l'écoute de la réception de bus.
*@Param : 
*/
void* OperatingCenter::thread_function_receive_position(void *a){

	cout<<"Appel des fonctions pour le traitement de la position"<<endl;
	t_position* maposition = (t_position *)a;
    cout<<"Bus Stop Id :"	<<maposition->busStopId	<<endl;
    cout<<"Distance :"		<<maposition->distance	<<endl;

//while(true){}
	
}

/**fonction de thread pour l'écoute de la réception d'information.
*@Param : 
*/
void* OperatingCenter::thread_function_receive_information(void *a){

	cout<<"Appel des fonctions pour le traitement de l'information"<<endl;
	
	
}

void* OperatingCenter::thread_function_returnInitBusStop(void *a){

		//appel au networkManager
	
}

/*----------------------------------------------------------------------------------
Fin mise en place des threads

----------------------------------------------------------------------------------*/
OperatingCenter *OperatingCenter::operatingCenter = NULL;

OperatingCenter * OperatingCenter::getInstance ()
{
    if (NULL == operatingCenter)
    {
        cout << "creating singleton OperatingCenter." <<endl;
        operatingCenter =  new OperatingCenter();
    }


    return operatingCenter;
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
void OperatingCenter::receivePosition(t_position* position){
    
    int etat;
    
    //création du thread pour traiter la position du BUS
	pthread_t receive_position_thread;

	//création du thread
	etat = pthread_create(&receive_position_thread,NULL,thread_function_receive_position, (void *)position);
	if (etat != 0) 
		perror("Echec creation de thread pour la réception des positions: %d\n");
    
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

/*------------------------------------------ Java -----------------------------------------------*/
void OperatingCenter::java_init_busStop(int nombre)
{
	adainit_busStop(nombre);
}

void OperatingCenter::java_init_line(int line)
{
}

void OperatingCenter::java_init_bus(int nombre, int ligne)
{
	adainit_bus(nombre,ligne);
}

/*------------------------------ methodes que ADA appelle ---------------------- */
void OperatingCenter::returnInitBusStop()
{
	int etat;
	pthread_t busStop_thread;
	
	cout<<"Fonction du retour de la fonction returnInitBusStop"<<endl;
	etat = pthread_create(&busStop_thread,NULL,thread_function_returnInitBusStop,NULL );
	if (etat != 0) cout<<"Echec creation de thread pour le retour de initBusStop: %d"<<endl;
}


//void OperatingCenter::p_sendPriorityMessage
