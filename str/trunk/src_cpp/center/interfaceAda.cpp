// 
// File:   main.cc
// Author: soosuske
//
// Created on 28 février 2007, 01:34
//

#include "OperatingCenter.h"
#include <stdlib.h>


extern "C"{ 
	void adainit();	
	void adafinal();
	void p_initBusStop(int nombre);
	void receivePosition(t_position *pos);

}


void receivePosition(t_position *pos)
{
	OperatingCenter* oc = new OperatingCenter();
	oc->receivePosition(pos);
	//cout<<" je suis dans le c++ et je recois"<<endl;
}

int main ()
{
	cout<<"main c"<<endl;
	adainit();
	p_initBusStop(1);
	adafinal();
}
