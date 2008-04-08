package isimarket.db.dao;

import isimarket.db.manager.DatabaseManager;
import isimarket.model.ActionType;
import isimarket.model.Operator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TODO faire operator dao
public class OperatorDao {
	
	protected WalletDao walletDao = new WalletDao();

//	public void delete(String _code) {
//		PreparedStatement stmt = null;
//		try {
//			stmt = DatabaseManager.getInstance().getConnection()
//					.prepareStatement("delete from actiontype where code=?");
//			stmt.setString(1, _code);
//			stmt.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("ActionTypeDao -> delete(): "+ e.getMessage());
//			e.printStackTrace();
//		} finally {
//			try {
//				if (stmt != null)
//					stmt.close();
//			} catch (SQLException e) {
//			}
//		}
//	}
//
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
				System.out.println("operator inconnu : " + _login);
				return null;
			}
		} catch (SQLException e) {
			System.out.println("operatordao -> get(): "+ e.getMessage());
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
//
//	public List<ActionType> getAll() {
//		PreparedStatement stmt = null;
//		ResultSet res = null;
//		List<ActionType> list = new ArrayList<ActionType>();
//		try {
//			stmt = DatabaseManager.getInstance().getConnection()
//					.prepareStatement("select * from actiontype");
//			res = stmt.executeQuery();
//			while (res.next()) {
//				list.add(new ActionType(res.getString("code"), res
//						.getString("label"),
//						res.getString("introduction_date"), res
//								.getFloat("introduction_price"), res
//								.getInt("quantity"), res
//								.getFloat("current_price")));
//			}
//			return list;
//		} catch (SQLException e) {
//			System.out.println("ActionTypeDao -> getAll(): "+ e.getMessage());
//			e.printStackTrace();
//			return null;
//		} finally {
//			try {
//				if (res != null)
//					res.close();
//			} catch (SQLException e) {
//			}
//			try {
//				if (stmt != null)
//					stmt.close();
//			} catch (SQLException e) {
//			}
//		}
//	}
//
	public void insert(Operator _operator) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("insert into operator values (?,?,?)");
			stmt.setString(1, _operator.login);
			stmt.setString(2, _operator.password);
			stmt.setInt(3, _operator.wallet.wallet_id);
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
//
//	public void updateQuantity(ActionType _actionType) {
//		PreparedStatement stmt = null;
//		try {
//			stmt = DatabaseManager.getInstance().getConnection()
//					.prepareStatement("update actiontype set quantity = ? where code = ?");
//			stmt.setInt(1, _actionType.quantity);
//			stmt.setString(2, _actionType.code);
//			stmt.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("ActionTypeDao -> update(): "+ e.getMessage());
//			e.printStackTrace();
//		} finally {
//			try {
//				if (stmt != null)
//					stmt.close();
//			} catch (SQLException e) {
//			}
//		}
//	}
//	
//	public void updateCurrentPrice(ActionType _actionType) {
//		PreparedStatement stmt = null;
//		try {
//			stmt = DatabaseManager.getInstance().getConnection()
//					.prepareStatement("update actiontype set current_price = ? where code = ?");
//			stmt.setFloat(1, _actionType.currentPrice);
//			stmt.setString(2, _actionType.code);
//			stmt.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("ActionTypeDao -> update(): "+ e.getMessage());
//			e.printStackTrace();
//		} finally {
//			try {
//				if (stmt != null)
//					stmt.close();
//			} catch (SQLException e) {
//			}
//		}
//	}

}