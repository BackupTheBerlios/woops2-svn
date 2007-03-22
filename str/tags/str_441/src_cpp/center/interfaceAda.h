#ifndef _interfaceAda_H
#define _interfaceAda_H

void adainit_busStop(int nombre, int ligne);
void adainit_bus(int nombre, int ligne);
void ada_startBus(int busId);
void ada_stopBus(int busId);
void ada_accelerateBus(int busId);
void ada_decelerateBus(int busId);

#endif	/* _OperationCenter_H */
