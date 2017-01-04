package com.alpargabos.tuda.moments.details;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import com.alpargabos.tuda.moments.ImportantMoment;

import java.util.List;

class ActionBarTitleSetterListener implements ViewPager.OnPageChangeListener {
	private Activity activity;
	private List<ImportantMoment> moments;

	public ActionBarTitleSetterListener(Activity activity, List<ImportantMoment> moments) {
		this.activity = activity;
		this.moments = moments;
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		activity.setTitle(moments.get(position).getTitle());
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}
}