package model;

/**
 * UserテーブルのBeans
 * @author 31510007-PC
 *
 */
public class User {
	private int id;
	private String loginId;
	private String name;
	private String birthDate;
	private String password;
	private String createDate;
	private String updateDate;

	// ログインセッションを保存
	public User (String loginId, String name) {
		this.loginId = loginId;
		this.name = name;
	}

	// 全てのユーザ情報を保存
	public User (int id, String loginId, String name, String birthDate, String password, String createDate, String updateDate) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthDate;
		this.password = password;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	// ログインID比較用
	public User (String loginId) {
		this.loginId = loginId;
	}

	// 指定したidのユーザ情報の1行レコードを保存
	public User (String loginId, String name, String birthDate, String password, String createDate, String updateDate) {
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthDate;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
}
