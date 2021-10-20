package main;

import java.sql.Date;
import java.sql.Time;

public class Empwages {
  protected Date txndate;
  protected String labour_shift;
  protected String name;
  protected String sgname;
  protected Float mth_salary;
  protected String attendance;
  protected Float crdamount;
  protected Float dbamount;
  protected Float ch_crdamount;
  protected Float ch_dbamount;
  protected Float amtinhand;
  protected Float pastdue;
  protected Float act_salary;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  protected String newentry;
  protected Date psdate;
  protected Date pedate;
  public Empwages() {	  
  }
  
  public Empwages(Date txndate, String labour_shift, String name) {
	  this.txndate = txndate; 
	  this.labour_shift = labour_shift;
	  this.name = name;
  }

  public Empwages( Date txndate, String labour_shift, String name, Float crdamount, Float dbamount,Float ch_crdamount, Float ch_dbamount, Float amtinhand, Float  pastdue, Float act_salary, String changedby, Date changeddate, Time changedtime) {	  
      this(crdamount, dbamount,ch_crdamount, ch_dbamount,amtinhand, pastdue, act_salary, changedby, changeddate, changedtime);
      this.txndate = txndate; 
	  this.labour_shift = labour_shift;
	  this.name = name;
  }

  public Empwages(Float crdamount, Float dbamount, Float ch_crdamount, Float ch_dbamount, Float amtinhand, Float pastdue, Float act_salary, String changedby, Date changeddate, Time changedtime) {
	  this.crdamount = crdamount;
	  this.dbamount = dbamount;
	  this.ch_crdamount = ch_crdamount;
	  this.ch_dbamount = ch_dbamount;
	  this.amtinhand = amtinhand;
	  this.pastdue = pastdue;
	  this.act_salary = act_salary;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
}
  public Empwages(String name, String sgname, Float mth_salary, String attendance, Float crdamount, Float dbamount, Float ch_crdamount, Float ch_dbamount, Float amtinhand, Float act_salary) {
	  this.name = name;
	  this.sgname = sgname;
	  this.mth_salary = mth_salary;
	  this.attendance = attendance;
	  this.crdamount = crdamount;
	  this.dbamount = dbamount;
	  this.ch_crdamount = ch_crdamount;
	  this.ch_dbamount = ch_dbamount;
	  this.amtinhand = amtinhand;
	  this.act_salary = act_salary;
}
  public Empwages(String name, String sgname, Float mth_salary, String attendance, Float crdamount, Float dbamount, Float amtinhand, Float act_salary) {
	  this.name = name;
	  this.sgname = sgname;
	  this.mth_salary = mth_salary;
	  this.attendance = attendance;
	  this.crdamount = crdamount;
	  this.dbamount = dbamount;
	  this.amtinhand = amtinhand;
	  this.act_salary = act_salary;	  
}  
  public Empwages(String name, String sgname, Float mth_salary, String attendance, Float crdamount, Float dbamount, Float act_salary) {
	  this.name = name;
	  this.sgname = sgname;
	  this.mth_salary = mth_salary;
	  this.attendance = attendance;
	  this.crdamount = crdamount;
	  this.dbamount = dbamount;
	  this.act_salary = act_salary;	  
}    
  public Empwages(String name, String attendance, Float crdamount, Float dbamount, Float ch_crdamount, Float ch_dbamount, Float pastdue, Float act_salary) {
	  this.name = name;
	  this.attendance = attendance;
	  this.crdamount = crdamount;
	  this.dbamount = dbamount;
	  this.ch_crdamount = ch_crdamount;
	  this.ch_dbamount = ch_dbamount;
	  this.pastdue = pastdue;
	  this.act_salary = act_salary;
}
public Date getTxndate() {
	return txndate;
}

public void setTxndate(Date txndate) {
	this.txndate = txndate;
}

public String getLabour_shift() {
	return labour_shift;
}

public void setLabour_shift(String labour_shift) {
	this.labour_shift = labour_shift;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getSgname() {
	return sgname;
}

public void setSgname(String sgname) {
	this.sgname = sgname;
}

public Float getMth_salary() {
	return mth_salary;
}

public void setMth_salary(Float mth_salary) {
	this.mth_salary = mth_salary;
}

public String getAttendance() {
	return attendance;
}

public void setAttendance(String attendance) {
	this.attendance = attendance;
}

public Float getCrdamount() {
	return crdamount;
}

public void setCrdamount(Float crdamount) {
	this.crdamount = crdamount;
}

public Float getDbamount() {
	return dbamount;
}

public void setDbamount(Float dbamount) {
	this.dbamount = dbamount;
}
public Float getCh_crdamount() {
	return ch_crdamount;
}

public void setCh_crdamount(Float ch_crdamount) {
	this.ch_crdamount = ch_crdamount;
}

public Float getCh_dbamount() {
	return ch_dbamount;
}

public void setCh_dbamount(Float ch_dbamount) {
	this.ch_dbamount = ch_dbamount;
}
public Float getAmtinhand() {
	return amtinhand;
}

public void setAmtinhand(Float amtinhand) {
	this.amtinhand = amtinhand;
}
public Float getPastdue() {
	return pastdue;
}

public void setPastdue(Float pastdue) {
	this.pastdue = pastdue;
}

public Float getAct_salary() {
	return act_salary;
}

public void setAct_salary(Float act_salary) {
	this.act_salary = act_salary;
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
public String getNewentry() {
	return newentry;
}

public void setNewentry(String newentry) {
	this.newentry = newentry;
}
public Date getPsdate() {
	return psdate;
}

public void setPsdate(Date psdate) {
	this.psdate = psdate;
}
public Date getPedate() {
	return pedate;
}

public void setPedate(Date pedate) {
	this.pedate = pedate;
}
}
