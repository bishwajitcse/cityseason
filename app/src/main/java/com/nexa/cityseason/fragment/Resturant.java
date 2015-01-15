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

public class Resturant extends Fragment {
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



	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private ViewFlipper mViewFlipper;	
	private AnimationListener mAnimationListener;


	CircleImageView imgLeft,imgRight,imgMiddle;

	Object image[]={"abc","abc1","ic_hoteldetails"};


	Button btnView;

	public Resturant newInstance(String text){
		Resturant mFragment = new Resturant();		
		Bundle mBundle = new Bundle();
		mBundle.putString(Constant.TEXT_FRAGMENT, "Offers");
		mFragment.setArguments(mBundle);
		return mFragment;
	}

	@SuppressWarnings("deprecation")
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

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


		View rootView = inflater.inflate(R.layout.fragment_resturant, container, false);


		btnView=(Button)rootView.findViewById(R.id.btnView);



		//RelativeLayout mStickyView = (RelativeLayout) rootView.findViewById(R.id.sticky);
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.textView1), "fonts/GothamRnd-Medium.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.txtHotleName), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(mContext, btnView, "fonts/GothamRnd-Medium.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.view_flipper), "fonts/GothamRnd-Medium.otf");
		FontHelper.applyFont(mContext, rootView.findViewById(R.id.layTop), "fonts/GothamRnd-Medium.otf");

		imgLeft=(CircleImageView)rootView.findViewById(R.id.imageView1);
		imgLeft.setImageResource(R.drawable.abc);

		imgRight=(CircleImageView)rootView.findViewById(R.id.imageView2);
		imgRight.setImageResource(R.drawable.abc1);

		imgMiddle=(CircleImageView)rootView.findViewById(R.id.imageView3);
		imgMiddle.setImageResource(R.drawable.ic_hoteldetails);

		mHandler = new Handler();


		mViewFlipper = (ViewFlipper) rootView.findViewById(R.id.view_flipper);

		mViewFlipper.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(final View view, final MotionEvent event) {
				detector.onTouchEvent(event);
				return true;
			}
		});

		//animation listener
		mAnimationListener = new Animation.AnimationListener() {
			public void onAnimationStart(Animation animation) {
				//animation started event
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				//TODO animation stopped event
			}
		};


		btnView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager frgManager = getFragmentManager();
				Fragment mFragment = new ResturantDetails(); 
				FragmentTransaction ft = frgManager.beginTransaction();

				if(((RootActivity)getActivity()).fragmentStack.size()>0){
					ft.hide(((RootActivity)getActivity()).fragmentStack.lastElement());
				}
				ft.add(R.id.content_frame, mFragment);
				((RootActivity)getActivity()).fragmentStack.push(mFragment);
				((RootActivity)getActivity()).menuStatus.push("0");
				ft.commit();
			}
		});

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}


	class SwipeGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			try {
				// right to left swipe
				int i=0;
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showNext();

					loadNextData(i);
					i++;

					return true;
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showPrevious();
					loadPreData(i);
					i--;
					return true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
	}

	public void loadNextData(int i){
		//
		imgRight.setImageResource(R.drawable.abc1);
		imgMiddle.setImageResource(R.drawable.ic_hoteldetails);
		imgLeft.setImageResource(R.drawable.abc);
	}

	public void loadPreData(int i){

		imgMiddle.setImageResource(R.drawable.abc);
		imgLeft.setImageResource(R.drawable.abc1);
		imgRight.setImageResource(R.drawable.ic_hoteldetails);
	}


}
