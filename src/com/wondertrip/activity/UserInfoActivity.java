package com.wondertrip.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * page for PPT 24 25 26 28 user_page.xml
 * */
public class UserInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}

}
