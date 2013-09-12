package com.bbk.iplan.data;

import java.util.Date;
import java.util.List;


public class TermInfo {
	private int ID;
	private List<SubjectInfo> subjectInfos;  //学科基本信息
	private Long StartTime;  //学期开始时间
	private Long EndTime;    //学期结束时间
	private Boolean isTerm;  //判断是否为假期
	private String name; //假期名称
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SubjectInfo> getSubjectInfos() {
		return subjectInfos;
	}
	public void setSubjectInfos(List<SubjectInfo> subjectInfos) {
		this.subjectInfos = subjectInfos;
	}
	public Long getStartTime() {
		return StartTime;
	}
	public void setStartTime(Date startTime) {
		
		StartTime = startTime.getTime();
	}
	public Long getEndTime() {
		return EndTime;
	}
	public void setEndTime(Date endTime) {
		EndTime = endTime.getTime();
	}
	public Boolean getIsTerm() {
		return isTerm;
	}
	public void setIsTerm(Boolean isTerm) {
		this.isTerm = isTerm;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
}
