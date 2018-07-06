package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;

/**
 * UserテーブルのDao
 * @author 31510007-PC
 *
 */

public class UserDao {

	/**
	 * ログインセッション
	 * @param loginId
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public User findByLoginInfo(String loginId, String password) throws NoSuchAlgorithmException {

		// パスワード暗号化
				String source = password;
				Charset charset = StandardCharsets.UTF_8;
				String algorithm = "MD5";

				byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
				String encryptedPassword = DatatypeConverter.printHexBinary(bytes);

		Connection conn = null;
		try {
			// DBに接続してSQL文を実行
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, encryptedPassword);
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
	 * Userテーブルの全てのユーザ情報を取得
	 */
	public List<User> findAll() {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user where login_id not in ('admin')";

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

	// ユーザ情報の検索
	public List<User> findSearch(String loginId, String name, String MinBirthDate, String MaxBirthDate) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user where login_id not in ('admin')";

			if(!loginId.equals("")) {
				sql += " AND login_id = '" + loginId + "'";
			}

			if(!name.equals("")) {
				sql += " AND name LIKE '%" + name + "%'";
			}

			if (!MinBirthDate.equals("")) {
				sql += " AND birth_date >= '" + MinBirthDate + "'";
			}

			if (!MaxBirthDate.equals("")) {
				sql += " AND birth_date <= '" + MaxBirthDate + "'";
			}

			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("id");
				String getLoginId = rs.getString("login_id");
				String getName = rs.getString("name");
				String birthDate = rs.getString("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				User user = new User(id, getLoginId, getName, birthDate, password, createDate, updateDate);
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
			String sql = "SELECT login_id from user where login_id = ?";
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


	// 指定したidの1行レコードを取得
	public User findByUserInfo(String id) {
		Connection conn = null;
		try {
			// DBに接続してSQL文を実行
			conn = DBManager.getConnection();
			String sql = "SELECT * FROM user WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, id);
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				int getId = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				String birthDate = rs.getString("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				return new User(getId, loginId, name, birthDate, password, createDate, updateDate);
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

	// ユーザ新規登録
	public void InsertUserInfo(String loginId, String name, String birthDate, String password) throws NoSuchAlgorithmException {

		// パスワード暗号化
		String source = password;
		Charset charset = StandardCharsets.UTF_8;
		String algorithm = "MD5";

		byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
		String encryptedPassword = DatatypeConverter.printHexBinary(bytes);


		// SQL実行
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO user(login_id, name, birth_date, password, create_date, update_date) VALUES(?, ?, ?, ?, now(), now());";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.setString(2, name);
			pStmt.setString(3, birthDate);
			pStmt.setString(4, encryptedPassword);

			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// レコードの削除
	public void DeleteUserInfo(String loginId) {

		// SQL実行
		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM user WHERE login_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ユーザ情報更新（パスワードの更新あり）
	public void UpdateUserInfo(String loginId, String password, String name, String birthDate) throws NoSuchAlgorithmException {

		// パスワード暗号化
		String source = password;
		Charset charset = StandardCharsets.UTF_8;
		String algorithm = "MD5";
		byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
		String encryptedPassword = DatatypeConverter.printHexBinary(bytes);

		Connection conn = null;
		try {
			conn = DBManager.getConnection();
			String sql = "UPDATE user SET name = ?, password = ?, birth_date = ?, update_date = now() WHERE login_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setString(2, encryptedPassword);
			pStmt.setString(3, birthDate);
			pStmt.setString(4, loginId);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ユーザ情報更新（パスワードの更新なし）
		public void UpdateNonPasswordUserInfo(String loginId, String name, String birthDate) throws NoSuchAlgorithmException {

			Connection conn = null;
			try {
				conn = DBManager.getConnection();
				String sql = "UPDATE user SET name = ?, birth_date = ?, update_date = now() WHERE login_id = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, name);
				pStmt.setString(2, birthDate);
				pStmt.setString(3, loginId);
				pStmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
}
