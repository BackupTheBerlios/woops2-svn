#ifndef _Interpretor_H
#define _Interpretor_H

#include "../center/OperatingCenter.h"

class Interpretor {

	static Interpretor* interpretor;      	
	
	private:
		Interpretor();
		void createBusStop(string buffer);
        public:
	  ~Interpretor();
           static Interpretor* getInstance();
           void receiveInformation(char* buffer);
    };

#endif
