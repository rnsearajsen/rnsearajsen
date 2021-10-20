package main;

import java.sql.Date;
import java.sql.Time;
//import java.util.Timer;
//import java.util.Date;

//import java.math.BigInteger;

public class Employee {
  protected int employee_id;
  protected String name;
  protected String group_name;
  protected String gender;
  protected String subgroup_name;
  protected Double salary;
  protected String address;
  protected String contact1;
  protected String contact2;
  protected String idtype1;
  protected String idnum1;
  protected String comments;
  protected Date joindate;
  protected Date lastdate;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  
  public Employee() {	  
  }
  
  public Employee(int employee_id) {
	  this.employee_id = employee_id; }

  public Employee(int employee_id, String name, String group_name, String gender, String subgroup_name, Double salary,
		  String address, String contact1, String contact2, String idtype1, String idnum1,
		  String comments, Date joindate, Date lastdate, String changedby, Date changeddate, Time changedtime) {	  
      this(name, group_name, gender, subgroup_name, salary, address, contact1, contact2, idtype1,idnum1, 
    		  comments, joindate, lastdate, changedby, changeddate, changedtime );
      this.employee_id = employee_id;     
  }
  public Employee(String name, String group_name, String gender, String subgroup_name, Double salary,
		      String address, String contact1, String contact2,  String idtype1, String idnum1,
		      String comments, Date joindate, Date lastdate, String changedby, Date changeddate, Time changedtime) {
	  this.name = name;
	  this.group_name = group_name;
	  this.gender = gender;
	  this.subgroup_name = subgroup_name;
	  this.salary = salary;
	  this.address = address;
	  this.contact1 = contact1;
	  this.contact2 = contact2;
	  this.idtype1 = idtype1;
	  this.idnum1 = idnum1;
	  this.comments = comments;
	  this.joindate = joindate;
      this.lastdate = lastdate;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
}

// employee_id Get & Set
  public int getId() {
      return employee_id;
  }
  public void setId(int employee_id) {
	  this.employee_id = employee_id;
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
 
public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getSubgroup_name() {
	return subgroup_name;
}

public void setSubgroup_name(String subgroup_name) {
	this.subgroup_name = subgroup_name;
}

public Double getSalary() {
	return salary;
}

public void setSalary(Double salary) {
	this.salary = salary;
}

//address Get & Set
public String getAddress() {
	  return address;
}	  
public void setAddress(String address) {
	  this.address = address;
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

}
