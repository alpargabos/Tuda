package com.alpargabos.tuda;

import android.app.Application;
import net.danlew.android.joda.JodaTimeAndroid;

public class TudaApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		JodaTimeAndroid.init(this);
	}
}