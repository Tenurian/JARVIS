package accountmanager.accounts;

/**
 * 
 * @author Brian Vega-Rivera
 *
 */
public class Store extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4379799383880272715L;

	/**
	 * Inherited Constructor from Account
	 * @param username The Username attributed to this account
	 * @param passworld The Password attributed to this account
	 * @param actType The Account Type attributed to this account
	 * @param desc The Description attributed to this account
	 */
	public Store(String username, String passworld, String actType, String desc) {
		super(username, passworld, actType, desc);
	}
}