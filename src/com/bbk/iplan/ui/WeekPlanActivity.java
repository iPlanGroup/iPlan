package com.bbk.iplan.ui ;



import java.util.Date;

import com.bbk.iplan.R;
import com.bbk.iplan.model.FacadePlan;
import com.bbk.iplan.model.WeekPlan;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class WeekPlanActivity extends Activity {
	private GridView grid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.plan_week);

		
		FacadePlan mweekFacadePlan = new FacadePlan();
		Date time = new Date(System.currentTimeMillis());
		WeekPlan mWeekPlan=mweekFacadePlan.getCurWeekPlan(time);
		
		grid = (GridView) findViewById(R.id.main_GridView);
		grid.setAdapter(new GridViewAdapter(this));
		grid.setOnItemClickListener(new ItemClickListener());

	}

	class ItemClickListener implements OnItemClickListener {
		public void onItemClick(AdapterView<?> arg0, View view, int id,
				long arg3) {
			Toast.makeText(getApplicationContext(), "click", 1).show();
		}

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (ev.getAction() == MotionEvent.ACTION_MOVE) {
			return true;// forbid its child(gridview) to scroll
		}
		return super.dispatchTouchEvent(ev);
	}
	public void setFullscreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
}
