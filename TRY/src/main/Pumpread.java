package main;

import java.sql.Date;
import java.sql.Time;
//import java.util.Timer;
//import java.util.Date;

//import java.math.BigInteger;

public class Pumpread {
  protected String product;
  protected Date date;
  protected String pump_id;
  protected String labour_shift;
  protected Float sales_priceunit;
  protected Float pump_reading_open;
  protected Float pump_reading_close;
  protected Float sales_qty;
  protected Float testqty;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  
  public Pumpread() {	  
  }
  
  public Pumpread(String product, Date date, String pump_id, String labour_shift, Float sales_priceunit) {
	  this.product = product; 
	  this.date = date;
	  this.pump_id = pump_id;
	  this.labour_shift = labour_shift;
	  this.sales_priceunit = sales_priceunit;}

  public Pumpread( String product, Date date, String pump_id, String labour_shift, Float sales_priceunit, Float pump_reading_open, Float pump_reading_close,		  
		  Float sales_qty, Float testqty, String changedby, Date changeddate, Time changedtime) {	  
      this( pump_reading_open ,pump_reading_close, sales_qty, testqty, changedby, changeddate, changedtime);
      this.product = product;
      this.date = date;
	  this.pump_id = pump_id;
	  this.labour_shift = labour_shift;
	  this.sales_priceunit = sales_priceunit;
  }
  public Pumpread(Float pump_reading_open, Float pump_reading_close, Float sales_qty, Float testqty, 
		  String changedby, Date changeddate, Time changedtime) {
	  this.pump_reading_open = pump_reading_open;
	  this.pump_reading_close = pump_reading_close;
	  this.sales_qty = sales_qty;
	  this.testqty = testqty;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
}
  public Pumpread( String product, Date date, String pump_id, String labour_shift, Float sales_priceunit, Float pump_reading_open, Float pump_reading_close,		  
		  Float sales_qty, String changedby, Date changeddate, Time changedtime) {	  
      this.product = product;
      this.date = date;
	  this.pump_id = pump_id;
	  this.labour_shift = labour_shift;
	  this.sales_priceunit = sales_priceunit;
	  this.pump_reading_open = pump_reading_open;
	  this.pump_reading_close = pump_reading_close;
	  this.sales_qty = sales_qty;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
  }

  public Pumpread(String product, Float testqty) {
		this.product = product;
		this.testqty = testqty;
	}  
  public Pumpread(String pump_id) {
	  this.pump_id = pump_id;
  };
  //public Pumpread(String product) {
	//  this.product = product;
  //}
public String getProduct() {
	return product;
}

public void setProduct(String product) {
	this.product = product;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public String getPump_id() {
	return pump_id;
}

public void setPump_id(String pump_id) {
	this.pump_id = pump_id;
}

public String getLabour_shift() {
	return labour_shift;
}

public void setLabour_shift(String labour_shift) {
	this.labour_shift = labour_shift;
}

public Float getSales_priceunit() {
	return sales_priceunit;
}

public void setSales_priceunit(Float sales_priceunit) {
	this.sales_priceunit = sales_priceunit;
}

public Float getPump_reading_open() {
	return pump_reading_open;
}

public void setPump_reading_open(Float pump_reading_open) {
	this.pump_reading_open = pump_reading_open;
}

public Float getPump_reading_close() {
	return pump_reading_close;
}

public void setPump_reading_close(Float pump_reading_close) {
	this.pump_reading_close = pump_reading_close;
}

public Float getSales_qty() {
	return sales_qty;
}

public void setSales_qty(Float sales_qty) {
	this.sales_qty = sales_qty;
}

public Float getTestqty() {
	return testqty;
}

public void setTestqty(Float testqty) {
	this.testqty = testqty;
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
