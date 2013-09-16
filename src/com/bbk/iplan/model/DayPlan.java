package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;

import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.data.HomeworkInfo;
import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.data.TermInfo;
import com.bbk.iplan.interfaces.ManagerInterface;

public class DayPlan {
	private Date LocalTime;
	private HomeworkManager mHomeworkManager;
	private SubjectManager mSubjectManager;
	private EventManager mEventManager;
	private CountManager mCountManager;
	private VacationManager mVacationManager;
	private List<HomeworkInfo> mListHomeworkInfos;
	private List<SubjectInfo> mListSubjectInfos;
	private List<EventInfo> mListEventInfos;

	public List<EventInfo> getmListEventInfos() {
		return mListEventInfos;
	}

	public void setmListEventInfos(List<EventInfo> mListEventInfos) {
		this.mListEventInfos = mListEventInfos;
	}

	public List<HomeworkInfo> getListHomeworkInfos() {
		return mListHomeworkInfos;
	}

	public void setListHomeworkInfos(List<HomeworkInfo> mListHomeworkInfos) {
		this.mListHomeworkInfos = mListHomeworkInfos;
	}

	public List<SubjectInfo> getListSubjectInfos() {
		return mListSubjectInfos;
	}

	public void setListSubjectInfos(List<SubjectInfo> mListSubjectInfos) {
		this.mListSubjectInfos = mListSubjectInfos;
	}

	public DayPlan(Date time) {
		LocalTime = time;
		mHomeworkManager = (HomeworkManager) SystemManager.getInstance()
				.getSystemManager(SystemManager.MODE_HOMEWORK);
		mSubjectManager = (SubjectManager) SystemManager.getInstance()
				.getSystemManager(SystemManager.MODE_SUBJECT);
		mEventManager = (EventManager) SystemManager.getInstance()
				.getSystemManager(SystemManager.MODE_EVENT);
		mCountManager = (CountManager) SystemManager.getInstance()
				.getSystemManager(SystemManager.MODE_COUNT);
		mVacationManager= (VacationManager)SystemManager.getInstance().getSystemManager(SystemManager.MODE_VACATION);
	}

	public List<HomeworkInfo> getDayHomework() {
		return mListHomeworkInfos = mHomeworkManager
				.getLocalHomework(LocalTime);
	}

	public List<SubjectInfo> getDaySubject() {
		return mListSubjectInfos = mSubjectManager.getLocalSubject(LocalTime);
	}

	public HomeworkInfo CreateHomework(String name, String subjectName,
			Date EndTime, int Level, String Mark) {
		HomeworkInfo info = mHomeworkManager.CreateHomework(name, subjectName,
				LocalTime, EndTime, Level, Mark);
		mListHomeworkInfos.add(info);
		return info;
	}

	public SubjectInfo CreateLongSubject(String name, Date time,
			int[] week_time, Date remindTime, String place, String teacher,
			String mark, TermInfo termInfo) {
		SubjectInfo info = mSubjectManager.CreateLongSubject(name, time,
				week_time, remindTime, place, teacher, mark, termInfo);
		mListSubjectInfos.add(info);
		return info;
	}

	public EventInfo CreateExam(String name, Date remindTime,
			SubjectInfo subject, String mark, Date StartTime, Date EndTime) {
		EventInfo info = mEventManager.CreateExam(EventInfo.MODE_EXAM, name,
				remindTime, subject, mark, StartTime, EndTime);
		return info;
	}

	public EventInfo CreateSingleSubject(String name, Date remindTime,
			Date StartTime, Date EndTime, String mark, TermInfo term,
			SubjectInfo subject) {
		EventInfo info = mEventManager.CreateSingleSubject(
				EventInfo.MODE_SINGLE, name, remindTime, StartTime, EndTime,
				mark, term, subject);
		return info;
	}

	public Date getLocalTime() {
		return LocalTime;
	}

	public void setLocalTime(Date localTime) {
		LocalTime = localTime;
	}

	public void DeleteInfo(int mode, int id) {
		ManagerInterface mInterface = null;
		switch (mode) {
		case SystemManager.MODE_EVENT:
			mInterface = mEventManager;
			break;
		case SystemManager.MODE_HOMEWORK:
			mInterface = mHomeworkManager;
			break;
		case SystemManager.MODE_SUBJECT:
			mInterface = mSubjectManager;
			break;
		case SystemManager.MODE_VACATION:
			mInterface = new VacationManager();
			break;
		}
		mInterface.DeleteInfo(id);
	}

	public void ModifyInfo(int mode, int id, ContentValues contentValues) {
		ManagerInterface mInterface = null;
		switch (mode) {
		case SystemManager.MODE_EVENT:
			mInterface = mEventManager;
			break;
		case SystemManager.MODE_HOMEWORK:
			mInterface = mHomeworkManager;
			break;
		case SystemManager.MODE_SUBJECT:
			mInterface = mSubjectManager;
			break;
		case SystemManager.MODE_VACATION:
			mInterface = new VacationManager();
			break;
		}
		mInterface.ModifyInfo(id, contentValues);
	}
}
