package com.bbk.iplan.model;

import java.util.Date;

import android.content.ContentValues;

public abstract class  AbstractFacadePlan {
	private DayPlan mDayPlan;
	private WeekPlan mWeekPlan;
	public abstract DayPlan getCurDayPlan(Date time);
	public abstract WeekPlan getCurWeekPlan(Date time);
	public abstract Object CreateDayPlan(int mode);
	public abstract void ModifyDayPlan(int mode,int id,ContentValues contentValues);
	public abstract void DeleteDayPlan(int mode,int id);
	
	
}
