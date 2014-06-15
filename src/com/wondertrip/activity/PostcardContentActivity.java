package com.wondertrip.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;


/**
 * page for PPT 30
 * */
public class PostcardContentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.postcard_content);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.postcard_content, menu);
		return true;
	}

}
