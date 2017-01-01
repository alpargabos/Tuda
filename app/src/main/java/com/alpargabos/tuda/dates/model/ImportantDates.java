package com.alpargabos.tuda.dates.model;

// TODO: 12/30/16 add AutoValue and reformat this class with it
public class ImportantDates {
	String id;
	String title;
	Long date;
	String thumbnail;

	public ImportantDates() {
		//required
	}

	public ImportantDates(String id, String title, Long dateUtc, String thumbnail) {
		this.id = id;
		this.title = title;
		this.date = dateUtc;
		this.thumbnail = thumbnail;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Long getDate() {
		return date;
	}

	public String getThumbnail() {
		return thumbnail;
	}
}
