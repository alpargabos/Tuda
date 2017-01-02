package com.alpargabos.tuda;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alpargabos.tuda.connect.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TabActivity extends AppCompatActivity {
	@BindView(R.id.toolbar) Toolbar toolbar;
	@BindView(R.id.toolbar_name_first) TextView firstPersonName;
	@BindView(R.id.toolbar_avatar_thumbnail_first) CircleImageView firstPersonAvatar;
	@BindView(R.id.toolbar_name_second) TextView secondPersonName;
	@BindView(R.id.toolbar_avatar_thumbnail_second) CircleImageView secondPersonAvatar;
	@BindView(R.id.viewpager) ViewPager viewPager;
	@BindView(R.id.sliding_tabs) TabLayout tabLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		ButterKnife.bind(this);

		setSupportActionBar(toolbar);

		viewPager.setAdapter(new TudaFragmentPagerAdapter(getSupportFragmentManager(), TabActivity.this));
		// Give the TabLayout the ViewPager
		tabLayout.setupWithViewPager(viewPager);

		requestData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_list, menu);
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

	private void requestData() {
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference myRef = database.getReference("settings/1a2b3c");
		ValueEventListener eventListener = new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				SettingsInfo settingsInfo = dataSnapshot.getValue(SettingsInfo.class);
				firstPersonName.setText(settingsInfo.name_a);
				secondPersonName.setText(settingsInfo.name_b);
				Glide.with(getBaseContext())
					.load(settingsInfo.avatar_a)
					.asBitmap()
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					// TODO: 12/30/16 add placeholder + not found
					//			.placeholder(R.drawable.placeholder)
					//			.error(R.drawable.imagenotfound)
					.into(firstPersonAvatar);

				Glide.with(getBaseContext())
					.load(settingsInfo.avatar_b)
					.asBitmap()
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					// TODO: 12/30/16 add placeholder + not found
					//			.placeholder(R.drawable.placeholder)
					//			.error(R.drawable.imagenotfound)
					.into(secondPersonAvatar);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Snackbar.make(viewPager, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}
		};
		myRef.addValueEventListener(eventListener);
	}
}
