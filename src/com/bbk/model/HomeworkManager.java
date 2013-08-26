package com.bbk.model;

import java.util.Date;

public class HomeworkManager {
	public void getUnFinished() {

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
