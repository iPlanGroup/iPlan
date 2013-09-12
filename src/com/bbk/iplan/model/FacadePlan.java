package com.bbk.iplan.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.data.TermInfo;

public class FacadePlan extends AbstractFacadePlan {
	private DayPlan mDayPlan;
	private WeekPlan mWeekPlan;
	public static final int MODE_HOMEWORK = 0;
	public static final int MODE_EVENT = 1;
	public static final int MODE_SINGLESUBJECT = 2;
	public static final int MODE_LONGSUBJECT = 3;
	public SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd");
	private String name;
	private String subjectName;
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
	public void setCurTerm(Date startTime,Date endTime){
		term.setStartTime(startTime);
		term.setEndTime(endTime);
		term.setIsTerm(false);
	}
	public void setSubject(String name,Date StartTime,Date notifyTime,String teacher,String place,String mark){
		subject.setName(name);
		subject.setTime(StartTime);
		subject.setNotifyTime(notifyTime);
		subject.setTeacher(teacher);
		subject.setPlace(place);
		subject.setMark(mark);
	}
	public void setHomeworkInfo(String name, Date StartTime, Date EndTime, int Level,Date remindTime,TermInfo term,SubjectInfo subject,
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
		this.remindTime=remindTime;
		this.term=term;
		this.subject=subject;
	}
	@Override
	public Object CreateDayPlan(int mode) {
		Object object = new Object();
		switch (mode) {
		case MODE_HOMEWORK:
			object=mDayPlan.CreateHomework(name,subjectName, EndTime, mode, mark);
			break;
		case MODE_EVENT:
			object=mDayPlan.CreateExam(name, remindTime, mark, StartTime, EndTime);
			break;
		case MODE_SINGLESUBJECT:
			object=mDayPlan.CreateLongSubject(name, StartTime, remindTime, place, teacher, mark);
			break;
		case MODE_LONGSUBJECT:
			object=mDayPlan.CreateSingleSubject(name, remindTime, mark, term, subject);
			break;
		}
		return object;

	}

	@Override
	public DayPlan getCurDayPlan(Date time) {
		// TODO Auto-generated method stub
		mDayPlan.setLocalTime(time);
		mDayPlan.setListHomeworkInfos(mDayPlan.getDayHomework());
		mDayPlan.setListSubjectInfos(mDayPlan.getDaySubject());
		return mDayPlan;
	}

	@Override
	public WeekPlan getCurWeekPlan(Date time) {
		// TODO Auto-generated method stub
		mWeekPlan=new WeekPlan();
		mWeekPlan.setListHomework(mWeekPlan.getWeekPlanHomework(time));
		mWeekPlan.setListSubject(mWeekPlan.getWeekPlanSubject(time));
		return mWeekPlan;
	}

	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	@Override
	public void ModifyDayPlan(int mode,String column,Object value,int columnMode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void DeleteDayPlan(int mode,int id) {
		// TODO Auto-generated method stub
		switch (mode){
		case MODE_HOMEWORK:
			mDayPlan.(name,subjectName, EndTime, mode, mark);
			break;
		case MODE_EVENT:
			mDayPlan.CreateExam(name, remindTime, mark, StartTime, EndTime);
			break;
		case MODE_SINGLESUBJECT:
			mDayPlan.CreateLongSubject(name, StartTime, remindTime, place, teacher, mark);
			break;
		case MODE_LONGSUBJECT:
			mDayPlan.CreateSingleSubject(name, remindTime, mark, term, subject);
			break;
		}
	}

}
