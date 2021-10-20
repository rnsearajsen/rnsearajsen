package main;

import java.sql.Date;
import java.sql.Time;

public class Collect {
  protected Date txndate;
  protected String labour_shift;
  protected String collect_type;
  protected Float collect_amt;
  protected Float amtinhand;
  protected String comments;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  
  public Collect() {	  
  }
  
  public Collect(Date txndate, String labour_shift, String collect_type) {
	  this.txndate = txndate; 
	  this.labour_shift = labour_shift;
	  this.collect_type = collect_type;
  }

  public Collect( Date txndate, String labour_shift, String collect_type, Float collect_amt, String comments, String changedby, Date changeddate, Time changedtime) {	  
      this(collect_amt, comments, changedby, changeddate, changedtime);
      this.txndate = txndate; 
	  this.labour_shift = labour_shift;
	  this.collect_type = collect_type;
  }
  public Collect(Float collect_amt, String comments, String changedby, Date changeddate, Time changedtime) {
	  this.collect_amt = collect_amt;
	  this.comments = comments;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
}
  public Collect(String collect_type, Float collect_amt, Float amtinhand, String comments) {
	  this.collect_type = collect_type;
	  this.collect_amt = collect_amt;
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

public String getCollect_type() {
	return collect_type;
}

public void setCollect_type(String collect_type) {
	this.collect_type = collect_type;
}

public Float getCollect_amt() {
	return collect_amt;
}

public void setCollect_amt(Float collect_amt) {
	this.collect_amt = collect_amt;
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
