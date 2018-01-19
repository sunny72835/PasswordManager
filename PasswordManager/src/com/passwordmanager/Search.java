package com.passwordmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends Activity{
	EditText et;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		First.iflag=true;
		
		et=(EditText)findViewById(R.id.et_search);
		btn=(Button)findViewById(R.id.btn_search);
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
				String s=et.getText().toString();
				if(s!=null){
					Intent view=new Intent("com.passwordmanager.View_");
					Bundle b=new Bundle();
					b.putString("Name", s);
					view.putExtras(b);
					startActivity(view);
				}
				else
				{
					Toast.makeText(Search.this, "Enter something to search", Toast.LENGTH_LONG).show();
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
