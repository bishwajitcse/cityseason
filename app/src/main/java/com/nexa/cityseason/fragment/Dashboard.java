package com.nexa.cityseason.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import com.nexa.adapter.CustomGridViewAdapter;
import com.nexa.cityseason.R;
import com.nexa.cityseason.activity.RootActivity;
import com.nexa.cityseason.javabean.Item;

import com.nexa.cityseason.util.Constant;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.widget.EditText;
import android.app.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends Fragment {
	private Handler mHandler;
	ImageView ivIcon;
	TextView tvItemName;
	Button myButton;
	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
	public static final String ITEM_NAME = "itemName";
	ListView lv;
	Activity activity;
	ListAdapter adapter;
	Context mContext;
	ArrayList<HashMap<String, String>> assignment = new ArrayList<HashMap<String, String>>();

	private boolean mSearchCheck;
	HashMap hmRequestId=new HashMap();
	int globalCount=0;

	public Dashboard newInstance(String text){
		Dashboard mFragment = new Dashboard();		
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

		View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);



		mContext = container.getContext();
		createLayoutDynamically(6,rootView);

		mHandler = new Handler();
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}

	private void createLayoutDynamically(int n,View v) {

		GridView gridView; 
		final ArrayList<Item> gridArray = new ArrayList<Item>();
		CustomGridViewAdapter customGridAdapter;


		//set grid view item 
		Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_royalrose); 
		Bitmap b = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_alhamra); 
		Bitmap c = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_alaline); 
		Bitmap d = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_cityseasion_dubai); 
		Bitmap e = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_cityplus); 


		for (int i = 0; i < n; i++) {
			gridArray.add(new Item(homeIcon,(i+100)+""));
		}

		gridView = (GridView) v.findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(mContext,R.layout.dashboard_custom_icon, gridArray); 

		gridView.setAdapter(customGridAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {

				Fragment mFragment = null;
				// mFragmentManager = mContext.getSupportFragmentManager();
				mFragment = new HotelDetails();

				FragmentManager frgManager = getFragmentManager();

				FragmentTransaction ft = frgManager.beginTransaction();

				if(((RootActivity)getActivity()).fragmentStack.size()>0){
					ft.hide(((RootActivity)getActivity()).fragmentStack.lastElement());
				}
				ft.add(R.id.content_frame, mFragment);
				((RootActivity)getActivity()).fragmentStack.push(mFragment);
				ft.commit();
			}
		}
				);
	}




}
