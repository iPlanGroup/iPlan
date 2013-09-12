package com.bbk.iplan.interfaces;

import java.util.Date;
import java.util.List;

import com.bbk.iplan.data.HomeworkInfo;

public interface HomeworkInterface {
	public static final int MODE_STRING=1;
	public static final int MODE_LONG=2;
	public static final int MODE_INT=3;
	public static final int MODE_BOOLEAN=4;
	/**
	 * 获取未完成的作业
	 * @return
	 */
	public List<HomeworkInfo> getUnFinished();
	/**
	 * 获取当天的作业
	 * @param time
	 * @return
	 */
	public List<HomeworkInfo> getLocalHomework(Date time);
	/**
	 * 创建新的作业
	 * @param name
	 * @param StartTime
	 * @param EndTime
	 * @param Level
	 * @param Mark
	 * @return
	 */
	public HomeworkInfo CreateHomework(String name,String subjectName,
			Date StartTime, Date EndTime, int Level, String Mark);
	/**
	 * 删除家庭作业信息
	 * @param id
	 * @return
	 */
	public boolean DeleteHomeworkInfo(int id);
	
	public void modifyHomeworkInfo(int id,String column,Object value,int mode);
}
