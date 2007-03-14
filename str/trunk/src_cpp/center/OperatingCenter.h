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
#include "interfaceAda.h"

using namespace std;

class OperatingCenter
    {
	private :
   		static void* thread_function_initBus(void *a);
      	static void* thread_function_receive_position(void *position, int speed, int busId);
      	static void* thread_function_receive_information(void *a);
		static void* thread_function_returnInitBusStop(void *a);

		//mise en place du singleton
		static OperatingCenter * operatingCenter;

		OperatingCenter();
	public :
		
		~OperatingCenter();
       	static OperatingCenter* getInstance ();
		void initializeSystem();
		void receivePosition(t_position* position);
      	void p_sendPriorityMessage();
   		void receiveInformation(t_information* t_ptr_t_information);

		//--------------- méthode qui viennent de Java -------------
		void java_init_busStop(int nombre, int ligne);
		void java_init_line(int line);
		void java_init_bus(int nombre, int ligne);

		//------- méthode qui ada appelle et qui créée les threads
		void returnInitBusStop();
                
};

#endif	/* _OperationCenter_H */

