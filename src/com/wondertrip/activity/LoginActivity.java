package com.wondertrip.activity;


import javax.security.auth.PrivateCredentialPermission;

import com.wondertrip.model.User;
import com.wondertrip.util.MyProgressDialog;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;



/**
 * page for PPT 6
 * */
public class LoginActivity extends Activity {
    private ImageButton loginImageButton;
    private ImageButton registerImageButton;
    private EditText input_username_et;
    private EditText input_password_et;
    private UserProfileService profileService;
    
    public MyProgressDialog dialog = null;
    private Handler mHandler;
    static User user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//取消顶部的title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//填充全屏
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
		setContentView(R.layout.login);
		initWidget();
		mHandler = new Handler(){ 
	        @Override 
	        public void handleMessage(Message msg) { 
	        	switch(msg.what){
		        	case 0 : 
		        		Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
		        		Intent intent = new Intent();
						intent.setClass(LoginActivity.this, HomePageActivity.class);
						LoginActivity.this.startActivity(intent);
		        		break;
		        	case 1 : 
		                Toast.makeText(LoginActivity.this, "登录失败，请重试", Toast.LENGTH_SHORT).show();
		                break;
		            default:
		            	break;
	        	}
	        } 
	       
	    }; 
	    
	    
		loginImageButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				dialog = MyProgressDialog.createDialog(LoginActivity.this);			
				dialog.show();
				dialog.setContentView(R.layout.progressdialog);
				new Thread() {
					public void run() {
						try {
							/* 在这里写上要背景运行的程序片段 */
							Message msg = new Message();
							msg.what = validateUser();
							mHandler.sendMessage(msg);
							
						} 
						catch (Exception e) {
							e.printStackTrace();
						} 
						finally {
							// 卸载所创建的dialog对象。
							dialog.dismiss();
						}
					}

					
				}.start();
				
			}
		});
		
		registerImageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(LoginActivity.this,RegisterActivity.class);
				LoginActivity.this.startActivity(intent);
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void initWidget(){
		loginImageButton = (ImageButton)findViewById(R.id.login_btn);
		registerImageButton = (ImageButton) findViewById(R.id.register_page_btn);
		input_username_et = (EditText)findViewById(R.id.input_username);
		input_password_et= (EditText)findViewById(R.id.input_password);
	}
	
	private int validateUser() {
		// TODO Auto-generated method stub
		String username = input_username_et.getText().toString();
		String password = input_password_et.getText().toString();
		profileService = new UserProfileService();
		 user = profileService.login(username, password);
		if(user == null){
			return 1;
		}else {
			return 0;
		}
	}

}

