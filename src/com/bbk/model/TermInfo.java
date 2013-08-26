package com.bbk.model;

import java.util.Date;
import java.util.List;

public class TermInfo {
	private List<SubjectInfo> subjectInfos;
	private Date StartTime;
	private Date EndTime;
	private Boolean isTerm;
	public List<SubjectInfo> getSubjectInfos() {
		return subjectInfos;
	}
	public void setSubjectInfos(List<SubjectInfo> subjectInfos) {
		this.subjectInfos = subjectInfos;
	}
	public Date getStartTime() {
		return StartTime;
	}
	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}
	public Date getEndTime() {
		return EndTime;
	}
	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}
	public Boolean getIsTerm() {
		return isTerm;
	}
	public void setIsTerm(Boolean isTerm) {
		this.isTerm = isTerm;
	}
	
}
