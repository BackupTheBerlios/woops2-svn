// 
// File:   main.cc
// Author: soosuske
//
// Created on 28 février 2007, 01:34
//

#include <stdlib.h>
#include "OperatingCenter.h"

extern "C"{ 
	void adainit();	
	void adafinal();
}



int main ()
{
	cout<<"main c";
	adainit();
	//mainada();
	adafinal();
}
