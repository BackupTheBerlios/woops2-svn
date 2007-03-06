/* 
 * File:   OperationCenter.h
 * Author: soosuske
 *
 * Created on 28 février 2007, 01:32
 */



#ifndef _OperatingCenter_H
#define	_OperatingCenter_H

#include <iostream>
#include <pthread.h>
#include <fcntl.h>
#include <sstream>
#include <fstream>
#include "Types.h"


using namespace std;

class OperatingCenter
{
	private :
               static  void* thread_function_initBus(void *a);
            	static void* thread_function_receive_position(void *a);
            	static void* thread_function_receive_information(void *a);
	public :
		OperatingCenter();
		~OperatingCenter();
               
		void initializeSystem();
		void receivePosition();
                void p_sendPriorityMessage();
                void receiveInformation(t_information* t_ptr_t_information);
                
};

#endif	/* _OperationCenter_H */

