package isimarket.servants.administrationservant;


/**
* isimarket/model/servants/administrationservant/AdministrationServantOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* samedi 29 mars 2008 11 h 45 CET
*/

public interface AdministrationServantOperations 
{
  void updateCash (String operatorLogin, float newCash);
  void createOperator (isimarket.model.Operator _operator);
  void createWallet (String operatorLogin, float initalCash);
} // interface AdministrationServantOperations