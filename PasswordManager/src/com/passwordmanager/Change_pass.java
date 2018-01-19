package com.passwordmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Change_pass extends Activity {
	SharedPreferences sp;
	SharedPreferences.Editor e;
	String s1,s2,s3;
	EditText et1,et2,et3;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_pass);
		First.iflag=true;
		sp=getSharedPreferences("Password",MODE_PRIVATE);
		
		et1=(EditText)findViewById(R.id.et_search_cur_pass);
		et2=(EditText)findViewById(R.id.et_new_pass);
		et3=(EditText)findViewById(R.id.et_confirm_pass);
		 btn=(Button)findViewById(R.id.btn_change_pass);
		 if(First.flag1==false)
			 et1.setVisibility(4);
}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(First.iflag==false){
			Intent first=new Intent("com.passwordmanager.First");
			startActivity(first);
		}
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				s1=et1.getText().toString();
				s2=et2.getText().toString();
				s3=et3.getText().toString();
				if(First.flag1==true){
					et1.setVisibility(0);
				if(s1.equals(sp.getString("Password", null))){
					if(s2.equals(s3)){
						
						e=sp.edit();
						e.putString("Password", s2);
						e.commit();
						Toast.makeText(Change_pass.this, "Password changed successfully", Toast.LENGTH_LONG).show();
					}
					else
						Toast.makeText(Change_pass.this, "Both passwords are not matching", Toast.LENGTH_LONG).show();
				}
				else
					Toast.makeText(Change_pass.this, "Current password is incorrect!!!", Toast.LENGTH_LONG).show();
			}
				else{
					if(s2.equals(s3)){
						
						e=sp.edit();
						e.putString("Password", s2);
						e.commit();
						First.flag1=true;
						Toast.makeText(Change_pass.this, "Password created successfully", Toast.LENGTH_LONG).show();
						
					}
					else
						Toast.makeText(Change_pass.this, "Both passwords are not matching", Toast.LENGTH_LONG).show();
				}
				}
			
		});
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		First.iflag=false;
		Splash.flag=true;
		
	}

	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i1=new Intent("com.passwordmanager.Home");
		startActivity(i1);
	}
}
