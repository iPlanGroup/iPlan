package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

public  class EventManager {
	public  List<EventInfo> getAllEventInfo() {
		return null;
	}
	public  EventInfo CreateNewEvent(int Mode,String name,Date remindTime,String mark) {
		EventInfo eventInfo=new EventInfo();
		eventInfo.setMode(Mode);
		eventInfo.setName(name);
		eventInfo.setRemindTime(remindTime);
		eventInfo.setMark(mark);
		return eventInfo;
	}
	public  EventInfo CreateNewEvent(int Mode,String name,Date remindTime,String mark,TermInfo term,SubjectInfo subject) {
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
