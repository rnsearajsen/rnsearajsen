package main;

public class Company {
  protected int company_id;
  protected String name;
  protected String suffix;
  protected String address;
  protected String mail;
  protected String contact1;
  protected String contact2;
  
  public Company() {	  
  }
  
  public Company(int company_id) {
	  this.company_id = company_id; }

  public Company(int company_id, String name, String suffix, String address, String mail, String contact1, String contact2) {	  
      this(name, suffix, address, mail, contact1, contact2);
      this.company_id = company_id;     
  }
  public Company(String name, String suffix, String address, String mail, String contact1, String contact2) {
	  this.name = name;
	  this.suffix = suffix;
	  this.address = address;
	  this.mail = mail;
	  this.contact1 = contact1;
	  this.contact2 = contact2;
  }
// company_id Get & Set
  public int getId() {
      return company_id;
  }
  public void setId(int company_id) {
	  this.company_id = company_id;
  }
// name Get & Set
  public String getName() {
	  return name;
  }	  
  public void setName(String name) {
	  this.name = name;
  }
//suffix Get & Set
 public String getSuffix() {
	  return suffix;
 }	  
 public void setSuffix(String suffix) {
	  this.suffix = suffix;
 }
//address Get & Set
public String getAddress() {
	  return address;
}	  
public void setAddress(String address) {
	  this.address = address;
}
//mail Get & Set
public String getMail() {
	  return mail;
}	  
public void setMail(String mail) {
	  this.mail = mail;
}  
// contact1 Get & Set
public String getContact1() {
	  return contact1;
}	  
public void setContact1(String contact1) {
	  this.contact1 = contact1;
}  
//contact2 Get & Set
public String getContact2() {
	  return contact2;
}	  
public void setContact2(String contact2) {
	  this.contact2 = contact2;
}  

}
