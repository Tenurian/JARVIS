package contactbook;

import java.io.Serializable;

public class Contact implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5089136351577721492L;
	private String name, cellnumber, worknumber, personalemail, workemail, address;
	
	public Contact(String name){
		this.setName(name);
		setCellnumber("");
		setWorknumber("");
		setPersonalemail("");
		setWorkemail("");
		setAddress("");
	}

	public Contact(String name, String celln, String workn, String pemail, String wemail, String address) {
		this.name = name;
		this.cellnumber = celln;
		this.worknumber = workn;
		this.personalemail = pemail;
		this.workemail = wemail;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellnumber() {
		return cellnumber;
	}

	public void setCellnumber(String cellnumber) {
		this.cellnumber = cellnumber;
	}

	public String getWorknumber() {
		return worknumber;
	}

	public void setWorknumber(String worknumber) {
		this.worknumber = worknumber;
	}

	public String getPersonalemail() {
		return personalemail;
	}

	public void setPersonalemail(String personalemail) {
		this.personalemail = personalemail;
	}

	public String getWorkemail() {
		return workemail;
	}

	public void setWorkemail(String workemail) {
		this.workemail = workemail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}