package com.bbk.iplan.model;

public abstract class  AbstractFacadePlan {
	private DayPlan mDayPlan;
	private WeekPlan mWeekPlan;
	public abstract DayPlan getCurDayPlan();
	public abstract WeekPlan getCurWeekPlan();
	public abstract void CreateDayPlan(int mode);
	public abstract void ModifyDayPlan(int mode);
	public abstract void DeleteDayPlan(int mode);
	
}
