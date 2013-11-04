package me.poernomo.android.fenix;

import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NoteFragment extends Fragment {

	public static final String EXTRA_NOTE_ID = "me.poernomo.android.fenix.note_id";

	private Note mNote;
	private EditText mTitleEditText, mContentEditText;
	private Button mDateButton;

	public static NoteFragment newInstance(UUID id)
	{
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_NOTE_ID, id);
		NoteFragment fragment = new NoteFragment();
		fragment.setArguments(args);

		return fragment;

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		UUID noteId = (UUID) getArguments().getSerializable(EXTRA_NOTE_ID);

		mNote = NoteBinder.get(getActivity()).getNote(noteId);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_note, container, false);

		mTitleEditText = (EditText) v.findViewById(R.id.note_title_editText);
		mTitleEditText.setText(mNote.getTitle());
		mContentEditText = (EditText) v
				.findViewById(R.id.note_content_editText);
		mContentEditText.setText(mNote.getContent());
		mDateButton = (Button) v.findViewById(R.id.note_date_button);
		mDateButton.setEnabled(false);
		mDateButton.setText(mNote.getDate().toString());

		mTitleEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3)
			{
				mNote.setTitle(arg0.toString());

			}

		});

		mContentEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				mNote.setContent(s.toString());

			}

		});

		return v;

	}

}
