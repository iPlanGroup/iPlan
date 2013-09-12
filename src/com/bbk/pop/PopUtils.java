package com.bbk.pop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bbk.iplan.R;
import com.bbk.iplan.ui.DayPlanActivity;
import com.bbk.iplan.ui.MainActivity;

public class PopUtils implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener
{
    public List<HashMap<String, String>> groupList = new ArrayList<HashMap<String, String>>();
    public List<List<HashMap<String, String>>> childList = new ArrayList<List<HashMap<String, String>>>();
    
    public static final String GROUP = "group";
    public static final String CHILD = "child";
	private int[] hwLocationArr = {100, 200};
    public SummaryExListAdapter adapter;
    public ExpandableListView elv;
    public PopupWindow summaryPW = null;
    
    private TextView popDateBtn = null;
    private TextView popSubjectBtn = null;
    private TextView popPorityBtn = null;
    
    private String[] subjectArr={"语文","数学","英语","物理","化学", "生物","历史","地理","生物"};
    private String[] porityArr = {"高", "中", "低"};
    
    private DayPlanActivity dayPlanAc;
    public PopupWindow addPlanPM = null;
//	private ImageView addPlanBtn = null;
    
    public PopUtils(DayPlanActivity context)
    {
    	this.dayPlanAc = context;
    }
    
	public void showAddHWPop(ImageView addHWBtn)
	{
		LayoutInflater inflater = (LayoutInflater)dayPlanAc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View myView = inflater.inflate(R.layout.add_hw_pop, null);
		
		hwLocationArr[0] = 438;
		hwLocationArr[1] = 140;
		
	    showDateDialog(myView);
	    showSubjectDialog(myView);
	    showPorityDialog(myView);
	    
		PopupWindow pw = new PopupWindow(myView, 350, 380, true);
		pw.setOutsideTouchable(true);
		pw.setBackgroundDrawable(new BitmapDrawable());
		pw.showAtLocation(myView, Gravity.NO_GRAVITY, hwLocationArr[0], hwLocationArr[1]); 
	    
	    pw.showAsDropDown(addHWBtn);
	}

	public void showAddClassPop(ImageView addHWBtn)
	{
		LayoutInflater inflater = (LayoutInflater)dayPlanAc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View myView = inflater.inflate(R.layout.add_class_pop, null);
		
		hwLocationArr[0] = 438;
		hwLocationArr[1] = 140;
		
//	    showDateDialog(myView);
//	    showSubjectDialog(myView);
//	    showPorityDialog(myView);
	    
		PopupWindow pw = new PopupWindow(myView, 350, 550, true);
		pw.setOutsideTouchable(true);
		pw.setBackgroundDrawable(new BitmapDrawable());
		pw.showAtLocation(myView, Gravity.NO_GRAVITY, hwLocationArr[0], hwLocationArr[1]); 
	    
	    pw.showAsDropDown(addHWBtn);
	}
	
	public void showAddExamPop(ImageView addHWBtn)
	{
		LayoutInflater inflater = (LayoutInflater)dayPlanAc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View myView = inflater.inflate(R.layout.add_exam_pop, null);
		
		hwLocationArr[0] = 438;
		hwLocationArr[1] = 140;
		
//	    showDateDialog(myView);
//	    showSubjectDialog(myView);
//	    showPorityDialog(myView);
	    
		PopupWindow pw = new PopupWindow(myView, 350, 400, true);
		pw.setOutsideTouchable(true);
		pw.setBackgroundDrawable(new BitmapDrawable());
		pw.showAtLocation(myView, Gravity.NO_GRAVITY, hwLocationArr[0], hwLocationArr[1]); 
	    
	    pw.showAsDropDown(addHWBtn);
	}
	
	public void showAddHolidayPop(ImageView addHWBtn)
	{
		LayoutInflater inflater = (LayoutInflater)dayPlanAc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View myView = inflater.inflate(R.layout.add_holiday_pop, null);
		
		hwLocationArr[0] = 438;
		hwLocationArr[1] = 140;
		
//	    showDateDialog(myView);
//	    showSubjectDialog(myView);
//	    showPorityDialog(myView);
	    
		PopupWindow pw = new PopupWindow(myView, 350, 310, true);
		pw.setOutsideTouchable(true);
		pw.setBackgroundDrawable(new BitmapDrawable());
		pw.showAtLocation(myView, Gravity.NO_GRAVITY, hwLocationArr[0], hwLocationArr[1]); 
	    
	    pw.showAsDropDown(addHWBtn);
	}
	
	
	public void showSummaryPop(MainActivity mainActivity, Button summaryBtn)
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
	
	public void showPorityDialog(View myView)
	{
		popPorityBtn = (TextView)myView.findViewById(R.id.select_homework_pority_tv);
		
		popPorityBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new AlertDialog.Builder(dayPlanAc)
				.setTitle("优先级")
				.setIcon(android.R.drawable.ic_dialog_info)                
				.setSingleChoiceItems(porityArr, 0, 
				  new DialogInterface.OnClickListener()
				  {
				     public void onClick(DialogInterface dialog, int which) {
				    	popPorityBtn.setText(porityArr[which]);
				        dialog.dismiss();
				     }
				  }
				)
				.show();
			}
		});
	}
	
	public void showSubjectDialog(View myView)
	{
		popSubjectBtn = (TextView)myView.findViewById(R.id.selected_subject_tv);
		
		
		popSubjectBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new AlertDialog.Builder(dayPlanAc)
				.setTitle("学科")
				.setIcon(android.R.drawable.ic_dialog_info)                
				.setSingleChoiceItems(subjectArr, 0, 
				  new DialogInterface.OnClickListener()
				  {
				     public void onClick(DialogInterface dialog, int which) {
				    	popSubjectBtn.setText(subjectArr[which]);
				        dialog.dismiss();
				     }
				  }
				)
				.show();				
			}
		});
	}
	  
	public void showDateDialog(View myView)
	{
		final Calendar cd = Calendar.getInstance();
		Date date = new Date();
	    popDateBtn = (TextView)myView.findViewById(R.id.date_picker_btn);
	    
	    popDateBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				new DatePickerDialog(dayPlanAc, new OnDateSetListener()
				{
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
					{
						popDateBtn.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
					}
				}, cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd
						.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
	}
	
	public void showAddPlanPop(ImageView addPlanBtn)
	{
		LayoutInflater inflater = (LayoutInflater)dayPlanAc.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View myView = inflater.inflate(R.layout.add_plan_pop, null);
		
		Button addClassBtn = (Button)myView.findViewById(R.id.new_class_btn);
		Button addExamBtn = (Button)myView.findViewById(R.id.new_exam_btn);
		Button addHolidayBtn = (Button)myView.findViewById(R.id.new_holiday_btn);
		
		addClassBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
                 System.out.println("add class");				
			}
		});
		
		addExamBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				System.out.println("add exam");
			}
		});
		
		addHolidayBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				System.out.println("add holiday");
			}
		});
		
		addPlanPM = new PopupWindow(myView, 250, 205, true);
		addPlanPM.setBackgroundDrawable(new BitmapDrawable());
	    addPlanPM.setOutsideTouchable(true);
		addPlanPM.showAsDropDown(addPlanBtn);
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
