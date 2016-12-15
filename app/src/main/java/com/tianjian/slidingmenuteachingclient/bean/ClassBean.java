package com.tianjian.slidingmenuteachingclient.bean;

public class ClassBean {
	private String id;
	private String classCode;
	private String className;
	private String parentId;
	private String inputCode;
	private String comments;
	private String seqNo;
	private String levelId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getInputCode() {
		return inputCode;
	}
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getLevelId() {
		return levelId;
	}
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	public ClassBean(String id, String classCode, String className,
			String parentId, String inputCode, String comments, String seqNo,
			String levelId) {
		super();
		this.id = id;
		this.classCode = classCode;
		this.className = className;
		this.parentId = parentId;
		this.inputCode = inputCode;
		this.comments = comments;
		this.seqNo = seqNo;
		this.levelId = levelId;
	}
	public ClassBean() {
		super();
	}
	
}
