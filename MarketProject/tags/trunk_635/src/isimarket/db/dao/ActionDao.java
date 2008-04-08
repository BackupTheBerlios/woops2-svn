package isimarket.db.dao;

import isimarket.db.manager.DatabaseManager;
import isimarket.model.Action;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActionDao {

	public void insert(Action _action) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("insert into action values (NULL,?,?,?,?,?)");
			stmt.setFloat(1, _action.buyPrice);
			stmt.setString(2, _action.buyDate);
			stmt.setFloat(3, _action.quantity);
			stmt.setString(4, _action.actiontype.code);
			//stmt.setInt(6, _action.)
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("WalletDao -> insert(): "+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

}
