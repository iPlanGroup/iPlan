package com.bbk.iplan.model;

import java.util.List;

import com.bbk.iplan.dao.CountInterfaceImpl;
import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.data.HomeworkInfo;
import com.bbk.iplan.interfaces.CountInterface;

public  class Count {
	private CountInterface mInterface=CountInterfaceImpl.getInstance();
	public  List<HomeworkInfo> getCountHomewokeInfo(){
		return mInterface.getCountHomeworkInfo();
	}
	public  List<EventInfo> getCountEventInfo(){
		return mInterface.getCountEventInfo();
	}
	public  List<HomeworkInfo> getCountHomewokeInfoByLevel(){
		return mInterface.getCountHomeworkInfoByLevel();
	}

}
