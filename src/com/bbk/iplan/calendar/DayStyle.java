package com.bbk.iplan.calendar;

import java.util.Calendar;

public class DayStyle {
	// methods

	private static String[] getWeekDayNames() {
		String[] vec = new String[10];


		vec[Calendar.SUNDAY] = "日";
		vec[Calendar.MONDAY] = "一";
		vec[Calendar.TUESDAY] = "二"; 
		vec[Calendar.WEDNESDAY] = "三"; 
		vec[Calendar.THURSDAY] = "四"; 
		vec[Calendar.FRIDAY] = "五"; 
		vec[Calendar.SATURDAY] ="六";
		return vec;
	}

	public static String getWeekDayName(int iDay) {
		return vecStrWeekDayNames[iDay];
	}

	// fields
	private final static String[] vecStrWeekDayNames = getWeekDayNames();

	// fields
	public final static int iColorFrameHeader = 0xff1d99f9;
	public final static int iColorFrameHeaderHoliday = 0xff1d99f9;
	public final static int iColorTextHeader = 0xffffffff;
	public final static int iColorTextHeaderHoliday = 0xffffffff;

	public final static int iColorText = 0xffffffff;
	public final static int iColorBkg = 0xff272a31;
	public final static int iColorTextHoliday = 0xffffffff;
	public final static int iColorBkgHoliday = 0xff272a31;

	public final static int iColorTextToday = 0xffa9abab;
	public final static int iColorBkgToday = 0xff1d99f9;

	public final static int iColorTextSelected = 0xffa9abab;
	public final static int iColorBkgSelectedLight = 0xff1d99f9;
	public final static int iColorBkgSelectedDark = 0xff1d99f9;

	public final static int iColorTextFocused = 0xffa9abab;
	public final static int iColorBkgFocusLight = 0xff1d99f9;
	public final static int iColorBkgFocusDark = 0xff1d99f9;

	// methods
	public static int getColorFrameHeader(boolean bHoliday) {
		if (bHoliday)
			return iColorFrameHeaderHoliday;
		return iColorFrameHeader;
	}

	public static int getColorTextHeader(boolean bHoliday) {
		if (bHoliday)
			return iColorTextHeaderHoliday;
		return iColorTextHeader;
	}

	public static int getColorText(boolean bHoliday, boolean bToday) {
		if (bToday)
			return iColorTextToday;
		if (bHoliday)
			return iColorTextHoliday;
		return iColorText;
	}

	public static int getColorBkg(boolean bHoliday, boolean bToday) {
		if (bToday)
			return iColorBkgToday;
		if (bHoliday)
			return iColorBkgHoliday;
		return iColorBkg;
	}

	public static int getWeekDay(int index, int iFirstDayOfWeek) {
		int iWeekDay = -1;

		if (iFirstDayOfWeek == Calendar.MONDAY) {
			iWeekDay = index + Calendar.MONDAY;
			if (iWeekDay > Calendar.SATURDAY)
				iWeekDay = Calendar.SUNDAY;
		}

		if (iFirstDayOfWeek == Calendar.SUNDAY) {
			iWeekDay = index + Calendar.SUNDAY;
		}

		return iWeekDay;
	}

}
