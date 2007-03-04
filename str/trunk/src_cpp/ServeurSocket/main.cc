// 
// File:   main.cc
// Author: garwind
//
// Created on 2 mars 2007, 13:25
//
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include "NetworkManager.h"

int main(int argc, char** argv) {
    
    NetworkManager nm;
    nm.setRemoteHost("localhost");
    nm.prepareSocket();
    nm.startListening();
    for (int i = 0 ; i < 4 ; i++)
        nm.sendToRemoteClient("buffer_test\n");
    nm.closeSocket();
    
    return (EXIT_SUCCESS);
    
}


