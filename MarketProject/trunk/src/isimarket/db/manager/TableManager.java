package isimarket.db.manager;

import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {
	// alarmtype
	private static final String _DROP_ALARMTYPE = "drop table alarmtype";
	private static final String _CREATE_ALARMTYPE = "create table alarmtype(alarm_type_id integer, label varchar(100), " +
													"primary key (alarm_type_id))";
	// ActionType
	private static final String _CREATE_ACTIONTYPE = "create table actiontype(code varchar(2)," +
							"label varchar(3),introduction_date varchar(10) ,introduction_price float , " +
							"quantity integer , current_price float, primary key (code) )";
	private static final String _DROP_ACTIONTYPE = "drop table actiontype";
	
	// Action
	private static final String _CREATE_ACTION = "create table action(action_id integer, buy_price float, buy_date varchar(10)," +
																	" quantity integer, action_type_code varchar(2), primary key (action_id)," +
																	" foreign key (action_type_code) references actiontype(code))";
	private static final String _DROP_ACTION = "drop table action";
	
	// Event
	private static final String _CREATE_EVENT = "create table event(event_id integer, price float, date varchar(10),action_type_code varchar(2)," +
			"														primary key (event_id), foreign key (action_type_code) references actiontype(code))";
	private static final String _DROP_EVENT = "drop table event";
	
	public static void main(String[] args) {
		DatabaseManager dbman = DatabaseManager.getInstance();
		Statement s = null;
		try {
			s = dbman.getConnection().createStatement();
			s.executeUpdate(_DROP_ALARMTYPE);
			s.executeUpdate(_DROP_ACTION);
			s.executeUpdate(_DROP_ACTIONTYPE);
			s.executeUpdate(_DROP_EVENT);	
			s.executeUpdate(_CREATE_ACTIONTYPE);
			s.executeUpdate(_CREATE_ALARMTYPE);
			s.executeUpdate(_CREATE_ACTION);
			s.executeUpdate(_CREATE_EVENT);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		dbman.closeConnection();
		}
	}
}
