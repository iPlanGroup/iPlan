package com.bbk.iplan.ui;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.bbk.iplan.R;
import com.bbk.iplan.ui.TitleView;

public class GridViewAdapter extends BaseAdapter {
	private Context context;
	
	public GridViewAdapter(Context context) {
		
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 100;
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
		TitleView result = new TitleView(context);
		result.setTextColor(Color.BLACK);
		result.setTextSize(23);
		result.setMinimumHeight(46);
		result.setLayoutParams(new AbsListView.LayoutParams(
				new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.WRAP_CONTENT)));
		result.setGravity(Gravity.CENTER);
		result.setBackgroundColor(0xEBE9EA);
		
		return result;
	
	}

}
