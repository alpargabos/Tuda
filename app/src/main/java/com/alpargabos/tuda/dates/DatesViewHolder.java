package com.alpargabos.tuda.dates;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alpargabos.tuda.R;

public class DatesViewHolder extends RecyclerView.ViewHolder {
	@BindView(R.id.thumbnail) ImageView thumbnail;
	@BindView(R.id.title) TextView title;
	@BindView(R.id.years) TextView years;
	@BindView(R.id.months) TextView months;
	@BindView(R.id.days) TextView days;
	@BindView(R.id.hours) TextView hours;
	@BindView(R.id.minutes) TextView minutes;

	public DatesViewHolder(View view) {
		super(view);
		ButterKnife.bind(this, view);
	}
}