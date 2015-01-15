package com.nexa.cityseason;

import com.nexa.cityseason.activity.Login;
import com.nexa.cityseason.activity.RootActivity;
import com.nexa.cityseason.util.FontHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	Button btnfb,btnEmail;
	TextView txtSkip;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnfb=(Button)findViewById(R.id.btnFb);
		btnEmail=(Button)findViewById(R.id.btnEmail);
		txtSkip=(TextView)findViewById(R.id.txtskip);
		
		
	
		
		FontHelper.applyFont(this, findViewById(R.id.btnlayout), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(this, findViewById(R.id.toplayout), "fonts/GothamRnd-Medium.otf");
		
		//FontHelper.applyFont(this, txtSkip, "fonts/GothamRnd-Medium.otf");
		
		
		btnEmail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,Login.class));
			}
		});
		
		
		LinearLayout skip=(LinearLayout)findViewById(R.id.imageButton1);
		
		skip.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				startActivity(new Intent(MainActivity.this,RootActivity.class));
			}
		});
		
	}



}
