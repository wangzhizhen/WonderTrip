package com.wondertrip.activity;

import com.wondertrip.model.User;
import com.wondertrip.webservice.UserProfileService;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * page for PPT 9
 * */
public class RegisterActivity extends Activity {

	Spinner genderSpinner;
	private EditText rg_username_et;
	private EditText rg_password_et;
	private EditText rg_confirmPassword_et;
	private EditText rg_nickname_et;
	private String gender;
	private UserProfileService profileService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		initWidget();
		ImageButton reg_bt = (ImageButton)findViewById(R.id.register_btn);
		ImageButton back_bt = (ImageButton)findViewById(R.id.register_back);
		profileService = new UserProfileService();
		genderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position,
					long id) {
				gender = parent.getItemAtPosition(position).toString();				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
				
		reg_bt.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//get all the input message				
				new Thread(new Runnable() {					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String password_new = rg_password_et.getText().toString();
						String password_check = rg_confirmPassword_et.getText().toString();
						String username_new = rg_username_et.getText().toString();
						String nickname_new = rg_nickname_et.getText().toString();
						if(username_new.equals("")){
							mhandler.obtainMessage(1).sendToTarget();
						}else if(checkPassword(password_new,password_check) == 0){
							if(checkIfUserExist(username_new)==0){
								 addNewUser(username_new,password_new,nickname_new);
							}else{
								mhandler.obtainMessage(3).sendToTarget(); 
							}
					   
						}else{
							mhandler.obtainMessage(2).sendToTarget(); // password is not consistent
						}						
						
						
					}
				}).start();
				
			}
			
		});
		back_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(RegisterActivity.this, LoginActivity.class);
				RegisterActivity.this.startActivity(intent);
			}
		});
		
	}

	Handler mhandler = new Handler(){ 
        @Override 
        public void handleMessage(Message msg) { 
        	switch(msg.what){       		
	        	case 1 : 
	        		Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
	        		break;
	        	case 2 : 
	                Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
	                break;	        	
	        	case 3 :
	                Toast.makeText(RegisterActivity.this, "注册失败，用户名已存在", Toast.LENGTH_SHORT).show();
	                break;
	        	case 4 :
	                Toast.makeText(RegisterActivity.this, "注册失败，请再试一次", Toast.LENGTH_SHORT).show();
	                break;
	        	case 0 :
	                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
	                Intent intent = new Intent();
	                intent.setClass(RegisterActivity.this, HomePageActivity.class);
	                RegisterActivity.this.startActivity(intent);
	                break;
	            default:
	            	break;
        	}
        } 
       
    }; 
	private int checkPassword(String password_new, String password_check) {
		// TODO Auto-generated method stub		
		if(password_check.equals(password_new)){
			//two input password is consistent 
			return 0;
		}else{
			//two input password is not consistent 
			return 2;
		}
	}

	private int checkIfUserExist(String username_new) {
		// TODO Auto-generated method stub
		User user = profileService.queryProfile(username_new);
		if(user == null){
			return 0;
		}else{
			return 3;
		}
	}
	
	private void addNewUser(final String username_new, final String password_new, final String nickname_new) {
		// TODO Auto-generated method stub
			new Thread(new Runnable() {			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					int result = profileService.register(username_new, password_new, gender,nickname_new);
					if(result == -1){
						mhandler.obtainMessage(4).sendToTarget();
					}else{
						mhandler.obtainMessage(0).sendToTarget();
					}
				}
			}).start();	
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}

	public void initWidget(){
		genderSpinner = (Spinner)findViewById(R.id.gender_new);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this, R.array.genderSelector, android.R.layout.simple_spinner_item); 
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		genderSpinner.setAdapter(adapter);
		rg_username_et = (EditText) findViewById(R.id.username_new);
		rg_password_et = (EditText) findViewById(R.id.password_new);
		rg_confirmPassword_et = (EditText) findViewById(R.id.password_check);
		rg_nickname_et = (EditText) findViewById(R.id.nickname_new);
	}
	
}
