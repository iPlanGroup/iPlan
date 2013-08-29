package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

public class DayPlan {
	private Date LocalTime;
	private HomeworkManager mHomeworkManager;
	private SubjectManager mSubjectManager;
	private List<HomeworkInfo> mListHomeworkInfos;
	private List<SubjectInfo> mListSubjectInfos;

	public DayPlan(Date time) {
		LocalTime = time;
		mHomeworkManager = (HomeworkManager) SystemManager.getInstance()
				.getSystemManager(SystemManager.MODE_HOMEWORK);
		mSubjectManager = (SubjectManager) SystemManager.getInstance()
				.getSystemManager(SystemManager.MODE_SUBJECT);
	}

	public List<HomeworkInfo> getDayHomework() {
		return mListHomeworkInfos = mHomeworkManager
				.getLocalHomework(LocalTime);
	}

	public List<SubjectInfo> getDaySubject() {
		return mListSubjectInfos = mSubjectManager.getLocalSubject(LocalTime);
	}

	public HomeworkInfo CreateNewHomework(SubjectInfo Info, Date EndTime,
			int Level, String Mark) {
		HomeworkInfo info = mHomeworkManager.CreateNewHomework(Info, LocalTime,
				EndTime, Level, Mark);
		mListHomeworkInfos.add(info);
		return info;
	}

	public SubjectInfo CreateNewSubject(String name, Date time,
			Date notifytime, String place, String teacher, String mark) {
		SubjectInfo info=mSubjectManager.CreateNewSubject(name, time, notifytime, place, teacher, mark);
		mListSubjectInfos.add(info);
		return info;
	}
}
