package com.alpargabos.tuda.moments.details;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alpargabos.tuda.R;
import com.alpargabos.tuda.moments.ImportantMoment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import static com.alpargabos.tuda.moments.MomentsAdapter.retriveVideoFrameFromVideo;

/**
 * A placeholder fragment containing a simple view.
 */
public class MomentsDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_IMPORTANT_MOMENT = "section_number";
	// TODO: 1/4/17 bind with butterknife

	public MomentsDetailFragment() {
	}

	public static MomentsDetailFragment newInstance(ImportantMoment moment) {
		MomentsDetailFragment fragment = new MomentsDetailFragment();
		Bundle args = new Bundle();
		args.putParcelable(ARG_IMPORTANT_MOMENT, moment);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_moments_detail, container, false);
		ImportantMoment moment = getArguments().getParcelable(ARG_IMPORTANT_MOMENT);
		final SubsamplingScaleImageView imageView = (SubsamplingScaleImageView) rootView.findViewById(R.id.moments_detail_image);
		if (moment.getType() == ImportantMoment.VIDEO) {
			//				holder.videoMark.setVisibility(View.VISIBLE);
			try {
				Bitmap bitmap = retriveVideoFrameFromVideo(moment.getUrl());
				imageView.setImage(ImageSource.bitmap(bitmap));
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		} else {
			//				holder.videoMark.setVisibility(View.GONE);
			Target<Bitmap> bitmapTarget = new SimpleTarget<Bitmap>() {
				@Override
				public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
					imageView.setImage(ImageSource.bitmap(resource));
				}
			};
			Glide.with(getContext())
				.load(moment.getUrl())
				.asBitmap()
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				// TODO: 12/30/16 add placeholder + not found
				//			.placeholder(R.drawable.placeholder)
				//			.error(R.drawable.imagenotfound)
				.into(bitmapTarget);
		}

		return rootView;
	}
}