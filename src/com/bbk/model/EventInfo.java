package com.bbk.model;

import java.util.Date;
/**
 * 
* @ClassName: EventInfo  
* @Description: TODO(事件基本信息)  
* @author Lee
* @date 2013-8-26 下午8:17:19  
*
 */
public class EventInfo {
public static final int MODE_SINGLE=1;     //事件类型:单次课
public static final int MODE_EXAM=2;       //事件类型:考试
public static final int MODE_LONGSUBJECT=3;//事件类型:长期课
public int Mode;                           //事件类型
public String name;                        //事件名称
public String mark;                        //备注
public Date remindTime;                    //提醒时间
public TermInfo term;                      //所在学期
public SubjectInfo subject;                //科目
public Date examTime;                      //考试时间

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
