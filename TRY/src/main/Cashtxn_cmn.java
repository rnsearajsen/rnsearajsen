package main;

public class Cashtxn_cmn {
	protected Float aggramt;
	protected Float collection_amt;
	protected Float difference;
	
	public Cashtxn_cmn() {	  
	  }	
	
	public Cashtxn_cmn(Float aggramt) {
		this.aggramt = aggramt;		
	}
	public Cashtxn_cmn(Float aggramt, Float collection_amt, Float difference) {
		this.aggramt = aggramt;	
		this.collection_amt = collection_amt;
		this.difference = difference;
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
	
}
