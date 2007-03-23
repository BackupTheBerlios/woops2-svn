/* 
 * File:   OperationCenter.h
 * Author: soosuske
 *
 * Created on 28 février 2007, 01:32
 */



#ifndef _OperatingCenter_H
#define _OperatingCenter_H

#include <iostream>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <netdb.h>
#include <pthread.h>
#include <fcntl.h>
#include <sstream>
#include <fstream>
#include "Types.h"


using namespace std;



class OperatingCenter
    {
	private :
   		static void* thread_function_initBus(void *a);
      		static void* thread_function_receive_position(void *position);
      		static void* thread_function_receive_information(void *a);
      		static void* thread_function_receive_priority_message(void *a);
		static void* thread_function_returnInitBusStop(void *a);
		static void* thread_function_getvaleur(void* a);
		static void* thread_function_archivage(void* a);

		//mise en place du singleton
		static OperatingCenter * operatingCenter;
		static int envoieInformation;
		OperatingCenter();
	public :
		
		~OperatingCenter();
       	static OperatingCenter* getInstance ();
		void initializeSystem();
		void receivePosition(t_structReceivePosition* position);
		void receivePriorityMessage(t_priorityMessage* ptr_mes);
		
		//??!
      	void p_sendPriorityMessage();
		
		//--------------- getteur et setteur sur la variable envoieinformation -----
		static int getEnvoieInformation();
		static void setEnvoieInformation(int information);
		//--------------- méthode qui viennent de Java -------------
		void java_init_busStop(int nombre, int ligne);
		void java_init_line(int line);
		void java_init_bus(int nombre, int ligne);
		void java_start_bus(int num_bus);
		void java_accelerate_bus(int num_bus);
		void java_decelerate_bus(int num_bus);
		void java_accident(int num_bus);
		void java_aggression(int num_bus);
		void java_breakdown(int num_bus);

		//------- méthode qui ada appelle et qui créée les threads

                
};

#endif	/* _OperationCenter_H */

