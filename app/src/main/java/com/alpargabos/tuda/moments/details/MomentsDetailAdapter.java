package com.alpargabos.tuda.moments.details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.alpargabos.tuda.moments.ImportantMoment;

import java.util.List;

public class MomentsDetailAdapter extends FragmentStatePagerAdapter {

	private final List<ImportantMoment> importantMoments;

	public MomentsDetailAdapter(List<ImportantMoment> importantMoments, FragmentManager fm) {
		super(fm);
		this.importantMoments = importantMoments;
	}

	@Override
	public Fragment getItem(int position) {
		return MomentsDetailFragment.newInstance(importantMoments.get(position));
	}

	@Override
	public int getCount() {
		return importantMoments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return importantMoments.get(position).getTitle();
	}
}