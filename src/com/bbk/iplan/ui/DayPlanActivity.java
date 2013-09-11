package com.bbk.iplan.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
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
	private Button scrollbtn;
	private CustomView mCustomView;
	private CustomView mHwDetails;
	private CalendarView mCalendarView;
	private FlipViewController flipView;
	public List<HashMap<String, Object>> bookDataList = null;
	public SimpleAdapter bookDataAdapter = null;
	private Calendar mCalendar = null;

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
		flipView.setAdapter(new TravelAdapter(this, bookDataAdapter));
		flipView.setOnViewFlipListener(new FlipViewController.ViewFlipListener() {
			@Override
			public void onViewFlipped(View view, int position) {
				Toast.makeText(view.getContext(), "当前页码: " + position,
						Toast.LENGTH_SHORT).show();
			}
		});

		/**
		 * 
		 * 
		 */
		DateWidget dateWidget = new DateWidget();
		View calendar = dateWidget.init(DayPlanActivity.this);
		final RelativeLayout calendarView = (RelativeLayout) findViewById(R.id.calendarview);
		calendarView.addView(calendar);
		dateWidget.init2();
		scrollbtn = (Button) findViewById(R.id.button111);

		scrollbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewMove(dateView, 0f, -300f, 0f, 0f, 1500);
				
				scrollLayout(mCustomView, 0, 0, 320, 0, 1500);

				viewMove(calendarView, 0f, 0f, 0f, -150f, 1500);

				scrollLayout(mHwDetails, 0, 0, 0, 250, 1700);

			}

		});
	}

	/**
	 * 点击日历单元格的时候调用此
	 * 
	 * @param myCalendar
	 */
	public void setDate(Calendar myCalendar) {
		this.mCalendar = myCalendar;
		System.out.println("设置日期成功");
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

	}

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
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.ic_launcher);// 图像资源的ID
			map.put("ItemTitle", "Level " + i);
			map.put("ItemText", "Finished ");
			listItem.add(map);
		}

		return listItem;
	}
}
