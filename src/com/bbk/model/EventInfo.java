package com.bbk.model;

import java.util.Date;

public class EventInfo {
public static final int MODE_SINGLE=1;
public static final int MODE_EXAM=2;
public static final int MODE_LONGSUBJECT=3;
public int Mode;
public String name;
public String mark;
public Date remindTime;
public TermInfo term;
public SubjectInfo subject;
public Date examTime;
public String getName() {
	return name;
}
public int getMode() {
	return Mode;
}
public void setMode(int mode) {
	Mode = mode;
}
public void setName(String name) {
	this.name = name;
}
public String getMark() {
	return mark;
}
public void setMark(String mark) {
	this.mark = mark;
}
public Date getRemindTime() {
	return remindTime;
}
public void setRemindTime(Date remindTime) {
	this.remindTime = remindTime;
}
public TermInfo getTerm() {
	return term;
}
public void setTerm(TermInfo term) {
	this.term = term;
}
public SubjectInfo getSubject() {
	return subject;
}
public void setSubject(SubjectInfo subject) {
	this.subject = subject;
}
public Date getExamTime() {
	return examTime;
}
public void setExamTime(Date examTime) {
	this.examTime = examTime;
}
}
