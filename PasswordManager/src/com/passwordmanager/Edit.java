package com.passwordmanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends Activity {
	Button btn_new_save, btn_new_reset;
	EditText et_name, et_pass, et_login, et_other;
	DbHandler db;
	Cursor c;
	String s,s1,s2,s3,s4;
	int i=0;
	char[] data;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_);
		First.iflag=true;
		Bundle b = getIntent().getExtras(); 
		s=b.getString("Name");

		db=new DbHandler(this);
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
		try{
			db.open();
			c=db.getData(s);
			s1=c.getString(1);
			s2=c.getString(2);
			s3=c.getString(3);
			s4=c.getString(4);
			db.close();
			et_name.setText(s1);
			et_login.setText(s2);
			et_pass.setText(s3);
			et_other.setText(s4);
		}catch(Exception e){
			Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
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
				
				try {
					db.open();
					db.doUpdate(et_name.getText().toString(),et_login.getText().toString(),et_pass.getText().toString(),et_other.getText().toString());
					db.close();
					Toast.makeText(Edit.this,"Data saved", Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(Edit.this, "Error", Toast.LENGTH_LONG).show();
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
