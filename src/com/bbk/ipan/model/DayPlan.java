package com.bbk.ipan.model;

import java.util.Date;
import java.util.List;

public class DayPlan {
	private Date LocalTime;
	private HomeworkManager mHomeworkManager;
	private SubjectManager mSubjectManager;
	private List<HomeworkInfo> mListHomeworkInfos;
	private List<SubjectInfo> mListSubjectInfos;
	public DayPlan(Date time)
	{
		LocalTime=time;
		mHomeworkManager=(HomeworkManager)SystemManager.getInstance().getSystemManager(SystemManager.MODE_HOMEWORK);
		mSubjectManager=(SubjectManager)SystemManager.getInstance().getSystemManager(SystemManager.MODE_SUBJECT);
	}
	public  List<HomeworkInfo> getDayHomework(){
		return mListHomeworkInfos=mHomeworkManager.getLocalHomework(LocalTime);
	}
	public  List<SubjectInfo> getDaySubject(){
		return mListSubjectInfos=mSubjectManager.getLocalSubject(LocalTime);
	}
	public CreateNewHomework()
	{
		HomeworkInfo info=mHomeworkManager.CreateNewHomework(Info, StartTime, EndTime, Level, Mark);
		mListHomeworkInfos.add(info);
	}
}
