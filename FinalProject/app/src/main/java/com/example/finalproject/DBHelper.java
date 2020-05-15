package com.example.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "project.db";
	private static final int DATABASE_VERSION = 2;

	private static final String CREATE_CITY =
	"CREATE TABLE city (id integer, city string, longitude float,latitude float); ";
	private static final String CREATE_USER =
			"CREATE TABLE user (id integer, username string, password string); ";
	Context dBcontext;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		dBcontext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_CITY);
		database.execSQL(CREATE_USER);
		Log.i("DBHelper", "DB Created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DBHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");

		dBcontext.deleteDatabase("project.db");
		db.execSQL("DROP TABLE IF EXISTS city;");
		db.execSQL("DROP TABLE IF EXISTS user;");
		onCreate(db);
	}
}
