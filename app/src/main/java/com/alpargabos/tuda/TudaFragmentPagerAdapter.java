package com.alpargabos.tuda;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.alpargabos.tuda.connect.ConnectFragment;
import com.alpargabos.tuda.dates.DatesFragment;
import com.alpargabos.tuda.moments.MomentsFragment;

public class TudaFragmentPagerAdapter extends FragmentPagerAdapter {
	final int PAGE_COUNT = 3;
	private String tabTitles[] = new String[] {"Dates", "Moments", "Connect"};
	private Context context;

	public TudaFragmentPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				// TODO: 12/30/16 cache fragments if needed
				return DatesFragment.newInstance();
			case 1:
				return MomentsFragment.newInstance();
			case 2:
				return ConnectFragment.newInstance();
			default:
				return DatesFragment.newInstance();
		}
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// Generate title based on item position
		return tabTitles[position];
	}
}
