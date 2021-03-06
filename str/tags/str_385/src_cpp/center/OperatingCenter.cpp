/* 
 * File:   OperatingCenter.h
 * Author: soosuske
 *
 */

#include "OperatingCenter.h"
#include "util.h"
#include "fstream"
#include <stdio.h>
#include <stdlib.h>
#include "../network/Interpretor.h"
#include "interfaceAda.h"

int DISTANCE_BETWEEN_2_STOP = 100;
int TIME_BETWEEN_2_STOP = 12;
t_memoire* memoire[50];
int taillememoire = 0;
static pthread_mutex_t mutex;
static pthread_mutex_t mutex_fichier;

//variable qui indique si on a deja créée un bus.
int init_bus = 0;

void initSystem()
{
    cout<<"hello"<<endl;
}

/**
constructeur de operatingCenter
*/
OperatingCenter::OperatingCenter()
{
 	//initialisation du mutex pour lexclusion lors de la mise a jour de lespace mémoire commun
	pthread_mutex_init (&mutex, NULL);
	//initialisation du mutex pour l"ecriture dans le fichier
	pthread_mutex_init (&mutex_fichier, NULL);
}

/**
Destructeur
*/
OperatingCenter::~OperatingCenter()
{
}

/*----------------------------------------------------------------------------------
Mise en place des threads

----------------------------------------------------------------------------------*/

/**
Thread qui permet de mettre a jour l'espace memoire commun pour laffichage des temps
*/
void* OperatingCenter::thread_function_miseajour(void* a){
	//on prend un jeton	
	t_memoire *structmem = (t_memoire *)a;
	pthread_mutex_lock (&mutex);
	
	//traitement pour la mise a jour de l'espace mémoire.
	cout<<"on rentre dans le mutex"<<endl;	
	bool trouver = false;
	for(int i=0;i<taillememoire;i++)
	{
		if(memoire[i]->busStop == structmem->busStop )
		{
			memoire[i] = structmem;
			trouver = true;
		}
	}
	if(!trouver)
	{
		memoire[taillememoire] = structmem;
		taillememoire ++;
	}

	//on rend le jeton
	free(structmem);
	pthread_mutex_unlock (&mutex);
}
/**
thread qui permet l'archivage des positions d'un bus dans un fichier
*/
void* OperatingCenter::thread_function_archivage(void* a){
	//on prend un jeton	
	t_archivage *structarch = (t_archivage *)a;
	pthread_mutex_lock (&mutex_fichier);
	char* buffer;	
	//itoa (structarch->ligne,buffer,10);
	string chaine = " Bus :"+ stringify((double)structarch->ligne);
	chaine += ", BusStop :"+stringify(structarch->busStop) ;
	chaine += ", Ligne :"+stringify(structarch->ligne);
	chaine += " , distance :"+stringify((double)structarch->distance);
	chaine += "\n";
	ofstream fichier;
	fichier.open("archive.txt", ios::app);
	if(fichier.is_open())
	{
		cout<<"la chaine :"<<chaine<<endl;
		fichier<<chaine;
	}
	pthread_mutex_unlock (&mutex_fichier);
}

/**
Thread qui permet toute les n secondes de recuperer les valeur de la memoire partagée
*/
void* OperatingCenter::thread_function_getvaleur(void* a){
	while(1)
	{
		sleep(10);
		for(int i=0;i<taillememoire;i++)
		{
			//Interpretor::getInstance()->sendInformation(memoire[i]->ligne,memoire[i]->bus,memoire[i]->busStop,memoire[i]->time);
		}

	}
}

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
* speed en Km/heure
*/
void* OperatingCenter::thread_function_receive_position(void *structPosition){

	cout<<"OperatingCenter::Appel des fonctions pour le traitement de la position"<<endl;
	t_structReceivePosition *maStructPosition = (t_structReceivePosition *)structPosition;
	t_position* maposition = (t_position *)maStructPosition->position;

	cout<<"Position recue par le centre: ";
	cout<<"(lineNumber => " << maposition->lineNumber;
	cout<<", busStopId => " << maposition->busStopId;
	cout<<", distance => " << maposition->distance << ")" << endl;
	cout<<"vitesse du bus " << maStructPosition->busId << ": " << maStructPosition->speed << endl;
    	
	int remainingDistance = 1000 - (int)maposition->distance;
	int speedInMeterPerSeconde = maStructPosition->speed*1000/3600;
	int timeInSeconde = 0;
	if(speedInMeterPerSeconde != 0)
		timeInSeconde = remainingDistance / speedInMeterPerSeconde ;
	cout<<"Time (sec) calculee :"	<<  	timeInSeconde			<<endl;
	
	//calcule du pourcenetage de l'evolution du bus entre 2 bus stop.
	int percent = ((int)maposition->distance*100)/DISTANCE_BETWEEN_2_STOP;
	if(TIME_BETWEEN_2_STOP - timeInSeconde > 1){
		//le bus est en retard, il faut lui demander d'accelerer.
		adaaccelerate_bus(maposition->busStopId);
	}
	else if(TIME_BETWEEN_2_STOP - timeInSeconde < -1){
		//le bus est en avance, il faut lui demander de decelerer.
		adadecelerate_bus(maposition->busStopId);
	}
		
	//mise en place de lecriture dans le fichier pour larchivage
	
	//création de la structure qui va se trouver dans la mémoire partagé
	t_memoire *structmem = (t_memoire *)malloc(sizeof(t_memoire));
	structmem->ligne = 1;
	structmem->busStop = maposition->busStopId ;
	structmem->bus = maStructPosition->busId;
	structmem->time = timeInSeconde;
	int etat;
	pthread_t thread;
	//etat = pthread_create(&thread,NULL,thread_function_miseajour, (void*)structmem);
	//if (etat != 0) cout<<"Echec creation de thread pour l'initialisation des bus: %d"<<endl;

	//mise en place des threads pour l'archivage
	t_archivage *structarch = (t_archivage *)malloc(sizeof(t_archivage));
	structarch->ligne = 1;
	cout<<"STRUCTURE"<<structarch->ligne<<endl<<endl;
	structarch->busStop = maposition->busStopId ;
	structarch->bus = maStructPosition->busId;
	structarch->distance = maposition->distance;
	pthread_t thread_fichier;
	//etat = pthread_create(&thread_fichier,NULL,thread_function_archivage, (void*)structarch);
	Interpretor::getInstance()->sendPosition(maposition->lineNumber, maStructPosition->busId, maposition->busStopId, percent );
	cout<<"-----------------------------------------------------------------------"<<endl;
	cout<<"FIN DUN THREAD"<<endl;
	cout<<"On libere"<<endl;
	free(maStructPosition);

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
void OperatingCenter::receivePosition(t_structReceivePosition* position){
    
    int etat;
    
    //création du thread pour traiter la position du BUS
	pthread_t receive_position_thread;

	//création du thread
	etat = pthread_create(&receive_position_thread,NULL,thread_function_receive_position, (void *)position);
	if (etat != 0) 
		perror("Echec creation de thread pour la réception des positions\n");
    
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
/**
méthode pour initialiser les busStop
*/
void OperatingCenter::java_init_busStop(int nombre, int ligne)
{
	adainit_busStop(nombre, ligne);
}

/**
méthode pour initialiser les bus
*/
void OperatingCenter::java_init_bus(int nombre, int ligne)
{
	adainit_bus(nombre,ligne);
	
	if (init_bus == 0)
	{
		int etat;
		pthread_t sendinfo_thread;

		//création du thread
		etat = pthread_create(&sendinfo_thread,NULL,thread_function_getvaleur, NULL);
		if (etat != 0) perror("Echec creation de thread pour la réception des positions: %d\n");
		init_bus = 1;
	}	
}

/**
methode qui start un bus
*/

void start_busStop(int num_bus)
{
	//ada_startBus(num_bus);
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
