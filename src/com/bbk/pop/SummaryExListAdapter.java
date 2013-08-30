package com.bbk.pop;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbk.UI.MainActivity;
import com.bbk.UI.R;


public class SummaryExListAdapter extends BaseExpandableListAdapter
{
    private MainActivity mainActivity;
    private List<HashMap<String, String>> groupList;
    private List<List<HashMap<String, String>	>> childList;
    
	public SummaryExListAdapter(MainActivity mainActivity,
			List<HashMap<String, String>> goupList,
			List<List<HashMap<String, String>>> childList)
	{
		super();
		this.mainActivity = mainActivity;
		this.groupList = goupList;
		this.childList = childList;
	}

	@Override
	public Object getChild(int arg0, int arg1)
	{
		return childList.get(arg0).get(arg1).get(MainActivity.CHILD).toString();
	}

	@Override
	public long getChildId(int arg0, int arg1)
	{
		return arg1;
	}

	@Override
	public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
			ViewGroup arg4)
	{
		View view = arg3;
		
		if(null == view)
		{
			LayoutInflater inflater = (LayoutInflater)mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.summary_child_list, null);
		}

		TextView childNameTV = (TextView)view.findViewById(R.id.child_name_tv);
		
		String childNameStr = childList.get(arg0).get(arg1).get(MainActivity.CHILD);
		
        childNameTV.setText(childNameStr);	
		return view;
	}

	@Override
	public int getChildrenCount(int arg0)
	{
		return childList.get(arg0).size();
	}

	@Override
	public Object getGroup(int arg0)
	{
		return groupList.get(arg0).get(MainActivity.GROUP).toString();
	}

	@Override
	public int getGroupCount()
	{
		return groupList.size();
	}

	@Override
	public long getGroupId(int arg0)
	{
		return arg0;
	}

	@Override
	public View getGroupView(int arg0, boolean arg1, View arg2, ViewGroup arg3)
	{
		View view = arg2;
		if(null == view)
		{
			LayoutInflater inflater = (LayoutInflater)mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.summary_group_list, null);
		}
		
		TextView groupNameTV = (TextView)view.findViewById(R.id.group_name_tv);
		ImageView groupPullIV = (ImageView)view.findViewById(R.id.group_pull_iv);
		
		String groupNameStr = groupList.get(arg0).get(MainActivity.GROUP);
		groupNameTV.setText(groupNameStr);
		
		if(arg1)
		{
			groupPullIV.setBackgroundResource(R.drawable.btn_browser);
		}
		else
		{
			groupPullIV.setBackgroundResource(R.drawable.btn_browser2);
		}
		
		return view;
	}

	@Override
	public boolean hasStableIds()
	{
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1)
	{
		return true;
	}

}
