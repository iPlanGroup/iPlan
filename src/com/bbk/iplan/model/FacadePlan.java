package com.bbk.iplan.model;

import java.util.Calendar;

public class FacadePlan extends AbstractFacadePlan{
	private DayPlan mDayPlan;
	private WeekPlan mWeekPlan;
	public static final int MODE_HOMEWORK=0;
	public static final int MODE_EVENT=1;
	public static final int MODE_SUBJECT=2;
	public FacadePlan() {
		// TODO Auto-generated constructor stub
		mDayPlan = new DayPlan(Calendar.getInstance().getTime());
		mWeekPlan = new WeekPlan();
	}
	@Override
	public void CreateDayPlan(int mode) {
		switch (mode) {

		}

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
