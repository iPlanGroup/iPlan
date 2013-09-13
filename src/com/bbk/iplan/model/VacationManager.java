package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

import android.content.ContentValues;

import com.bbk.iplan.data.TermInfo;
import com.bbk.iplan.interfaces.ManagerInterface;
import com.bbk.iplan.interfaces.VacationInterface;


public class VacationManager implements ManagerInterface{

	private VacationInterface mInterface;
	public List<TermInfo> getAllVacation()
	{
		
		return mInterface.getAllVacation();
	}
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
	public TermInfo CreateVacation(String name,Date StartTime,Date EndTime,boolean isVacation){
	TermInfo info=new TermInfo();
	info.setName(name);
	info.setStartTime(StartTime);
	info.setEndTime(EndTime);
	info.setIsTerm(isVacation);
	mInterface.CreateVacation(name,StartTime, EndTime, isVacation);
	return info;
}
	@Override
	public boolean DeleteInfo(int id) {
		// TODO Auto-generated method stub
		
		return mInterface.DeleteVacationInfo(id);
	}
	@Override
	public void ModifyInfo(int id, ContentValues contentValues) {
		// TODO Auto-generated method stub
		mInterface.modifyVacationInfo(id, contentValues);
	}

}
