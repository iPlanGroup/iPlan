package com.bbk.UI;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

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

<<<<<<< HEAD
=======
	@SuppressLint("ResourceAsColor")
>>>>>>> 4221d81fcc69414f94e65a289ef334b10f921941
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TitleView result = new TitleView(context);
//		TextView result = new TextView(context);
//		result.setText("Item " + position);
		result.setTextColor(Color.BLACK);
		result.setTextSize(23);
		result.setMinimumHeight(46);
		result.setLayoutParams(new AbsListView.LayoutParams(
				new LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.WRAP_CONTENT)));
		result.setGravity(Gravity.CENTER);
<<<<<<< HEAD
=======
		result.setBackgroundColor(R.color.white);
>>>>>>> 4221d81fcc69414f94e65a289ef334b10f921941
		
		return result;
	
	}

}
