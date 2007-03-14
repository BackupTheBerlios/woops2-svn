/* 
 * File:   OperatingCenter.h
 * Author: soosuske
 *
 */

#include "OperatingCenter.h"
#include "../network/Interpretor.h"

t_memoire* memoire[50];
int taillememoire = 0;
static pthread_mutex_t mutex;

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
}

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
	pthread_mutex_unlock (&mutex);
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
			cout<<"Bus Stop pour JAAAVVVAAAAA"<<memoire[i]->busStop<<endl;		
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
    	cout<<"Bus Stop Id :"			<< maposition->busStopId		<<endl;
    	cout<<"Distance :"				<< maposition->distance			<<endl;
	cout<<"Speed :"					<< maStructPosition->speed		<<endl;
	cout<<"BusId :"					<< maStructPosition->busId		<<endl;
	int speedInMeterPerSeconde = (int)maposition->distance*1000/3600;
	int timeInSeconde = speedInMeterPerSeconde / maStructPosition->speed ;
	cout<<"Time (sec) calculee :"	<<  	timeInSeconde			<<endl;

	//création de la structure qui va se trouver dans la mémoire partagé
	t_memoire *structmem = (t_memoire *)malloc(sizeof(t_memoire));
	structmem->ligne = 1;
	structmem->busStop = maposition->busStopId ;
	structmem->bus = maStructPosition->busId;
	structmem->time = timeInSeconde;
	int etat;
	pthread_t thread;
	etat = pthread_create(&thread,NULL,thread_function_miseajour, (void*)structmem);
	if (etat != 0) cout<<"Echec creation de thread pour l'initialisation des bus: %d"<<endl;
	//Interpretor::getInstance()->sendPosition(maposition->lineNumber, maStructPosition->busId, maposition->busStopId, timeInSeconde);
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
	int etat;

	//création du thread pour traiter l'information
	pthread_t sendinfo_thread;

	//création du thread
	etat = pthread_create(&sendinfo_thread,NULL,thread_function_getvaleur, NULL);
	if (etat != 0) perror("Echec creation de thread pour la réception des positions: %d\n");
	
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
