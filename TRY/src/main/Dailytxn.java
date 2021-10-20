package main;

import java.sql.Date;
import java.sql.Time;

public class Dailytxn {
  protected Date txndate;
  protected String labour_shift;
  protected String expense_type;
  protected Float expense_amt;
  protected Float chexp_amt;
  protected Float amtinhand;
  protected String comments;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  
  public Dailytxn() {	  
  }
  
  public Dailytxn(Date txndate, String labour_shift, String expense_type) {
	  this.txndate = txndate; 
	  this.labour_shift = labour_shift;
	  this.expense_type = expense_type;
  }

  public Dailytxn( Date txndate, String labour_shift, String expense_type, Float expense_amt, Float chexp_amt, String comments, String changedby, Date changeddate, Time changedtime) {	  
      this(expense_amt, chexp_amt, comments, changedby, changeddate, changedtime);
      this.txndate = txndate; 
	  this.labour_shift = labour_shift;
	  this.expense_type = expense_type;
  }
  public Dailytxn(Float expense_amt, Float chexp_amt, String comments, String changedby, Date changeddate, Time changedtime) {
	  this.expense_amt = expense_amt;
	  this.chexp_amt = chexp_amt;
	  this.comments = comments;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
}
  public Dailytxn(String expense_type, Float expense_amt, Float chexp_amt, Float amtinhand, String comments) {
	  this.expense_type = expense_type;
	  this.expense_amt = expense_amt;
	  this.chexp_amt = chexp_amt;
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

public String getExpense_type() {
	return expense_type;
}

public void setExpense_type(String expense_type) {
	this.expense_type = expense_type;
}

public Float getExpense_amt() {
	return expense_amt;
}

public void setExpense_amt(Float expense_amt) {
	this.expense_amt = expense_amt;
}
public Float getChexp_amt() {
	return chexp_amt;
}

public void setChexp_amt(Float chexp_amt) {
	this.chexp_amt = chexp_amt;
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
