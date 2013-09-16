package com.bbk.iplan.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bbk.flipview.library.fliputils.TravelAdapter;
import com.bbk.iplan.R;

public class ListViewAdapter extends BaseAdapter {
	private LayoutInflater inflater;

	public ListViewAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return TravelAdapter.listItem.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return TravelAdapter.listItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			System.out.println("list update");
			convertView = inflater.inflate(R.layout.list_item, null);

			// ImageView im1 = (ImageView) convertView
			// .findViewById(R.id.ItemImage1);
			// ImageView im2 = (ImageView) convertView
			// .findViewById(R.id.ItemImage2);
			// im2.setBackgroundResource(R.drawable.btn_browser2);
			// im2.setVisibility(View.INVISIBLE);
			// im1.setBackgroundResource(TravelAdapter.listItem.get(position)
			// .getImg());

			// if (TravelAdapter.listItem.get(position).getImg() !=
			// R.drawable.btn_browser) {
			// im2.setVisibility(View.VISIBLE);
			// im1.setVisibility(View.INVISIBLE);
			// }
			// System.out.println(TravelAdapter.listItem.get(position).getImg());
			// TextView tv = (TextView) convertView.findViewById(R.id.ItemText);
			// tv.setText(TravelAdapter.listItem.get(position).getContent());
			// TextView tvtitile = (TextView)
			// convertView.findViewById(R.id.title);

		}
		return convertView;
	}

}
