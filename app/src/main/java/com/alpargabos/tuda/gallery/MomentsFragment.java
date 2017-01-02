package com.alpargabos.tuda.gallery;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alpargabos.tuda.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MomentsFragment extends Fragment {

	@BindView(R.id.recycler_view) RecyclerView recyclerView;
	MomentsAdapter adapter;

	public static MomentsFragment newInstance() {
		return new MomentsFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.autofit_recycler_view, container, false);
		ButterKnife.bind(this, view);

		adapter = new MomentsAdapter(getContext());
		recyclerView.setAdapter(adapter);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		requestData();
	}

	private void requestData() {
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference myRef = database.getReference("moments/1a2b3c");
		ValueEventListener eventListener = new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				List<ImportantMoment> moments = new ArrayList<>();
				// Get Post object and use the values to update the UI
				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					moments.add(snapshot.getValue(ImportantMoment.class));
				}
				adapter.setList(moments);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Snackbar.make(MomentsFragment.this.getView(), "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}
		};
		myRef.addValueEventListener(eventListener);
	}
}
