package isimarket.db.dao;

import isimarket.db.manager.DatabaseManager;
import isimarket.model.ActionType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActionTypeDao {

	private static final String _COL_CODE = "code";

	private static final String _COL_LABEL = "label";

	private static final String _COL_INTRO_DATE = "introduction_date";

	private static final String _COL_INTRO_PRICE = "introduction_price";

	private static final String _COL_QUANTITY = "quantity";

	private static final String _COL_CURRENT_PRICE = "current_price";

	/**
	 * 
	 * @param _code
	 */
	public void delete(String _code) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"delete from actiontype where " + _COL_CODE + "=?");
			stmt.setString(1, _code);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ActionTypeDao -> delete(): " + e.getMessage());
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
	 * @param _code
	 * @return
	 */
	public ActionType get(String _code) {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"select * from actiontype where " + _COL_CODE
									+ "=?");
			stmt.setString(1, _code);
			res = stmt.executeQuery();
			if (res.next()) {
				return new ActionType(res.getString(_COL_CODE), res
						.getString(_COL_LABEL), res.getString(_COL_INTRO_DATE),
						res.getFloat(_COL_INTRO_PRICE), res
								.getInt(_COL_QUANTITY), res
								.getFloat(_COL_CURRENT_PRICE));
			} else {
				System.out.println("Tye d'action inconnu : " + _code);
				return null;
			}
		} catch (SQLException e) {
			System.out.println("ActionTypeDao -> get(): " + e.getMessage());
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
	 * @return
	 */
	public ActionType getLastInsertedActionType() {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"select * from actiontype where " + _COL_CODE
									+ " = select max(" + _COL_CODE
									+ ") from actiontype");
			res = stmt.executeQuery();
			if (res.next()) {
				return new ActionType(res.getString(_COL_CODE), res
						.getString(_COL_LABEL), res.getString(_COL_INTRO_DATE),
						res.getFloat(_COL_INTRO_PRICE), res
								.getInt(_COL_QUANTITY), res
								.getFloat(_COL_CURRENT_PRICE));
			} else {
				System.out.println("ActionType inconnue ");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("ActionDao -> getLastInsertedActionType(): "
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
	 * @return
	 */
	public List<ActionType> getAll() {
		PreparedStatement stmt = null;
		ResultSet res = null;
		List<ActionType> list = new ArrayList<ActionType>();
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("select * from actiontype");
			res = stmt.executeQuery();
			while (res.next()) {
				list.add(new ActionType(res.getString(_COL_CODE), res
						.getString(_COL_LABEL), res.getString(_COL_INTRO_DATE),
						res.getFloat(_COL_INTRO_PRICE), res
								.getInt(_COL_QUANTITY), res
								.getFloat(_COL_CURRENT_PRICE)));
			}
			return list;
		} catch (SQLException e) {
			System.out.println("ActionTypeDao -> getAll(): " + e.getMessage());
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
	 * @param code
	 * @param label
	 * @param introductionDate
	 * @param introductionPrice
	 * @param quantity
	 * @param currentPrice
	 */
	public void insert(String code, String label, String introductionDate,
			float introductionPrice, int quantity, float currentPrice) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"insert into actiontype values (?,?,?,?,?,?)");
			stmt.setString(1, code);
			stmt.setString(2, label);
			stmt.setString(3, introductionDate);
			stmt.setFloat(4, introductionPrice);
			stmt.setInt(5, quantity);
			stmt.setFloat(6, currentPrice);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ActionTypeDao -> insert(): " + e.getMessage());
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
	 * @param code
	 * @param quantity
	 */
	public void updateQuantity(String code, int quantity) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"update actiontype set " + _COL_QUANTITY
									+ " = ? where " + _COL_CODE + " = ?");
			stmt.setInt(1, quantity);
			stmt.setString(2, code);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ActionTypeDao -> update(): " + e.getMessage());
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
	 * @param code
	 * @param currentPrice
	 */
	public void updateCurrentPrice(String code, float currentPrice) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement(
							"update actiontype set " + _COL_CURRENT_PRICE
									+ " = ? where " + _COL_CODE + " = ?");
			stmt.setFloat(1, currentPrice);
			stmt.setString(2, code);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ActionTypeDao -> update(): " + e.getMessage());
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