package com.alpargabos.tuda.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alpargabos.tuda.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MomentsAdapter extends RecyclerView.Adapter<MomentsViewHolder> {

	private Context context;
	private List<ImportantMoment> importantMoments = new ArrayList<>();

	public MomentsAdapter(Context context) {
		this.context = context;
	}

	public void setList(List<ImportantMoment> moments) {
		// TODO: 1/1/17 do differenciate to have nice animations
		importantMoments = moments;
		this.notifyDataSetChanged();
	}

	@Override
	public MomentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.moments_item, parent, false);
		return new MomentsViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(final MomentsViewHolder holder, int position) {
		final ImportantMoment moment = importantMoments.get(position);
		if (moment.getType() == ImportantMoment.VIDEO) {
			holder.videoMark.setVisibility(View.VISIBLE);
			// TODO: 1/2/17 server should store the thumbnail and also the video
			try {
				Bitmap bitmap = retriveVideoFrameFromVideo(moment.getUrl());
				holder.thumbnail.setImageBitmap(bitmap);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		} else {
			holder.videoMark.setVisibility(View.GONE);
			Glide.with(context)
				.load(moment.getUrl())
				.asBitmap()
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				// TODO: 12/30/16 add placeholder + not found
				//			.placeholder(R.drawable.placeholder)
				//			.error(R.drawable.imagenotfound)
				.into(holder.thumbnail);
		}

		holder.thumbnail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Card wit url " + moment.getUrl() + " was clicked!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}
		});
	}

	@Override
	public int getItemCount() {
		return importantMoments.size();
	}

	// TODO: 1/2/17 make it async
	public static Bitmap retriveVideoFrameFromVideo(String videoPath)
		throws Throwable {
		Bitmap bitmap = null;
		MediaMetadataRetriever mediaMetadataRetriever = null;
		try {
			mediaMetadataRetriever = new MediaMetadataRetriever();
			mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
			bitmap = mediaMetadataRetriever.getFrameAtTime();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());
		} finally {
			if (mediaMetadataRetriever != null) {
				mediaMetadataRetriever.release();
			}
		}
		return bitmap;
	}
}