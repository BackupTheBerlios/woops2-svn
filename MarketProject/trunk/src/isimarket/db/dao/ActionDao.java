package isimarket.db.dao;

import isimarket.db.manager.DatabaseManager;
import isimarket.model.Action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionDao {
	
	private static final String _COL_ACTION_ID = "action_id";
	
	private static final String _COL_PRICE = "buy_price";
	
	private static final String _COL_DATE = "buy_date";
	
	private static final String _COL_QUANTITY = "quantity";
	
	private static final String _COL_ACTION_TYPE = "action_type_code";
	
	private static final String _COL_WALLET = "wallet_id";
	
	protected ActionTypeDao actionTypeDao = new ActionTypeDao();
	
	protected WalletDao walletDao = new WalletDao();

	public void insert(Action _action) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("insert into action values (NULL,?,?,?,?,?)");
			stmt.setFloat(1, _action.buyPrice);
			stmt.setString(2, _action.buyDate);
			stmt.setInt(3, _action.quantity);
			stmt.setString(4, _action.actiontype.code);
			stmt.setInt(6, _action.wallet.walletId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ActionDao -> insert(): "+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	public Action getLastInsertedAction() {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("select * from action where "+_COL_ACTION_ID+" = select max("+_COL_ACTION_ID+") from action");
			res = stmt.executeQuery();
			if (res.next()) {				
				return new Action(res.getInt(_COL_ACTION_ID), res.getFloat(_COL_PRICE), res.getString(_COL_DATE),
						res.getInt(_COL_QUANTITY), this.actionTypeDao.get(res.getString(_COL_ACTION_TYPE)),
						this.walletDao.get(res.getInt(_COL_WALLET)));
			} else {
				System.out.println("Action inconnue ");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("ActionDao -> getLastInsertedAction(): "+ e.getMessage());
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

	public Action get(int actionId) {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("select * from action where "+_COL_ACTION_ID+" = "+ actionId);
			res = stmt.executeQuery();
			if (res.next()) {				
				return new Action(res.getInt(_COL_ACTION_ID), res.getFloat(_COL_PRICE), res.getString(_COL_DATE),
						res.getInt(_COL_QUANTITY), this.actionTypeDao.get(res.getString(_COL_ACTION_TYPE)),
						this.walletDao.get(res.getInt(_COL_WALLET)));
			} else {
				System.out.println("Action inconnue ");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("ActionDao -> getLastInsertedAction(): "+ e.getMessage());
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
