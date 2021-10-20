package main;

import java.sql.Date;
import java.sql.Time;

public class Crdcust {
  protected Date txndate;
  protected String labour_shift;
  protected String name;
  protected Float totcredit;
  protected Float crdamount;
  protected Float dbamount;
  protected String mop;
  protected Float ch_crdamount;
  protected Float ch_dbamount;
  protected Float amtinhand;
  protected String comments;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  protected String newentry;
  protected Date psdate;
  protected Date pedate;
  public Crdcust() {	  
  }
  
  public Crdcust(Date txndate, String labour_shift, String name) {
	  this.txndate = txndate; 
	  this.labour_shift = labour_shift;
	  this.name = name;
  }

  public Crdcust( Date txndate, String labour_shift, String name, String cash_handle, Float crdamount, Float dbamount, String  mop, Float ch_crdamount, Float ch_dbamount, Float amtinhand, String comments, String changedby, Date changeddate, Time changedtime) {	  
      this(crdamount, dbamount, mop, ch_crdamount, ch_dbamount, amtinhand, comments, changedby, changeddate, changedtime);
      this.txndate = txndate; 
	  this.labour_shift = labour_shift;
	  this.name = name;
  }

  public Crdcust(Float crdamount, Float dbamount, String mop, Float ch_crdamount, Float ch_dbamount, Float amtinhand, String comments, String changedby, Date changeddate, Time changedtime) {
	  this.crdamount = crdamount;
	  this.dbamount = dbamount;
	  this.mop = mop;
	  this.ch_crdamount = ch_crdamount;
	  this.ch_dbamount = ch_dbamount;
	  this.amtinhand = amtinhand;
	  this.comments = comments;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
}
  public Crdcust(String name, Float crdamount, String mop, String comments) {
	  this.name = name;
	  this.crdamount = crdamount;
	  this.mop = mop;
	  this.comments = comments;
}

  public Crdcust(String name, Float totcredit, Float crdamount, Float dbamount, String mop, Float ch_crdamount, Float ch_dbamount, Float amtinhand, String comments) {
	  this.name = name;
	  this.totcredit = totcredit;
	  this.crdamount = crdamount;
	  this.dbamount = dbamount;
	  this.mop = mop;
	  this.ch_crdamount = ch_crdamount;
	  this.ch_dbamount = ch_dbamount;
	  this.amtinhand = amtinhand;
	  this.comments = comments;
}
  public Crdcust(String name, Float totcredit, Float crdamount, Float dbamount, String mop, Float amtinhand, String comments) {
	  this.name = name;
	  this.totcredit = totcredit;
	  this.crdamount = crdamount;
	  this.dbamount = dbamount;
	  this.mop = mop;
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

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Float getTotcredit() {
	return totcredit;
}

public void setTotcredit(Float totcredit) {
	this.totcredit = totcredit;
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

public String getMop() {
	return mop;
}

public void setMop(String mop) {
	this.mop = mop;
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
