package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * UserテーブルのDao
 * @author 31510007-PC
 *
 */

public class UserDao {

	/**
	 * ログインIDとパスワードに該当するユーザ情報を取得
	 * @param loginId
	 * @param password
	 * @return
	 */
	public User findByLoginInfo(String loginId, String password) {
		Connection conn = null;
		try {
			// DBに接続してSQL文を実行
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, password);
			ResultSet rs = pStmt.executeQuery();

			// 検索結果は1件あるかないかなので、while文ではなくif文を使っている。
			if (!rs.next()) {
				return null;
			}
			// フィールドを取得
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			return new User(loginIdData, nameData);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	/**
	 * 全てのユーザ情報を取得
	 */
	public List<User> findAll() {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user";

			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				String birthDate = rs.getString("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	// ログインIDの取得
	public User findByLoginId(String loginId) {
		Connection conn = null;
		try {
			// DBに接続してSQL文を実行
			conn = DBManager.getConnection();
			String sql = "SELECT login_id = ? FROM user";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			// フィールドを取得
			String loginIdData = rs.getString("login_id");
			return new User(loginIdData);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}

	// ユーザ情報詳細参照に表示するユーザ情報を取得
	public User findByUserInfo(String id) {
		Connection conn = null;
		try {
			// DBに接続してSQL文を実行
			conn = DBManager.getConnection();
			String sql = "SELECT login_id, name, birth_date, create_date, update_date FROM user WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			ResultSet rs = pStmt.executeQuery();


			if (rs.next()) {
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				String birthDate = rs.getString("birth_date");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				return new User(loginId, name, birthDate, createDate, updateDate);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
}
