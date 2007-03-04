
#include "InterfaceAda.h"
#include "OperatingCenter.h"

#include <iostream>

using namespace std;

void receivePosition()
{
   OperatingCenter* oc = new OperatingCenter();
   oc->receivePosition();
};
