package com.wondertrip.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * page for PPT 27
 * */
public class UserHistoryListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_history_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_history_list, menu);
		return true;
	}

}
