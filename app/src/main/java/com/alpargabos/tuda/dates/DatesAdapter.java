package com.alpargabos.tuda.dates;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alpargabos.tuda.R;
import com.alpargabos.tuda.dates.model.ImportantDates;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.List;

public class DatesAdapter extends RecyclerView.Adapter<DatesViewHolder> {

	private Context context;
	private List<ImportantDates> importantDates = new ArrayList<>();

	public DatesAdapter(Context context) {
		this.context = context;
	}

	public void setList(List<ImportantDates> dates) {
		// TODO: 1/1/17 do differenciate to have nice animations
		importantDates = dates;
		this.notifyDataSetChanged();
	}

	@Override
	public DatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_important_date, parent, false);

		return new DatesViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(final DatesViewHolder holder, int position) {
		ImportantDates impDate = importantDates.get(position);
		holder.title.setText(impDate.getTitle());

		DateTime cardDate = new DateTime(impDate.getDate());
		DateTime now = new DateTime();

		Period period = new Period(cardDate, now);

		holder.years.setText(String.valueOf(period.getYears()));
		holder.months.setText(String.valueOf(period.getMonths()));
		holder.days.setText(String.valueOf(period.getDays()));
		holder.hours.setText(String.valueOf(period.getHours()));
		holder.minutes.setText(String.valueOf(period.getMinutes()));

		Glide.with(context)
			.load(impDate.getThumbnail())
			.diskCacheStrategy(DiskCacheStrategy.ALL)
			// TODO: 12/30/16 add placeholder + not found
			//			.placeholder(R.drawable.placeholder)
			//			.error(R.drawable.imagenotfound)
			.into(holder.thumbnail);

		holder.thumbnail.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Card wit title " + holder.title.getText() + " was clicked!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
			}
		});
	}

	@Override
	public int getItemCount() {
		return importantDates.size();
	}
}