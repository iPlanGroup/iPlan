package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

public class HomeworkManager {
	public List<HomeworkInfo> getUnFinished() {
		return null;
	}
	public List<HomeworkInfo> getLocalHomework(Date time)
	{
		return null;
	}
	public HomeworkInfo CreateNewHomework(SubjectInfo Info,
			Date StartTime, Date EndTime, int Level, String Mark) {
		HomeworkInfo homeworkInfo=new HomeworkInfo();
		homeworkInfo.setSubject(Info);
		homeworkInfo.setStartTime(StartTime);
		homeworkInfo.setEndTime(EndTime);
		homeworkInfo.setLevel(Level);
		homeworkInfo.setMark(Mark);
		return homeworkInfo;
	}
}
