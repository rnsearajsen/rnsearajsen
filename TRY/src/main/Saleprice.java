package main;

import java.sql.Date;
import java.sql.Time;
//import java.util.Timer;
//import java.util.Date;

//import java.math.BigInteger;

public class Saleprice {
	protected Date date;
	protected String product;  
	protected String subgroup_name;
  protected Float sell_price;  
  protected String uom;
  protected Float taxpercent_total;
  protected Float taxamt_total;  
  protected String comments;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  
  public Saleprice() {	  
  }
  
  public Saleprice(Date date, String product ) {	   
	  this.date = date;
	  this.product = product;}

  public Saleprice( Date date, String product, String subgroup_name, Float sell_price, String uom, Float taxpercent_total, Float taxamt_total, String comments, String changedby, Date changeddate, Time changedtime) {	  
      this( subgroup_name, sell_price, uom, taxpercent_total, taxamt_total, comments, changedby, changeddate, changedtime);
      this.date = date;
      this.product = product;
  }
  public Saleprice(String subgroup_name, Float sell_price, String uom, Float taxpercent_total,   
		   Float taxamt_total, String comments, String changedby, Date changeddate, Time changedtime) {
	  this.subgroup_name = subgroup_name;
	  this.sell_price = sell_price;
	  this.uom = uom;
	  this.taxpercent_total = taxpercent_total;
	  this.taxamt_total = taxamt_total;
	  this.comments = comments;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
}
//Sale Price 
  public Saleprice(Date date, String product, Float sell_price ) {	   
	  this.date = date;
	  this.product = product;
	  this.sell_price = sell_price;}
  
public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public String getProduct() {
	return product;
}

public void setProduct(String product) {
	this.product = product;
}

public String getSubgroup_name() {
	return subgroup_name;
}

public void setSubgroup_name(String subgroup_name) {
	this.subgroup_name = subgroup_name;
}

public Float getSell_price() {
	return sell_price;
}

public void setSell_price(Float sell_price) {
	this.sell_price = sell_price;
}

public String getUom() {
	return uom;
}

public void setUom(String uom) {
	this.uom = uom;
}

public Float getTaxpercent_total() {
	return taxpercent_total;
}

public void setTaxpercent_total(Float taxpercent_total) {
	this.taxpercent_total = taxpercent_total;
}

public Float getTaxamt_total() {
	return taxamt_total;
}

public void setTaxamt_total(Float taxamt_total) {
	this.taxamt_total = taxamt_total;
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
