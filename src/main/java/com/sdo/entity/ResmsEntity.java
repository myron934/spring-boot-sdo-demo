package com.sdo.entity;

import java.util.Date;

public class ResmsEntity {
	private int id;
	private String telecom;
	private String telecomName;
	private int resmsNumber;
	private int telecomStatus;
	private Date createTime;
	private Date updateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTelecom() {
		return telecom;
	}
	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}
	public String getTelecomName() {
		return telecomName;
	}
	public void setTelecomName(String telecomName) {
		this.telecomName = telecomName;
	}
	public int getResmsNumber() {
		return resmsNumber;
	}
	public void setResmsNumber(int resmsNumber) {
		this.resmsNumber = resmsNumber;
	}
	public int getTelecomStatus() {
		return telecomStatus;
	}
	public void setTelecomStatus(int telecomStatus) {
		this.telecomStatus = telecomStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "ResmsEntity [id=" + id + ", telecom=" + telecom + ", telecomName=" + telecomName + ", resmsNumber="
				+ resmsNumber + ", telecomStatus=" + telecomStatus + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
 
}
