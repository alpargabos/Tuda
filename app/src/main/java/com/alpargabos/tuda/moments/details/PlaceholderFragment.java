package com.alpargabos.tuda.moments.details;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.alpargabos.tuda.R;
import com.alpargabos.tuda.moments.ImportantMoment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import static com.alpargabos.tuda.moments.MomentsAdapter.retriveVideoFrameFromVideo;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_IMPORTANT_MOMENT = "section_number";

	public PlaceholderFragment() {
	}

	public static PlaceholderFragment newInstance(ImportantMoment moment) {
		PlaceholderFragment fragment = new PlaceholderFragment();
		Bundle args = new Bundle();
		args.putParcelable(ARG_IMPORTANT_MOMENT, moment);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_moments_detail, container, false);
		ImportantMoment moment = getArguments().getParcelable(ARG_IMPORTANT_MOMENT);
		ImageView imageView = (ImageView) rootView.findViewById(R.id.moments_detail_image);
		if (moment.getType() == ImportantMoment.VIDEO) {
			//				holder.videoMark.setVisibility(View.VISIBLE);
			try {
				Bitmap bitmap = retriveVideoFrameFromVideo(moment.getUrl());
				imageView.setImageBitmap(bitmap);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		} else {
			//				holder.videoMark.setVisibility(View.GONE);
			Glide.with(getContext())
				.load(moment.getUrl())
				.asBitmap()
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				// TODO: 12/30/16 add placeholder + not found
				//			.placeholder(R.drawable.placeholder)
				//			.error(R.drawable.imagenotfound)
				.into(imageView);
		}
		return rootView;
	}
}