package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserDataSource {

	// Database fields
	private SQLiteDatabase database;
	private DBHelper dbHelper;

	public UserDataSource(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		Log.i("UserDataSource", "open");
	}
	
	public boolean isOpen()
	{
		if (database == null) return false;
		return database.isOpen();
	} 

	public void close() {
		dbHelper.close();
	}

	void addUser(User user) {

		database.insert("user", null, setUserData(user));
		database.close();
	}

	// Getting single Person
	User getUser(int id) {
		Cursor cursor = database.query("user", new String[] { "id", "username", "password"}, "id= ?",
				new String[] { String.valueOf(id)}, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		User user = new User();
		user.setId((int) cursor.getLong(0));
		user.setUsername(cursor.getString(1));
		user.setPassword(cursor.getString(2));

		cursor.close();
		return user;
	}
	boolean checkUser(String username,String password)
	{
		Cursor cursor = database.query("user", new String[] { "id", "username", "password"}, "username= ? AND password = ?",
				new String[] { username,password}, null, null, null);
		int cursorCount=cursor.getCount();
		cursor.close();
		if(cursorCount>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	boolean checkUsername(String username)
	{
		Cursor cursor = database.query("user", new String[] { "id", "username", "password"}, "username= ?",
				new String[] { username}, null, null, null);
		int cursorCount=cursor.getCount();
		cursor.close();
		if(cursorCount>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// Updating single Person
	public int updateUser(User user) {
		// updating row
		return database.update("user", setUserData(user)," id = ?",
				new String[] { String.valueOf(user.getId()) });
	}

	// Deleting single Person
	public void deleteUser(User user) {
		database.delete("user"," id = ?",
				new String[] { String.valueOf(user.getId()) });
		database.close();
	}

	// Getting Person Count
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		String countQuery = "SELECT  * FROM " + "user";
		Cursor cursor = database.rawQuery(countQuery, null);
		if (cursor.moveToFirst()) {
			while (!cursor.isAfterLast()) {
				User user = new User();
				user.setId((int) cursor.getLong(0));
				user.setUsername(cursor.getString(1));
				user.setPassword(cursor.getString(2));
				Log.e("user", user.toString());
				users.add(user);
				cursor.moveToNext();
			}
		}
		cursor.close();

		// return count
		return users;
	}

	// Getting Person Count
	public int getUserCount() {
		String countQuery = "SELECT  * FROM " + "user";
		Cursor cursor = database.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();

		// return count
		return count;
	}

	private ContentValues setUserData(User user) {
		ContentValues values = new ContentValues();
		values.put("id", user.getId());
		values.put("username",user.getUsername());
		values.put("password", user.getPassword());
		return values;
	}
}
