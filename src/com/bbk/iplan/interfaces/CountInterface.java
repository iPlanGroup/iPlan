package com.bbk.iplan.interfaces;

import java.util.List;

import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.data.HomeworkInfo;
import com.bbk.iplan.data.SubjectInfo;

/** 
 * @Title: CountInterface.java 
 * @Package com.bbk.iplan.interfaces 
 * @Description: TODO(添加描述) 
 * @author A18ccms A18ccms_gmail_com 
 * @date 2013-9-12 下午03:13:27 
 * @version V1.0 
 */
public interface CountInterface {
	/**
	 * 
	 * @Title: getCountHomewokeInfo 
	 * @Description: 获取全部家庭作业信息
	 * @param @return    设定文件 
	 * @return List<HomeworkInfo>    返回类型 
	 * @throws
	 */
	public  List<HomeworkInfo> getCountHomeworkInfo();
	/**
	 * 
	 * @Title: getCountEventInfo 
	 * @Description: 获取全部单次课和考试信息
	 * @param @return    设定文件 
	 * @return List<EventInfo>    返回类型 
	 * @throws
	 */
	public  List<EventInfo> getCountEventInfo();
	/**
	 * 
	 * @Title: getCountHomewokeInfoByLevel 
	 * @Description: 通过优先级获取全部家庭作业信息
	 * @param @return    设定文件 
	 * @return List<HomeworkInfo>    返回类型 
	 * @throws
	 */
	public  List<HomeworkInfo> getCountHomeworkInfoByLevel();
	/**
	 * 
	 * @Title: getCountSubjectInfos 
	 * @Description: 获取课程信息(课程所在学期)
	 * @param @return    设定文件 
	 * @return List<SubjectInfo>    返回类型 
	 * @throws
	 */
	public  List<SubjectInfo> getCountSubjectInfos();
}
