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

	public HomeworkInfo CreateHomework(String name, Date EndTime, int Level,
			String Mark) {
		HomeworkInfo info = mHomeworkManager.CreateHomework(name, LocalTime,
				EndTime, Level, Mark);
		mListHomeworkInfos.add(info);
		return info;
	}

	public SubjectInfo CreateLongSubject(String name, Date time,
			Date remindTime, String place, String teacher, String mark) {
		SubjectInfo info = mSubjectManager.CreateLongSubject(name, time,
				remindTime, place, teacher, mark);
		mListSubjectInfos.add(info);
		return info;
	}

	public EventInfo CreateExam(String name, Date remindTime,
			String mark, Date StartTime, Date EndTime) {
		EventInfo info = mEventManager.CreateExam(EventInfo.MODE_EXAM, name, remindTime, mark,
				StartTime, EndTime);
		return info;
	}

	public EventInfo CreateSingleSubject(String name,Date remindTime,String mark,TermInfo term,SubjectInfo subject){
		EventInfo info =mEventManager.CreateSingleSubject(EventInfo.MODE_SINGLE, name, remindTime, mark, term, subject);
		return info;
	}

	public Date getLocalTime() {
		return LocalTime;
	}

	public void setLocalTime(Date localTime) {
		LocalTime = localTime;
	}
}
