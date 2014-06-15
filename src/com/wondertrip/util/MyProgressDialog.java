package com.wondertrip.util;

import com.wondertrip.activity.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;

public class MyProgressDialog extends ProgressDialog {
	private static MyProgressDialog customProgressDialog = null;

	public MyProgressDialog(Context context) {
		super(context);
	}

	public MyProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public static MyProgressDialog createDialog(Context context) {
		customProgressDialog = new MyProgressDialog(context,
				R.style.CustomProgressDialog);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		return customProgressDialog;

	}

	public void onWindowFocusChanged(boolean hasFocus) {
		if (customProgressDialog == null) {
			return;
		}
		ImageView imageView = (ImageView) customProgressDialog
				.findViewById(R.id.progressdialoganim);
		AnimationDrawable animationDrawable = (AnimationDrawable) imageView
				.getBackground();
		animationDrawable.start();
	}

	public MyProgressDialog setTitile(String strTitle) {
		return customProgressDialog;
	}
}