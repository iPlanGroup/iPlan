package com.bbk.iplan.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FacadePlan extends AbstractFacadePlan {
	private DayPlan mDayPlan;
	private WeekPlan mWeekPlan;
	private HomeworkInfo mHomeworkInfo = new HomeworkInfo();
	public static final int MODE_HOMEWORK = 0;
	public static final int MODE_EVENT = 1;
	public static final int MODE_SUBJECT = 2;
	public SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd");

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

	public void setHomeworkInfo(String name, Date LocalTime, Date EndTime, int Level,
			String Mark) {
		mHomeworkInfo.setSubjectName(name);
		if (mDayPlan.getLocalTime().equals(mFormat.format(LocalTime)))
			mHomeworkInfo.setStartTime(mDayPlan.getLocalTime());
		else
			try {
				mHomeworkInfo.setStartTime(mFormat.parse(mFormat
						.format(LocalTime)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		mHomeworkInfo.setLevel(Level);
		mHomeworkInfo.setMark(Mark);
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
