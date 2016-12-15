package com.tianjian.slidingmenuteachingclient.bean;

import java.io.Serializable;

public class OutpPrescBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemNo;
	private String drugName;
	private String drugSpec;
	private String packingQty;
	private String price;
	private String amount;
	private String dosage;
	private String dosageUnits;
	private String frequency;
	private String administration;
	private String costs;
	private String dosageTunit;
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugSpec() {
		return drugSpec;
	}
	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
	public String getPackingQty() {
		return packingQty;
	}
	public void setPackingQty(String packingQty) {
		this.packingQty = packingQty;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getDosageUnits() {
		return dosageUnits;
	}
	public void setDosageUnits(String dosageUnits) {
		this.dosageUnits = dosageUnits;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getCosts() {
		return costs;
	}
	public void setCosts(String costs) {
		this.costs = costs;
	}
	public String getAdministration() {
		return administration;
	}
	public void setAdministration(String administration) {
		this.administration = administration;
	}
	public String getDosageTunit() {
		return dosageTunit;
	}
	public void setDosageTunit(String dosageTunit) {
		this.dosageTunit = dosageTunit;
	}
	
	
}
