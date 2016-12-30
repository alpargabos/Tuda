package com.alpargabos.tuda.dates.model;

// TODO: 12/30/16 add AutoValue and reformat this class with it
public class ImportantDates {
	String id;
	String title;
	Long dateUtc;
	String bgUrl;
	Long reminderUtc;

	public ImportantDates(String id, String title, Long dateUtc, String bgUrl, Long reminderUtc) {
		this.id = id;
		this.title = title;
		this.dateUtc = dateUtc;
		this.bgUrl = bgUrl;
		this.reminderUtc = reminderUtc;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Long getDateUtc() {
		return dateUtc;
	}

	public String getThumbnail() {
		return bgUrl;
	}

	public Long getReminderUtc() {
		return reminderUtc;
	}
}
