package com.tianjian.slidingmenuteachingclient.bean;
import java.io.Serializable;


public class CommonPatientBean implements Serializable {
	private String id;
	private String	userId;
	private String name;
	private String idNo;
	private String mobelPhone;
	private String securityUserBaseinfoId;
	private String bindingPid;
	private String relationshipName;
	private String sexName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getMobelPhone() {
		return mobelPhone;
	}
	public void setMobelPhone(String mobelPhone) {
		this.mobelPhone = mobelPhone;
	}
	public String getSecurityUserBaseinfoId() {
		return securityUserBaseinfoId;
	}
	public void setSecurityUserBaseinfoId(String securityUserBaseinfoId) {
		this.securityUserBaseinfoId = securityUserBaseinfoId;
	}
	public String getBindingPid() {
		return bindingPid;
	}
	public void setBindingPid(String bindingPid) {
		this.bindingPid = bindingPid;
	}
	public String getRelationshipName() {
		return relationshipName;
	}
	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	
}
