package isimarket.db.dao;

import isimarket.db.manager.DatabaseManager;
import isimarket.model.Alarm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlarmDao {
	
	private static final String _COL_ALARM_ID = "alarm_id";

	private static final String _COL_NAME = "name";

	private static final String _COL_ALARM_TYPE = "alarm_type_id";

	private static final String _COL_ACTION_TYPE = "action_type_code";

	private static final String _COL_WALLET = "wallet_id";
	
	protected AlarmTypeDao alarmTypeDao = new AlarmTypeDao();
	
	protected ActionTypeDao actionTypeDao = new ActionTypeDao();
	
	protected WalletDao walletDao = new WalletDao();

	/**
	 * 
	 * @param alarm
	 */
	public void insert(Alarm alarm) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"insert into alarm values (NULL,?,?,?,?)");
			stmt.setString(1, alarm.name);
			stmt.setInt(2, alarm.type.alarmTypeId);
			stmt.setString(3, alarm.actionType.code);
			stmt.setInt(4, alarm.wallet.walletId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("AlarmDao -> insert(): " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public Alarm getLastInsertedAlarm() {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"select * from alarm where " + _COL_ALARM_ID
									+ " = select max(" + _COL_ALARM_ID
									+ ") from alarm");
			res = stmt.executeQuery();
			if (res.next()) {
				return new Alarm(res.getInt(_COL_ALARM_ID), res
						.getString(_COL_NAME), this.alarmTypeDao.get(res
						.getInt(_COL_ALARM_TYPE)), this.actionTypeDao.get(res
						.getString(_COL_ACTION_TYPE)), this.walletDao.get(res
						.getInt(_COL_WALLET)));
			} else {
				System.out.println("Alarme inconnue");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("AlarmDao -> getLastInsertedAlarm(): "
					+ e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (res != null)
					res.close();
			} catch (SQLException e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 
	 * @param alarmId
	 */
	public void delete(int alarmId) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("delete from alarm where "+ _COL_ALARM_ID +" =?");
			stmt.setInt(1, alarmId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("AlarmDao -> delete(): "+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Alarm get(int alarmId) {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"select * from alarm where " + _COL_ALARM_ID
									+ " = " + alarmId);
			res = stmt.executeQuery();
			if (res.next()) {
				return new Alarm(res.getInt(_COL_ALARM_ID), res
						.getString(_COL_NAME), this.alarmTypeDao.get(res
						.getInt(_COL_ALARM_TYPE)), this.actionTypeDao.get(res
						.getString(_COL_ACTION_TYPE)), this.walletDao.get(res
						.getInt(_COL_WALLET)));
			} else {
				System.out.println("Alarme inconnu");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("AlarmDao -> get(): "
					+ e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (res != null)
					res.close();
			} catch (SQLException e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<Alarm> getAllFromWallet(int _walletId) {
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<Alarm> list = new ArrayList<Alarm>();
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"select * from alarm where " + _COL_WALLET + " = "
									+ _walletId);
			res = stmt.executeQuery();
			while (res.next()) {
				list.add(new Alarm(res.getInt(_COL_ALARM_ID), res
						.getString(_COL_NAME), this.alarmTypeDao.get(res
								.getInt(_COL_ALARM_TYPE)), this.actionTypeDao.get(res
								.getString(_COL_ACTION_TYPE)), this.walletDao.get(res
								.getInt(_COL_WALLET))));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("AlarmDao -> getAllFromWallet(): " + e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (res != null)
					res.close();
			} catch (SQLException e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

}
