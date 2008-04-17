package isimarket.db.dao;

import isimarket.db.manager.DatabaseManager;
import isimarket.model.Wallet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletDao {
	
	// colonnes
	private static final String _COL_CASH = "cash";
	
	private static final String _COL_WALLET_ID = "wallet_id";

	public void delete(int _id) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("delete from wallet where "+_COL_WALLET_ID+"=?");
			stmt.setInt(1, _id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("wallet -> delete(): "+ e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
			}
		}
	}

	public Wallet get(int _walletId) {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("select * from wallet where "+_COL_WALLET_ID+"=?");
			stmt.setLong(1, _walletId);
			res = stmt.executeQuery();
			if (res.next()) {
				return new Wallet(res.getInt(_COL_WALLET_ID), 
						res.getFloat(_COL_CASH));
			} else {
				System.out.println("Wallet inconnu : " + _walletId);
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Wallet -> get(): "+ e.getMessage());
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
	
	public Wallet getLastInsertedWallet() {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("select * from wallet where "+_COL_WALLET_ID+" = select max("+_COL_WALLET_ID+") from wallet");
			res = stmt.executeQuery();
			if (res.next()) {
				
				System.out.println("walletdao ok !");
				
				return new Wallet(res.getInt(_COL_WALLET_ID), 
						res.getFloat(_COL_CASH));
			} else {
				System.out.println("Wallet inconnu ");
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Wallet -> get(): "+ e.getMessage());
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

//	public List<Wallet> getAll() {
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

	public void insert(Wallet _wallet) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("insert into wallet values (NULL,?)");
			stmt.setFloat(1, _wallet.cash);
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

	public void updateCash(Wallet _wallet) {
		PreparedStatement stmt = null;
		try {
			stmt = DatabaseManager.getInstance().getConnection()
					.prepareStatement("update wallet set "+_COL_CASH+" = ? where "+_COL_WALLET_ID+"= ?");
			stmt.setFloat(1, _wallet.cash);
			stmt.setInt(2, _wallet.walletId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("WalletDao -> update(): "+ e.getMessage());
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