package com.bbk.model;

import java.util.Date;

public class VacationManager {
<<<<<<< HEAD
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
=======
>>>>>>> 53890ecb3c13a9c152b319036411c826a7a31cba
public TermInfo CreateNewVacation(Date StartTime,Date EndTime){
	TermInfo info=new TermInfo();
	info.setStartTime(StartTime);
	info.setEndTime(EndTime);
	info.setIsTerm(false);
	return info;
}
}
