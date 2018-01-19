package com.passwordmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class View_ extends Activity {
	Button btn_view_edit,btn_view_delete;
	TextView tv_name,tv_pass,tv_login,tv_other;
	DbHandler db;
	
	String s1,s2,s3,s4,s;
	int i=0;
	char[] data;
	Bundle b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view);
		First.iflag=true;
		db=new DbHandler(View_.this);
		
		b = getIntent().getExtras(); 
		s=b.getString("Name");
		
		btn_view_edit=(Button)findViewById(R.id.view_btn_edit);
		btn_view_delete=(Button)findViewById(R.id.view_delete);

		tv_name=(TextView)findViewById(R.id.view_tv_name);
		tv_login=(TextView)findViewById(R.id.view_tv_login);
		tv_pass=(TextView)findViewById(R.id.view_tv_pass);
		tv_other=(TextView)findViewById(R.id.view_tv_other);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(First.iflag==false){
			Intent first=new Intent("com.passwordmanager.First");
			startActivity(first);
		}
		try {
			db.open();
			Cursor c=db.getData(s);
			s1=c.getString(1);
			s2=c.getString(2);
			s3=c.getString(3);
			s4=c.getString(4);
			db.close();
			tv_name.setText(s1);
			tv_login.setText(s2);
			tv_pass.setText(s3);
			tv_other.setText(s4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "Error occured check your input", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		btn_view_edit.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("com.passwordmanager.Edit");
				Bundle b1=new Bundle();
				b1.putString("Name",s);
				i.putExtras(b1);
				startActivity(i);
			}
		});
		btn_view_delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder ab = new AlertDialog.Builder(View_.this);
				ab.setTitle("Delete");
				ab.setMessage("Are you sure to delete entry: " + s1);
				ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						db.open();
						db.deleteEntry(s1);
						Toast.makeText(View_.this, "Entry Deleted",
								Toast.LENGTH_SHORT).show();
						db.close();
						Intent intent = new Intent("com.passwordmanager.Home");
					    finish();
					    startActivity(intent);
					}
				});
				ab.setNegativeButton("No", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.cancel();
					}
				});
				AlertDialog delete = ab.create();
				delete.show();
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
