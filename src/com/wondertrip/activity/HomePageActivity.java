package com.wondertrip.activity;

import com.wondertrip.util.TestForJena;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * page for PPT 14
 * */
public class HomePageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page);
//		TestForJena jena = new TestForJena();
//		jena.tryJena();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}

}
