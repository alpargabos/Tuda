package com.alpargabos.tuda.moments.details;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import com.alpargabos.tuda.R;
import com.alpargabos.tuda.moments.ImportantMoment;
import com.alpargabos.tuda.moments.MomentsAdapter;

import java.util.ArrayList;

public class MomentsDetailActivity extends AppCompatActivity {

	private MomentsDetailAdapter sectionsPagerAdapter;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

		setContentView(R.layout.activity_moments_detail);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		final ArrayList<ImportantMoment> importantMoments = getIntent().getParcelableArrayListExtra(MomentsAdapter.IMPORTANT_MOMENTS);
		int position = getIntent().getIntExtra(MomentsAdapter.IMPORTANT_MOMENT_POSITION, 0);
		sectionsPagerAdapter = new MomentsDetailAdapter(importantMoments, getSupportFragmentManager());

		viewPager = (ViewPager) findViewById(R.id.container);
		viewPager.setAdapter(sectionsPagerAdapter);
		viewPager.addOnPageChangeListener(new ActionBarTitleSetterListener(this, importantMoments));
		viewPager.setCurrentItem(position);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
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
}
