package accountmanager.fileio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import accountmanager.accounts.Account;
/**
 * Container class for the Accounts, FileIO and encryption when the user saves or loads different profiles
 * @author Richard "Jacob" Zamora
 *
 */
@SuppressWarnings("serial")
public class AccountManager implements Serializable{
	private ArrayList<Account> accountList = new ArrayList<Account>();
	private String password = null;
	private static byte[] keyValue = "SecretKe".getBytes();
	private String PATH = null;

	/**
	 * Saves and encrypts the AccountManager to the C:\USERS\USER\DOCUMENTS\ Folder as a .sav
	 * @param fileName the name of the file (no extension)
	 */
	public void saveAccountManager(String fileName) {
		this.PATH=fileName;
		try {
			Key key = new SecretKeySpec(keyValue, "DES");
			Cipher ecipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			SealedObject sealed = new SealedObject(this, ecipher);
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(sealed);
			out.close();
			file.close();
		} catch(IOException i) {
			i.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * Saves and encrypts the AccountManager to the C:\USERS\USER\DOCUMENTS\ Folder as a .sav with the current PATH and filename
	 */
	public void saveAccountManager(){
		if(PATH !=null){
			try {
				Key key = new SecretKeySpec(keyValue, "DES");
				Cipher ecipher = Cipher.getInstance("DES");
				ecipher.init(Cipher.ENCRYPT_MODE, key);
				SealedObject sealed = new SealedObject(this, ecipher);
				FileOutputStream file = new FileOutputStream(PATH);
				ObjectOutputStream out = new ObjectOutputStream(file);
				out.writeObject(sealed);
				out.close();
				file.close();
			} catch(IOException i) {
				i.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Reads in and decrypts the AccountManager from the selected file, then checks if the user enters the correct password.\n
	 * If the user inputs an incorrect password, it overrides the AccountManager with a new AccountManager with no entries
	 * @param fileName The PATH and filename for the file
	 * @param password	The input password to attempt and unlock the profile
	 * @return manager The account profile to be returned, new AccountManager if the password was incorrect
	 */
	public static AccountManager loadAccountManager(String fileName, String password) {
		AccountManager manager = new AccountManager();
		try {
			Key key  = new SecretKeySpec(keyValue, "DES");
			Cipher dcipher = Cipher.getInstance("DES");
			dcipher.init(Cipher.DECRYPT_MODE, key);


			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);
			SealedObject sealed = (SealedObject) in.readObject();


			manager = (AccountManager) sealed.getObject(dcipher);


			//			if(!manager.getPassword().equals(password)) {
			//				manager = new AccountManager();
			//			}

			if(password != null && manager.getPassword()!=null){
				if(!manager.getPassword().equals(password)) {
					manager = new AccountManager();
					JOptionPane.showMessageDialog(new JFrame(),"Incorrect Password","Incorrect Password",JOptionPane.ERROR_MESSAGE);
				}
			}else{
				if(!(manager.getPassword()==(null) && password==(null))){
					manager = new AccountManager();
				}
			}
			in.close();
			file.close();




		} catch(IOException i) {
			System.out.println("No file found!");
			i.printStackTrace();

		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manager;
	}

	/**
	 * Returns an ArrayList of Accounts to be used by the AccountManagerGUI to display the proper values in the tables.
	 * @return accountList The list of accounts in this profile.
	 */
	public ArrayList<Account> getAccountList() {
		return accountList;
	}

	/**
	 * Returns the String password for the current profile.
	 * @return password The password for this profile, used when the file is being loaded to protect data.
	 */
	protected String getPassword() {
		return password;
	}
	
	/**
	 * Used to set the password for a new profile.
	 * @param password The String value for the password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}