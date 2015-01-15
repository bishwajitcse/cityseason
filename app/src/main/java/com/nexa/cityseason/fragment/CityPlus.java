package com.nexa.cityseason.fragment;

import ir.noghteh.JustifiedTextView;

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
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
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

public class CityPlus extends Fragment {
	private Handler mHandler;
	ImageView ivIcon;
	TextView tvItemName;
	Button getcityplus,login,register;
	public  FragmentManager mFragmentManager;

	ListView lv;
	Activity activity;
	ListAdapter adapter;
	Context mContext;
	ArrayList<HashMap<String, String>> assignment = new ArrayList<HashMap<String, String>>();

	private boolean mSearchCheck;
	HashMap hmRequestId=new HashMap();
	int globalCount=0;




	Button btnView;

	public CityPlus newInstance(String text){
		CityPlus mFragment = new CityPlus();		
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


		View rootView = inflater.inflate(R.layout.fragment_cityplus, container, false);

		getcityplus=(Button) rootView.findViewById(R.id.getcityplus);
		
		login=(Button) rootView.findViewById(R.id.login);
		register=(Button) rootView.findViewById(R.id.register);
		addListenerOnGetCityPlus();
		addListenerOnLogin();
		addListenerOnRegister();
		
		
		
		
		



		//RelativeLayout mStickyView = (RelativeLayout) rootView.findViewById(R.id.sticky);
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.textView1), "fonts/GothamRnd-Medium.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.textView2), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.textView3), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.getcityplus), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.login), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.register), "fonts/GothamRnd-Light.otf");

		mHandler = new Handler();

		
		JustifiedTextView txtData=(JustifiedTextView)rootView.findViewById(R.id.textView2);
		txtData.setText(mContext.getResources().getString(R.string.sample));
		txtData.setTypeFace(Typeface.createFromAsset(mContext.getAssets(), "fonts/GothamRnd-Light.otf"));
		txtData.setAlignment(Align.LEFT);
		txtData.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
		txtData.setLineSpacing(11);
		
		JustifiedTextView txtData1=(JustifiedTextView)rootView.findViewById(R.id.textView3);
		txtData1.setText(mContext.getResources().getString(R.string.sample));
		txtData1.setTypeFace(Typeface.createFromAsset(mContext.getAssets(), "fonts/GothamRnd-Light.otf"));
		txtData1.setAlignment(Align.LEFT);
		txtData1.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
		txtData1.setLineSpacing(11);
		
		

		return rootView;
	}

	
	


	public void addListenerOnGetCityPlus() {

        //final Context context = this;

        

        getcityplus.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

            	Fragment mFragment = new CityPlusGetCityPlus(); 
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

        });			
}

	public void addListenerOnLogin() {

        //final Context context = this;

        

        login.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

            	Fragment mFragment = new CityPlusLogin(); ;
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

        });			
}

	
	public void addListenerOnRegister() {

        //final Context context = this;

        

        register.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {

            	Fragment mFragment = new CityPlusRegister(); ;
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

        });			
}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}


	


}
