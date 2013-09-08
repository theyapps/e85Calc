/***********************************************************************
 * 
 * PROGRAM:	E-85 Calculator Android App
 * DESC:	My first android app, helps a user decide if E-85 or gas
 * 			is the better deal considering the lowered MPG.
 * FILE:	SettingsActivity.java - Settings page.
 * AUTHOR: 	Ryan Boykin
 * DATE:	May 2013
 * 
 **********************************************************************/

package com.theyapps.e85calc;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;

public class SettingsActivity extends Activity 
{

	private Button 	btn_save;
	private Button 	btn_reset;
	private Spinner spn_distUnit;
	private Spinner spn_fuelUnit;
	private Spinner spn_currency;
	
	static 	SharedPreferences 			settings;
	static 	SharedPreferences.Editor 	editor;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		// Show the Up button in the action bar.
		setupActionBar();
		
		btn_save 		= (Button) findViewById(R.id.settings_save);
		btn_reset 		= (Button) findViewById(R.id.settings_reset);
		
		spn_distUnit 	= (Spinner)findViewById(R.id.dist_unit_prompt);
		spn_fuelUnit 	= (Spinner)findViewById(R.id.fuel_unit_prompt);
		spn_currency 	= (Spinner)findViewById(R.id.currency_prompt);
		
		loadSettings();
		
		// Well, I guess these buttons are unneeded so for the time being I am going to
		// hide them. If later I add any edit field I will reenable them.
		btn_save. setVisibility(View.INVISIBLE); 
		btn_reset.setVisibility(View.INVISIBLE);
		
		spn_distUnit.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) 
			{
				editor.putInt("distUnit", position).commit(); // value to store
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) 
			{
				Toast.makeText(getApplicationContext(), "None selected error!", Toast.LENGTH_LONG).show();				
			}
		});
		
		spn_fuelUnit.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) 
			{
				editor.putInt("fuelUnit", position).commit(); // value to store
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) 
			{
				Toast.makeText(getApplicationContext(), "None selected error!", Toast.LENGTH_LONG).show();				
			}
		});
		
		spn_currency.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) 
			{
				editor.putInt("currency", position).commit(); // value to store
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) 
			{
				Toast.makeText(getApplicationContext(), "None selected error!", Toast.LENGTH_LONG).show();	
			}
			
		});
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() 
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
		{
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// Load settings from db and set on screen controls to match
	public void loadSettings()
	{
		settings = getSharedPreferences("user-pref", MODE_PRIVATE);
		editor = settings.edit();
		spn_distUnit.setSelection(settings.getInt("distUnit", 0));
		spn_fuelUnit.setSelection(settings.getInt("fuelUnit", 0));
		spn_currency.setSelection(settings.getInt("currency", 0));
	}
	
	
}