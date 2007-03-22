#include "ControllerMalloc.h"

ControllerMalloc::ControllerMalloc()
{
	pthread_mutex_init (&mutexMalloc, NULL);
}

/*
méthode pour le singleton qui retourne l'instance
*/
ControllerMalloc* ControllerMalloc::getInstance()
{
	if (NULL == controllerMalloc)
      	{
        	cout << "creating singleton for ControllerMalloc." << endl;
        	controllerMalloc =  new ControllerMalloc();
	}
        return controllerMalloc;
} 

void ControllerMalloc::prendre_jeton()
{
	pthread_mutex_lock (&mutexMalloc);
}
void ControllerMalloc::rendre_jeton()
{
	pthread_mutex_unlock (&mutexMalloc);
}

ControllerMalloc  *ControllerMalloc::controllerMalloc = NULL;
pthread_mutex_t ControllerMalloc::mutexMalloc;
