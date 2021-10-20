package main;

import java.sql.Date;
import java.sql.Time;

public class Billpurpay {
	
  protected int bill_no;
  protected Date bill_date;
  protected String bill_prdgrp;
  protected Float bill_amt;
  protected Float discount	;
  protected Float interest	;
  protected Float paymnt_amt;
  protected Date paymnt_date;
  protected String paid;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  protected Float amtinhand;
  protected Date txndate;
  public Billpurpay() {	  
  }
  
  public Billpurpay(int bill_no, Date bill_date, String bill_prdgrp, Float bill_amt, Float discount, Float interest,	  
		  Float paymnt_amt, Date paymnt_date, String paid, String changedby, Date changeddate, Time changedtime) {	  
      this(bill_amt, discount, interest, paymnt_amt, paymnt_date, paid, changedby, changeddate, changedtime);
      this.bill_no = bill_no;   
      this.bill_date = bill_date;
      this.bill_prdgrp = bill_prdgrp;
  }
  
  public Billpurpay(Float bill_amt, Float discount, Float interest,	  
		  Float paymnt_amt, Date paymnt_date, String paid, String changedby, Date changeddate, Time changedtime) {
	  this.bill_amt= bill_amt;
	  this.discount = discount;
	  this.interest = interest;
	  this.paymnt_amt = paymnt_amt;
	  this.paymnt_date = paymnt_date;
	  this.paid = paid;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
}
  
  public Billpurpay(int bill_no, Date bill_date, String bill_prdgrp, Float bill_amt, Float discount, Float interest,	  
		  Float paymnt_amt, Date paymnt_date, String paid) {	  
	  this.bill_no = bill_no;   
      this.bill_date = bill_date;
      this.bill_prdgrp = bill_prdgrp;
      this.bill_amt= bill_amt;
	  this.discount = discount;
	  this.interest = interest;
	  this.paymnt_amt = paymnt_amt;
	  this.paymnt_date = paymnt_date;
	  this.paid = paid;
  }

public int getBill_no() {
	return bill_no;
}

public void setBill_no(int bill_no) {
	this.bill_no = bill_no;
}

public Date getBill_date() {
	return bill_date;
}

public void setBill_date(Date bill_date) {
	this.bill_date = bill_date;
}

public String getBill_prdgrp() {
	return bill_prdgrp;
}

public void setBill_prdgrp(String bill_prdgrp) {
	this.bill_prdgrp = bill_prdgrp;
}

public Float getBill_amt() {
	return bill_amt;
}

public void setBill_amt(Float bill_amt) {
	this.bill_amt = bill_amt;
}

public Float getDiscount() {
	return discount;
}

public void setDiscount(Float discount) {
	this.discount = discount;
}

public Float getInterest() {
	return interest;
}

public void setInterest(Float interest) {
	this.interest = interest;
}

public Float getPaymnt_amt() {
	return paymnt_amt;
}

public void setPaymnt_amt(Float paymnt_amt) {
	this.paymnt_amt = paymnt_amt;
}

public Date getPaymnt_date() {
	return paymnt_date;
}

public void setPaymnt_date(Date paymnt_date) {
	this.paymnt_date = paymnt_date;
}

public String getPaid() {
	return paid;
}

public void setPaid(String paid) {
	this.paid = paid;
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

public Float getAmtinhand() {
	return amtinhand;
}

public void setAmtinhand(Float amtinhand) {
	this.amtinhand = amtinhand;
}

public Date getTxndate() {
	return txndate;
}

public void setTxndate(Date txndate) {
	this.txndate = txndate;
}

}
