package com.alpargabos.tuda.dates;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.alpargabos.tuda.R;
import com.alpargabos.tuda.dates.model.ImportantDates;
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
public class DatesFragment extends Fragment {

	@BindView(R.id.recycler_view) RecyclerView recyclerView;
	DatesAdapter adapter;

	public static DatesFragment newInstance() {
		return new DatesFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dates, container, false);
		ButterKnife.bind(this, view);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(linearLayoutManager);

		adapter = new DatesAdapter(getContext());
		recyclerView.setAdapter(adapter);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		requestImportantDates();
	}

	private void requestImportantDates() {
		FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference myRef = database.getReference("dates/1a2b3c");
		ValueEventListener eventListener = new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				List<ImportantDates> importantDates = new ArrayList<>();
				// Get Post object and use the values to update the UI
				for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
					importantDates.add(snapshot.getValue(ImportantDates.class));
				}
				adapter.setList(importantDates);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Snackbar.make(DatesFragment.this.getView(), "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}
		};
		myRef.addValueEventListener(eventListener);
	}

	@OnClick(R.id.fab)
	public void onClickOnFab(View view) {
		Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
	}
}
