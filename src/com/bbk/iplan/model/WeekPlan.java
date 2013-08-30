package com.bbk.iplan.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public  class WeekPlan {
	private DayPlan mDayPlan=new DayPlan(null);
	private List<List<HomeworkInfo>> listHomework;
	public List<List<SubjectInfo>> listSubject;
	public List<List<HomeworkInfo>> getWeekPlanHomework(Date today){
		mDayPlan.setLocalTime(today);
		listHomework=new ArrayList<List<HomeworkInfo>>();
		int day_of_week=today.getDay();
		int day_of_month=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		for (int i=0;i<=7;i++){
			today.setDate(day_of_month-day_of_week+i);
			mDayPlan.setLocalTime(today);
			List<HomeworkInfo> DayList=mDayPlan.getDayHomework();
			listHomework.add(DayList);
		}
		return listHomework;
	}
	public List<HomeworkInfo> getDayPlanHomework(Date today)
	{
		mDayPlan.setLocalTime(today);
		List<HomeworkInfo> DayList=mDayPlan.getDayHomework();
		return DayList;
	}
	public List<List<SubjectInfo>> getWeekPlanSubject(Date today){
		mDayPlan.setLocalTime(today);
		listSubject=new ArrayList<List<SubjectInfo>>();
		int day_of_week=today.getDay();
		int day_of_month=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		for (int i=0;i<=7;i++){
			today.setDate(day_of_month-day_of_week+i);
			mDayPlan.setLocalTime(today);
			List<SubjectInfo> DayList=mDayPlan.getDaySubject();
			listSubject.add(DayList);
		}
		return listSubject;
	}
	public List<SubjectInfo> getDayPlanSubject(Date today)
	{
		mDayPlan.setLocalTime(today);
		List<SubjectInfo> DayList=mDayPlan.getDaySubject();
		return DayList;
	}
	public List<List<HomeworkInfo>> getListHomework() {
		return listHomework;
	}
	public void setListHomework(List<List<HomeworkInfo>> listHomework) {
		this.listHomework = listHomework;
	}
	public List<List<SubjectInfo>> getListSubject() {
		return listSubject;
	}
	public void setListSubject(List<List<SubjectInfo>> listSubject) {
		this.listSubject = listSubject;
	}

}
