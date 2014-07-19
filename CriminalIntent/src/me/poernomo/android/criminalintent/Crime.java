package me.poernomo.android.criminalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Crime {

	private UUID mId;
	private String mTitle;
	private Calendar mDate;
	private Photo mPhoto;
	private boolean mSolved;

	private static final String JSON_ID = "id";
	private static final String JSON_TITLE = "title";
	private static final String JSON_SOLVED = "solved";
	private static final String JSON_DATE = "date";
	private static final String JSON_PHOTO = "photo";

	public Crime() {
		mId = UUID.randomUUID();
		mDate = Calendar.getInstance();
		mTitle = ""; // setting default value (no nulls please)
		mSolved = false;// setting default value (no nulls please)
	}

	public Crime(JSONObject json) throws JSONException {
		mId = UUID.fromString(json.getString(JSON_ID));
		mTitle = json.getString(JSON_TITLE);
		mSolved = json.getBoolean(JSON_SOLVED);
		Date temp = new Date(json.getLong(JSON_DATE));
		mDate = Calendar.getInstance();
		mDate.setTime(temp);
		if (json.has(JSON_PHOTO))
			mPhoto = new Photo(json.getJSONObject(JSON_PHOTO));

	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		json.put(JSON_TITLE, mTitle);
		json.put(JSON_SOLVED, mSolved);
		// messy, converting from calendar to date, to long
		json.put(JSON_DATE, mDate.getTime().getTime());
		if (mPhoto != null)
			json.put(JSON_PHOTO, mPhoto.toJSON());
		return json;
	}

	@Override
	public String toString() {
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

	public Photo getPhoto(){
		return mPhoto;
	}
	
	public void setPhoto(Photo p) {
		mPhoto = p;
	}
}
