package com.hp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
		
		// Contact tab
		Intent intentContact = new Intent().setClass(this, ContactActivity.class);
		TabSpec tabSpecContact = tabHost
		  .newTabSpec("Contact")
		  .setIndicator("", ressources.getDrawable(R.drawable.icon_contact_config))
		  .setContent(intentContact);

		// Map tab
		Intent intentMap = new Intent().setClass(this, MapActivity.class);
		TabSpec tabSpecMap = tabHost
		  .newTabSpec("Map")
		  .setIndicator("", ressources.getDrawable(R.drawable.icon_map_config))
		  .setContent(intentMap);
		
		
		
		
	
		// add all tabs 
		tabHost.addTab(tabSpecContact);
		tabHost.addTab(tabSpecMap);
		
		
		//set Windows tab as default (zero based)
		//tabHost.setCurrentTab(index)
	}

}