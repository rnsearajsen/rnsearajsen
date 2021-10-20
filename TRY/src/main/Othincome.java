package main;

import java.sql.Date;
import java.sql.Time;

public class Othincome {
  protected Date txndate;
  protected String labour_shift;
  protected String inctype;
  protected Float dbamount;
  protected String mop;
  protected Float ch_dbamount;
  protected Float amtinhand;
  protected String comments;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  public Othincome() {	  
  }
  
  public Othincome(Date txndate, String labour_shift, String inctype) {
	  this.txndate = txndate; 
	  this.labour_shift = labour_shift;
	  this.inctype = inctype;
  }

  public Othincome( Date txndate, String labour_shift, String inctype, Float dbamount, String  mop, Float ch_dbamount, String comments, String changedby, Date changeddate, Time changedtime) {	  
      this(dbamount, mop, ch_dbamount, comments, changedby, changeddate, changedtime);
      this.txndate = txndate; 
	  this.labour_shift = labour_shift;
	  this.inctype = inctype;
  }

  public Othincome(Float dbamount, String mop, Float ch_dbamount, String comments, String changedby, Date changeddate, Time changedtime) {
	  this.dbamount = dbamount;
	  this.mop = mop;
	  this.ch_dbamount = ch_dbamount;
	  this.comments = comments;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
}

  public Othincome(String inctype,Float dbamount, String mop, Float ch_dbamount, Float amtinhand, String comments) {
	  this.inctype = inctype;
	  this.dbamount = dbamount;	  
	  this.mop = mop;
	  this.ch_dbamount = ch_dbamount;
	  this.amtinhand = amtinhand;
	  this.comments = comments;
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

public String getInctype() {
	return inctype;
}

public void setInctype(String inctype) {
	this.inctype = inctype;
}

public Float getDbamount() {
	return dbamount;
}

public void setDbamount(Float dbamount) {
	this.dbamount = dbamount;
}

public String getMop() {
	return mop;
}

public void setMop(String mop) {
	this.mop = mop;
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
}
