package com.bbk.iplan.data;

import java.util.Date;
import java.util.List;
public class HomeworkInfo {
	private int ID;
	private String name;  //作业名称

	private String subjectName;   //作业所在科目
	private Long StartTime;   //开始时间
	private Long EndTime;     //结束时间
	private int level;        //优先级
	private String mark;      //备注

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subject) {
		this.subjectName = subject;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
