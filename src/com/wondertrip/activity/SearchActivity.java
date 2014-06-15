package com.wondertrip.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TableLayout;

/**
 * page for PPT 18 -20 22 23 
 * */
public class SearchActivity extends Activity {
	private ImageButton sightImageButton;
	private ImageButton trafficImageButton;
	private ImageButton hotelImageButton;
	private TableLayout inputForSightLayout;
	private TableLayout inputForTrafficLayout;
	private TableLayout inputForHotelLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_page);
		initWidget();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}

	public void initWidget() {
		sightImageButton = (ImageButton) findViewById(R.id.type_sight_btn);
		trafficImageButton = (ImageButton) findViewById(R.id.type_traffic_btn);
		hotelImageButton = (ImageButton) findViewById(R.id.type_hotel_btn);
		inputForSightLayout = (TableLayout) findViewById(R.id.input_for_sight);
		inputForTrafficLayout = (TableLayout) findViewById(R.id.input_for_traffic);
		inputForHotelLayout = (TableLayout) findViewById(R.id.input_for_hotel);

		sightImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputForSightLayout.setVisibility(View.VISIBLE);
				inputForTrafficLayout.setVisibility(View.GONE);
				inputForHotelLayout.setVisibility(View.GONE);

			}
		});
		
		trafficImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputForSightLayout.setVisibility(View.GONE);
				inputForTrafficLayout.setVisibility(View.VISIBLE);
				inputForHotelLayout.setVisibility(View.GONE);

			}
		});
		hotelImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inputForSightLayout.setVisibility(View.GONE);
				inputForTrafficLayout.setVisibility(View.GONE);
				inputForHotelLayout.setVisibility(View.VISIBLE);

			}
		});
	}

}
