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

import com.nexa.adapter.FaciltyListAdapter;
import com.nexa.adapter.HotelDetailsAdapter;
import com.nexa.adapter.ResturantDetailsAdapter;
import com.nexa.adapter.RoomDetailsAdapter;

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

public class FacilityAndService extends Fragment {

	private RelativeLayout mStickyView;
	private View mPlaceholderView;
	private ListView mListView;
	private View mItemTop;
	Activity activity;
	Context mContext;
	Handler handler = new Handler();
	int color=0;

	public FacilityAndService newInstance(String text){
		FacilityAndService mFragment = new FacilityAndService();		
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
		View rootView = inflater.inflate(R.layout.fragment_facility_list, container, false);

		mContext = container.getContext();




		mListView = (ListView) rootView.findViewById(R.id.listView);





		FaciltyListAdapter simpleAdpt=new FaciltyListAdapter(mContext, createListViewData());
		mListView.setAdapter(simpleAdpt);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {

				openFragment();
			}
		});


		return rootView;
	}

	private void openFragment(){

		Fragment mFragment = null;

		mFragment = new FacilityDetails(); 

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


	/**
	 * Populate the ListView with example data.
	 * @return
	 */
	private List<Map<String, String>> createListViewData() {
		List<Map<String, String>> itemList = new ArrayList<Map<String,String>>();

		//for (int i = 0; i < 5; i++) {
		itemList.add(createItem("item", "Room & Suites"));
		itemList.add(createItem("item", "Room & Suites"));
		itemList.add(createItem("item", "Room & Suites"));
		itemList.add(createItem("item", "Room & Suites"));
		itemList.add(createItem("item", "Room & Suites"));
		itemList.add(createItem("item", "Room & Suites"));
		itemList.add(createItem("item", "Room & Suites"));
		itemList.add(createItem("item", "Room & Suites"));
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
