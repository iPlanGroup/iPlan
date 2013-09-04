package com.bbk.iplan.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.CalendarView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bbk.flipview.library.flipcontrol.FlipViewController;
import com.bbk.flipview.library.fliputils.TravelAdapter;
import com.bbk.iplan.R;
import com.bbk.iplan.calendar.DateWidget;
import com.bbk.iplan.model.SystemManager;

public class DayPlanActivity extends Activity {
	private SystemManager manager;
	// private RelativeLayout container = null;
	// private LinearLayout dateView = null;
	private RelativeLayout dateView = null;
	// private Button scrollbtn;
	private CustomView mCustomView;
	private CustomView mHwDetails;
	private CalendarView mCalendarView;
	private FlipViewController flipView;
	public List<HashMap<String, Object>> bookDataList = null;
	public SimpleAdapter bookDataAdapter = null;

	// private ImageView eventsaddbtn;
	// private ImageView hwaddbtn;
	// private TextView hweditbtn;
	// private ListView eventsListview;
	// private ListView hwListView;

	// private Button dayplanButton;
	// private Button weekplanButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.plan_dayplan);
		init();
		bookDataList = getBookDataList();
		bookDataAdapter = getBookDataAdapter(bookDataList);
		flipView = (FlipViewController) findViewById(R.id.flip_view);
//		 flipView.setAdapter(new TravelAdapter(this));
		flipView.setAdapter(new TravelAdapter(this, bookDataAdapter));
		flipView.setOnViewFlipListener(new FlipViewController.ViewFlipListener() {
			@Override
			public void onViewFlipped(View view, int position) {
				Toast.makeText(view.getContext(), "当前页码: " + position,
						Toast.LENGTH_SHORT).show();
			}
		});
		//DateWidget dateWidget = new DateWidget();
		//View calendar = dateWidget.init(DayPlanActivity.this);
		//RelativeLayout calendarView = (RelativeLayout)findViewById(R.id.calendarview);
		//calendarView.addView(calendar);
		//dateWidget.init2();
		// scrollbtn = (Button) findViewById(R.id.button111);
		//
		// scrollbtn.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		//
		// scrollLayout(mCustomView, 0, 0, 320, 0, 1000);
		// viewMove(dateView, 0f, -300f, 0f, 0f, 500);
		// // viewMove(mCalendarView, 0f, 0f, 0f, -150f, 700);
		// scrollLayout(mHwDetails, 0, 0, 0, 250, 2000);
		//
		// }
		//
		// });
		// eventsaddbtn.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// Toast.makeText(DayPlanActivity.this, "events", 0).show();
		//
		// }
		//
		// });
		// hweditbtn.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// Toast.makeText(DayPlanActivity.this, "hweditbtn", 0).show();
		//
		// }
		//
		// });
		// hwaddbtn.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		//
		// Toast.makeText(DayPlanActivity.this, "hwaddbtn", 0).show();
		// }
		//
		// });
		// SimpleAdapter adapter = new
		// SimpleAdapter(this,getData(),R.layout.vlist,
		// new String[]{"title","info","img"},
		// new int[]{R.id.title,R.id.info,R.id.img});
		// setListAdapter(adapter);
		// eventsListview.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_expandable_list_item_1, getData()));
		//
		// eventsListview.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View v, int position,
		// long id) {
		// String title = (String) arg0.getItemAtPosition(position);
		// Toast.makeText(DayPlanActivity.this, title, 0).show();
		//
		// }
		// });

		// hwListView.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_expandable_list_item_1, getData2()));
		// hwListView.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View v, int position,
		// long id) {
		// String title = (String) arg0.getItemAtPosition(position);
		// Toast.makeText(DayPlanActivity.this, title, 0).show();
		//
		// }
		// });
	}

	public void scrollLayout(CustomView view, int startX, int startY, int dx,
			int dy, int durating) {

		view.beginScroll(startX, startY, dx, dy, durating);

	}

	public void viewMove(View view, float startX, float startY, float dx,
			float dy, int durating) {

		TranslateAnimation translateAnimation = new TranslateAnimation(startX,
				startY, dx, dy);
		translateAnimation.setDuration(durating);
		translateAnimation.setFillAfter(true);
		view.startAnimation(translateAnimation);
		view.setVisibility(0);

	}

	public void init() {
		mCustomView = (CustomView) findViewById(R.id.custom_viewgroup);
		mHwDetails = (CustomView) findViewById(R.id.custom_detail);
		// mCalendarView = (CalendarView) findViewById(R.id.calendarview);

		dateView = (RelativeLayout) findViewById(R.id.dategroup);
		// eventsaddbtn = (ImageView) findViewById(R.id.arraysaddbtn);
		// hwaddbtn = (ImageView) findViewById(R.id.hwaddbtn);
		// hweditbtn = (TextView) findViewById(R.id.hweditbtn);
		// eventsListview = (ListView) findViewById(R.id.arrayslistView);
		// hwListView = (ListView) findViewById(R.id.hwlistView);

	}

	// private List<String> getData() {
	//
	// List<String> data = new ArrayList<String>();
	// data.add("测试数据1");
	// data.add("测试数据2");
	// data.add("测试数据3");
	// data.add("测试数据4");
	//
	// return data;
	// }
	//
	// private List<String> getData2() {
	//
	// List<String> data = new ArrayList<String>();
	// data.add("作业1");
	// data.add("作业2");
	// data.add("作业3");
	// data.add("作业4");
	// data.add("作业5");
	//
	// return data;
	// }
	public SimpleAdapter getBookDataAdapter(
			List<HashMap<String, Object>> dataList) {
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, dataList,
				R.layout.list_item, new String[] { "ItemImage", "ItemTitle",
						"ItemText" }, new int[] { R.id.ItemImage,
						R.id.ItemTitle, R.id.ItemText });

		return listItemAdapter;
	}

	public ArrayList<HashMap<String, Object>> getBookDataList() {
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.ic_launcher);// 图像资源的ID
			map.put("ItemTitle", "Level " + i);
			map.put("ItemText", "Finished ");
			listItem.add(map);
		}

		return listItem;
	}
}
