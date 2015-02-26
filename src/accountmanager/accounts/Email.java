package accountmanager.accounts;

/**
 * The Account child class Email
 * @author Brian Vega-Rivera
 *
 */
public class Email extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3173671193927555697L;

	/**
	 * Inherited Constructor from Account
	 * @param username The Username attributed to this account
	 * @param passworld The Password attributed to this account
	 * @param actType The Account Type attributed to this account
	 * @param desc The Description attributed to this account
	 */
	public Email(String username, String passworld, String actType, String desc) {
		super(username, passworld, actType, desc);
	}
}