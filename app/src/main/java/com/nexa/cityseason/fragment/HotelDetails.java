/*
 * Copyright 2013 Javier Tarazaga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nexa.cityseason.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nexa.adapter.HotelDetailsAdapter;
import com.nexa.cityseason.R;
import com.nexa.cityseason.activity.RootActivity;
import com.nexa.cityseason.util.Constant;
import com.nexa.cityseason.util.FontHelper;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class HotelDetails extends Fragment {

	private RelativeLayout mStickyView;
	private View mPlaceholderView;
	private ListView mListView;
	private View mItemTop;
	Activity activity;
	Context mContext;
	Handler handler = new Handler();
	int color=0;

	public HotelDetails newInstance(String text){
		HotelDetails mFragment = new HotelDetails();		
		Bundle mBundle = new Bundle();
		mBundle.putString(Constant.TEXT_FRAGMENT, "Offers");
		mFragment.setArguments(mBundle);
		return mFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		if ( getActivity() instanceof RootActivity){
			activity = (RootActivity) getActivity();
		}

		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_hotel_details, container, false);

		mContext = container.getContext();


		mStickyView = (RelativeLayout) rootView.findViewById(R.id.sticky);
		FontHelper.applyFont(mContext, mStickyView, "fonts/GothamRnd-Medium.otf");

		//	mStickyView.setText("aa");

		mListView = (ListView) rootView.findViewById(R.id.listView);


		mItemTop = rootView.findViewById(R.id.itemTop);

		//LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View v = inflater.inflate(R.layout.fragment_roomsuies_top_layout,null);


		mPlaceholderView = v.findViewById(R.id.placeholder);
		mListView.addHeaderView(v);

		mListView.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@SuppressLint("NewApi")
					@SuppressWarnings("deprecation")
					@Override
					public void onGlobalLayout() {
						onScrollChanged();

						ViewTreeObserver obs = mListView.getViewTreeObserver();
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
							obs.removeOnGlobalLayoutListener(this);
						} else {
							obs.removeGlobalOnLayoutListener(this);
						}
					}
				});

		mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				onScrollChanged();
			}
		});

		// Create and set the adapter for the listView.
		SimpleAdapter simpleAdpt = new HotelDetailsAdapter(mContext, createListViewData(), R.layout.list_item, new String[] {"item"}, new int[] {R.id.txtTitle});		
		mListView.setAdapter(simpleAdpt);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {

				openFragment(position);
			}
		});

		return rootView;
	}

	private void openFragment(int position){

		Fragment mFragment = null;
		// mFragmentManager = mContext.getSupportFragmentManager();



		//Toast.makeText(mContext, position+"", Toast.LENGTH_SHORT).show();
		switch (position) {

		case 1:			
			mFragment = new RoomSuites(); 
			break;			


		case 2:			
			mFragment = new Resturant(); 
			break;

		case 3:			
			mFragment = new FacilityAndService(); 
			break;
		case 4:			
			//mFragment = new OutletDashBoardFragment(); 
			break;
		case 5:			
			//mFragment = new BookingFragment(); 
			break;
		}

		//mFragment = new RoomSuites();

		if(mFragment!=null){
			FragmentManager frgManager = getFragmentManager();

			FragmentTransaction ft = frgManager.beginTransaction();

			if(((RootActivity)getActivity()).fragmentStack.size()>0){
				ft.hide(((RootActivity)getActivity()).fragmentStack.lastElement());
			}
			ft.add(R.id.content_frame, mFragment);
			((RootActivity)getActivity()).fragmentStack.push(mFragment);
			((RootActivity)getActivity()).menuStatus.push("1");
			ft.commit();
		}
	}

	/**
	 * Function used to calculate the position of the sticky view according to the position of the first item in the ListView.
	 */
	private void onScrollChanged() {
		View v = mListView.getChildAt(0);
		int top = (v == null) ? 0 : v.getTop();

		//Toast.makeText(mContext, top+"", 2000).show();
		if(top<-150){
			mStickyView.setBackgroundResource(R.drawable.ic_hotel_logobar);
		}
		else{
			mStickyView.setBackgroundResource(R.drawable.ic_hotel_logobar);
		}
		// This check is needed because when the first element reaches the top of the window, the top values from top are not longer valid. 
		if (mListView.getFirstVisiblePosition() == 0) {

			mStickyView.setTranslationY(
					Math.max(0, mPlaceholderView.getTop() + top));

			// Set the image to scroll half of the amount scrolled in the ListView.
			mItemTop.setTranslationY(top / 2);
		}			
	}

	/**
	 * Populate the ListView with example data.
	 * @return
	 */
	private List<Map<String, String>> createListViewData() {
		List<Map<String, String>> itemList = new ArrayList<Map<String,String>>();

		//for (int i = 0; i < 5; i++) {
		itemList.add(createItem("item", "Room & Suites"));
		itemList.add(createItem("item", "Restaurents"));
		itemList.add(createItem("item", "Facilities & Services"));
		itemList.add(createItem("item", "Special Offers"));
		itemList.add(createItem("item", "Booking"));
		//}

		return itemList;
	}

	/**
	 * Function used to create the HashMap needed for ListView item using Simple Adapter.
	 * @param key
	 * @param name
	 * @return
	 */
	private HashMap<String, String> createItem(String key, String name) {
		HashMap<String, String> item = new HashMap<String, String>();
		item.put(key, name);

		return item;
	}

}
