package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.data.SubjectInfo;
import com.bbk.iplan.data.TermInfo;
import com.bbk.iplan.interfaces.EventInterface;
import com.bbk.iplan.interfaces.ManagerInterface;

public  class EventManager implements ManagerInterface{
	private EventInterface mInterface;
	public  List<EventInfo> getAllEventInfo() {
		
		return mInterface.getAllEventInfo();
	}
	public  EventInfo CreateExam(int Mode,String name,Date remindTime,String mark,Date StartTime,Date EndTime) {
		EventInfo eventInfo=new EventInfo();
		eventInfo.setMode(Mode);
		eventInfo.setName(name);
		eventInfo.setRemindTime(remindTime);
		eventInfo.setMark(mark);
		eventInfo.setStartTime(StartTime);
		eventInfo.setEndTime(EndTime);
		mInterface.CreateExam(Mode, name, remindTime, mark, StartTime, EndTime);
		return eventInfo;
	}
	public  EventInfo CreateSingleSubject(int Mode,String name,Date remindTime,String mark,TermInfo term,SubjectInfo subject) {
		EventInfo eventInfo=new EventInfo();
		eventInfo.setMode(Mode);
		eventInfo.setName(name);
		eventInfo.setRemindTime(remindTime);
		eventInfo.setMark(mark);
		eventInfo.setTerm(term);
		eventInfo.setSubject(subject);
		mInterface.CreateSingleSubject(Mode, name, remindTime, mark, term, subject);
		return eventInfo;
	}
	public  void Recommand() {
	}
	@Override
	public boolean DeleteInfo(int id) {
		// TODO Auto-generated method stub
		return mInterface.DeleteEventInfo(id);
	}
	@Override
	public void ModifyInfo(int id, String column, Object value, int mode) {
		// TODO Auto-generated method stub
		mInterface.ModifyEventInfo(id, column, value, mode);
	}

}
