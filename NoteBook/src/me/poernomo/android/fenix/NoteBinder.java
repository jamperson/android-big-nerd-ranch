package me.poernomo.android.fenix;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class NoteBinder {

	private ArrayList<Note> sNotes;
	private static NoteBinder mNoteBinder;
	private Context mAppContext;

	private NoteBinder(Context appContext)
	{
		sNotes = new ArrayList<Note>();
		mAppContext = appContext;

		// dummy generator
		for (int i = 0; i < 100; i++)
		{
			sNotes.add(new Note("Note #" + i, "Content " + i));
		}
	}

	public static NoteBinder get(Context c)
	{
		if (mNoteBinder == null)
			mNoteBinder = new NoteBinder(c.getApplicationContext());
		return mNoteBinder;
	}

	public ArrayList<Note> getNotes()
	{
		return sNotes;
	}

	public Note getNote(UUID id)
	{
		if (id == null)
			return null;
		for (Note n : sNotes)
		{
			if (n.getId().equals(id))
				return n;
		}
		return null;
	}
}
