package com.bbk.iplan.utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.bbk.iplan.R;

public class BookUtils
{
	
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
}
