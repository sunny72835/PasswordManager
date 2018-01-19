package com.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHandler {
		public static final String name="Name";
		public static final String login="Login_ID";
		public static final String pass="Password";
		public static final String other="Other";
		private static final String db_name="pass_db";
		private static final String db_table="table_db";
		private static final int version=2;
		
		private DbHelper ob;
		private final Context mycontext;
		private SQLiteDatabase my_db; 
		private static class DbHelper extends SQLiteOpenHelper {

			public DbHelper(Context context) {
				super(context, db_name, null, version);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				// TODO Auto-generated method stub
				db.execSQL("CREATE TABLE "+db_table+" ("+name +" PRIMARY KEY,"+login+","+pass+","+"other"+");");
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion,
					int newVersion) {
				// TODO Auto-generated method stub
				db.execSQL("DROP TABLE IF EXISTS "+db_table);
			}
			
		}
		public DbHandler(Context c){mycontext=c;}
		public DbHandler open() throws SQLException{
			ob= new DbHelper(mycontext);
			my_db=ob.getWritableDatabase();
			return this;
		}
		public void close(){
			ob.close();
		}
		public void createEntry(String name_val,String login_val,String pass_val,String other_val){
			ContentValues cv=new ContentValues();
			cv.put(name, name_val);
			cv.put(login, login_val);
			cv.put(pass, pass_val);
			cv.put(other, other_val);
			my_db.insert(db_table, null, cv);
		}
		public Cursor getData(String s){
			Log.i("Sunny ","Searching for "+s);
			String[] columns=new String[]{"rowid _id",name,login,pass,other};
			Cursor c=my_db.query(db_table, columns, name +"='"+s+"'", null,null,null,null);
			if(c!=null){
				c.moveToFirst();
				return c;
			}
			return null;
		}		
		public Cursor queueAll() {
			// TODO Auto-generated method stub
			String[] columns = new String[]{"rowid _id",name};
			Cursor cursor = my_db.query(db_table, columns,
			  null, null, null, null, name + " ASC");
			return cursor;
		}
		public void doUpdate(String s1, String s2, String s3,
				String s4) {
			// TODO Auto-generated method stub
			ContentValues cv = new ContentValues();
			cv.put(name, s1);
			cv.put(login, s2);
			cv.put(pass, s3);
			cv.put(other, s4);

			my_db.update(db_table, cv, name+"='"+s1+"'", null);
		}
		public void deleteEntry(String s1) {
			// TODO Auto-generated method stub
			my_db.delete(db_table, name+"='"+s1+"'", null);
		}
		
}
