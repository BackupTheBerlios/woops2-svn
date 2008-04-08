package isimarket.servants.alarmservant;


/**
* isimarket/model/servants/alarmservant/AlarmServantOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from services.idl
* mardi 8 avril 2008 19 h 27 CEST
*/

public interface AlarmServantOperations 
{
  isimarket.model.AlarmType[] getAlarmTypeList ();
  isimarket.model.AlarmType getAlarmType (int alarmTypeId);
  void createAlarmType (int alarmTypeId, String label);
  void deleteAlarmType (int alarmTypeId);
  isimarket.model.Alarm[] getAlarmList (int _walletId);
  isimarket.model.Alarm getAlarm (int walletId, int alarmType_id, String actionTypeCode);
  void createAlarm (int walletId, int alarmType_id, String actionTypeCode);
  void deleteAlarm (int alarmId);
} // interface AlarmServantOperations
