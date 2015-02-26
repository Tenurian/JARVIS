package contactbook;

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

public class ContactBookProfile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5198045761253045356L;
	private ArrayList<Contact> contacts = new ArrayList<Contact>();

	private static byte[] keyValue = "SecretKe".getBytes();
	private String PATH = null;
	private String password;
	
	public ArrayList<Contact> getAccountList(){
		return contacts;
	}

	public ArrayList<Contact> getContactList() {
		return contacts;
	}
	
	public void saveContactProfile(){
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
	
	public void saveContactProfile(String fileName) {
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

	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public static ContactBookProfile loadAccountManager(String fileName, String password) {
		ContactBookProfile manager = new ContactBookProfile();
		try {
			Key key  = new SecretKeySpec(keyValue, "DES");
			Cipher dcipher = Cipher.getInstance("DES");
			dcipher.init(Cipher.DECRYPT_MODE, key);


			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);
			SealedObject sealed = (SealedObject) in.readObject();

			manager = (ContactBookProfile) sealed.getObject(dcipher);

			if(password != null && manager.getPassword()!=null){
				if(!manager.getPassword().equals(password)) {
					manager = new ContactBookProfile();
					JOptionPane.showMessageDialog(new JFrame(),"Incorrect Password","Incorrect Password",JOptionPane.ERROR_MESSAGE);
				}
			}else{
				if(!(manager.getPassword()==(null) && password==(null))){
					manager = new ContactBookProfile();
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
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return manager;
	}
}
