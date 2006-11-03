package woops2.model.activity;

import woops2.model.breakdownelement.WorkBreakdownElement;


public class Activity extends WorkBreakdownElement {
	
	private java.util.Set breakDownElements = new java.util.TreeSet();

	public java.util.Set getBreakDownElements() {
		return breakDownElements;
	}

	public void setBreakDownElements(java.util.Set breakDownElements) {
		this.breakDownElements = breakDownElements;
	}

}
