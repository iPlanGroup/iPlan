package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

public class DayPlan {
	private Date LocalTime;
	private HomeworkManager mHomeworkManager;
	private SubjectManager mSubjectManager;
	private EventManager mEventManager;
	private List<HomeworkInfo> mListHomeworkInfos;
	private List<SubjectInfo> mListSubjectInfos;

	public DayPlan(Date time) {
		LocalTime = time;
		mHomeworkManager = (HomeworkManager) SystemManager.getInstance()
				.getSystemManager(SystemManager.MODE_HOMEWORK);
		mSubjectManager = (SubjectManager) SystemManager.getInstance()
				.getSystemManager(SystemManager.MODE_SUBJECT);
		mEventManager = (EventManager) SystemManager.getInstance()
				.getSystemManager(SystemManager.MODE_EVENT);
	}

	public List<HomeworkInfo> getDayHomework() {
		return mListHomeworkInfos = mHomeworkManager
				.getLocalHomework(LocalTime);
	}

	public List<SubjectInfo> getDaySubject() {
		return mListSubjectInfos = mSubjectManager.getLocalSubject(LocalTime);
	}

	public HomeworkInfo CreateNewHomework(String name, Date EndTime,
			int Level, String Mark) {
		HomeworkInfo info = mHomeworkManager.CreateHomework(name, LocalTime,
				EndTime, Level, Mark);
		mListHomeworkInfos.add(info);
		return info;
	}

	public SubjectInfo CreateNewSubject(String name, Date time,
			Date notifytime, String place, String teacher, String mark) {
		SubjectInfo info = mSubjectManager.CreateLongSubject(name, time,
				notifytime, place, teacher, mark);
		mListSubjectInfos.add(info);
		return info;
	}

	public EventInfo CreateExam(int Mode,String name,Date remindTime,String mark,Date StartTime,Date EndTime)
	{
		EventInfo info= mEventManager.CreateExam(Mode, name, remindTime, mark, StartTime, EndTime);
		return info;
	}
	public EventInfo CreateSingleSubject(){
		return null;
	}
	public Date getLocalTime() {
		return LocalTime;
	}

	public void setLocalTime(Date localTime) {
		LocalTime = localTime;
	}
}
