package com.bbk.iplan.notification;


import com.bbk.iplan.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Toast;

public class DialogActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		String mode = extras.getString("mode");

		MyDialog(mode);
	}

	public void MyDialog(final String type) {
		AlertDialog.Builder abBuilder = new AlertDialog.Builder(
				DialogActivity.this);
		abBuilder.setTitle("来自iPlan的通知");
		abBuilder.setNegativeButton("修改", new OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {

				Toast.makeText(DialogActivity.this, "启动程序,修改日期", 0).show();
			

			}

		});

		abBuilder.setPositiveButton("确定", new OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				NotificationManager manger = (NotificationManager) DialogActivity.this
						.getSystemService(NOTIFICATION_SERVICE);
				manger.cancel(R.layout.plan_dayplan);
				finish();
			}
		});
		abBuilder.create().show();

	}

}
