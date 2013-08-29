package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

public  class EventManager {
	public  List<EventInfo> getAllEventInfo() {
		return null;
	}
	public  EventInfo CreateExam(int Mode,String name,Date remindTime,String mark,Date StartTime,Date EndTime) {
		EventInfo eventInfo=new EventInfo();
		eventInfo.setMode(Mode);
		eventInfo.setName(name);
		eventInfo.setRemindTime(remindTime);
		eventInfo.setMark(mark);
		eventInfo.setStatrTime(StartTime);
		eventInfo.setEndTime(EndTime);
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
		return eventInfo;
	}
	public  void Recommand() {
	}
}
