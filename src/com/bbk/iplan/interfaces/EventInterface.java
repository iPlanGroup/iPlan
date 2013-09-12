package com.bbk.iplan.interfaces;

import java.util.Date;
import java.util.List;

import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.data.TermInfo;

public interface EventInterface {
	public static final int MODE_STRING=1;
	public static final int MODE_LONG=2;
	public static final int MODE_INT=3;
	public static final int MODE_BOOLEAN=4;
	
	/**
	 * 获取所有事件,包括考试和单次课
	 * @return
	 */
	public List<EventInfo> getAllEventInfo(); 
	/**
	 * 创建考试信息
	 * @param Mode
	 * @param name
	 * @param remindTime
	 * @param mark
	 * @param StartTime
	 * @param EndTime
	 * @return
	 */
	public EventInfo CreateExam(int Mode, String name, Date remindTime,
			String mark, Date StartTime, Date EndTime); 
	/**
	 * 创建单次课信息
	 * @param Mode
	 * @param name
	 * @param remindTime
	 * @param mark
	 * @param term
	 * @param subject
	 * @return
	 */
	public EventInfo CreateSingleSubject(int Mode, String name,
			Date remindTime, String mark, TermInfo term, SubjectInfo subject);
	/**
	 * 删除事件信息
	 * @param id
	 * @return
	 */
	public boolean DeleteEventInfo(int id);
	
	public void ModifyColumn(int id,String column,Object value,int Mode);

}
