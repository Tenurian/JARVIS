package mainmenu.exebar;

import java.io.File;
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
public class ShortcutManager implements Serializable {
	private ArrayList<Shortcut> shortlist = new ArrayList<Shortcut>();
	private static byte[] keyValue2 = "SecretKe".getBytes();
	private static final String DOCSPATH2 = ((System.getProperty("user.home") + "/Documents/").replace("\\", "/"));
	
	public void saveShortcutManager(String fileName){
		try{
			Key key = new SecretKeySpec(keyValue2, "DES");
			Cipher ecipher = Cipher.getInstance("DES");
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			SealedObject sealed = new SealedObject(this, ecipher);
			new File(DOCSPATH2 + "JARVIS/").mkdir();
			FileOutputStream file = new FileOutputStream(DOCSPATH2 + "JARVIS/" + fileName +".sct");
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
	
	public static ShortcutManager loadShortcutManager(String filename){
		ShortcutManager manager = new ShortcutManager();
		try{
			Key key = new SecretKeySpec(keyValue2, "DES");
			Cipher dcipher = Cipher.getInstance("DES");
			dcipher.init(Cipher.DECRYPT_MODE, key);
			new File(DOCSPATH2 + "JARVIS/").mkdir();
			FileInputStream file = new FileInputStream(DOCSPATH2 + "JARVIS/" + filename + ".sct");
			ObjectInputStream in = new ObjectInputStream(file);
			SealedObject sealed = (SealedObject) in.readObject();
			manager = (ShortcutManager) sealed.getObject(dcipher);
			in.close();
			file.close();
		} catch(IOException i) {
			//System.out.println("No file found!");
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
	
	public ArrayList<Shortcut> getShortList(){
		return shortlist;
	}
}
