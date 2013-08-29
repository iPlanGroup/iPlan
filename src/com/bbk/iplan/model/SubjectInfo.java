package com.bbk.iplan.model;

import java.util.Date;

public class SubjectInfo {
	private int ID;

	private String name;
	private Long time;
	private Long NotifyTime;
	private String place;
	private String teacher;
	private String mark;
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
