package me.poernomo.android.fenix;

import java.util.Date;
import java.util.UUID;

public class Note {
	private String mTitle;
	private String mContent;
	private final UUID mId;
	private Date mDate;

	public Note()
	{
		mId = UUID.randomUUID();
		mTitle = "";
		mContent = "";
		mDate = new Date();

	}

	public Note(String title, String content)
	{
		this();
		mTitle = title;
		mContent = content;
	}

	public UUID getId()
	{
		return mId;
	}

	public Date getDate()
	{
		return mDate;
	}

	public String getTitle()
	{
		return mTitle;
	}

	public void setTitle(String title)
	{
		mTitle = title;
	}

	public String getContent()
	{
		return mContent;
	}

	public void setContent(String content)
	{
		mContent = content;
	}

	public void setDate(Date date)
	{
		mDate = date;
	}
}
