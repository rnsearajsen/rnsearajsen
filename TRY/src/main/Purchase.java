package main;

import java.sql.Date;
import java.sql.Time;
//import java.util.Timer;
//import java.util.Date;

//import java.math.BigInteger;

public class Purchase {
  protected String product;
  protected Date purchasedate;
  protected String subgroup_name;
  protected Float purchase_qty;
  protected Float stock_qty;
  protected String uom;
  protected Float price_unit;
  protected Float price_total;
  protected Float taxpercent_total;
  protected Float taxamt_total;
  protected String comments;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  
  public Purchase() {	  
  }
  
  public Purchase(String product, Date purchasedate) {
	  this.product = product; 
	  this.purchasedate = purchasedate;}

  public Purchase( String product, Date purchasedate, String subgroup_name, Float purchase_qty, Float stock_qty,		  
		  String uom, Float price_unit, Float price_total, Float taxpercent_total, Float taxamt_total, String comments, String changedby, Date changeddate, Time changedtime) {	  
      this( subgroup_name, purchase_qty,stock_qty, uom, price_unit, price_total, taxpercent_total, taxamt_total, comments, changedby, changeddate, changedtime);
      this.product = product;
      this.purchasedate = purchasedate;
  }
  public Purchase(String subgroup_name, Float purchase_qty, Float stock_qty,String uom, Float price_unit, Float price_total,  
		  Float taxpercent_total, Float taxamt_total, String comments, String changedby, Date changeddate, Time changedtime) {
	  this.subgroup_name = subgroup_name;
	  this.purchase_qty = purchase_qty;
	  this.stock_qty = stock_qty;
	  this.uom = uom;
	  this.price_unit = price_unit;
	  this.price_total = price_total;
	  this.taxpercent_total = taxpercent_total;
	  this.taxamt_total = taxamt_total;
	  this.comments = comments;
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

public Date getPurchasedate() {
	return purchasedate;
}

public void setPurchasedate(Date purchasedate) {
	this.purchasedate = purchasedate;
}

public String getSubgroup_name() {
	return subgroup_name;
}

public void setSubgroup_name(String subgroup_name) {
	this.subgroup_name = subgroup_name;
}

public Float getPurchase_qty() {
	return purchase_qty;
}

public void setPurchase_qty(Float purchase_qty) {
	this.purchase_qty = purchase_qty;
}

public Float getStock_qty() {
	return stock_qty;
}

public void setStock_qty(Float stock_qty) {
	this.stock_qty = stock_qty;
}

public String getUom() {
	return uom;
}

public void setUom(String uom) {
	this.uom = uom;
}

public Float getPrice_unit() {
	return price_unit;
}

public void setPrice_unit(Float price_unit) {
	this.price_unit = price_unit;
}

public Float getPrice_total() {
	return price_total;
}

public void setPrice_total(Float price_total) {
	this.price_total = price_total;
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
