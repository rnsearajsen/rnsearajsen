package main;

import java.sql.Date;
import java.sql.Time;

public class Overall {
  protected String product;
  protected Float sales_qty;
  protected Float totsales_price;
  protected String subgrp;
  protected String txndet;
  protected Float sumamt;
  protected Float amtinhand;
  protected Date txndate;
  protected String labour_shift;
  protected Float aggramt;
  protected Float collection_amt;
  protected Float difference;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  public Overall() {	  
  }
  
  public Overall(String product, Float sales_qty, Float totsales_price, String subgrp, String txndet, Float sumamt, Float amtinhand, Float collection_amt, Float difference) {
	  this.product = product; 
	  this.sales_qty = sales_qty;
	  this.totsales_price = totsales_price;
	  this.subgrp = subgrp;
	  this.txndet = txndet;
	  this.sumamt = sumamt;
	  this.amtinhand = amtinhand;
	  this.collection_amt = collection_amt;
	  this.difference = difference;
  }
  public Overall(Date txndate, String labour_shift, Float aggramt, Float collection_amt, Float difference, String changedby, Date changeddate, Time changedtime) {
	  this.txndate = txndate;
	  this.labour_shift = labour_shift;
	  this.aggramt = aggramt;
	  this.collection_amt = collection_amt;
	  this.difference = difference;
	  this.changedby = changedby;
	  this.changeddate = changeddate;
	  this.changedtime = changedtime;
  }
public String getProduct() {
	return product;
}

public void setProduct(String product) {
	this.product = product;
}

public Float getSales_qty() {
	return sales_qty;
}

public void setSales_qty(Float sales_qty) {
	this.sales_qty = sales_qty;
}

public Float getTotsales_price() {
	return totsales_price;
}

public void setTotsales_price(Float totsales_price) {
	this.totsales_price = totsales_price;
}

public String getSubgrp() {
	return subgrp;
}

public void setSubgrp(String subgrp) {
	this.subgrp = subgrp;
}

public String getTxndet() {
	return txndet;
}

public void setTxndet(String txndet) {
	this.txndet = txndet;
}

public Float getSumamt() {
	return sumamt;
}

public void setSumamt(Float sumamt) {
	this.sumamt = sumamt;
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

public Float getAggramt() {
	return aggramt;
}

public void setAggramt(Float aggramt) {
	this.aggramt = aggramt;
}

public Float getCollection_amt() {
	return collection_amt;
}

public void setCollection_amt(Float collection_amt) {
	this.collection_amt = collection_amt;
}

public Float getDifference() {
	return difference;
}

public void setDifference(Float difference) {
	this.difference = difference;
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
