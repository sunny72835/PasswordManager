package com.passwordmanager;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {
public static boolean flag=false;
MediaPlayer m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		m = MediaPlayer.create(Splash.this, R.raw.beep);
		m.start();
		Thread t=new Thread(){
			public void run(){
				try {
					sleep(2000);
					
					Intent i=new Intent("com.passwordmanager.First");
					startActivity(i);
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		m.release();
		flag=false;
		finish();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		flag=false;
	}
			
}
