package accountmanager.accounts;

import java.io.Serializable;
/**
 * The Parent Account type, requires a child class to be instantized
 * @author Brian Vega-Rivera
 *
 */
public abstract class Account implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8146050992327444420L;
	protected String username, password, actType, desc, url = null;
	
	/**
	 * Constructor requiring the child classes to input a Username, Password, Account Type, and Description
	 * @param username The Username attributed to this account
	 * @param passworld	The Password attributed to this account
	 * @param actType The Account Type attributed to this account
	 * @param desc The Description attributed to this account
	 */
	public Account(String username, String passworld, String actType, String desc){
		this.username = username;
		this.password = passworld;
		this.actType = actType;
		this.desc = desc;
	}

	/**
	 * Returns the username of this account
	 * @return username The username of this account
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Sets the username of this account
	 * @param username The String to be set as the username
	 */
	protected void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Returns the password of this account
	 * @return password The password of this account
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Sets the password of this account
	 * @param password The String to be set as the password
	 */
	protected void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Returns the Account Type of this account
	 * @return actType The Account Type of this account
	 */
	public String getActType() {
		return actType;
	}
	/**
	 * Sets the Account Type of this account
	 * @param actType The String to be set as the Account Type. If the Account Type is Other, this is filled out by the user.
	 */
	protected void setActType(String actType) {
		this.actType = actType;
	}
	/**
	 * Returns the Description of this account
	 * @return desc The Description of this account
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * Sets the description of this account
	 * @param desc The String to be set as the Description
	 */
	protected void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void setURL(String url){
		this.url = url;
	}
	
	public String getURL(){
		return this.url;
	}
}