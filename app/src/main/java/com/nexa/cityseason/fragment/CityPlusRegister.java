package com.nexa.cityseason.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import com.devprecise.customcomponent.CircleImageView;

import com.nexa.adapter.CustomGridViewAdapter;
import com.nexa.cityseason.R;
import com.nexa.cityseason.activity.RootActivity;
import com.nexa.cityseason.javabean.Item;

import com.nexa.cityseason.util.Constant;
import com.nexa.cityseason.util.FontHelper;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.widget.EditText;
import android.app.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
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
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class CityPlusRegister extends Fragment {
	private Handler mHandler;
	ImageView ivIcon;
	TextView tvItemName;


	ListView lv;
	Activity activity;
	ListAdapter adapter;
	Context mContext;
	ArrayList<HashMap<String, String>> assignment = new ArrayList<HashMap<String, String>>();

	private boolean mSearchCheck;
	HashMap hmRequestId=new HashMap();
	int globalCount=0;




	Button btnView;

	public CityPlusRegister newInstance(String text){
		CityPlusRegister mFragment = new CityPlusRegister();		
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
		mContext = container.getContext();

		((RootActivity)getActivity()).btnMenu.setVisibility(View.GONE);
		((RootActivity)getActivity()).btnBack.setVisibility(View.VISIBLE);


		View rootView = inflater.inflate(R.layout.fragment_cityplusregister, container, false);


		



		//RelativeLayout mStickyView = (RelativeLayout) rootView.findViewById(R.id.sticky);
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.textView1), "fonts/GothamRnd-Medium.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.textView2), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.textView3), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.textView4), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.textView5), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.button1), "fonts/GothamRnd-Light.otf");
		

		mHandler = new Handler();



		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}


	


}
