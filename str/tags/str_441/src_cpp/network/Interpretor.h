#ifndef _Interpretor_H
#define _Interpretor_H

#include <iostream>
#include <queue>

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
		static void startBus(string buffer);
		static void accelerateBus(string buffer);
		static void decelerateBus(string buffer);
		static void accidentBus(string buffer);
    };

#endif
