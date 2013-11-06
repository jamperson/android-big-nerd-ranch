package me.poernomo.android.criminalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Crime {

	private UUID mId;
	private String mTitle;
	private Calendar mDate;
	private boolean mSolved;
	
	public Crime()
	{
		mId = UUID.randomUUID();
		mDate= Calendar.getInstance();
		mTitle = ""; 	// setting default value (no nulls please)
		mSolved = false;// setting default value (no nulls please)
	}
	
	@Override
	public String toString()
	{
		return mTitle;
	}

	public Calendar getDate() {
		return mDate;
	}

	public void setDate(Calendar date) {
		mDate = date;
	}

	public boolean isSolved() {
		return mSolved;
	}

	public void setSolved(boolean solved) {
		mSolved = solved;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public UUID getId() {
		return mId;
	}
}
