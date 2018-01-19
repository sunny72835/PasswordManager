package com.passwordmanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class First extends Activity {
	Button btn_ok;
	EditText cur_pass;
	SharedPreferences sp;
	public static boolean flag1=true;
	public static boolean iflag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		sp=getSharedPreferences("Password",MODE_PRIVATE);
		btn_ok=(Button)findViewById(R.id.btn_ok);
		cur_pass=(EditText)findViewById(R.id.cur_pass);
		btn_ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(sp.contains("Password")){
				if((cur_pass.getText().toString()).equals(sp.getString("Password",null)) ){
					if(Splash.flag==false){
						Intent i=new Intent("com.passwordmanager.Home");
						startActivity(i);
						}
						else{
							iflag=true;
							finish();
						}
					Toast.makeText(First.this,"Welcome", Toast.LENGTH_LONG).show();
				}
				else
					Toast.makeText(First.this,"Incorrect Password", Toast.LENGTH_LONG).show();
			}
				else{
					flag1=false;
					Intent x=new Intent("com.passwordmanager.Change_pass");
					startActivity(x);
				}
			}
		});
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
        Intent intent = new Intent(Intent.ACTION_MAIN); 
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
        intent.addCategory(Intent.CATEGORY_HOME); 
        startActivity(intent);
	}
	
	
		
}
