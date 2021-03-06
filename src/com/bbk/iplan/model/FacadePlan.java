package com.bbk.iplan.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;

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
	private Date StartTime = new Date();
	private Date EndTime = new Date();
	private String place;
	private String teacher;
	private int Level;
	private String mark;
	private Date remindTime;
	private TermInfo term;
	private SubjectInfo subject;
	private TermInfo termInfo;
	private int[] week_time = new int[7];
	private CountManager mCountManager=new CountManager();
	private VacationManager mVacationManager=new VacationManager();

	public FacadePlan() {
		String datestr = mFormat.format(Calendar.getInstance().getTime());
		try {
			mDayPlan = new DayPlan(mFormat.parse(datestr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mWeekPlan = new WeekPlan();
	}

	public TermInfo CreateTerm(String name, Date StartTime, Date EndTime) {
		TermInfo info;
		VacationManager manager = (VacationManager) SystemManager.getInstance()
				.getSystemManager(SystemManager.MODE_VACATION);
		info = manager.CreateVacation(name, StartTime, EndTime, false);
		return info;

	}
	public TermInfo CreateVacation(String name, Date StartTime, Date EndTime)
	{
		TermInfo info;
		VacationManager manager = (VacationManager) SystemManager.getInstance()
				.getSystemManager(SystemManager.MODE_VACATION);
		info = manager.CreateVacation(name, StartTime, EndTime, true);
		return info;
	}
	public void setCurTerm(Date startTime, Date endTime) {
		term.setStartTime(startTime);
		term.setEndTime(endTime);
		term.setIsTerm(false);
	}
	public TermInfo getCurTerm(){
		return term;
	}

	public void setSubject(String name, Date StartTime, Date notifyTime,
			String teacher, String place, String mark) {
		subject.setName(name);
		subject.setTime(StartTime);
		subject.setNotifyTime(notifyTime);
		subject.setTeacher(teacher);
		subject.setPlace(place);
		subject.setMark(mark);
	}

	public void setHomeworkInfo(String name, Date StartTime, Date EndTime,
			int Level, Date remindTime, TermInfo term, SubjectInfo subject,
			String Mark) {
		this.name = name;
		if (mDayPlan.getLocalTime().equals(mFormat.format(StartTime)))
			StartTime = mDayPlan.getLocalTime();
		else
			try {
				StartTime = mFormat.parse(mFormat.format(StartTime));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		this.Level = Level;
		mark = Mark;
		this.remindTime = remindTime;
		this.term = term;
		this.subject = subject;
	}

	@Override
	public Object CreateDayPlan(int mode) {
		Object object = new Object();
		switch (mode) {
		case MODE_HOMEWORK:
			object = mDayPlan.CreateHomework(name, subjectName, EndTime, mode,
					mark);
			break;
		case MODE_EVENT:
			object = mDayPlan.CreateExam(name, remindTime, subject, mark,
					StartTime, EndTime);
			break;
		case MODE_LONGSUBJECT:
			object = mDayPlan.CreateLongSubject(name, StartTime, week_time,
					remindTime, place, teacher, mark, termInfo);
			break;
		case MODE_SINGLESUBJECT:
			object = mDayPlan.CreateSingleSubject(name, remindTime, StartTime,
					EndTime, mark, term, subject);
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
		mWeekPlan = new WeekPlan();
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
	public List<SubjectInfo> getAllSubjectInfo()
	{
		return mCountManager.getAllSubjectInfos();
	}
	public List<TermInfo> getAllVacation()
	{
		return mVacationManager.getAllVacation();
	}
	public List<SubjectInfo> getSubjectInfoByVacationID(int id)
	{
		return ((SubjectManager)SystemManager.getInstance().
				getSystemManager(SystemManager.MODE_VACATION)).
				getSubjectInfoByVacationID(id);
	}
	@Override
	public void ModifyDayPlan(int mode, int id, ContentValues contentValues) {
		// TODO Auto-generated method stub
		mDayPlan.ModifyInfo(mode, id, contentValues);
	}


	@Override
	public void DeleteDayPlan(int mode, int id) {
		mDayPlan.DeleteInfo(mode, id);
	}

	public int[] getWeek_time() {
		return week_time;
	}

	public void setWeek_time(int[] week_time) {
		this.week_time = week_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Date getEndTime() {
		return EndTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Date getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}

	public TermInfo getTerm() {
		return term;
	}

	public void setTerm(TermInfo term) {
		this.term = term;
	}

	public SubjectInfo getSubject() {
		return subject;
	}

	public void setSubject(SubjectInfo subject) {
		this.subject = subject;
	}

}
