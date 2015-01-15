package com.nexa.cityseason.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import com.nexa.adapter.NavigationAdapter;
import com.nexa.cityseason.R;
import com.nexa.cityseason.R.layout;
import com.nexa.cityseason.fragment.CityPlus;
import com.nexa.cityseason.fragment.Dashboard;
import com.nexa.cityseason.util.Constant;
import com.nexa.cityseason.util.FontHelper;
import com.nexa.cityseason.util.Menus;
import com.nexa.cityseason.util.NavigationList;




import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class RootActivity extends FragmentActivity {

	ActionBar mActionBar;

	private int mLastPosition = 0;
	private ListView mListDrawer;    

	private DrawerLayout mLayoutDrawer;		
	private RelativeLayout mUserDrawer;
	private RelativeLayout mRelativeDrawer;	

	public  FragmentManager mFragmentManager;
	private NavigationAdapter mNavigationAdapter;
	private ActionBarDrawerToggleCompat mDrawerToggle;	


	private TabHost mTabHost;
	private Intent mAIntent;
	private Intent mBIntent;
	private Intent mCIntent;
	private Intent mDIntent;
	private Intent mEIntent;
	public Stack<Fragment> fragmentStack;

	//public Stack<Object> menuStatus;

	public static Stack menuStatus;

	RadioButton rbUser;

	public ImageButton btnMenu;

	public ImageButton btnBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigation_main);

		mActionBar=getActionBar();
		fragmentStack = new Stack<Fragment>();

		menuStatus=new Stack();

		addCustomHeader();
		drawerToggleint(savedInstanceState);


		actionbarMenuButton();


		RadioButton cityPlus=(RadioButton)findViewById(R.id.radio_button4);

		cityPlus.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				FragmentTransaction ft = mFragmentManager.beginTransaction();
				Fragment mFragment = new CityPlus(); 
				if(fragmentStack.size()>0){
					ft.hide(fragmentStack.lastElement());
				}
				menuStatus.push("1");
				ft.add(R.id.content_frame, mFragment);
				fragmentStack.push(mFragment);
				ft.commit();

			}
		});


	}
	public void actionbarMenuButton(){
		btnMenu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mLayoutDrawer.isDrawerOpen(mRelativeDrawer)) {
					mLayoutDrawer.closeDrawer(mRelativeDrawer);
				} else {
					mLayoutDrawer.openDrawer(mRelativeDrawer);
				}
			}
		});
	}

	public void drawerToggleint(Bundle savedInstanceState){

		//getActionBar().setDisplayHomeAsUpEnabled(true);


		//   getActionBar().setHomeButtonEnabled(true);		

		mListDrawer = (ListView) findViewById(R.id.listDrawer);        
		mRelativeDrawer = (RelativeLayout) findViewById(R.id.relativeDrawer);		
		mLayoutDrawer = (DrawerLayout) findViewById(R.id.layoutDrawer);	

		mUserDrawer = (RelativeLayout) findViewById(R.id.userDrawer);
		mUserDrawer.setOnClickListener(userOnClick);

		if (mListDrawer != null) {

			// All header menus should be informed here
			// listHeader.add(MENU POSITION)			
			List<Integer> mListHeader = new ArrayList<Integer>();
			//			mListHeader.add(0);
			//			mListHeader.add(6);			
			//			mListHeader.add(10);

			// All menus which will contain an accountant should be informed here
			// Counter.put ("POSITION MENU", "VALUE COUNTER");			
			SparseIntArray  mCounter = new SparseIntArray();			
			//mCounter.put(Constant.MENU_DOWNLOADS,7);
			//mCounter.put(Constant.MENU_MAPS,10);						
			mNavigationAdapter = new NavigationAdapter(this, NavigationList.getNavigationAdapter(this, mListHeader, mCounter, null));
		}

		mListDrawer.setAdapter(mNavigationAdapter);
		mListDrawer.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggleCompat(this, mLayoutDrawer);		
		mLayoutDrawer.setDrawerListener(mDrawerToggle);

		if (savedInstanceState != null ) { 			
			setLastPosition(savedInstanceState.getInt(Constant.LAST_POSITION));



			setTitleFragments(mLastPosition);			
			mNavigationAdapter.resetarCheck();		
			mNavigationAdapter.setChecked(mLastPosition, true);							
		}else{
			setLastPosition(mLastPosition); 
			setFragmentList(mLastPosition);
		}		

		//int titleId = getResources().getIdentifier("action_bar_title", "id",
		//		"android");
		//TextView yourTextView = (TextView) findViewById(titleId);
		//yourTextView.setTextColor(getResources().getColor(R.color.black));
		//yourTextView.setTypeface(custom_font);
	}

	public void addCustomHeader( )
	{

		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);

		View mCustomView = mInflater.inflate(R.layout.cutom_actionbar, null);


		btnMenu=(ImageButton)mCustomView.findViewById(R.id.imgBtnMenu);
		btnBack=(ImageButton)mCustomView.findViewById(R.id.btnBack);

		btnBack.setVisibility(View.GONE);

		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);

		FontHelper.applyFont(this, mCustomView.findViewById(R.id.txtTitle), "fonts/GothamRnd-Medium.otf");

	}


	@Override
	public void onBackPressed() {

		Log.i("aa",menuStatus.size()+"");

		if (fragmentStack.size() > 1) {
			FragmentTransaction ft = mFragmentManager.beginTransaction();

			fragmentStack.lastElement().onPause();
			ft.remove(fragmentStack.pop());

			fragmentStack.lastElement().onResume();
			ft.show(fragmentStack.lastElement());
			Log.i("bbb",fragmentStack.lastElement()+"");
			ft.commit();

			//Log.i("value:",menuStatus.lastElement()+"");
			if(menuStatus.size()>0){
				if(menuStatus.lastElement().equals("1")){
					btnMenu.setVisibility(View.VISIBLE);
					btnBack.setVisibility(View.GONE);
				}
				else{
					//btnBack
				}

				menuStatus.pop();
			}
		} else {
			super.onBackPressed();
		}
	}


	/**************toggle bar*****************/

	public void resetActionBar(boolean childAction, int drawerMode)
	{
		if (childAction) {
			// [Undocumented?] trick to get up button icon to show
			mDrawerToggle.setDrawerIndicatorEnabled(false);
			getActionBar().setDisplayHomeAsUpEnabled(true);

		} else {
			mDrawerToggle.setDrawerIndicatorEnabled(true);
		}

		mLayoutDrawer.setDrawerLockMode(drawerMode);
	}


	private void setFragmentList(int posicao){

		Fragment mFragment = null;		
		mFragmentManager = getSupportFragmentManager();

		//Toast.makeText(NavigationMain.this, posicao+"", Toast.LENGTH_SHORT).show();
		switch (posicao) {

		case Constant.MENU_DASHBOARD:			
			mFragment = new Dashboard(); 
			break;			


		case Constant.MENU_LOCATION:			
			//mFragment = new AddressInfo(); 
			break;

		case Constant.MENU_ADDRESS:			
			//mFragment = new OutletDashBoardFragment(); 
			break;
		case Constant.MENU_HIGHLIGHT:			
			//mFragment = new OutletDashBoardFragment(); 
			break;
		case Constant.MENU_RESERVE:			
			//mFragment = new BookingFragment(); 
			break;
		case 10:			
			//mFragment = new CityPlus(); 
			break;
		}

		if (mFragment != null && posicao!=-1){
			setTitleFragments(mLastPosition);
			mNavigationAdapter.resetarCheck();		
			mNavigationAdapter.setChecked(posicao, true);			
			//mFragmentManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();

			FragmentTransaction ft = mFragmentManager.beginTransaction();

			if(fragmentStack.size()>0){
				ft.hide(fragmentStack.lastElement());
			}
			menuStatus.push("1");
			ft.add(R.id.content_frame, mFragment);
			fragmentStack.push(mFragment);
			ft.commit();


		}
		if(mFragment != null && posicao==-1){

			FragmentTransaction ft = mFragmentManager.beginTransaction();

			ft.add(R.id.content_frame, mFragment);
			fragmentStack.push(mFragment);
			ft.commit();
			//mFragmentManager.beginTransaction().replace(R.id.content_frame, mFragment).commit();
		}


	}

	private void hideMenus(Menu menu, int posicao) {

		boolean drawerOpen = mLayoutDrawer.isDrawerOpen(mRelativeDrawer);    	

		switch (posicao) {


		case Constant.MENU_GALLERY:
			// menu.findItem(Menus.ADD).setVisible(!drawerOpen);	
			// menu.findItem(Menus.SEARCH).setVisible(!drawerOpen);	        
			break;			
		}  
	}	

	private void setTitleFragments(int position){	
		//	setIconActionBar(Utils.iconNavigation[position]);
		//setSubtitleActionBar(Utils.getTitleItem(RootActivity.this, position));	
		setSubtitleActionBar("ab");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub		
		super.onSaveInstanceState(outState);		
		outState.putInt(Constant.LAST_POSITION, mLastPosition);					
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {  

		switch (item.getItemId()) {		
		case Menus.HOME:
			if (mLayoutDrawer.isDrawerOpen(mRelativeDrawer)) {
				mLayoutDrawer.closeDrawer(mRelativeDrawer);
			} else {
				mLayoutDrawer.openDrawer(mRelativeDrawer);
			}
			return true;			
		default:

			if (mDrawerToggle.onOptionsItemSelected(item)) {
				return true;
			}		

			return super.onOptionsItemSelected(item);			
		}		             
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		hideMenus(menu, mLastPosition);
		return super.onPrepareOptionsMenu(menu);  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);        		
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);	     
		mDrawerToggle.syncState();	
	}	

	public void setTitleActionBar(CharSequence informacao) {   

		getActionBar().setTitle(informacao);

	}	

	public void setSubtitleActionBar(CharSequence informacao) {    	
		//getActionBar().setSubtitle(informacao);
	}	

	//	public void setIconActionBar(int icon) {    	
	//    	getActionBar().setIcon(icon);
	//    }	
	//	
	public void setLastPosition(int posicao){		
		this.mLastPosition = posicao;
	}	

	private class ActionBarDrawerToggleCompat extends ActionBarDrawerToggle {

		public ActionBarDrawerToggleCompat(Activity mActivity, DrawerLayout mDrawerLayout){
			super(
					mActivity,
					mDrawerLayout, 
					R.drawable.ic_action_navigation_drawer, 
					R.string.drawer_open,
					R.string.drawer_close);
		}

		@Override
		public void onDrawerClosed(View view) {			
			supportInvalidateOptionsMenu();				
		}

		@Override
		public void onDrawerOpened(View drawerView) {	
			mNavigationAdapter.notifyDataSetChanged();			
			supportInvalidateOptionsMenu();			
		}		
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);		
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {          	        	
			setLastPosition(posicao);        	
			setFragmentList(mLastPosition);	    	
			mLayoutDrawer.closeDrawer(mRelativeDrawer);	 
		}
	}	

	private OnClickListener userOnClick = new OnClickListener() {		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mLayoutDrawer.closeDrawer(mRelativeDrawer);
		}
	};	
}
