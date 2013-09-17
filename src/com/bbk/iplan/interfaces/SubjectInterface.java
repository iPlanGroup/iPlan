package com.bbk.iplan.interfaces;

import java.util.Date;
import java.util.List;

import android.content.ContentValues;

import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.data.TermInfo;

public interface SubjectInterface {
	
	public static final int MODE_STRING=1;
	public static final int MODE_LONG=2;
	public static final int MODE_INT=3;
	public static final int MODE_BOOLEAN=4;
	/**
	 * 获取所有科目
	 * @return
	 */
	public List<SubjectInfo> getAllSubject(int ID); 
	/**
	 * 获取当天科目
	 * @param time
	 * @return
	 */
	public List<SubjectInfo> getLocalSubject(Date time);  
	/**
	 * 创建长期科目
	 * @param name
	 * @param time
	 * @param notifytime
	 * @param place
	 * @param teacher
	 * @param mark
	 * @return
	 */
	public SubjectInfo CreateLongSubject(String name, Date time,int[] week_time,
			Date notifytime, String place, String teacher, String mark,TermInfo termInfo);  
	/**
	 * 删除长期课信息
	 * @param id
	 * @return
	 */
	
	public boolean DeleteSubjectInfo(int id);
	/**
	 * 
	 * @Title: modifySubjectInfo 
	 * @Description: 修改课程信息
	 * @param @param id
	 * @param @param column
	 * @param @param value
	 * @param @param mode    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	public void modifySubjectInfo(int id,ContentValues contentValues);
	
	/**
	 * 
	 * @Title: getSubjectInfosByID 
	 * @Description: 通过学期的ID查找相关的课程信息 
	 * @param @param id
	 * @param @return    设定文件 
	 * @return List<SubjectInfo>    返回类型 
	 * @throws
	 */
	public List<SubjectInfo> getSubjectInfosByVacationID(int id);
}
