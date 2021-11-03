package main;

import java.sql.Date;

public class Report {
	  protected String product;
	  protected Date salesdate;
	  protected Float sales_qty;
	  protected Float testqty;
	  protected Float total_sales_price;
	  protected String uom ;	
	  
	  public Report(String product, Date salesdate, Float sales_qty, Float testqty, Float total_sales_price) {
		  this.product = product; 
		  this.salesdate = salesdate;
		  this.sales_qty = sales_qty;
		  this.testqty = testqty;
		  this.total_sales_price = total_sales_price;
	  }	  
}