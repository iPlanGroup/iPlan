package com.bbk.model;

import java.util.Date;
/**
 * 
* @ClassName: EventInfo  
* @Description: TODO(浜嬩欢鍩烘湰淇℃伅)  
* @author Lee
* @date 2013-8-26 涓嬪崍8:17:19  
*
 */
public class EventInfo {
public static final int MODE_SINGLE=1;     //浜嬩欢绫诲瀷:鍗曟璇�
public static final int MODE_EXAM=2;       //浜嬩欢绫诲瀷:鑰冭瘯
public static final int MODE_LONGSUBJECT=3;//浜嬩欢绫诲瀷:闀挎湡璇�
public int Mode;                           //浜嬩欢绫诲瀷
public String name;                        //浜嬩欢鍚嶇О
public String mark;                        //澶囨敞
public Date remindTime;                    //鎻愰啋鏃堕棿
public TermInfo term;                      //鎵�湪瀛︽湡
public SubjectInfo subject;                //绉戠洰
public Date examTime;                      //鑰冭瘯鏃堕棿



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
