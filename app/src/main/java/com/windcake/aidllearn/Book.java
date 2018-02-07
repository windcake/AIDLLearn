package com.windcake.aidllearn;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 创建者： windcake
 * 创建时间： 2018/2/4下午4:23.
 */

public class Book implements Parcelable
{
    private String mBookName;

    public Book(String bookName)
    {
        this.mBookName = bookName;
    }

    public String getName()
    {
        return mBookName;
    }

    protected Book(Parcel in)
    {
        this.mBookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>()
    {
        @Override
        public Book createFromParcel(Parcel in)
        {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size)
        {
            return new Book[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(mBookName);
    }

    @Override
    public String toString()
    {
        return mBookName;
    }
}
