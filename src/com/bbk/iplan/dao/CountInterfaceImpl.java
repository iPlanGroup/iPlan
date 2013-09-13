package com.bbk.iplan.dao;

import java.util.ArrayList;
import java.util.List;

import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.data.HomeworkInfo;
import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.interfaces.CountInterface;

public class CountInterfaceImpl implements CountInterface{

	private static CountInterfaceImpl mCountInterfaceImpl = new CountInterfaceImpl(); 
	
	private CountInterfaceImpl() {}
	
	public static CountInterfaceImpl getInstance()
	{
		return mCountInterfaceImpl;
	}
	
	@Override
	public List<HomeworkInfo> getCountHomeworkInfo() {
		
		return HomeWorkTable.getInstance().getAllHomework();
	}

	@Override
	public List<EventInfo> getCountEventInfo() {
		return EventTable.getInstance().getAllEventInfo();
	}

	@Override
	public List<HomeworkInfo> getCountHomeworkInfoByLevel() {
		
		List<HomeworkInfo> list = new ArrayList<HomeworkInfo>();
		list.addAll(HomeWorkTable.getHomeWorkByLevel(3));
		list.addAll(HomeWorkTable.getHomeWorkByLevel(2));
		list.addAll(HomeWorkTable.getHomeWorkByLevel(1));
		return list;
	}

	@Override
	public List<SubjectInfo> getCountSubjectInfos() {
		
		return SubjectTable.getInstance().getAllSubject()   ;
	}

}
