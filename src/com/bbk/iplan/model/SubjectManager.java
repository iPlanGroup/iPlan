package com.bbk.iplan.model;

import java.util.Date;
import java.util.List;

public class SubjectManager {
	public List<SubjectInfo> getAllSubjet() {
		return null;

	}

	public List<SubjectInfo> getLocalSubject(Date time) {
		return null;
	}

	public SubjectInfo CreateLongSubject(String name, Date time,
			Date notifytime, String place, String teacher, String mark) {
		SubjectInfo info = new SubjectInfo();
		info.setName(name);
		info.setNotifyTime(time);
		info.setTime(time);
		info.setPlace(place);
		info.setTeacher(teacher);
		info.setMark(mark);
		return info;
	}
}
