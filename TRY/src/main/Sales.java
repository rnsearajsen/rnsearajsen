package main;

import java.sql.Date;
import java.sql.Time;
//import java.util.Timer;
//import java.util.Date;

//import java.math.BigInteger;

public class Sales {
  protected String product;
  protected Date salesdate;
  protected String labour_shift;
  protected Float sales_priceunit;
  protected Float purchase_priceunit; 
  protected Date purchasedate;
  protected String subgroup_name;
  protected Float sales_qty;
  protected Float testqty;
  protected Float total_sales_price;
  protected String uom ;
  protected Float taxpercent_total;
  protected Float taxamt_total;
  protected String comments;
  protected String changedby;
  protected Date changeddate;
  protected Time changedtime;  
  
  public Sales() {	  
  }
  
  public Sales(String product, Date salesdate, String labour_shift, Float sales_priceunit, Float purchase_priceunit) {
	  this.product = product; 
	  this.salesdate = salesdate;
	  this.labour_shift = labour_shift;
	  this.sales_priceunit = sales_priceunit;
	  this.purchase_priceunit = purchase_priceunit;
  }

  public Sales( String product, Date salesdate, String labour_shift, Float sales_priceunit,Float purchase_priceunit, Date purchasedate, String subgroup_name, Float sales_qty, 		  
		   Float total_sales_price,String uom, Float taxpercent_total, Float taxamt_total, String comments, String changedby, Date changeddate, Time changedtime) {	  
      this( purchasedate, subgroup_name, sales_qty, total_sales_price, uom, taxpercent_total, taxamt_total, comments, changedby, changeddate, changedtime);
      this.product = product;
      this.salesdate = salesdate;
	  this.labour_shift = labour_shift;
	  this.sales_priceunit = sales_priceunit;
	  this.purchase_priceunit = purchase_priceunit;
  }
  public Sales(Date purchasedate, String subgroup_name, Float sales_qty, Float total_sales_price,String uom, Float taxpercent_total, 		  
		   Float taxamt_total, String comments, String changedby, Date changeddate, Time changedtime) {
	  this.purchasedate = purchasedate;
	  this.subgroup_name = subgroup_name;
	  this.sales_qty = sales_qty;
	  this.total_sales_price = total_sales_price; 
	  this.uom = uom;
	  this.taxpercent_total = taxpercent_total;
	  this.taxamt_total = taxamt_total;
	  this.comments = comments;
      this.changedby = changedby;
      this.changeddate = changeddate;
      this.changedtime = changedtime;
}
public Sales(String product, Date salesdate, String labour_shift, Float sales_priceunit, String subgroup_name, Float sales_qty, Float testqty, Float taxpercent_total, 		  
		   String comments, String changedby, Date changeddate, Time changedtime) {	
	this( subgroup_name, sales_qty, testqty, taxpercent_total, comments, changedby, changeddate, changedtime);
    this.product = product;
    this.salesdate = salesdate;
    this.labour_shift = labour_shift;
	this.sales_priceunit = sales_priceunit;
}
public Sales(String subgroup_name, Float sales_qty, Float testqty, Float taxpercent_total, 		  
		   String comments, String changedby, Date changeddate, Time changedtime) {
	  this.subgroup_name = subgroup_name;
	  this.sales_qty = sales_qty;
	  this.testqty = testqty;
	  this.taxpercent_total = taxpercent_total;
	  this.comments = comments;
   this.changedby = changedby;
   this.changeddate = changeddate;
   this.changedtime = changedtime;
}
public Sales(String product, Float sales_priceunit,Float purchase_priceunit, Float sales_qty, Float total_sales_price, Float taxpercent_total) {
	this.product = product;
	this.sales_priceunit = sales_priceunit;
	this.purchase_priceunit = purchase_priceunit;
	this.sales_qty = sales_qty;
	this.total_sales_price = total_sales_price;
	this.taxpercent_total = taxpercent_total;
}
public Sales(String product, Date salesdate, String labour_shift, Float sales_priceunit, String subgroup_name, Float sales_qty, Float total_sales_price,
		String changedby, Date changeddate, Time changedtime) {
	this.product = product;
    this.salesdate = salesdate;
	this.labour_shift = labour_shift;
	this.sales_priceunit = sales_priceunit;
	this.subgroup_name = subgroup_name;
    this.sales_qty = sales_qty;
	this.total_sales_price = total_sales_price;
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

public Date getSalesdate() {
	return salesdate;
}

public void setSalesdate(Date salesdate) {
	this.salesdate = salesdate;
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

public String getSubgroup_name() {
	return subgroup_name;
}

public void setSubgroup_name(String subgroup_name) {
	this.subgroup_name = subgroup_name;
}

public Float getSales_qty() {
	return sales_qty;
}

public void setSales_qty(Float sales_qty) {
	this.sales_qty = sales_qty;
}

public Float getPurchase_priceunit() {
	return purchase_priceunit;
}

public void setPurchase_priceunit(Float purchase_priceunit) {
	this.purchase_priceunit = purchase_priceunit;
}

public Date getPurchasedate() {
	return purchasedate;
}

public void setPurchasedate(Date purchasedate) {
	this.purchasedate = purchasedate;
}

public Float getTotal_sales_price() {
	return total_sales_price;
}

public void setTotal_sales_price(Float total_sales_price) {
	this.total_sales_price = total_sales_price;
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

public Float getTestqty() {
	return testqty;
}

public void setTestqty(Float testqty) {
	this.testqty = testqty;
}

}
