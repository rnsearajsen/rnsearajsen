package main;

import java.sql.Date;
import java.sql.Time;
//import java.util.Timer;
//import java.util.Date;

//import java.math.BigInteger;

public class Bankacct {
  protected int bankacct_id;
  protected String name;
  protected String group_name;
  protected String subgroup_name;
  protected String bank	;
  protected String acctype	;
  protected String ifsc;
  protected String accountno;
  protected String comments;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  protected Date createddate;
  
  public Bankacct() {	  
  }
  
  public Bankacct(int bankacct_id) {
	  this.bankacct_id = bankacct_id; }

  public Bankacct(int bankacct_id, String name, String group_name, String subgroup_name, String bank, String acctype,	  
		  String ifsc, String accountno, String comments, String changedby, Date changeddate, Time changedtime, Date createddate) {	  
      this(name, group_name, subgroup_name, bank, acctype, ifsc, accountno, comments, changedby, changeddate, changedtime, createddate);
      this.bankacct_id = bankacct_id;     
  }
  
  public Bankacct(String name, String group_name, String subgroup_name, String bank, String acctype,	  
		  String ifsc, String accountno, String comments, String changedby, Date changeddate, Time changedtime, Date createddate) {
	  this.name = name;
	  this.group_name = group_name;
	  this.subgroup_name = subgroup_name;
	  this.bank = bank;
	  this.acctype = acctype;
	  this.ifsc = ifsc;
	  this.accountno = accountno;
	  this.comments = comments;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
      this.createddate = createddate;
}
  public Bankacct(int bankacct_id, String name, String subgroup_name) {
this(name, subgroup_name);
this.bankacct_id = bankacct_id;
}

  public Bankacct(String name, String subgroup_name) {
	  this.name = name;
	  this.subgroup_name = subgroup_name;	  
}
  public Bankacct(int bankacct_id, String name) {
this(name);
this.bankacct_id = bankacct_id;
}

  public Bankacct(String name) {
	  this.name = name;	  
}
  // bankacct_id Get & Set
  public int getId() {
      return bankacct_id;
  }
  public void setId(int bankacct_id) {
	  this.bankacct_id = bankacct_id;
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

public String getBank() {
	return bank;
}

public void setBank(String bank) {
	this.bank = bank;
}

public String getAcctype() {
	return acctype;
}

public void setAcctype(String acctype) {
	this.acctype = acctype;
}

public String getIfsc() {
	return ifsc;
}

public void setIfsc(String ifsc) {
	this.ifsc = ifsc;
}

public String getAccountno() {
	return accountno;
}

public void setAccountno(String accountno) {
	this.accountno = accountno;
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
