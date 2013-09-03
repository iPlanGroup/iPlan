package com.bbk.iplan.interfaces;

import java.util.Date;
import java.util.List;

import com.bbk.iplan.data.SubjectInfo;

public interface SubjectInterface {
	/**
	 * 获取所有科目
	 * @return
	 */
	public List<SubjectInfo> getAllSubjet(); 
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
	public SubjectInfo CreateLongSubject(String name, Date time,
			Date notifytime, String place, String teacher, String mark);  
	/**
	 * 删除长期课信息
	 * @param id
	 * @return
	 */
	
	public boolean DeleteSubjectInfo(int id);
}
