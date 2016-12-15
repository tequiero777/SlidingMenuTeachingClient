package com.tianjian.slidingmenuteachingclient.bean;

import java.io.Serializable;
import java.util.List;

public class ChinesePrescBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chineseMedicineDosage;
	private String prescriptionType;
	private String tcmDecoctionName;
	private String tcmDrugUseDesc;
	private List<OutpPrescBean> drugs;
	public String getChineseMedicineDosage() {
		return chineseMedicineDosage;
	}
	public void setChineseMedicineDosage(String chineseMedicineDosage) {
		this.chineseMedicineDosage = chineseMedicineDosage;
	}
	public String getPrescriptionType() {
		return prescriptionType;
	}
	public void setPrescriptionType(String prescriptionType) {
		this.prescriptionType = prescriptionType;
	}
	public String getTcmDecoctionName() {
		return tcmDecoctionName;
	}
	public void setTcmDecoctionName(String tcmDecoctionName) {
		this.tcmDecoctionName = tcmDecoctionName;
	}
	public String getTcmDrugUseDesc() {
		return tcmDrugUseDesc;
	}
	public void setTcmDrugUseDesc(String tcmDrugUseDesc) {
		this.tcmDrugUseDesc = tcmDrugUseDesc;
	}
	public List<OutpPrescBean> getDrugs() {
		return drugs;
	}
	public void setDrugs(List<OutpPrescBean> drugs) {
		this.drugs = drugs;
	}
}
