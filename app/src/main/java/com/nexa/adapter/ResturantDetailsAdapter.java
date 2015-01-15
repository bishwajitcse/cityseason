package com.nexa.adapter;

import ir.noghteh.JustifiedTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.nexa.cityseason.R;
import com.nexa.cityseason.util.FontHelper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.text.Html;
import android.text.Layout.Alignment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResturantDetailsAdapter extends BaseAdapter {

	private List<Map<String, String>> listData;

	private LayoutInflater layoutInflater;
	Context mContext;

	public ResturantDetailsAdapter(Context context, List<Map<String, String>> listData) {
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
		mContext=context;
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
	
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.fragment_resturant_details_item, null);
			holder = new ViewHolder();
			
			JustifiedTextView txtData=(JustifiedTextView)convertView.findViewById(R.id.justifiedTextView1);
			txtData.setText(mContext.getResources().getString(R.string.sample1));
			
			txtData.setAlignment(Align.LEFT);
			txtData.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
			txtData.setLineSpacing(11);
			
		txtData.setTypeFace(Typeface.createFromAsset(mContext.getAssets(), "fonts/GothamRnd-Light.otf"));
			
			
			TextView txtEmail=(TextView)convertView.findViewById(R.id.txtEmail);
			FontHelper.applyFont(mContext,txtEmail, "fonts/GothamRnd-Bold.otf");
			
			TextView txtPhone=(TextView)convertView.findViewById(R.id.txtPhone);
			FontHelper.applyFont(mContext, txtPhone, "fonts/GothamRnd-Bold.otf");
			TextView txtOpen=(TextView)convertView.findViewById(R.id.txtOpen);
			FontHelper.applyFont(mContext, txtOpen, "fonts/GothamRnd-Bold.otf");
			
			
			
			//big details
			holder.txtbigDetails=(JustifiedTextView)convertView.findViewById(R.id.txtbigDetails);
			
			holder.txtbigDetails.setText(mContext.getResources().getString(R.string.sample));
			holder.txtbigDetails.setAlignment(Align.LEFT);
			holder.txtbigDetails.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
			holder.txtbigDetails.setLineSpacing(11);
			holder.txtbigDetails.setTypeFace(Typeface.createFromAsset(mContext.getAssets(), "fonts/GothamRnd-Light.otf"));
			
			
			// email and phone
			
		
		
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
	
		return convertView;
	}

	static class ViewHolder {
		
		JustifiedTextView headlineView,txtbigDetails;

		TextView reportedDateView;
	}

}