package com.bbk.model;

import java.util.Date;

public class VacationManager {
public TermInfo CreateNewVacation(Date StartTime,Date EndTime){
	TermInfo info=new TermInfo();
	info.setStartTime(StartTime);
	info.setEndTime(EndTime);
	info.setIsTerm(false);
	return info;
}
}
