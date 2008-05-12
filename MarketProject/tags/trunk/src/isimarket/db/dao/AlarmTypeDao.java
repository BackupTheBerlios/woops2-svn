package isimarket.db.dao;

import isimarket.db.manager.DatabaseManager;
import isimarket.model.AlarmType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlarmTypeDao {
	
	private static final String _COL_ALARM_TYPE_ID = "alarm_type_id";

	private static final String _COL_ALARM_TYPE_LABEL = "label";

	public void insert(AlarmType alarmType) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"insert into alarmtype values (NULL,?)");
			stmt.setString(1, alarmType.label);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("AlarmTypeDao -> insert(): " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	public AlarmType get(int alarmTypeId) {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"select * from alarmtype where " + _COL_ALARM_TYPE_ID
									+ " = " + alarmTypeId);
			res = stmt.executeQuery();
			if (res.next()) {
				return new AlarmType(res.getInt(_COL_ALARM_TYPE_ID), res
						.getString(_COL_ALARM_TYPE_LABEL));
			} else {
				System.out.println("Type d'alarme inconnu");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("AlarmTypeDao -> get(): "
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

	public void delete(int alarmTypeId) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("delete from alarmtype where "+ _COL_ALARM_TYPE_ID +" =?");
			stmt.setInt(1, alarmTypeId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("AlarmTypeDao -> delete(): "+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	public List<AlarmType> getAll() {
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<AlarmType> list = new ArrayList<AlarmType>();
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("select * from alarmtype");
			res = stmt.executeQuery();
			while (res.next()) {
				list.add(new AlarmType(res.getInt(_COL_ALARM_TYPE_ID), res
						.getString(_COL_ALARM_TYPE_LABEL)));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("AlarmTypeDao -> getAll(): "+ e.getMessage());
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
