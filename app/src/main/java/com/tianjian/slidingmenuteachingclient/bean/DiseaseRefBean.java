package com.tianjian.slidingmenuteachingclient.bean;

import java.io.Serializable;

public class DiseaseRefBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String diseaseDesc;
	private String clinicalRepresent;
	private String diagnosePoint;
	private String treatPrescription;
	private String doctorsAdvice;
	public String getDiseaseDesc() {
		return diseaseDesc;
	}
	public void setDiseaseDesc(String diseaseDesc) {
		this.diseaseDesc = diseaseDesc;
	}
	public String getClinicalRepresent() {
		return clinicalRepresent;
	}
	public void setClinicalRepresent(String clinicalRepresent) {
		this.clinicalRepresent = clinicalRepresent;
	}
	public String getDiagnosePoint() {
		return diagnosePoint;
	}
	public void setDiagnosePoint(String diagnosePoint) {
		this.diagnosePoint = diagnosePoint;
	}
	public String getTreatPrescription() {
		return treatPrescription;
	}
	public void setTreatPrescription(String treatPrescription) {
		this.treatPrescription = treatPrescription;
	}
	public String getDoctorsAdvice() {
		return doctorsAdvice;
	}
	public void setDoctorsAdvice(String doctorsAdvice) {
		this.doctorsAdvice = doctorsAdvice;
	}
	
}
