
#include "Network.h"
#include "../center/OperatingCenter.h"


//instance pour le singleton de NetworkManager


//---------------- Classe Interpretor

AbstractSystemObject* Interpretor::getAbstractSystemObject()
{
	return abstractSystemObject;
}

void Interpretor::setAbstractSystemObject(AbstractSystemObject* aso )
{
	abstractSystemObject = aso;
}

void Interpretor::buildAbstractSystemObject(string buffer)
{
}

string Interpretor::interpreteCurrentAbstractSystemObject()
{
}


//--------------------- Classe AbstractSystemObject
 Interpretor* AbstractSystemObject::getInterpretor()
{
	return interpretor;
}

 void AbstractSystemObject::setInterpretor(Interpretor* _i){
	interpretor = _i;
}

 string AbstractSystemObject::getBuffer(){
	return buffer;
}

 void AbstractSystemObject::setBuffer(string _buff){
	buffer = _buff;
}
            
AbstractSystemObject::AbstractSystemObject(string _buff){
	buffer = _buff;
}





//----------------------------------- Classe SystemObject

SystemObject * DataSystemObject::getSystemObject(){
	return systemObject;
}

void DataSystemObject::setSystemObject(SystemObject * _s){
	systemObject = _s;
}
            
void DataSystemObject::applyToSystem(){
}



//----------------------------------- CommandSystemObject
void CommandSystemObject::applyToSystem(){
}


