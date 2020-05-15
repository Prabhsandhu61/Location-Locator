package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CityDataSource {

	// Database fields
	private SQLiteDatabase database;
	private DBHelper dbHelper;

	public CityDataSource(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		Log.i("CityDataSource", "open");
	}
	
	public boolean isOpen()
	{
		if (database == null) return false;
		return database.isOpen();
	} 

	public void close() {
		dbHelper.close();
	}

	void addCity(City city) {
//		ContentValues values = new ContentValues();
//		values.put("id", person.getId());
//		values.put("firtname", person.getFirstname());
//		values.put("longitude", person.getLastname());
//		values.put("age", person.getAge());

		database.insert("city", null, setCityData(city));
		database.close();
	}

	// Getting single City
	City getCity(int id) {
		Log.e("id", database.toString());
		Cursor cursor = database.query("city", new String[] { "id",	"city", "longitude", "latitude"}," id = ?",
				new String[] { String.valueOf(id) }, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		City city = new City();
		city.setId((int) cursor.getLong(0));
		city.setCity(cursor.getString(1));
		city.setLongitude(Float.parseFloat(cursor.getString(2)));
		city.setLatitude(Float.parseFloat(cursor.getString(3)));
		cursor.close();
		
		// return Person
		return city;
	}

	// Updating single Person
	public int updateCity(City city) {
//		ContentValues values = new ContentValues();
//		values.put("id", city.getId());
//		values.put("firtname", person.getFirstname());
//		values.put("longitude", person.getLastname());
//		values.put("age", person.getAge());

		// updating row
		return database.update("city", setCityData(city)," id = ?",
				new String[] { String.valueOf(city.getId()) });
	}

	// Deleting single Person
	public void deleteCity(City city) {
		database.delete("city"," id = ?",
				new String[] { String.valueOf(city.getId()) });
		database.close();
	}

	// Getting Person Count
	public List<City> getAllCities() {
		List<City> cities = new ArrayList<City>();
		String countQuery = "SELECT  * FROM " + "city";
		Cursor cursor = database.rawQuery(countQuery, null);
		if (cursor.moveToFirst()) {
			while (!cursor.isAfterLast()) {
				City city = new City();
				city.setId((int) cursor.getLong(0));
				city.setCity(cursor.getString(1));
				city.setLongitude(Float.parseFloat(cursor.getString(2)));
				city.setLatitude(Float.parseFloat(cursor.getString(3)));
				Log.e("city", city.toString());
				cities.add(city);
				cursor.moveToNext();
			}
		}
		cursor.close();

		// return count
		return cities;
	}

	// Getting Person Count
	public int getCityCount() {
		String countQuery = "SELECT  * FROM " + "city";
		Cursor cursor = database.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();

		// return count
		return count;
	}

	private ContentValues setCityData(City city) {
		ContentValues values = new ContentValues();
		values.put("id", city.getId());
		values.put("city", city.getCity());
		values.put("longitude", city.getLongitude());
		values.put("latitude", city.getLatitude());
		return values;
	}
}
