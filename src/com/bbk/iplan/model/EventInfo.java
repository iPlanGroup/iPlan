package com.bbk.iplan.model;

import java.util.Date;

/**
 * 
 * @ClassName: EventInfo
 * @Description: TODO(事件基本信息)
 * @author Lee
 * @date 2013-8-26 下午8:17:19
 * 
 */
public class EventInfo {
	public static final int MODE_SINGLE = 1; // 课程类型:单次科
	public static final int MODE_EXAM = 2; // 课程类型:考试
	public static final int MODE_LONGSUBJECT = 3;// 课程类型:长期课
	private int ID;
	private int Mode; // 学科类型
	private String name; // 事件名称
	private String mark; // 备注
	private Long remindTime; // 提醒时间
	private TermInfo term; // 所在学期
	private SubjectInfo subject; // 课程信息
	private Long StartTime;  //事件开始时间
	private Long EndTime;    //事件结束时间

	public String getName() {
		return name;
	}

	public int getMode() {
		return Mode;
	}

	public void setMode(int mode) {
		Mode = mode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Long getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime.getTime();
	}

	public TermInfo getTerm() {
		return term;
	}

	public void setTerm(TermInfo term) {
		this.term = term;
	}

	public SubjectInfo getSubject() {
		return subject;
	}

	public void setSubject(SubjectInfo subject) {
		this.subject = subject;
	}

	public Long getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date statrTime) {
		StartTime = statrTime.getTime();
	}

	public Long getEndTime() {
		return EndTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime.getTime();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	

}
