package com.bbk.flipview.library.fliputils;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bbk.flipview.library.baseutils.AphidLog;
import com.bbk.iplan.R;
import com.bbk.iplan.ui.DayPlanActivity;
import com.bbk.iplan.ui.HwListDate;
import com.bbk.iplan.ui.ListViewAdapter;
import com.bbk.iplan.utils.BookUtils;

public class TravelAdapter extends BaseAdapter {

	private LayoutInflater inflater;

	private int repeatCount = 1;

	private List<Travels.Data> travelData;

	private BookUtils bookUtils;
	public static ListViewAdapter planAdapter;
	public static ListViewAdapter hwAdapter;

	public static ArrayList<HwListDate> listItem = new ArrayList<HwListDate>();
	private ListView planLV;
	private ListView hwLV;
	HwListDate date;
	private boolean isList = false;
	private DayPlanActivity dayPlanAc = null;

	public TravelAdapter(DayPlanActivity context) {
		inflater = LayoutInflater.from(context);
		this.dayPlanAc = context;
		// travelData = new ArrayList<Travels.Data>(Travels.IMG_DESCRIPTIONS);

	}

	public TravelAdapter(DayPlanActivity context, SimpleAdapter sa) {
		inflater = LayoutInflater.from(context);
		this.dayPlanAc = context;
		travelData = new ArrayList<Travels.Data>(Travels.IMG_DESCRIPTIONS);
		for (int i = 0; i < 5; i++) {
			date = new HwListDate();
			date.setImg(R.drawable.btn_browser);
			date.setContent("123");
			date.setTitile("textView");
			listItem.add(date);
		}
		// mAdapter = new ListViewAdapter(dayPlanAc);
		hwAdapter = new ListViewAdapter(dayPlanAc);
	}

	@Override
	public int getCount() {
		return travelData.size() * repeatCount;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View layout = convertView;

		if (convertView == null) {
			layout = inflater.inflate(R.layout.book_display, null);
			planLV = (ListView) layout.findViewById(R.id.planlistView);
			hwLV = (ListView) layout.findViewById(R.id.hwlistView);
			hwAdapter = new ListViewAdapter(dayPlanAc);
			planAdapter = new ListViewAdapter(dayPlanAc);

			planLV.setAdapter(planAdapter);
			hwLV.setAdapter(hwAdapter);

			planAdapter.notifyDataSetChanged();
			hwAdapter.notifyDataSetChanged();

			hwLV.setOnItemClickListener(dayPlanAc);
			hwLV.setOnItemLongClickListener(dayPlanAc);

		}
		final ImageView addPlanBtn = (ImageView) layout
				.findViewById(R.id.add_plan_btn);
		final ImageView addHWBtn = (ImageView) layout
				.findViewById(R.id.add_hw_btn);

		addHWBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dayPlanAc.pop.showAddHolidayPop(addHWBtn);
			}
		});

		addPlanBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dayPlanAc.pop.showAddPlanPop(addPlanBtn);
			}
		});

		return layout;
	}

	public void removeData(int index) {
		if (travelData.size() > 1) {
			travelData.remove(index);
		}
	}

}
