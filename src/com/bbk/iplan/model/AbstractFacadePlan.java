package com.bbk.iplan.model;

public abstract class  AbstractFacadePlan {
	private DayPlan mDayPlan;
	private WeekPlan mWeekPlan;
	private AbstractSystemManager mAbstractSystemManager;
	public abstract DayPlan getCurDayPlan();
	public abstract WeekPlan getCurWeekPlan();
	public abstract void CreateNewHomework();
	public abstract void CreateNewEvent();
	public abstract void CreateNewSubject();
	public abstract void ModifyHomework();
	
}
