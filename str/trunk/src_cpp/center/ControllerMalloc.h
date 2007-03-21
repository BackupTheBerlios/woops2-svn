#ifndef _ControllerMalloc_H
#define _ControllerMalloc_H

#include <iostream>

using namespace std;

/**
Classe qui va permettre d'éviter que deux thread tente d'alloué en meme temps le meme espace mémoire.
*/
class ControllerMalloc {

	static ControllerMalloc* controllerMalloc;      	
	static pthread_mutex_t mutexMalloc;
	private:
		ControllerMalloc();
		
   	public:
	  	~ControllerMalloc();
       		static ControllerMalloc* getInstance();
		void prendre_jeton();
		void rendre_jeton();
		
    };

#endif
