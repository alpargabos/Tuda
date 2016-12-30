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
import net.danlew.android.joda.DateUtils;
import org.joda.time.DateTime;

import java.util.List;

import static net.danlew.android.joda.DateUtils.FORMAT_SHOW_DATE;
import static net.danlew.android.joda.DateUtils.FORMAT_SHOW_TIME;
import static net.danlew.android.joda.DateUtils.FORMAT_SHOW_YEAR;

public class ImportantDatesAdapter extends RecyclerView.Adapter<ImportantDatesViewHolder> {

	private Context context;
	private List<ImportantDates> importantDates;

	public ImportantDatesAdapter(Context context, List<ImportantDates> importantDates) {
		this.context = context;
		this.importantDates = importantDates;
	}

	@Override
	public ImportantDatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_important_date, parent, false);

		return new ImportantDatesViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(final ImportantDatesViewHolder holder, int position) {
		ImportantDates impDate = importantDates.get(position);
		holder.title.setText(impDate.getTitle());
		holder.time.setText(DateUtils.formatDateTime(context, new DateTime(impDate.getDateUtc()), FORMAT_SHOW_YEAR | FORMAT_SHOW_DATE | FORMAT_SHOW_TIME));

		Glide.with(context)
			.load(impDate.getThumbnail())
			.diskCacheStrategy(DiskCacheStrategy.ALL)
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