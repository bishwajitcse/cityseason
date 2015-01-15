package com.nexa.cityseason.activity;

import com.nexa.cityseason.MainActivity;
import com.nexa.cityseason.R;
import com.nexa.cityseason.R.layout;
import com.nexa.cityseason.R.menu;
import com.nexa.cityseason.util.FontHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {
	
	EditText txtUsername,txtPassword;
	
	TextView txtRegistration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		txtUsername=(EditText)findViewById(R.id.editText1);
		txtPassword=(EditText)findViewById(R.id.editText3);
		txtRegistration=(TextView)findViewById(R.id.textView2);
		txtRegistration.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Login.this,Registration.class));
			}
		});
		//FontHelper.applyFont(this, findViewById(R.id.logincmd), "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(this, findViewById(R.id.logincmd), "fonts/GothamRnd-Medium.otf");
		FontHelper.applyFont(this,txtUsername, "fonts/GothamRnd-Light.otf");
		FontHelper.applyFont(this, txtPassword, "fonts/GothamRnd-Light.otf");
		
	}


}
