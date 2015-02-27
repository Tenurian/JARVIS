package mainmenu.favsites;

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

@SuppressWarnings("serial")
public class HotlinkManager implements Serializable {
	private ArrayList<Hotlink> linkList = new ArrayList<Hotlink>();
	private static byte[] keyValue = "SecretKe".getBytes();
	
	public void saveHotlinkManager(String fileName) {
		try {
			Key key = new SecretKeySpec(keyValue, "DES");
			Cipher ecipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			SealedObject sealed = new SealedObject(this, ecipher);
			FileOutputStream file = new FileOutputStream(fileName + ".sav");
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
	
	public static HotlinkManager loadHotlinkManager(String fileName) {
		HotlinkManager manager = new HotlinkManager();
		try {
			Key key  = new SecretKeySpec(keyValue, "DES");
			Cipher dcipher = Cipher.getInstance("DES");
			dcipher.init(Cipher.DECRYPT_MODE, key);
			FileInputStream file = new FileInputStream(fileName + ".sav");
			ObjectInputStream in = new ObjectInputStream(file);
			SealedObject sealed = (SealedObject) in.readObject();
			manager = (HotlinkManager) sealed.getObject(dcipher);
			in.close();
			file.close();
		} catch(IOException i) {
			System.out.println("No file found!");
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

	public ArrayList<Hotlink> getLinkList() {
		return linkList;
	}
}
