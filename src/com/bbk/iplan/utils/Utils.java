package com.bbk.iplan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils{
	private static SimpleDateFormat mFormat=new SimpleDateFormat("yyyy/MM/dd");
	public static String DateForMate(Date time){
		return mFormat.format(time);
	}
}
