package com.alpargabos.tuda.moments;

public class ImportantMoment {
	public static final int IMAGE = 1;
	public static final int VIDEO = 2;

	private String title;
	private int type;
	private String url;
	private Long createdAt;

	public ImportantMoment() {
		//required
	}

	public ImportantMoment(String title, int type, String url, Long createdAt) {
		this.title = title;
		this.type = type;
		this.url = url;
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
}
