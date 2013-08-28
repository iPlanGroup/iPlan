package com.bbk.ipan.model;

public abstract class  AbstractFacadePlan {
	private AbstractDayPlan mDayPlan;
	private AbstractWeekPlan mWeekPlan;
	private AbstractSystemManager mAbstractSystemManager;
	public abstract AbstractDayPlan getCurDayPlan();
	public abstract AbstractWeekPlan getCurWeekPlan();
	public abstract void CreateNewHomework();
	public abstract void CreateNewEvent();
	public abstract void CreateNewSubject();
	public abstract void ModifyHomework();
	
}
