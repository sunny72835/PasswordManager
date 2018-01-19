package com.passwordmanager;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends ListActivity {

	Cursor c;
	int i = 0;
	ListView list;
	String s1;

	DbHandler db = new DbHandler(this);
	// ListView lv;
	SimpleCursorAdapter cursorAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.home);
		First.iflag = true;
		// lv=(ListView)findViewById(R.id.lv);
		setContentView(R.layout.home);
		list = (ListView) findViewById(android.R.id.list);
		registerForContextMenu(list);
	}

	@Override
	public void onCreateContextMenu(ContextMenu context_menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(context_menu, v, menuInfo);
		MenuInflater inflate1 = getMenuInflater();
		inflate1.inflate(R.menu.context_menu, context_menu);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (First.iflag == false) {
			Intent first = new Intent("com.passwordmanager.First");
			startActivity(first);
		}
		db.open();
		c = db.queueAll();

		final String[] from = new String[] { DbHandler.name };
		final int[] to = new int[] { R.id.text };
		cursorAdapter = new SimpleCursorAdapter(this, R.layout.row1, c, from,
				to);

		setListAdapter(cursorAdapter);

		db.close();
		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> av, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				db.open();
				Cursor c1 = ((SimpleCursorAdapter) av.getAdapter()).getCursor();
				c1.moveToPosition(position);
				s1 = c1.getString(1);
				Toast.makeText(Home.this, s1, Toast.LENGTH_SHORT).show();
				db.close();
				return false;
			}
		});

	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		// l.setId(R.id.list);
		Intent i = new Intent("com.passwordmanager.View_");
		Bundle b = new Bundle();
		db.open();
		Cursor c1 = ((SimpleCursorAdapter) l.getAdapter()).getCursor();
		c1.moveToPosition(position);
		String s = c1.getString(1);
		Toast.makeText(Home.this, s, Toast.LENGTH_SHORT).show();
		db.close();
		b.putString("Name", s);
		i.putExtras(b);
		startActivity(i);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		First.iflag = false;
		Splash.flag = true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onCreateOptionsMenu(ContextMenu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.menu_new:
			Intent i = new Intent("com.passwordmanager.New_");
			startActivity(i);
			break;
		case R.id.search:
			Intent i1 = new Intent("com.passwordmanager.Search");
			startActivity(i1);
			break;
		case R.id.change_pass:
			Intent i2 = new Intent("com.passwordmanager.Change_pass");
			startActivity(i2);
			break;
		case R.id.about:
			TextView tv = new TextView(Home.this);
			tv.setText("Developers: Sunny Patel & Azim Kangda" + "\n"
					+ "Version:1.0");
			AlertDialog.Builder build = new AlertDialog.Builder(this);
			build.setTitle("About");
			build.setMessage("Name: Password Manager\nVersion:1.0\nDevelopers:Sunny Patel & Azim Kangda");
			build.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
			AlertDialog a = build.create();
			a.show();
			break;

		}

		return true;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.view:
			Intent call_view = new Intent("com.passwordmanager.View_");
			Bundle bview = new Bundle();
			bview.putString("Name", s1);
			call_view.putExtras(bview);
			startActivity(call_view);
			break;
		case R.id.edit:
			Intent call_edit = new Intent("com.passwordmanager.Edit");
			Bundle bedit = new Bundle();
			bedit.putString("Name", s1);
			call_edit.putExtras(bedit);
			startActivity(call_edit);
			break;
		case R.id.delete:
			AlertDialog.Builder ab = new AlertDialog.Builder(Home.this);
			ab.setTitle("Delete");
			ab.setMessage("Are you sure to delete entry: " + s1);
			ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					db.open();
					db.deleteEntry(s1);
					Toast.makeText(Home.this, "Entry Deleted",
							Toast.LENGTH_SHORT).show();
					db.close();
					Intent intent = getIntent();
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
			
			break;
		}
		return true;
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
