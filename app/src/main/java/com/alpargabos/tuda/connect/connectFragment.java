package com.alpargabos.tuda.connect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.alpargabos.tuda.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConnectFragment extends Fragment {

	private ConnectionInfo connectionInfo;
	@BindView(R.id.connect_call_button) ImageButton callButton;
	@BindView(R.id.connect_sms_button) ImageButton smsButton;
	@BindView(R.id.connect_avatar_thumbnail) ImageView avatar;

	public static ConnectFragment newInstance() {
		return new ConnectFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_connect, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		requestConnectInfo();
	}

	private void requestConnectInfo() {
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference myRef = database.getReference("connect/1a2b3c");
		ValueEventListener eventListener = new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				connectionInfo = dataSnapshot.getValue(ConnectionInfo.class);

				callButton.setVisibility(View.VISIBLE);
				smsButton.setVisibility(View.VISIBLE);

				Glide.with(getContext())
					.load(connectionInfo.getAvatar())
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					// TODO: 12/30/16 add placeholder + not found
					//			.placeholder(R.drawable.placeholder)
					//			.error(R.drawable.imagenotfound)
					.into(avatar);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Snackbar.make(getView(), "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}
		};
		myRef.addValueEventListener(eventListener);
	}

	@OnClick(R.id.connect_call_button)
	public void onCallClicked() {
		if (connectionInfo != null && !connectionInfo.getPhone().isEmpty()) {
			Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + connectionInfo.getPhone()));
			startActivity(callIntent);
		} else {
			Snackbar.make(getView(), "No phone number :(", Snackbar.LENGTH_LONG).setAction("Action", null).show();
		}
	}

	@OnClick(R.id.connect_sms_button)
	public void onSmsClicked() {
		if (connectionInfo != null && !connectionInfo.getPhone().isEmpty()) {
			Intent intent = new Intent(Intent.ACTION_SENDTO);
			intent.setData(Uri.parse("smsto:" + Uri.encode(connectionInfo.getPhone())));
			intent.putExtra("sms_body", connectionInfo.getSms());
			startActivity(intent);
		} else {
			Snackbar.make(getView(), "No phone number :(", Snackbar.LENGTH_LONG).setAction("Action", null).show();
		}
	}
}
