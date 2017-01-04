package com.alpargabos.tuda.moments.details;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.alpargabos.tuda.R;
import com.alpargabos.tuda.moments.ImportantMoment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import static com.alpargabos.tuda.moments.MomentsAdapter.retriveVideoFrameFromVideo;

public class MomentsDetailActivity extends AppCompatActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link FragmentPagerAdapter} derivative, which will keep every
	 * loaded fragment in memory. If this becomes too memory intensive, it
	 * may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private MomentsDetailAdapter sectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moments_detail);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		List<ImportantMoment> importantMoments = new ArrayList<>();

		sectionsPagerAdapter = new MomentsDetailAdapter(importantMoments, getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		viewPager = (ViewPager) findViewById(R.id.container);
		viewPager.setAdapter(sectionsPagerAdapter);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_moments_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_IMPORTANT_MOMENT = "section_number";

		public PlaceholderFragment() {
		}

		public static PlaceholderFragment newInstance(ImportantMoment moment) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			//			args.putParcelable(ARG_IMPORTANT_MOMENT, moment);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_moments_detail, container, false);
			ImportantMoment moment = getArguments().getParcelable(ARG_IMPORTANT_MOMENT);
			ImageView imageView = (ImageView) rootView.findViewById(R.id.moments_detail_image);
			if (moment.getType() == ImportantMoment.VIDEO) {
				//				holder.videoMark.setVisibility(View.VISIBLE);
				try {
					Bitmap bitmap = retriveVideoFrameFromVideo(moment.getUrl());
					imageView.setImageBitmap(bitmap);
				} catch (Throwable throwable) {
					throwable.printStackTrace();
				}
			} else {
				//				holder.videoMark.setVisibility(View.GONE);
				Glide.with(getContext())
					.load(moment.getUrl())
					.asBitmap()
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					// TODO: 12/30/16 add placeholder + not found
					//			.placeholder(R.drawable.placeholder)
					//			.error(R.drawable.imagenotfound)
					.into(imageView);
			}
			return rootView;
		}
	}

	public class MomentsDetailAdapter extends FragmentStatePagerAdapter {

		private final List<ImportantMoment> importantMoments;

		public MomentsDetailAdapter(List<ImportantMoment> importantMoments, FragmentManager fm) {
			super(fm);
			this.importantMoments = importantMoments;
		}

		@Override
		public Fragment getItem(int position) {
			return PlaceholderFragment.newInstance(importantMoments.get(position));
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
				case 0:
					return "SECTION 1";
				case 1:
					return "SECTION 2";
				case 2:
					return "SECTION 3";
			}
			return null;
		}
	}
}
