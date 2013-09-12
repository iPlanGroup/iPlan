package com.bbk.iplan.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;

import com.bbk.iplan.R;
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
}
