package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

import com.bbk.iplan.data.HomeworkInfo;

public class HomeworkManager {
	public List<HomeworkInfo> getUnFinished() {
		return null;
	}
	public List<HomeworkInfo> getLocalHomework(Date time)
	{
		return null;
	}
	public HomeworkInfo CreateHomework(String name,String subjectName,
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
