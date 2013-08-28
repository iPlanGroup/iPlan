package com.bbk.model;

import java.util.Date;


public class VacationManager {
	/**
	 * 
	* @Title: CreateNewVacation  
	* @Description: TODO(新建一个假期)  
	* @param @param StartTime
	* @param @param EndTime
	* @param @return    学期信息  
	* @return TermInfo    返回类型  
	* @throws
	 */
public TermInfo CreateNewVacation(Date StartTime,Date EndTime){
	TermInfo info=new TermInfo();
	info.setStartTime(StartTime);
	info.setEndTime(EndTime);
	info.setIsTerm(false);
	return info;
}
}
