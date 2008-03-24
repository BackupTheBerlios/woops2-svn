package isimarket.db;

import isimarket.db.manager.DatabaseManager;

import java.sql.SQLException;
import java.sql.Statement;



public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatabaseManager dbman = DatabaseManager.getInstance();
		Statement s = null;
		try {
			s = dbman.getConnection().createStatement();
			s.execute("create table Test()");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
