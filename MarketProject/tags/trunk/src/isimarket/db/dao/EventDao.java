package isimarket.db.dao;

import isimarket.db.manager.DatabaseManager;
import isimarket.model.Event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

	private static final String _COL_EVENT_ID = "event_id";

	private static final String _COL_PRICE = "price";

	private static final String _COL_DATE = "date";

	private static final String _COL_ACTION_TYPE_CODE = "action_type_code";

	protected ActionTypeDao actionTypeDao = new ActionTypeDao();

	public void insert(Event event) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("insert into event values (NULL,?,?,?)");
			stmt.setFloat(1, event.price);
			stmt.setString(2, event.date);
			stmt.setString(3, event.actiontype.code);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("EventDao -> insert(): " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	public void delete(int eventId) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"delete from event where " + _COL_EVENT_ID + " =?");
			stmt.setInt(1, eventId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("EventDao -> delete(): " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	public Event get(int eventId) {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"select * from event where " + _COL_EVENT_ID
									+ " = " + eventId);
			res = stmt.executeQuery();
			if (res.next()) {
				return new Event(res.getInt(_COL_EVENT_ID), res
						.getFloat(_COL_PRICE), res.getString(_COL_DATE),
						this.actionTypeDao.get(res
								.getString(_COL_ACTION_TYPE_CODE)));
			} else {
				System.out.println("Evenement inconnu");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("EventDao -> getLastInsertedAction(): "
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

	public List<Event> getAllFromActionType(String actionTypeCode) {
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<Event> list = new ArrayList<Event>();
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"select * from event where "
									+ _COL_ACTION_TYPE_CODE + " = '"
									+ actionTypeCode+"'");
			res = stmt.executeQuery();
			while (res.next()) {
				list.add(new Event(res.getInt(_COL_EVENT_ID), res
						.getFloat(_COL_PRICE), res.getString(_COL_DATE),
						this.actionTypeDao.get(res
								.getString(_COL_ACTION_TYPE_CODE))));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("EventDao -> getAllFromActionType(): "
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

}