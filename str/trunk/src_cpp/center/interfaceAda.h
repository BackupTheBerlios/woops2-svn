#ifndef _interfaceAda_H
#define _interfaceAda_H

void adainit_busStop(int nombre, int ligne);
void adainit_bus(int nombre, int ligne);
void ada_startBus(int busId);
void adastop_bus(int busId);
void adaaccelerate_bus(int busId);
void adadecelerate_bus(int busId);

#endif	/* _OperationCenter_H */
