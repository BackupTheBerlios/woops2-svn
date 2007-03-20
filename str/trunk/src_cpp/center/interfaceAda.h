#ifndef _interfaceAda_H
#define _interfaceAda_H

void adainit_busStop(int nombre, int ligne);
void adainit_bus(int nombre, int ligne);
void adastart_bus(int busId, int lineId);
void adastop_bus(int busId, int lineId);
void adaaccelerate_bus(int busId, int lineId);
void adadecelerate_bus(int busId, int lineId);

#endif	/* _OperationCenter_H */
