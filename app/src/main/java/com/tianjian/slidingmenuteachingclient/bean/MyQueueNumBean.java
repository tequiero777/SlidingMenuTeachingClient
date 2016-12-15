package com.tianjian.slidingmenuteachingclient.bean;

import java.io.Serializable;

public class MyQueueNumBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deptCode = "";
	private String deptName = "";
	private String myWaitingSequence = "";
	private String prevCount = "";
	private String registerDate = "";
	
	private String roomName = "";
	private String visitDate = "";	
	private String visitNo = "";
	
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getMyWaitingSequence() {
		return myWaitingSequence;
	}
	public void setMyWaitingSequence(String myWaitingSequence) {
		this.myWaitingSequence = myWaitingSequence;
	}
	public String getPrevCount() {
		return prevCount;
	}
	public void setPrevCount(String prevCount) {
		this.prevCount = prevCount;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	
}
