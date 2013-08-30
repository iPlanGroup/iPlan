package com.bbk.iplan.app;

import android.app.Application;

import com.bbk.iplan.dao.IPlanDataBaseHelper;


/**
 * @since 2013/8/26
 *
 */
public class IPlanApplication extends Application
{

	private static IPlanDataBaseHelper mDataBaseHelper;
	
	public static IPlanDataBaseHelper getDataBaseHelper()
	{
		return mDataBaseHelper;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		mDataBaseHelper = new IPlanDataBaseHelper(this, "", null, 0);
		
	}

	@Override
	public void onLowMemory()
	{
		super.onLowMemory();
	}

	
}
