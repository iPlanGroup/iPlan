package com.bbk.iplan.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bbk.UI.R;
import com.bbk.flipview.library.flipcontrol.FlipViewController;
import com.bbk.flipview.library.fliputils.TravelAdapter;
import com.bbk.iplan.model.SystemManager;
import com.bbk.pop.SummaryExListAdapter;

public class MainActivity extends ActivityGroup implements OnClickListener, ExpandableListView.OnGroupClickListener,
    ExpandableListView.OnChildClickListener{
	private SystemManager manager;
	private RelativeLayout container = null;
	
	private ImageView addPlanBtn = null;
	private ImageView addHWBtn = null;
	private Button summaryBtn = null;
	private int[] hwLocationArr = {100, 200};
	
    public List<HashMap<String, String>> groupList = new ArrayList<HashMap<String, String>>();
    public List<List<HashMap<String, String>>> childList = new ArrayList<List<HashMap<String, String>>>();
    
    public static final String GROUP = "group";
    public static final String CHILD = "child";
      
    public SummaryExListAdapter adapter;
    public ExpandableListView elv;
    
    public PopupWindow summaryPW = null;
    public PopupWindow addPlanPM = null;
    
    public TextView showSubjectTV = null;
    public Spinner selectSubjectSp = null;
    private ArrayAdapter subjectSpAdapter = null;
    private String[] subjectArr={"语文","数学","英语","物理","化学", "生物","历史","地理","生物"};
    private String[] porityArr = {"高", "中", "低"};
    private Switch summarySwitch = null;
    
    private TextView popDateBtn = null;
    private TextView popSubjectBtn = null;
    private TextView popPorityBtn = null;
    
	private FlipViewController flipView;
	
	public List<HashMap<String, Object>> bookDataList = null;
	public SimpleAdapter bookDataAdapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.plan_main);
		
		getSystemService(WINDOW_SERVICE);
		manager = SystemManager.getInstance();
		manager.getSystemManager(SystemManager.MODE_EVENT);
		
		container = (RelativeLayout) findViewById(R.id.dayplanLayout);
		Button dayplanButton = (Button) findViewById(R.id.dayplanbtn);
		Button weekplanButton = (Button) findViewById(R.id.weekplanbtn);

        bookDataList = getBookDataList();
		bookDataAdapter = getBookDataAdapter(bookDataList);

		
	    flipView = (FlipViewController)findViewById(R.id.flip_view);
//	    flipView.setAdapter(new TravelAdapter(this));
	    flipView.setAdapter(new TravelAdapter(this, bookDataAdapter));
	    flipView.setOnViewFlipListener(new FlipViewController.ViewFlipListener() 
	    {
	        @Override
	        public void onViewFlipped(View view, int position) 
	        {
	            Toast.makeText(view.getContext(), "当前页码: " + position, Toast.LENGTH_SHORT).show();
	        }
	      });
	    
//		addPlanBtn = (ImageView)findViewById(R.id.add_plan_btn);
//		addHWBtn = (ImageView)findViewById(R.id.add_hw_btn);
		summaryBtn = (Button)findViewById(R.id.summary_btn);
		
//		addPlanBtn.setOnClickListener(this);
//		addHWBtn.setOnClickListener(this);
		summaryBtn.setOnClickListener(this);
		
		dayplanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"Module1",
						new Intent(MainActivity.this, DayPlanActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());

			}
		});
		weekplanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				container.removeAllViews();
				container.addView(getLocalActivityManager().startActivity(
						"Module1",
						new Intent(MainActivity.this, WeekPlanActivity.class)
								.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
						.getDecorView());
			}
		});

	}
	
	@Override
	public void onClick(View v)
	{
        switch(v.getId())
        {
           case R.id.summary_btn:
           {
        	   showSummaryPop();
        	   break;
           }
        }
	}
	
	public SimpleAdapter getBookDataAdapter(List<HashMap<String, Object>> dataList)
	{
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,dataList,
            R.layout.list_item,
            new String[] {"ItemImage","ItemTitle", "ItemText"}, 
            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText}
        );
        
        return listItemAdapter;
	}
	
	public ArrayList<HashMap<String, Object>> getBookDataList()
	{
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<10;i++)
        {
        	HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("ItemImage", R.drawable.ic_launcher);//图像资源的ID
        	map.put("ItemTitle", "Level "+i);
        	map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");
        	listItem.add(map);
        }
        
        return listItem;
	}
	
	public void showAddPlanPop()
	{
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
	
	public void showAddHWPop()
	{
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View myView = inflater.inflate(R.layout.add_hw_pop, null);
		
		hwLocationArr[0] = 458;
		hwLocationArr[1] = 140;
		
	    showDateDialog(myView);
	    showSubjectDialog(myView);
	    showPorityDialog(myView);
	    
		PopupWindow pw = new PopupWindow(myView, 300, 315, true);
		pw.setOutsideTouchable(true);
		pw.setBackgroundDrawable(new BitmapDrawable());
		pw.showAtLocation(myView, Gravity.NO_GRAVITY, hwLocationArr[0], hwLocationArr[1]); 
	    
	    pw.showAsDropDown(addHWBtn);
	}
	
	public void showPorityDialog(View myView)
	{
		popPorityBtn = (TextView)myView.findViewById(R.id.select_homework_pority_tv);
		
		popPorityBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new AlertDialog.Builder(MainActivity.this)
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
				new AlertDialog.Builder(MainActivity.this)
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
				new DatePickerDialog(MainActivity.this, new OnDateSetListener()
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
	
	public void showSummaryPop()
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
		
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View myView = inflater.inflate(R.layout.summary, null);
		
		summarySwitch = (Switch)myView.findViewById(R.id.finsh_or_un_sw);
		summarySwitch.setOnCheckedChangeListener(new SummSwitchListener());
		
		adapter = new SummaryExListAdapter(this, groupList, childList);
		elv = (ExpandableListView)myView.findViewById(R.id.summary_list);
		elv.setOnChildClickListener(this);
		elv.setOnGroupClickListener(this);
		elv.setAdapter(adapter);
		elv.setGroupIndicator(null);
//		elv.setDivider(null);
		
		
		summaryPW = new PopupWindow(myView, 250, 350, true);
		summaryPW.setOutsideTouchable(true);
		summaryPW.setBackgroundDrawable(new BitmapDrawable());
		summaryPW.showAsDropDown(summaryBtn);
	}
	
	class SummSwitchListener implements CompoundButton.OnCheckedChangeListener
	{
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked)
		{
             System.out.println(isChecked);			
		}
	}
	//使用数组形式操作
	class SubjectSpSelectedListener implements OnItemSelectedListener
	{

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) 
		{
			showSubjectTV.setText("学科： "+subjectArr[arg2]);
//			showSubjectTV.setText("subject: " + subjectSpAdapter.getItem(arg2));
		}

		public void onNothingSelected(AdapterView<?> arg0) 
		{
			System.out.println("git test two2222222222222222222222222222222222222222");
		}
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
	
	public void setFullscreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}