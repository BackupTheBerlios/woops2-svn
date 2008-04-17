package isimarket.servants.actiontypeservant;


/**
* isimarket/model/servants/actiontypeservant/ActionTypeServantOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* jeudi 17 avril 2008 23 h 33 CEST
*/

public interface ActionTypeServantOperations 
{
  isimarket.model.ActionType[] getActionTypeList ();
  isimarket.model.ActionType getActionType (String actionTypeCode);
  void createActionType (String code, String label, String introductionDate, float introductionPrice, int quantity, float currentPrice);
  void updateActionTypeQuantity (String code, int quantity);
  void updateActionTypeCurrentPrice (String code, float currentPrice);
  isimarket.model.Event[] getEvents (String actionTypeCode);
} // interface ActionTypeServantOperations
