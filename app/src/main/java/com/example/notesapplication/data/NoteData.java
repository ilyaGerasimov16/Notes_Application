package com.example.notesapplication.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class NoteData implements Parcelable {
    private final String tittle;
    private final String description;
    private final int picture;
    private final boolean like;
    private final Date date;

    public NoteData(String tittle, String description, int picture, boolean like, Date date) {
        this.tittle = tittle;
        this.description = description;
        this.picture = picture;
        this.like = like;
        this.date = date;
    }

    protected NoteData(Parcel in) {
        tittle = in.readString();
        description = in.readString();
        picture = in.readInt();
        like = in.readByte() != 0;
        date = new Date(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tittle);
        dest.writeString(description);
        dest.writeInt(picture);
        dest.writeByte((byte) (like ? 1 : 0));
        dest.writeLong(date.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NoteData> CREATOR = new Creator<NoteData>() {
        @Override
        public NoteData createFromParcel(Parcel in) {
            return new NoteData(in);
        }

        @Override
        public NoteData[] newArray(int size) {
            return new NoteData[size];
        }
    };

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public int getPicture() {
        return picture;
    }

    public boolean isLike() {
        return like;
    }

    public Date getDate() {
        return date;
    }

}
