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
Structure pour passer au thred receivePosition
*/
typedef struct t_structReceivePosition {
	t_position *position;
	int speed;
   	int busId;
}t_structReceivePosition;

/**
structure pour la mise enum place des dates
*/
typedef struct t_dateHour {
	int day;
	int month;
	int years;
	int hour;
	int minute;
	int second;
}t_dateHour;

// definition du type information envoyé à un BusStop
typedef struct t_information { 
	int lineNumber;
	int idBus;
	string message;
}t_information;

//type enum pour le type de message
enum t_code {ACCIDENT, BREAKDOWN, AGRESSION, CONGESTION};

typedef struct t_priorityMessage {
		t_code code;
		string message;
}t_priorityMessage;

typedef struct t_line { 
	int number;
}t_line;

#endif	/* _Type_H */

