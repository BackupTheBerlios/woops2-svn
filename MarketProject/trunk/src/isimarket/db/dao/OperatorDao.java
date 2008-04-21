package isimarket.db.dao;

import isimarket.db.manager.DatabaseManager;
import isimarket.model.Operator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatorDao {
	
	protected WalletDao walletDao = new WalletDao();
	
	public Operator get(String _login) {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("select * from operator where login=?");
			stmt.setString(1, _login);
			res = stmt.executeQuery();
			if (res.next()) {
				return new Operator(res.getString("login"), res
						.getString("password"),
						this.walletDao.get(res.getInt("wallet_id")));
			} else {
				System.out.println("Operateur inconnu : " + _login);
				return null;
			}
		} catch (SQLException e) {
			System.out.println("OperatorDao -> get(): "+ e.getMessage());
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

	public void insert(Operator _operator) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("insert into operator values (?,?,?)");
			stmt.setString(1, _operator.login);
			stmt.setString(2, _operator.password);
			stmt.setInt(3, _operator.wallet.walletId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("OperatorDao -> insert(): "+ e.getMessage());
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