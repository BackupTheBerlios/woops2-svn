package isimarket.db.dao;

import isimarket.db.manager.DatabaseManager;
import isimarket.model.ActionType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TODO faire operator dao
public class OperatorDao {

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
//	public ActionType get(String _code) {
//		PreparedStatement stmt = null;
//		ResultSet res = null;
//		try {
//			stmt = DatabaseManager.getInstance().getConnection()
//					.prepareStatement("select * from actiontype where code=?");
//			stmt.setString(1, _code);
//			res = stmt.executeQuery();
//			if (res.next()) {
//				return new ActionType(res.getString("code"), res
//						.getString("label"),
//						res.getString("introduction_date"), res
//								.getFloat("introduction_price"), res
//								.getInt("quantity"), res
//								.getFloat("current_price"));
//			} else {
//				System.out.println("Tye d'action inconnu : " + _code);
//				return null;
//			}
//		} catch (SQLException e) {
//			System.out.println("ActionTypeDao -> get(): "+ e.getMessage());
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
	public void insert(ActionType _actionType) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("insert into operator values (?,?,?,?,?,?)");
			stmt.setString(1, _actionType.code);
			stmt.setString(2, _actionType.label);
			stmt.setString(3, _actionType.introductionDate);
			stmt.setFloat(4, _actionType.introductionPrice);
			stmt.setInt(5, _actionType.quantity);
			stmt.setFloat(6, _actionType.currentPrice);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ActionTypeDao -> insert(): "+ e.getMessage());
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