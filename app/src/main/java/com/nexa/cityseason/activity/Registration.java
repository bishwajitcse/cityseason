package com.nexa.cityseason.activity;

import com.nexa.cityseason.R;
import com.nexa.cityseason.R.layout;
import com.nexa.cityseason.R.menu;
import com.nexa.cityseason.util.FontHelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;

public class Registration extends Activity {
	
	Button btnRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_registration);
		btnRegister=(Button)findViewById(R.id.button1);
		
		FontHelper.applyFont(this, findViewById(R.id.l_layout), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(this, btnRegister, "fonts/GothamRnd-Medium.otf");
		
		
		
	}

	
}
