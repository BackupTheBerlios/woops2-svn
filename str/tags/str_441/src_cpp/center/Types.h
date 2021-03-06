/* 
 * File:   Type.h
 * Author: soosuske
 *
 * Created on 2 mars 2007, 08:55
 */

#ifndef _Types_H
#define	_Types_H

#include <string>

using namespace std;




/**
Structure qui correspond a la position d'un bus.
*/
typedef struct t_position {
	int lineNumber;
    int busStopId;
    float distance;//en metre.
}t_position;

/**
Structure qui correspond a la mémoire partagée.
*/
typedef struct t_memoire {
	int ligne;
	int busStop;
	int bus;
	int time;
}t_memoire;

/**
Structure qui correspond a l'archivage des données
*/
typedef struct t_archivage {
	int ligne;
	int busStop;
	int bus;
	float distance;
}t_archivage;

/**
Structure pour passer au thread receivePosition
*/
typedef struct t_structReceivePosition {
	t_position *position;
	int speed;
   	int busId;
}t_structReceivePosition;


//type enum pour le type de message
enum t_code {ACCIDENT, BREAKDOWN, AGRESSION, CONGESTION};

typedef struct t_priorityMessage {
		int busId;
		t_code code;
}t_priorityMessage;

#endif	/* _Type_H */

