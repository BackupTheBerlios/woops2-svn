// 
// File:   main.cc
// Author: soosuske
//
// Created on 28 février 2007, 01:34
//

#include <stdlib.h>
#include "OperatingCenter.h"

//
// 
//
int
main(int argc, char** argv) {
    
    OperatingCenter* oc = new OperatingCenter();
    oc->receivePosition();
   // oc->receiveInformation();
    oc->initializeSystem();
    while(1){}
    return (EXIT_SUCCESS);
}

