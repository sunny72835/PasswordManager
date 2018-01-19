package com.passwordmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class New_ extends Activity {
Button btn_new_save, btn_new_reset;
EditText et_name, et_pass, et_login, et_other;
DbHandler db=new DbHandler(this);
//FileOutputStream fo1,fo2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_);
		First.iflag=true;
	/*	try {
			fo1=openFileOutput("Index",Context.MODE_APPEND);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		btn_new_save=(Button)findViewById(R.id.btn_save);
		btn_new_reset=(Button)findViewById(R.id.btn_reset);
		et_name=(EditText)findViewById(R.id.et_name);
		et_login=(EditText)findViewById(R.id.et_login);
		et_pass=(EditText)findViewById(R.id.et_pass);
		et_other=(EditText)findViewById(R.id.et_other);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(First.iflag==false){
		Intent first=new Intent("com.passwordmanager.First");
		startActivity(first);
		}
		btn_new_reset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et_name.setText("");
				et_login.setText("");
				et_pass.setText("");
				et_other.setText("");
			}
		});
		btn_new_save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//String s=et_name.getText().toString();
				try {/*
					fo1.write(s.getBytes());
					fo1.close();
					fo2=openFileOutput(et_name.getText().toString(), MODE_APPEND);*/
					String s1,s2,s3,s4;
					s1=et_name.getText().toString();
					s2=et_login.getText().toString();
					s3=et_pass.getText().toString();
					s4=et_other.getText().toString();
					db.open();
					db.createEntry(s1, s2, s3, s4);
					db.close();
					//S=s1+"\n"+s2+"\n"+s3+"\n"+s4+"\n";
					//fo2.write(S.getBytes());
					Toast.makeText(New_.this,"Data saved", Toast.LENGTH_LONG).show();
					/*Testing View:
						Intent i1 = new Intent("com.passwordmanager.View_");
						Bundle b=new Bundle();
						b.putString("Name", s1);
						i1.putExtras(b);
						startActivity(i1);
					//End*/
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(New_.this,"Sorry:Data didn't saved", Toast.LENGTH_LONG).show();
					
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
