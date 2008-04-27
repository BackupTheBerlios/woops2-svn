package isimarket.db.dao;

import isimarket.db.manager.DatabaseManager;
import isimarket.model.Action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActionDao {

	private static final String _COL_ACTION_ID = "action_id";

	private static final String _COL_PRICE = "buy_price";

	private static final String _COL_DATE = "buy_date";

	private static final String _COL_QUANTITY = "quantity";

	private static final String _COL_ACTION_TYPE = "action_type_code";

	private static final String _COL_WALLET = "wallet_id";

	protected ActionTypeDao actionTypeDao = new ActionTypeDao();

	protected WalletDao walletDao = new WalletDao();

	/**
	 * 
	 * @param _action
	 */
	public void insert(Action _action) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"insert into action values (NULL,?,?,?,?,?)");
			stmt.setFloat(1, _action.buyPrice);
			stmt.setString(2, _action.buyDate);
			stmt.setInt(3, _action.quantity);
			stmt.setString(4, _action.actiontype.code);
			stmt.setInt(6, _action.wallet.walletId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ActionDao -> insert(): " + e.getMessage());
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
	public Action getLastInsertedAction() {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"select * from action where " + _COL_ACTION_ID
									+ " = select max(" + _COL_ACTION_ID
									+ ") from action");
			res = stmt.executeQuery();
			if (res.next()) {
				return new Action(res.getInt(_COL_ACTION_ID), res
						.getFloat(_COL_PRICE), res.getString(_COL_DATE), res
						.getInt(_COL_QUANTITY), this.actionTypeDao.get(res
						.getString(_COL_ACTION_TYPE)), this.walletDao.get(res
						.getInt(_COL_WALLET)));
			} else {
				System.out.println("Action inconnue ");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("ActionDao -> getLastInsertedAction(): "
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
	 * @param actionId
	 * @return
	 */
	public Action get(int actionId) {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"select * from action where " + _COL_ACTION_ID
									+ " = " + actionId);
			res = stmt.executeQuery();
			if (res.next()) {
				return new Action(res.getInt(_COL_ACTION_ID), res
						.getFloat(_COL_PRICE), res.getString(_COL_DATE), res
						.getInt(_COL_QUANTITY), this.actionTypeDao.get(res
						.getString(_COL_ACTION_TYPE)), this.walletDao.get(res
						.getInt(_COL_WALLET)));
			} else {
				System.out.println("Action inconnue ");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("ActionDao -> getLastInsertedAction(): "
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
	 * @param _walletId
	 * @return
	 */
	public List<Action> getAllFromWallet(int _walletId) {
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<Action> list = new ArrayList<Action>();
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"select * from action where " + _COL_WALLET + " = "
									+ _walletId);
			res = stmt.executeQuery();
			while (res.next()) {
				list.add(new Action(res.getInt("action_id"), res
						.getFloat("buy_price"), res.getString("buy_date"), res
						.getInt("quantity"), this.actionTypeDao.get(res
						.getString("action_type_code")), this.walletDao.get(res
						.getInt("wallet_id"))));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("ActionDao -> getAllFromWallet(): " + e.getMessage());
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
	 * @param action
	 */
	public void delete(Action action) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("delete from action where "+ _COL_ACTION_ID +" =?");
			stmt.setInt(1, action.actionId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ActionDao -> delete(): "+ e.getMessage());
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
	 * @param actionId
	 * @param quantity
	 */
	public void updateQuantity(int actionId, int quantity) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("update action set "+ _COL_QUANTITY +" = ? where "+ _COL_ACTION_ID +"= ?");
			stmt.setFloat(1, quantity);
			stmt.setInt(2, actionId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ActionDao -> updateQuantity(): "+ e.getMessage());
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
