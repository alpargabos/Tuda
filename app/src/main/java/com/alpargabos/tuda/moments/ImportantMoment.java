package com.alpargabos.tuda.moments;

import android.os.Parcel;
import android.os.Parcelable;

public class ImportantMoment implements Parcelable {
	public static final int IMAGE = 1;
	public static final int VIDEO = 2;

	private String key;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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

	protected ImportantMoment(Parcel in) {
		key = in.readString();
		title = in.readString();
		type = in.readInt();
		url = in.readString();
		createdAt = in.readByte() == 0x00 ? null : in.readLong();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(key);
		dest.writeString(title);
		dest.writeInt(type);
		dest.writeString(url);
		if (createdAt == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeLong(createdAt);
		}
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<ImportantMoment> CREATOR = new Parcelable.Creator<ImportantMoment>() {
		@Override
		public ImportantMoment createFromParcel(Parcel in) {
			return new ImportantMoment(in);
		}

		@Override
		public ImportantMoment[] newArray(int size) {
			return new ImportantMoment[size];
		}
	};
}