package com.bbk.iplan.ui;

import com.bbk.iplan.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Setting extends Activity {
	public static final int classCode = 1;
	public static final int examCode = 2;
	public static final int workCode = 3;

	Uri classRingUri = null;
	Uri examRingUri = null;
	Uri workRingUri = null;

	SharedPreferences.Editor localEditor = null;
	SharedPreferences settings = null;
	CheckBox pageCheckBoxAnimation = null;
	CheckBox pageCheckBoxRing = null;
	CheckBox classCheckBox = null;
	LinearLayout classTime = null;
	LinearLayout classRing = null;
	CheckBox workCheckBox = null;
	LinearLayout workTime = null;
	LinearLayout workRing = null;
	CheckBox examCheckBox = null;
	LinearLayout examTime = null;
	LinearLayout examRing = null;

	TextView mHintText = null;
	String[] timeString = new String[3];
	int mTimeFlag = 0;
	String mTimeHintPreference = null;

	TextView classTimeText = null;
	TextView classTimeHint = null;
	TextView classRingText = null;
	TextView classRingHint = null;

	TextView examTimeText = null;
	TextView examTimeHint = null;
	TextView examRingText = null;
	TextView examRingHint = null;

	TextView workTimeText = null;
	TextView workTimeHint = null;
	TextView workRingText = null;
	TextView workRingHint = null;
	int timeNumber = 0;
	final String[] classTimeArray = { "10分钟", "20分钟", "30分钟" };
	final String[] examTimeArray = { "1天", "2天", "3天" };
	final String[] workTimeArray = { "6小时", "12小时", "18小时" };

	/**
	 * sharepreference 字段 page_checkBox_animation page_checkBox_ring
	 * class_checkBox class_time class_ring exam_checkBox exam_time exam_ring
	 * work_checkBox work_time work_ring
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.mysetting);

		settings = this.getSharedPreferences("settingXML", 0);
		localEditor = settings.edit();
		// 翻页开关
		pageCheckBoxAnimation = (CheckBox) findViewById(R.id.page_checkBox_animation);
		pageCheckBoxAnimation.setTag(1);
		pageCheckBoxRing = (CheckBox) findViewById(R.id.page_checkBox_ring);
		pageCheckBoxRing.setTag(2);

		// 课
		classCheckBox = (CheckBox) findViewById(R.id.class_checkBox);
		classCheckBox.setTag(11);
		classTime = (LinearLayout) findViewById(R.id.class_time);
		classTime.setTag(12);
		classRing = (LinearLayout) findViewById(R.id.class_ring);
		classRing.setTag(13);
		// 考试
		examCheckBox = (CheckBox) findViewById(R.id.exam_checkBox);
		examCheckBox.setTag(21);
		examTime = (LinearLayout) findViewById(R.id.exam_time);
		examTime.setTag(22);
		examRing = (LinearLayout) findViewById(R.id.exam_ring);
		examRing.setTag(23);
		// 作业
		workCheckBox = (CheckBox) findViewById(R.id.work_checkBox);
		workCheckBox.setTag(31);
		workTime = (LinearLayout) findViewById(R.id.work_time);
		workTime.setTag(32);
		workRing = (LinearLayout) findViewById(R.id.work_ring);
		workRing.setTag(33);
		//
		classTimeText = (TextView) findViewById(R.id.class_time_text);
		classTimeHint = (TextView) findViewById(R.id.class_time_hint);
		classRingText = (TextView) findViewById(R.id.class_ring_text);
		classRingHint = (TextView) findViewById(R.id.class_ring_hint);
		//
		examTimeText = (TextView) findViewById(R.id.exam_time_text);
		examTimeHint = (TextView) findViewById(R.id.exam_time_hint);
		examRingText = (TextView) findViewById(R.id.exam_ring_text);
		examRingHint = (TextView) findViewById(R.id.exam_ring_hint);
		//
		workTimeText = (TextView) findViewById(R.id.work_time_text);
		workTimeHint = (TextView) findViewById(R.id.work_time_hint);
		workRingText = (TextView) findViewById(R.id.work_ring_text);
		workRingHint = (TextView) findViewById(R.id.work_ring_hint);

		// checkBox监听

		pageCheckBoxAnimation.setOnCheckedChangeListener(boxListener);
		pageCheckBoxRing.setOnCheckedChangeListener(boxListener);
		classCheckBox.setOnCheckedChangeListener(boxListener);
		examCheckBox.setOnCheckedChangeListener(boxListener);
		workCheckBox.setOnCheckedChangeListener(boxListener);

		// 时间间隔监听
		classTime.setOnClickListener(viewTimeListener);
		examTime.setOnClickListener(viewTimeListener);
		workTime.setOnClickListener(viewTimeListener);
		//
		// 铃声监听
		classRing.setOnClickListener(viewRingListener);
		examRing.setOnClickListener(viewRingListener);
		workRing.setOnClickListener(viewRingListener);
		init();

	}

	OnCheckedChangeListener boxListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			switch ((Integer) buttonView.getTag()) {
			case 1:
				localEditor.putBoolean("page_checkBox_animation", isChecked);
				localEditor.commit();
				break;
			case 2:
				localEditor.putBoolean("page_checkBox_ring", isChecked);
				localEditor.commit();
				break;
			case 11:
				localEditor.putBoolean("class_checkBox", isChecked);
				localEditor.commit();
				if (isChecked == true) {
					classTime.setEnabled(true);
					classRing.setEnabled(true);
					setTextLight(classTimeText);
					setTextLight(classTimeHint);
					setTextLight(classRingText);
					setTextLight(classRingHint);

				} else {
					classTime.setEnabled(false);
					classRing.setEnabled(false);
					setTextDark(classTimeText);
					setTextDark(classTimeHint);
					setTextDark(classRingText);
					setTextDark(classRingHint);
				}
				break;
			case 21:
				localEditor.putBoolean("exam_checkBox", isChecked);
				localEditor.commit();
				if (isChecked == true) {
					examTime.setEnabled(true);
					examRing.setEnabled(true);
					setTextLight(examTimeText);
					setTextLight(examTimeHint);
					setTextLight(examRingText);
					setTextLight(examRingHint);

				} else {
					examTime.setEnabled(false);
					examRing.setEnabled(false);
					setTextDark(examTimeText);
					setTextDark(examTimeHint);
					setTextDark(examRingText);
					setTextDark(examRingHint);
				}
				break;
			case 31:
				localEditor.putBoolean("work_checkBox", isChecked);
				localEditor.commit();
				if (isChecked == true) {
					workTime.setEnabled(true);
					workRing.setEnabled(true);
					setTextLight(workTimeText);
					setTextLight(workTimeHint);
					setTextLight(workRingText);
					setTextLight(workRingHint);

				} else {
					workTime.setEnabled(false);
					workRing.setEnabled(false);
					setTextDark(workTimeText);
					setTextDark(workTimeHint);
					setTextDark(workRingText);
					setTextDark(workRingHint);
				}
				break;
			default:
				break;

			}
		}

	};

	View.OnClickListener viewTimeListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int flagChoiceTime = 0;
			switch ((Integer) v.getTag()) {
			case 12:
				long flag_time1 = settings
						.getLong("class_time", 10 * 60 * 1000);
				if (flag_time1 == 10 * 60 * 1000) {
					flagChoiceTime = 0;
				} else if (flag_time1 == 20 * 60 * 1000) {
					flagChoiceTime = 1;
				} else if (flag_time1 == 30 * 60 * 1000) {
					flagChoiceTime = 2;
				}

				timeSelect(classTimeHint, classTimeArray, 1, flagChoiceTime);

				break;
			case 22:
				long flag_time2 = settings.getLong("exam_time", 1 * 24 * 60
						* 60 * 1000);
				System.out.println("===============" + flag_time2
						+ "====================");
				if (flag_time2 == 1 * 24 * 60 * 60 * 1000) {
					flagChoiceTime = 0;
				} else if (flag_time2 == 2 * 24 * 60 * 60 * 1000) {
					flagChoiceTime = 1;
				} else if (flag_time2 == 3 * 24 * 60 * 60 * 1000) {
					flagChoiceTime = 2;
				}
				timeSelect(examTimeHint, examTimeArray, 2, flagChoiceTime);

				break;
			case 32:
				long flag_time3 = settings.getLong("work_time",
						6 * 60 * 60 * 1000);
				if (flag_time3 == 6 * 60 * 60 * 1000) {
					flagChoiceTime = 0;
				} else if (flag_time3 == 12 * 60 * 60 * 1000) {
					flagChoiceTime = 1;
				} else if (flag_time3 == 18 * 60 * 60 * 1000) {
					flagChoiceTime = 2;
				}
				timeSelect(workTimeHint, workTimeArray, 3, flagChoiceTime);

				break;
			}
		}
	};

	View.OnClickListener viewRingListener = new View.OnClickListener() {

		@Override
		public void onClick(View arg0) {

			// TODO Auto-generated method stub
			String uri_default = null;
			switch ((Integer) arg0.getTag()) {
			case 13:
				uri_default = settings.getString("class_ring",
						"content://settings/system/ringtone");
				ringSelect(classCode, uri_default);
				break;
			case 23:
				uri_default = settings.getString("exam_ring",
						"content://settings/system/ringtone");
				ringSelect(examCode, uri_default);
				break;
			case 33:
				uri_default = settings.getString("work_ring",
						"content://settings/system/ringtone");
				ringSelect(workCode, uri_default);
				break;

			}
		}
	};

	/* 当设置铃声之后的回调函数 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {

		case classCode:
			try {
				// 得到我们选择的铃声
				classRingUri = data
						.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
				// Toast.makeText(Mysetting.this,
				// "class_ring"+settings.getString("class_ring","无"),
				// Toast.LENGTH_SHORT).show();
				System.out.println(settings.getString("class_ring", "无"));
				Ringtone str = RingtoneManager.getRingtone(this, classRingUri);
				String str2 = str.getTitle(this);
				localEditor.putString("class_ring", classRingUri.toString());
				localEditor.commit();
				localEditor.putString("class_ring_title", str2);
				localEditor.commit();
				classRingHint.setText(str2);
			} catch (Exception e) {
			}
			break;
		case examCode:
			try {
				// 得到我们选择的铃声
				examRingUri = data
						.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

				Ringtone str = RingtoneManager.getRingtone(this, examRingUri);
				String str2 = str.getTitle(this);
				localEditor.putString("exam_ring", examRingUri.toString());
				localEditor.commit();
				localEditor.putString("exam_ring_title", str2);
				localEditor.commit();
				examRingHint.setText(str2);
				// Toast.makeText(Mysetting.this,
				// "exam_ring"+settings.getString("exam_ring","无"),
				// Toast.LENGTH_SHORT).show();
				// System.out.println(settings.getString("exam_ring","无"));
			} catch (Exception e) {
			}
			break;
		case workCode:
			try {
				// 得到我们选择的铃声
				workRingUri = data
						.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

				Ringtone str = RingtoneManager.getRingtone(this, workRingUri);
				String str2 = str.getTitle(this);
				workRingHint.setText(str2);
				localEditor.putString("work_ring", workRingUri.toString());
				localEditor.commit();
				localEditor.putString("work_ring_title", str2);
				localEditor.commit();

				// System.out.println(settings.getString("work_ring","无"));
			} catch (Exception e) {
			}
			break;

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void setTextLight(TextView tv) {
		tv.setTextColor(getResources().getColor(R.color.light));
	}

	public void setTextDark(TextView tv) {
		tv.setTextColor(getResources().getColor(R.color.dark));
	}

	// 弹出dialog对话框
	public int timeSelect(TextView mText, String[] str, int timeFlag,
			int timeChoiceFlag) {

		mTimeFlag = timeFlag;
		mHintText = mText;
		timeString = str;
		// WindowManager.LayoutParams layoutParams =
		// dialog.getWindow().getAttributes();
		// layoutParams.width = 200;
		// layoutParams.height = LayoutParams.WRAP_CONTENT;
		new AlertDialog.Builder(Setting.this)
				.setTitle("选择间隔时间")
				.setSingleChoiceItems(timeString, timeChoiceFlag,
						new DialogInterface.OnClickListener() {// 设置条目
							public void onClick(DialogInterface dialog,
									int which) {// 响应事件
								// do something
								// 关闭对话框
								timeNumber = which + 1;
								dialog.dismiss();
								// Toast.makeText(Mysetting.this,
								// "弹出时间间隔" + which, Toast.LENGTH_SHORT)
								// .show();
								System.out.println("序号" + which);
								if (mTimeFlag == 1) {
									// mTimeHintPreference = "class_time";
									if (timeNumber == 1) {
										classTimeHint.setText(timeString[0]);
										localEditor.putLong("class_time",
												10 * 60 * 1000);
										localEditor.commit();
									} else if (timeNumber == 2) {
										classTimeHint.setText(timeString[1]);
										localEditor.putLong("class_time",
												20 * 60 * 1000);
										localEditor.commit();
									} else if (timeNumber == 3) {
										classTimeHint.setText(timeString[2]);
										localEditor.putLong("class_time",
												30 * 60 * 1000);
										localEditor.commit();
									}
								} else if (mTimeFlag == 2) {
									// mTimeHintPreference = "exam_time";
									if (timeNumber == 1) {
										examTimeHint.setText(examTimeArray[0]);
										localEditor.putLong("exam_time", 1 * 24
												* 60 * 60 * 1000);
										localEditor.commit();
									} else if (timeNumber == 2) {
										examTimeHint.setText(examTimeArray[1]);
										System.out.println("==============="
												+ "选择二"
												+ "====================");
										localEditor.putLong("exam_time", 2 * 24
												* 60 * 60 * 1000);
										localEditor.commit();

									} else if (timeNumber == 3) {
										examTimeHint.setText(examTimeArray[2]);
										localEditor.putLong("exam_time", 3 * 24
												* 60 * 60 * 1000);
										localEditor.commit();
									}
								} else if (mTimeFlag == 3) {
									// mTimeHintPreference = "work_time";
									if (timeNumber == 1) {
										workTimeHint.setText(workTimeArray[0]);
										localEditor.putLong("work_time",
												6 * 60 * 60 * 1000);
										localEditor.commit();
									} else if (timeNumber == 2) {
										workTimeHint.setText(workTimeArray[1]);
										localEditor.putLong("work_time",
												12 * 60 * 60 * 1000);
										localEditor.commit();
									} else if (timeNumber == 3) {
										workTimeHint.setText(workTimeArray[2]);
										localEditor.putLong("work_time",
												18 * 60 * 60 * 1000);
										localEditor.commit();
									}
								}

							}
						})
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// do something
						timeNumber = which + 2;
						// Toast.makeText(Mysetting.this, "弹出时间间隔" + which,
						// Toast.LENGTH_SHORT).show();
						System.out.println("onClick序号" + which);
						// if(mTimeFlag == 1){
						// //mTimeHintPreference = "class_time";
						// if ( timeNumber == 1) {
						// classTimeHint.setText(timeString[0]);
						// System.out.println("点击确定按钮"+timeString[0]);
						// localEditor.putLong("class_time", 10 * 60 * 1000);
						// localEditor.commit();
						// } else if (timeNumber == 2) {
						// classTimeHint.setText(timeString[1]);
						// localEditor.putLong("class_time", 20 * 60 * 1000);
						// localEditor.commit();
						// } else if (timeNumber == 3) {
						// classTimeHint.setText(timeString[2]);
						// localEditor.putLong("class_time", 30 * 60 * 1000);
						// localEditor.commit();
						// }
						// }else if(mTimeFlag == 2){
						// //mTimeHintPreference = "exam_time";
						// if (timeNumber == 1) {
						// examTimeHint.setText(examTimeArray[0]);
						// localEditor.putLong("exam_time", 1 * 24 * 60 * 60 *
						// 1000);
						// } else if (timeNumber == 2) {
						// examTimeHint.setText(examTimeArray[1]);
						// localEditor.putLong("exam_time", 2 * 24 * 60 * 60 *
						// 1000);
						// } else if (timeNumber == 3) {
						// examTimeHint.setText(examTimeArray[2]);
						// localEditor.putLong("exam_time", 3 * 24 * 60 * 60 *
						// 1000);
						// }
						// }else if(mTimeFlag == 3){
						// //mTimeHintPreference = "work_time";
						// if (timeNumber == 1) {
						// workTimeHint.setText(workTimeArray[0]);
						// localEditor.putLong("work_time", 6 * 60 * 60 * 1000);
						// } else if (timeNumber == 2) {
						// workTimeHint.setText(workTimeArray[1]);
						// localEditor.putLong("work_time", 12 * 60 * 60 *
						// 1000);
						// } else if (timeNumber == 3) {
						// workTimeHint.setText(workTimeArray[2]);
						// localEditor.putLong("work_time", 18 * 60 * 60 *
						// 1000);
						// }
						// }

					}
				})// 添加button并响应点击事件
				.show();

		return timeNumber + 1;

	}

	public void ringSelect(int requestCode, String str) {
		String UriString = str;
		// Toast.makeText(Mysetting.this, "requestCode"+requestCode,
		// Toast.LENGTH_SHORT).show();
		// 打开系统铃声设置
		// try{ RingtoneManager.setActualDefaultRingtoneUri(Mysetting.this,
		// RingtoneManager.TYPE_ALARM,
		// Uri.parse("content://media/internal/audio/media/68"));
		// System.out.println("*****************设置默认********************");
		// }
		// catch(Exception e){
		// e.printStackTrace();
		//
		// }
		// rm.setActualDefaultRingtoneUri(Mysetting.this, type,
		// Uri.parse("content://media/internal/audio/media/68"));
		Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);

		// 设置铃声类型和title
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
				RingtoneManager.TYPE_ALARM);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "设置闹铃铃声");
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,
				Uri.parse(UriString));
		// 当设置完成之后返回到当前的Activity
		startActivityForResult(intent, requestCode);

	}

	public void init() {
		pageCheckBoxAnimation.setChecked(settings.getBoolean(
				"page_checkBox_animation", true));
		pageCheckBoxRing.setChecked(settings.getBoolean("page_checkBox_ring",
				true));
		classCheckBox.setChecked(settings.getBoolean("class_checkBox", true));
		if (settings.getBoolean("class_checkBox", true) == true) {
			classTime.setEnabled(true);
			classRing.setEnabled(true);
			setTextLight(classTimeText);
			setTextLight(classTimeHint);
			setTextLight(classRingText);
			setTextLight(classRingHint);
		} else {
			classTime.setEnabled(false);
			classRing.setEnabled(false);
			setTextDark(classTimeText);
			setTextDark(classTimeHint);
			setTextDark(classRingText);
			setTextDark(classRingHint);
		}
		long flag_time1 = settings.getLong("class_time", 10 * 60 * 1000);
		int classDefaultText = 0;
		if (flag_time1 == 10 * 60 * 1000) {
			classDefaultText = 0;
		} else if (flag_time1 == 20 * 60 * 1000) {
			classDefaultText = 1;
		} else if (flag_time1 == 30 * 60 * 1000) {
			classDefaultText = 2;
		}
		classTimeHint.setText(classTimeArray[classDefaultText]);
		// ///////////////////////////////////////////////////

		// //////////////////////////////////////////////////
		examCheckBox.setChecked(settings.getBoolean("exam_checkBox", true));
		if (settings.getBoolean("exam_checkBox", true) == true) {
			examTime.setEnabled(true);
			examRing.setEnabled(true);
			setTextLight(examTimeText);
			setTextLight(examTimeHint);
			setTextLight(examRingText);
			setTextLight(examRingHint);

		} else {
			examTime.setEnabled(false);
			examRing.setEnabled(false);
			setTextDark(examTimeText);
			setTextDark(examTimeHint);
			setTextDark(examRingText);
			setTextDark(examRingHint);
		}
		long flag_time2 = settings
				.getLong("exam_time", 1 * 24 * 60 * 60 * 1000);
		int examDefaultText = 0;
		if (flag_time2 == 1 * 24 * 60 * 60 * 1000) {
			examDefaultText = 0;
		} else if (flag_time2 == 2 * 24 * 60 * 60 * 1000) {
			examDefaultText = 1;
		} else if (flag_time2 == 3 * 24 * 60 * 60 * 1000) {
			examDefaultText = 2;
		}
		examTimeHint.setText(examTimeArray[examDefaultText]);
		// //////////////////////////////////////////////////////////

		// ////////////////////////////////////////////////////////////
		workCheckBox.setChecked(settings.getBoolean("work_checkBox", true));
		if (settings.getBoolean("work_checkBox", true) == true) {
			workTime.setEnabled(true);
			workRing.setEnabled(true);
			setTextLight(workTimeText);
			setTextLight(workTimeHint);
			setTextLight(workRingText);
			setTextLight(workRingHint);

		} else {
			workTime.setEnabled(false);
			workRing.setEnabled(false);
			setTextDark(workTimeText);
			setTextDark(workTimeHint);
			setTextDark(workRingText);
			setTextDark(workRingHint);
		}

		int workDefaultText = 0;
		long flag_time3 = settings.getLong("work_time", 6 * 60 * 60 * 1000);
		if (flag_time3 == 6 * 60 * 60 * 1000) {
			workDefaultText = 0;
		} else if (flag_time3 == 12 * 60 * 60 * 1000) {
			workDefaultText = 1;
		} else if (flag_time3 == 18 * 60 * 60 * 1000) {
			workDefaultText = 2;
		}
		workTimeHint.setText(workTimeArray[workDefaultText]);
		// ////////////////////////////////////////////////////////

		// //////////////////////////////////////////////////////////
		classRingHint.setText(settings.getString("class_ring_title", "默认铃声"));
		examRingHint.setText(settings.getString("exam_ring_title", "默认铃声"));
		workRingHint.setText(settings.getString("work_ring_title", "默认铃声"));

	}

}
