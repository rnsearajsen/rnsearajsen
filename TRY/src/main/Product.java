package main;

import java.sql.Date;
import java.sql.Time;
//import java.util.Timer;
//import java.util.Date;

//import java.math.BigInteger;

public class Product {
  protected int product_id;
  protected String name;
  protected String group_name;
  protected String subgroup_name;
  protected String uom;
  protected String comments;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  protected Date createddate;
  
  public Product() {	  
  }
  
  public Product(int product_id) {
	  this.product_id = product_id; }

  public Product(int product_id, String name, String group_name, String subgroup_name, String uom,		  
		  String comments, String changedby, Date changeddate, Time changedtime, Date createddate) {	  
      this(name, group_name, subgroup_name, uom, comments, changedby, changeddate, changedtime, createddate);
      this.product_id = product_id;     
  }
  public Product(String name, String group_name, String subgroup_name, String uom, 
		      String comments, String changedby, Date changeddate, Time changedtime, Date createddate) {
	  this.name = name;
	  this.group_name = group_name;
	  this.subgroup_name = subgroup_name;
	  this.uom = uom;
	  this.comments = comments;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
      this.createddate = createddate;
}

// product_id Get & Set
  public int getId() {
      return product_id;
  }
  public void setId(int product_id) {
	  this.product_id = product_id;
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

public String getUom() {
	return uom;
}

public void setUom(String uom) {
	this.uom = uom;
}

public String getComments() {
	return comments;
}

public void setComments(String comments) {
	this.comments = comments;
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

}
