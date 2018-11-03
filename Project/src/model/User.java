package model;

import java.util.Date;

/**
 * Userテーブルのデータを格納するためのBeans
 * @author takano
 *
 */
public class User {
	private int id;
	private String loginId;
	private String name;
	private Date birthDate;
	private String password;
	private String createDate;
	private String updateDate;

	// ログインセッションを保存するためのコンストラクタ
	public User(int idData,String loginId, String name,Date birthData) {
		this.id =idData;
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthData;
	}

	// 全てのデータをセットするコンストラクタ
	public User(int id, String loginId, String name, Date birthDate, String password, String createDate,
			String updateDate) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthDate;
		this.password = password;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public User(String loginIdData, String nameData, Date birthData, String createData, String updateData, String passData){
		this.loginId = loginIdData;
		this.name = nameData;
		this.birthDate = birthData;
		this.createDate = createData;
		this.updateDate = updateData;
		this.password = passData;
	}

	public User(String loginId) {
		this.loginId = loginId;
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
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
