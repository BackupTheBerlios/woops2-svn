package isimarket.servants.alarmservant.impl;

import java.util.List;

import isimarket.db.dao.ActionTypeDao;
import isimarket.db.dao.AlarmDao;
import isimarket.db.dao.AlarmTypeDao;
import isimarket.db.dao.WalletDao;
import isimarket.model.ActionType;
import isimarket.model.Alarm;
import isimarket.model.AlarmType;
import isimarket.model.Wallet;
import isimarket.servants.alarmservant._AlarmServantImplBase;

public class AlarmServantImpl extends _AlarmServantImplBase {
	
	private static final long serialVersionUID = 1L;

	protected AlarmDao alarmDao = new AlarmDao();
	
	protected AlarmTypeDao alarmTypeDao = new AlarmTypeDao();

	protected WalletDao walletDao = new WalletDao();

	protected ActionTypeDao actionTypeDao = new ActionTypeDao();

	/**
	 * 
	 * @param _name
	 * @param _walletId
	 * @param _alarmTypeId
	 * @param _actionTypeCode
	 * @return
	 */
	public Alarm createAlarm(String _name, float value, int _walletId, int _alarmTypeId,
			String _actionTypeCode) {
		
		Wallet wallet = this.walletDao.get(_walletId);
		ActionType actionType = this.actionTypeDao.get(_actionTypeCode);
		AlarmType alarmType = this.alarmTypeDao.get(_alarmTypeId);
		
		Alarm alarm = new Alarm();
		alarm.name = _name;
		alarm.type = alarmType;
		alarm.actionType = actionType;
		alarm.wallet = wallet;
		alarm.value= value;
		
		this.alarmDao.insert(alarm);
		
		return this.alarmDao.getLastInsertedAlarm();
		
	}

	/**
	 * 
	 * @param _label
	 */
	public void createAlarmType(String _label, String _symbol) {
		AlarmType alarmType = new AlarmType();
		alarmType.label = _label;
		alarmType.symbol = _symbol;
		this.alarmTypeDao.insert(alarmType);
	}

	/**
	 * 
	 */
	public void deleteAlarm(int _alarmId) {
		this.alarmDao.delete(_alarmId);
	}

	/**
	 * 
	 */
	public void deleteAlarmType(int _alarmTypeId) {
		this.alarmTypeDao.delete(_alarmTypeId);
	}

	/**
	 * 
	 * @param _alarmId
	 * @return
	 */
	public Alarm getAlarm(int _alarmId) {
		return this.alarmDao.get(_alarmId);
	}

	/**
	 * 
	 */
	public Alarm[] getAlarmListFromWallet(int _walletId) {
		List<Alarm> alarmlist = this.alarmDao.getAllFromWallet(_walletId);
		Alarm[] alarmArray = new Alarm[alarmlist.size()];
		return alarmlist.toArray(alarmArray);
	}

	/**
	 * 
	 */
	public AlarmType getAlarmType(int _alarmTypeId) {
		return this.alarmTypeDao.get(_alarmTypeId);
	}

	/**
	 * 
	 */
	public AlarmType[] getAlarmTypeList() {
		List<AlarmType> alarmTypelist = this.alarmTypeDao.getAll();
		AlarmType[] alarmTypeArray = new AlarmType[alarmTypelist.size()];
		return alarmTypelist.toArray(alarmTypeArray);
	}

}
