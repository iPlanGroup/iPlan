package com.bbk.iplan.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;

import com.bbk.iplan.R;
import com.bbk.iplan.app.IPlanApplication;
import com.bbk.iplan.data.EventInfo;
import com.bbk.iplan.ui.MainActivity;
import com.bbk.pop.SummaryExListAdapter;

public class Utils implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener
{
	private static SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd");
    
	private String[] subjectArr={"语文","数学","英语","物理","化学", "生物","历史","地理","生物"};
    private String[] porityArr = {"高", "中", "低"};
    
    public List<HashMap<String, String>> groupList = new ArrayList<HashMap<String, String>>();
    public List<List<HashMap<String, String>>> childList = new ArrayList<List<HashMap<String, String>>>();
    
    public static final String GROUP = "group";
    public static final String CHILD = "child";
    
    public SummaryExListAdapter adapter;
    public ExpandableListView elv;
    
    public PopupWindow summaryPW = null;
    
    public MainActivity mainActivity;
    
    public Utils(MainActivity context)
    {
    	this.mainActivity = context;
    }
    
	public static String DateForMate(Date time)
	{
		return mFormat.format(time);
	}
	
	public void showSummaryPop(Button summaryBtn)
	{
		for(int i = 0; i < 2; i++)
		{
			HashMap<String, String> groupMap = new HashMap<String, String>();
			groupMap.put(GROUP, "group " + i);
			groupList.add(groupMap);
			
			List<HashMap<String, String>> childItemList = new ArrayList<HashMap<String, String>>();
			for(int j = 0; j < 3; j++)
			{
				HashMap<String, String> childMap = new HashMap<String, String>();
				childMap.put(CHILD, "child " + j);
				childItemList.add(childMap);
			}
			childList.add(childItemList);
		}
		
		LayoutInflater inflater = (LayoutInflater)mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View myView = inflater.inflate(R.layout.summary, null);
		
//		summarySwitch = (Switch)myView.findViewById(R.id.finsh_or_un_sw);
//		summarySwitch.setOnCheckedChangeListener(new SummSwitchListener());
		
		adapter = new SummaryExListAdapter(mainActivity, groupList, childList);
		elv = (ExpandableListView)myView.findViewById(R.id.summary_list);
		elv.setOnChildClickListener(this);
		elv.setOnGroupClickListener(this);
		elv.setAdapter(adapter);
		elv.setGroupIndicator(null);
//		elv.setDivider(null);
		
		
		summaryPW = new PopupWindow(myView, 360, 500, true);
		summaryPW.setOutsideTouchable(true);
		summaryPW.setBackgroundDrawable(new BitmapDrawable());
		summaryPW.showAsDropDown(summaryBtn);
	}
	
	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id)
	{
        System.out.println("group...............................");
		return false;
	}
	
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id)
	{
        System.out.println("group: " + groupPosition + " child: " + childPosition);
		return false;
	}
	public static final String MODE_SINGLE_TEXT = "单次课";
	public static final String MODE_LONGSUBJECT_TEXT = "定期课";
	public static final String MODE_EXAM_TEXT = "考试";
	
	/**
	 * 
	 * 判断是输入的时间是否是在定期课的时间里面
	 * 
	 * @param startDate
	 * @param endDate
	 * @param inputDate
	 * @return
	 */
	public static boolean isSubjectDate(Date startDate, Date endDate, Date inputDate)
	{
		
		if(inputDate.before(endDate) && inputDate.after(startDate))
		{
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * 判断是否是截止时间，这个时间要精确到秒
	 * @return
	 */
	public static boolean isDeadLine(Date dealLineDate)
	{
		//当前时间,判断是否是截止时间，只需判断这个时间是否大于dealline
		
		Date date = new Date(System.currentTimeMillis());
		
		if(date.after(dealLineDate))
		{
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * 
	 * 判断输入的时间是否是
	 * @param inputDate
	 * @param localDate
	 * @return
	 */
	public static boolean isLocalDate(Date inputDate, Date localDate)
	{
		
		boolean isToday = false;
		
		int inputYear = inputDate.getYear();
		int inputMon = inputDate.getMonth();
		int inputDay = inputDate.getDay();
		
		int localYear = localDate.getYear();
		int localMon = localDate.getMonth();
		int localDay = localDate.getDay();
		
		if(inputYear == localYear && inputMon == localMon && inputDay == localDay)
		{
			isToday = true;
		}
		
		return isToday;
		
	}
	
	/**
	 * 获得数据库最大的ID
	 * @param tableName
	 * @return
	 */
	public static int getMaxId(String tableName)
	{

		int id = 1;

		SQLiteDatabase db = IPlanApplication.getDataBaseHelper()
				.getReadableDatabase();

		Cursor cursor = db.query(tableName, null, null, null, null, null, "_id DESC");
		if(cursor.moveToNext())
		{
		  id = cursor.getInt(cursor.getColumnIndex("_id"));
		}
		
		db.close();
		return id;
		
	}
	
	public static String getModeName(int mode)
	{

		switch (mode)
		{
			case EventInfo.MODE_SINGLE:

				return MODE_SINGLE_TEXT;
			case EventInfo.MODE_LONGSUBJECT:

				return MODE_LONGSUBJECT_TEXT;
			case EventInfo.MODE_EXAM:

				return MODE_EXAM_TEXT;

			default:
				break;
		}

		return null;

	}

	public static int getMode(String type)
	{

		if (type.equals(MODE_SINGLE_TEXT))
		{
			return EventInfo.MODE_SINGLE;
		}

		if (type.equals(MODE_LONGSUBJECT_TEXT))
		{
			return EventInfo.MODE_LONGSUBJECT;
		}

		if (type.equals(MODE_EXAM_TEXT))
		{
			return EventInfo.MODE_EXAM;
		}

		return 0;

	}
}
