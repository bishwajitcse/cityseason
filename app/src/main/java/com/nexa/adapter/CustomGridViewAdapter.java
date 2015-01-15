package com.nexa.adapter;

import java.util.ArrayList;

import com.nexa.cityseason.R;
import com.nexa.cityseason.javabean.Item;


import android.app.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author manish.s
 *
 */
public class CustomGridViewAdapter extends ArrayAdapter<Item> {
	Context context;
	int layoutResourceId;
	ArrayList<Item> data = new ArrayList<Item>();

	public CustomGridViewAdapter(Context context, int layoutResourceId,
			ArrayList<Item> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();

			holder.imageItem = (ImageView) row.findViewById(R.id.preloader);
			
			row.setTag(holder);
			
		} else {
			holder = (RecordHolder) row.getTag();
		}

		
	
		//holder.imageItem.setImageBitmap(item.getImage());
		if(position==0)
			holder.imageItem.setBackgroundResource(R.drawable.ic_royalrose);
		else if(position==1)
			holder.imageItem.setBackgroundResource(R.drawable.ic_alhamra);
		else if(position==2)
			holder.imageItem.setImageResource(R.drawable.ic_alaline);
		else if(position==3)
			holder.imageItem.setImageResource(R.drawable.ic_cityseasion_dubai);
		else if(position==4)
			holder.imageItem.setImageResource(R.drawable.ic_suitesdubai);
		else
			holder.imageItem.setImageResource(R.drawable.ic_moscat);
		return row;

	}

	static class RecordHolder {
		TextView txtTitle;
		ImageView imageItem;

	}
}