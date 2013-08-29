package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;


public class VacationManager {
	/**
	 * 
	* @Title: CreateNewVacation  
	* @Description: TODO(新建一个假期)  
	* @param @param StartTime
	* @param @param EndTime
	* @param @param isVacation
	* @param @return    学期信息  
	* @return TermInfo    返回类型  
	* @throws
	 */
	public List<TermInfo> getAllVacation()
	{
		return null;
	}
	public TermInfo CreateNewVacation(Date StartTime,Date EndTime,boolean isVacation){
	TermInfo info=new TermInfo();
	info.setStartTime(StartTime);
	info.setEndTime(EndTime);
	info.setIsTerm(isVacation);
	return info;
}

}
