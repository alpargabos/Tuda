package com.alpargabos.tuda.dates;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alpargabos.tuda.R;

public class ImportantDatesViewHolder extends RecyclerView.ViewHolder {
	@BindView(R.id.thumbnail) ImageView thumbnail;
	@BindView(R.id.title) TextView title;
	@BindView(R.id.time) TextView time;

	public ImportantDatesViewHolder(View view) {
		super(view);
		ButterKnife.bind(this, view);
	}
}