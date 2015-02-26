package accountmanager.accounts;

/**
 * 
 * @author Brian Vega-Rivera
 *
 */
public class Other extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4115109800891571859L;

	/**
	 * Inherited Constructor from Account
	 * @param username The Username attributed to this account
	 * @param passworld The Password attributed to this account
	 * @param actType The Account Type attributed to this account, filled out by user in AccountManagerGUI
	 * @param desc The Description attributed to this account
	 */
	public Other(String username, String passworld, String actType, String desc) {
		super(username, passworld, actType, desc);
	}
}