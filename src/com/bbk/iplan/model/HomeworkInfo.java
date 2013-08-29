package com.bbk.iplan.model;

import java.util.Date;

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
}
