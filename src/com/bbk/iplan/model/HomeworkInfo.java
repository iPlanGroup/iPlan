package com.bbk.iplan.model;

import java.util.Date;

<<<<<<< HEAD
public class HomeworkInfo
{
	private SubjectInfo subject;
	private Date StartTime;
	private Date EndTime;
	private int level;
	private String mark;
	
	
	public Date getStartTime()
	{
		return StartTime;
	}

	public void setStartTime(Date startTime)
	{
		StartTime = startTime;
	}

	public Date getEndTime()
	{
		return EndTime;
	}

	public void setEndTime(Date endTime)
	{
		EndTime = endTime;
	}

	public SubjectInfo getSubject()
	{
		return subject;
	}

	public void setSubject(SubjectInfo subject)
	{
		this.subject = subject;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public String getMark()
	{
		return mark;
	}

	public void setMark(String mark)
	{
		this.mark = mark;
	}
=======
public class HomeworkInfo {
	private int ID;
	private String subjectName;
	private Long StartTime;
	private Long EndTime;
	private int level;
	private String mark;

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
	
>>>>>>> ec102675260c781a54de887ba14c2e45ab230e3a
}
