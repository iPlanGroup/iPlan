package com.bbk.iplan.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FacadePlan extends AbstractFacadePlan {
	private DayPlan mDayPlan;
	private WeekPlan mWeekPlan;
	public static final int MODE_HOMEWORK = 0;
	public static final int MODE_EVENT = 1;
	public static final int MODE_SUBJECT = 2;
	public SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd");
	private String name;
	private Date StartTime=new Date();
	private Date EndTime=new Date();
	private String place;
	private String teacher;
	private int Level;
	private String mark;
	private Date remindTime;
	private TermInfo term;
	private SubjectInfo subject;
	public FacadePlan() {
		// TODO Auto-generated constructor stub
		String datestr = mFormat.format(Calendar.getInstance().getTime());
		try {
			mDayPlan = new DayPlan(mFormat.parse(datestr));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mWeekPlan = new WeekPlan();
	}

	public void setHomeworkInfo(String name, Date StartTime, Date EndTime, int Level,
			String Mark) {
		this.name=name;
		if (mDayPlan.getLocalTime().equals(mFormat.format(StartTime)))
			StartTime=mDayPlan.getLocalTime();
		else
			try {
				StartTime=mFormat.parse(mFormat
						.format(StartTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.Level=Level;
		mark=Mark;
	}
	public void setSubjectInfo()
	{
		
	}
	@Override
	public Object CreateDayPlan(int mode) {
		Object object = new Object();
		switch (mode) {
		case MODE_HOMEWORK:
			break;
		case MODE_EVENT:
			break;
		case MODE_SUBJECT:
			break;
		}
		return object;

	}

	@Override
	public DayPlan getCurDayPlan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WeekPlan getCurWeekPlan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ModifyDayPlan(int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void DeleteDayPlan(int mode) {
		// TODO Auto-generated method stub

	}

}
