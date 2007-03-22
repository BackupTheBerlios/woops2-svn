#ifndef _Interpretor_H
#define _Interpretor_H

#include <iostream>
#include <queue>
#include "../center/Types.h"

using namespace std;

class Interpretor {

	static Interpretor* interpretor;      	
	static queue<string> *messageFromNetwork;
	private:
		Interpretor();
		
		static void createBusStop(string buffer);
		//void createLine(string buffer);
		static void createBus(string buffer);
		static void* threadTraitementInformation(void *a);

		
   	public:
	  	~Interpretor();
       		static Interpretor* getInstance();
		queue<string>* getMessageFromNetwork();
		void setMessageFromNetwork(queue<string>* _messageFromNetwork);
		void sendPosition(int lineId, int busId, int busStopId, int percent, int speed);
		void sendInformation(int lineId, int busId, int busStopId, int time);
		void sendPriorityMessage(int busId, t_code code);
		static void startBus(string buffer);
		static void accelerateBus(string buffer);
		static void decelerateBus(string buffer);
		static void accident(string buffer);
		static void aggression(string buffer);
		static void breakdown(string buffer);
    };

#endif
