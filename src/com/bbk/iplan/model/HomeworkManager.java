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
	public HomeworkInfo CreateHomework(String name,
			Date StartTime, Date EndTime, int Level, String Mark) {
		HomeworkInfo homeworkInfo=new HomeworkInfo();
		homeworkInfo.setSubjectName(name);
		homeworkInfo.setStartTime(StartTime);
		homeworkInfo.setEndTime(EndTime);
		homeworkInfo.setLevel(Level);
		homeworkInfo.setMark(Mark);
		return homeworkInfo;
	}
}
