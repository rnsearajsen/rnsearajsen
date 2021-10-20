package main;

import java.sql.Date;
//import java.sql.Time;
//import java.util.Timer;
//import java.util.Date;

//import java.math.BigInteger;

public class Commonfn {
  protected String product;
  protected String uom;
  
  public Commonfn() {	  
  }
  
  public Commonfn(String product, Date purchasedate) {
	  this.product = product; 
}


//Product Structure
  public Commonfn(String product, String uom) {
	  this.product = product;
	  this.uom = uom;
  }

public String getProduct() {
	return product;
}

public void setProduct(String product) {
	this.product = product;
}

public String getUom() {
	return uom;
}

public void setUom(String uom) {
	this.uom = uom;
}

}
