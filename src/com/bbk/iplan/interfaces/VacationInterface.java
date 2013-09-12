package com.bbk.iplan.interfaces;

import java.util.Date;
import java.util.List;

import com.bbk.iplan.data.TermInfo;

public interface VacationInterface {
	public static final int MODE_STRING=1;
	public static final int MODE_LONG=2;
	public static final int MODE_INT=3;
	public static final int MODE_BOOLEAN=4;
	/**
	 * 获取所有假期信息包括学期
	 * @return
	 */
	public List<TermInfo> getAllVacation();
	/**
	 * 创建一个新的假期或者学期
	 * @param StartTime
	 * @param EndTime
	 * @param isVacation
	 * @return
	 */
	public TermInfo CreateVacation(Date StartTime,Date EndTime,boolean isVacation);
	/**
	 * 删除假期或者学期信息
	 * @param id
	 * @return
	 */
	public boolean DeleteVacationInfo(int id);
	
	public void modifyVacationInfo(int id,String column,Object value,int mode);
}
