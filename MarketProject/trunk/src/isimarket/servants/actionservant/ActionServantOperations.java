package isimarket.servants.actionservant;


/**
* isimarket/model/servants/actionservant/ActionServantOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 25 mars 2008 18 h 20 CET
*/

public interface ActionServantOperations 
{
  isimarket.model.ActionType[] getActionTypeList ();
  isimarket.model.Action[] buyAction (String actionTypeLabel, int quantity);
  void sellAction (String actionTypeLabel, int quantity, int actions_id);
  isimarket.model.ActionType getActionType (String label);
} // interface ActionServantOperations
