package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

import com.bbk.iplan.data.HomeworkInfo;
import com.bbk.iplan.interfaces.HomeworkInterface;
import com.bbk.iplan.interfaces.ManagerInterface;

public class HomeworkManager implements ManagerInterface{
	private HomeworkInterface mInterface;
	public List<HomeworkInfo> getUnFinished() {
		return mInterface.getUnFinished();
	}
	public List<HomeworkInfo> getLocalHomework(Date time)
	{
		return mInterface.getLocalHomework(time);
	}
	public HomeworkInfo CreateHomework(String name,String subjectName,
			Date StartTime, Date EndTime, int Level, String Mark) {
		HomeworkInfo homeworkInfo=new HomeworkInfo();
		homeworkInfo.setSubjectName(name);
		homeworkInfo.setStartTime(StartTime);
		homeworkInfo.setEndTime(EndTime);
		homeworkInfo.setLevel(Level);
		homeworkInfo.setMark(Mark);
		mInterface.CreateHomework(name, subjectName, StartTime, EndTime, Level, Mark);
		return homeworkInfo;
	}
	@Override
	public boolean DeleteInfo(int id) {
		// TODO Auto-generated method stub
		return mInterface.DeleteHomeworkInfo(id);
	}
	@Override
	public void ModifyInfo(int id, String column, Object value, int mode) {
		// TODO Auto-generated method stub
		mInterface.modifyHomeworkInfo(id, column, value, mode);
	}
}
