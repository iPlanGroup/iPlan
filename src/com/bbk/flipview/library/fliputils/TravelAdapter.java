package com.bbk.flipview.library.fliputils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bbk.flipview.library.baseutils.AphidLog;
import com.bbk.iplan.R;
import com.bbk.iplan.ui.DayPlanActivity;
import com.bbk.iplan.ui.MainActivity;

public class TravelAdapter extends BaseAdapter
{

	private LayoutInflater inflater;

	private int repeatCount = 1;

	private List<Travels.Data> travelData;
	
    private SimpleAdapter sa = null;
    private DayPlanActivity mainActivity = null;
    
	public TravelAdapter(DayPlanActivity context)
	{
		inflater = LayoutInflater.from(context);
		this.mainActivity = context;
		travelData = new ArrayList<Travels.Data>(Travels.IMG_DESCRIPTIONS);
	}
	
	public TravelAdapter(DayPlanActivity context, SimpleAdapter sa)
	{
		inflater = LayoutInflater.from(context);   
		this.sa = sa;
		this.mainActivity = context;
		travelData = new ArrayList<Travels.Data>(Travels.IMG_DESCRIPTIONS);
	}

	@Override
	public int getCount()
	{
		return travelData.size() * repeatCount;
	}

	public int getRepeatCount()
	{
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount)
	{
		this.repeatCount = repeatCount;
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View layout = convertView;
		if (convertView == null)
		{
			layout = inflater.inflate(R.layout.book_display, null);
			AphidLog.d("created new view from adapter: %d", position);
			
			ListView planLV = (ListView)layout.findViewById(R.id.planlistView);
			ListView hwLV = (ListView)layout.findViewById(R.id.hwlistView);
			
			planLV.setAdapter(sa);
			hwLV.setAdapter(sa);
		}
		
		ImageView addPlanBtn = (ImageView)layout.findViewById(R.id.add_plan_btn);
		ImageView addHWBtn = (ImageView)layout.findViewById(R.id.add_hw_btn);
		
		addPlanBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				System.out.println("show add plan");
//				mainActivity.showAddPlanPop();
			}
		});
		
		addHWBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				System.out.println("show add homework");
//                mainActivity.showAddHWPop();				
			}
		});

		final Travels.Data data = travelData.get(position % travelData.size());

//		UI.<TextView> findViewById(layout, R.id.planlistView).setText(
//				Html.fromHtml(data.description));
//		UI.<TextView> findViewById(layout, R.id.hwlistView).setText(
//				Html.fromHtml(data.description));
		return layout;
	}

	public void removeData(int index)
	{
		if (travelData.size() > 1)
		{
			travelData.remove(index);
		}
	}
}
