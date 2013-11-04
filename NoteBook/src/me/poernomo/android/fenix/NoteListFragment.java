package me.poernomo.android.fenix;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NoteListFragment extends ListFragment {

	private static final String TAG = "NoteListFragment";

	private ArrayList<Note> mNotes;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		getActivity().setTitle(R.string.app_name);
		mNotes = NoteBinder.get(getActivity()).getNotes();

		NoteListAdapter adapter = new NoteListAdapter(mNotes);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		Note c = ((NoteListAdapter) getListAdapter()).getItem(position);
		Log.d(TAG, c.getTitle() + " was clicked");

		Intent i = new Intent(getActivity(), NotePagerActivity.class);
		i.putExtra(NoteFragment.EXTRA_NOTE_ID, c.getId());
		startActivity(i);

	}

	@Override
	public void onResume()
	{
		super.onResume();
		((NoteListAdapter) getListAdapter()).notifyDataSetChanged();
	}

	private class NoteListAdapter extends ArrayAdapter<Note> {
		public NoteListAdapter(ArrayList<Note> notes)
		{
			super(getActivity(), 0, notes);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			if (convertView == null)
			{
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_item_note, null);
			}

			Note note = getItem(position);

			TextView titleTextView = (TextView) convertView
					.findViewById(R.id.list_title_textView);
			titleTextView.setText(note.getTitle());

			TextView dateTextView = (TextView) convertView
					.findViewById(R.id.list_date_textView);
			dateTextView.setText(note.getDate().toString());

			return convertView;
		}
	}

}
