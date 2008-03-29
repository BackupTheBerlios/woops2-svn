package isimarket.db.manager;

import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {
	// alarmtype
	private static final String _CREATE_ALARMTYPE = "create table alarmtype(alarm_type_id integer, label varchar(100), "
			+ "primary key (alarm_type_id))";
	private static final String _DROP_ALARMTYPE = "drop table alarmtype";
	
	// ActionType
	private static final String _CREATE_ACTIONTYPE = "create table actiontype(code varchar(5),"
			+ "label varchar(32),introduction_date varchar(10) ,introduction_price float , "
			+ "quantity integer , current_price float, primary key (code) )";
	private static final String _DROP_ACTIONTYPE = "drop table actiontype";

	// Action
	private static final String _CREATE_ACTION = "create table action(action_id integer, buy_price float, buy_date varchar(10),"
			+ " quantity integer, action_type_code varchar(5), wallet_id integer, primary key (action_id),"
			+ " foreign key (action_type_code) references actiontype(code),"
			+ " foreign key (wallet_id) references wallet(wallet_id))";
	private static final String _DROP_ACTION = "drop table action";

	// Event
	private static final String _CREATE_EVENT = "create table event(event_id integer, price float, date varchar(10),action_type_code varchar(5),"
			+ " primary key (event_id), foreign key (action_type_code) references actiontype(code))";
	private static final String _DROP_EVENT = "drop table event";

	// Alarm
	private static final String _CREATE_ALARM = "create table alarm(alarm_id integer, name varchar(32), alarm_type_id integer ,action_type_code varchar(5),"
			+ " wallet_id integer, primary key (alarm_id), "
			+ " foreign key (wallet_id) references wallet(wallet_id),"
			+ " foreign key (alarm_type_id) references alarmtype(alarm_type_id),"
			+ " foreign key (action_type_code) references actiontype(code))";
	private static final String _DROP_ALARM = "drop table alarm";

	// Wallet
	private static final String _CREATE_WALLET = "create table wallet(wallet_id integer, cash float,"
			+ " primary key(wallet_id))";
	private static final String _DROP_WALLET = "drop table wallet";
	
	// Operator
	private static final String _CREATE_OPERATOR = "create table operator(login varchar(32), password varchar(32),wallet_id integer, "
			+ "foreign key (wallet_id) references wallet(wallet_id),  primary key(login))";
	private static final String _DROP_OPERATOR = "drop table operator";
	
	// Admin
	private static final String _CREATE_ADMINISTRATOR = "create table administrator(login varchar(32), password varchar(32), "
			+ "primary key(login))";
	private static final String _DROP_ADMINISTRATOR = "drop table administrator";
	
	public static void main(String[] args) {
		dropAndCreateTables();
	}

	private static void dropAndCreateTables() {
		DatabaseManager dbman = DatabaseManager.getInstance();
		Statement s = null;
		try {
			s = dbman.getConnection().createStatement();
			
			dropTables(s);
			createTables(s);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbman.closeConnection();
		}
	}
	
	public static void createTables() {
		DatabaseManager dbman = DatabaseManager.getInstance();
		Statement s = null;
		try {
			s = dbman.getConnection().createStatement();
			
			dropTables(s);
			createTables(s);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbman.closeConnection();
		}
	}
	
	public static void dropTables() {
		DatabaseManager dbman = DatabaseManager.getInstance();
		Statement s = null;
		try {
			s = dbman.getConnection().createStatement();
			
			dropTables(s);
			createTables(s);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbman.closeConnection();
		}
	}

	private static void dropTables(Statement s) throws SQLException {
		s.executeUpdate(_DROP_ALARM);
		s.executeUpdate(_DROP_ACTION);
		s.executeUpdate(_DROP_EVENT);
		s.executeUpdate(_DROP_OPERATOR);
		s.executeUpdate(_DROP_WALLET);
		s.executeUpdate(_DROP_ALARMTYPE);
		s.executeUpdate(_DROP_ACTIONTYPE);
		s.executeUpdate(_DROP_ADMINISTRATOR);
	}

	private static void createTables(Statement s) throws SQLException {
		s.executeUpdate(_CREATE_ACTIONTYPE);
		s.executeUpdate(_CREATE_ALARMTYPE);
		s.executeUpdate(_CREATE_ADMINISTRATOR);
		s.executeUpdate(_CREATE_WALLET);
		s.executeUpdate(_CREATE_EVENT);
		s.executeUpdate(_CREATE_ACTION);
		s.executeUpdate(_CREATE_ALARM);
		s.executeUpdate(_CREATE_OPERATOR);
	}
}
