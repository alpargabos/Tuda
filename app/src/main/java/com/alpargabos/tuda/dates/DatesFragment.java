package com.alpargabos.tuda.dates;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alpargabos.tuda.R;
import com.alpargabos.tuda.dates.model.ImportantDates;

import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class DatesFragment extends Fragment {

	@BindView(R.id.dates_recycler_view) RecyclerView recyclerView;
	ImportantDatesAdapter adapter;

	public static DatesFragment newInstance() {
		return new DatesFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dates, container, false);
		ButterKnife.bind(this, view);

		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(linearLayoutManager);

		Long yearAgo = 360 * 24 * 60 * 60L * 1000L;
		ImportantDates d1 = new ImportantDates("1", "Wedding", System.currentTimeMillis() - yearAgo, "http://www.doublejj.com/wp-content/uploads/2015/03/white-wedding.jpg", System.currentTimeMillis());
		ImportantDates d2 = new ImportantDates("1", "Japan", System.currentTimeMillis() - 30000000L, "http://japan-magazine.jnto.go.jp/jnto2wm/wp-content/uploads/1608_special_TOTO_main.jpg", System.currentTimeMillis());

		adapter = new ImportantDatesAdapter(getContext(), Arrays.asList(d1, d2, d1, d2, d2, d2, d1));
		recyclerView.setAdapter(adapter);

		return view;
	}
}
