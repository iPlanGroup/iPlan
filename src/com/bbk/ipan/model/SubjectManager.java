package com.bbk.ipan.model;

import java.util.Date;
import java.util.List;

public  class SubjectManager {
public  List<SubjectInfo> getAllSubjet(){
	return null;
	
}
public  TermInfo CreateNewTerm(Date StartTime,Date EndTime) {
	TermInfo term=new TermInfo();
	term.setStartTime(StartTime);
	term.setEndTime(EndTime);
	return term;
}
public SubjectInfo CreateNewSubject(String name,Date time,Date notifytime,String place,String teacher,String mark){
	SubjectInfo info=new SubjectInfo();
	info.setName(name);
	info.setNotifyTime(time);
	info.setTime(time);
	info.setPlace(place);
	info.setTeacher(teacher);
	info.setMark(mark);
	return info;
}
}
