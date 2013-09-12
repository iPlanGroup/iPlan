package com.bbk.iplan.data;

import java.util.Date;

public class SubjectInfo {
	private int ID;

	private String name;  //课程名称
	private Long time;  //课程开始时间(通用) 
	private Long NotifyTime;  //提醒时间(长期课结束时间)
	private String place;  //上课地点
	private String teacher;  //上课老师
	private String mark;   //备注
	private TermInfo termInfo;
	
	
	public TermInfo getTermInfo() {
		return termInfo;
	}
	public void setTermInfo(TermInfo termInfo) {
		this.termInfo = termInfo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time.getTime();
	}
	public Long getNotifyTime() {
		return NotifyTime;
	}
	public void setNotifyTime(Date notifyTime) {
		NotifyTime = notifyTime.getTime();
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
}
