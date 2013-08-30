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
	private int Mode; // 浜嬩欢绫诲瀷
	private String name; // 浜嬩欢鍚嶇О
	private String mark; // 澶囨敞
	private Date remindTime; // 鎻愰啋鏃堕棿
	private TermInfo term; // 鎵�湪瀛︽湡
	private SubjectInfo subject; // 绉戠洰
	private Long StatrTime;
	private Long EndTime;

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

	public Date getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
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

	public Long getStatrTime() {
		return StatrTime;
	}

	public void setStatrTime(Date statrTime) {
		StatrTime = statrTime.getTime();
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
