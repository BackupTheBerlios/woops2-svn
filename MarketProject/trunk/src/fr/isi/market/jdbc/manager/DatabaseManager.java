package fr.isi.market.jdbc.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	private static final String HSQLDB_JDBC_DRIVER = "org.hsqldb.jdbcDriver";
	private static final String DB_PWD = "";
	private static final String DB_USER = "sa";
	private static final String JDBC_HSQLDB_URL = "jdbc:hsqldb:file:./db/marketdb";

	private static DatabaseManager instance = new DatabaseManager();
	
	private Connection currentConnection;
	
	public static DatabaseManager getInstance(){
		return instance;
	}
	
	private DatabaseManager(){
		
	}
	
	private void createConnexion(){
		try {
			Class.forName(HSQLDB_JDBC_DRIVER);
			currentConnection = DriverManager.getConnection(JDBC_HSQLDB_URL, DB_USER, DB_PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		if(currentConnection == null)
			createConnexion();
		return currentConnection;
	}
}
