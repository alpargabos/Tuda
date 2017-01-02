package com.alpargabos.tuda.moments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alpargabos.tuda.R;

public class MomentsViewHolder extends RecyclerView.ViewHolder {
	@BindView(R.id.moments_thumbnail) ImageView thumbnail;
	@BindView(R.id.moments_video_mark) ImageView videoMark;

	public MomentsViewHolder(View view) {
		super(view);
		ButterKnife.bind(this, view);
	}
}