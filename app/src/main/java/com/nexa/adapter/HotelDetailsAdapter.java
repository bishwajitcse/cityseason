package com.nexa.adapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nexa.cityseason.util.FontHelper;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class HotelDetailsAdapter extends SimpleAdapter {
	private int[] colors = new int[] { 0x30FF0000, 0x300000FF };
	View view;
	Context mContext;
	public HotelDetailsAdapter(Context context,List<Map<String, String>> items, int resource, String[] from, int[] to) {

		super(context, items, resource, from, to);
		mContext=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		view = super.getView(position, convertView, parent);
		int colorPos = position % colors.length;
		//view.setBackgroundColor(colors[colorPos]);

		FontHelper.applyFont(mContext, view, "fonts/GothamRnd-Medium.otf");

		return view;
	}


}