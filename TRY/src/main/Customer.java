package main;

import java.sql.Date;
import java.sql.Time;
//import java.util.Timer;
//import java.util.Date;

//import java.math.BigInteger;

public class Customer {
  protected int customer_id;
  protected String name;
  protected String group_name;
  protected String subgroup_name;
  protected String address;
  protected String mail;
  protected String contact1;
  protected String contact2;
  protected String idtype1;
  protected String idnum1;
  protected String idtype2;
  protected String idnum2;
  protected String comments;
  protected Date joindate;
  protected Date lastdate;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;
  protected Date createddate;
  protected Time createdtime;
  
  
  public Customer() {	  
  }
  
  public Customer(int customer_id) {
	  this.customer_id = customer_id; }

  public Customer(int customer_id, String name, String group_name, String subgroup_name, 
		  String address, String mail, String contact1, String contact2, String idtype1, String idnum1,String idtype2, String idnum2,
		  String comments, Date joindate, Date lastdate, String changedby, Date changeddate, Time changedtime, Date createddate, Time createdtime) {	  
      this(name, group_name, subgroup_name, address, mail, contact1, contact2, idtype1,idnum1, idtype2, idnum2,
    		  comments, joindate, lastdate, changedby, changeddate, changedtime, createddate, createdtime );
      this.customer_id = customer_id;     
  }
  public Customer(String name, String group_name, String subgroup_name,
		      String address, String mail, String contact1, String contact2,  String idtype1, String idnum1,String idtype2, String idnum2,
		      String comments, Date joindate, Date lastdate, String changedby, Date changeddate, Time changedtime, Date createddate, Time createdtime) {
	  this.name = name;
	  this.group_name = group_name;
	  this.subgroup_name = subgroup_name;
	  this.address = address;
	  this.mail = mail;
	  this.contact1 = contact1;
	  this.contact2 = contact2;
	  this.idtype1 = idtype1;
	  this.idnum1 = idnum1;
	  this.idtype2 = idtype2;
	  this.idnum2 = idnum2;
	  this.comments = comments;
	  this.joindate = joindate;
      this.lastdate = lastdate;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
      this.createddate = createddate;
      this.createdtime = createdtime; 
  }

// customer_id Get & Set
  public int getId() {
      return customer_id;
  }
  public void setId(int customer_id) {
	  this.customer_id = customer_id;
  }
// name Get & Set
  public String getName() {
	  return name;
  }	  
  public void setName(String name) {
	  this.name = name;
  }
//group_name Get & Set
 public String getGroup_name() {
	  return group_name;
 }	  
 public void setGroup_name(String group_name) {
	  this.group_name = group_name;
 }
 
public String getSubgroup_name() {
	return subgroup_name;
}

public void setSubgroup_name(String subgroup_name) {
	this.subgroup_name = subgroup_name;
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

public String getIdtype1() {
	return idtype1;
}

public void setIdtype1(String idtype1) {
	this.idtype1 = idtype1;
}

public String getIdnum1() {
	return idnum1;
}

public void setIdnum1(String idnum1) {
	this.idnum1 = idnum1;
}

public String getIdtype2() {
	return idtype2;
}

public void setIdtype2(String idtype2) {
	this.idtype2 = idtype2;
}

public String getIdnum2() {
	return idnum2;
}

public void setIdnum2(String idnum2) {
	this.idnum2 = idnum2;
}

public String getComments() {
	return comments;
}

public void setComments(String comments) {
	this.comments = comments;
}

public Date getJoindate() {
	return joindate;
}

public void setJoindate(Date joindate) {
	this.joindate = joindate;
}

public Date getLastdate() {
	return lastdate;
}

public void setLastdate(Date lastdate) {
	this.lastdate = lastdate;
}

public String getChangedby() {
	return changedby;
}

public void setChangedby(String changedby) {
	this.changedby = changedby;
}

public Date getChangeddate() {
	return changeddate;
}

public void setChangeddate(Date changeddate) {
	this.changeddate = changeddate;
}

public Time getChangedtime() {
	return changedtime;
}

public void setChangedtime(Time changedtime) {
	this.changedtime = changedtime;
}

public Date getCreateddate() {
	return createddate;
}

public void setCreateddate(Date createddate) {
	this.createddate = createddate;
}

public Time getCreatedtime() {
	return createdtime;
}

public void setCreatedtime(Time createdtime) {
	this.createdtime = createdtime;
}

}
