package me.poernomo.android.criminalintent;

import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class CrimeFragment extends Fragment {

	private static final String DIALOG_DATE = "date";
	private static final String DIALOG_TIME = "time";
	private static final int REQUEST_DATE = 0;

	public static final String EXTRA_CRIME_ID = "me.poernomo.android.criminalintent.crime_id";
	private Crime mCrime;
	private EditText mTitleField;
	private Button mDateButton, mTimeButton;
	private CheckBox mSolvedCheckBox;

	public static CrimeFragment newInstance(UUID crimeId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CRIME_ID, crimeId);
		CrimeFragment fragment = new CrimeFragment();
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK)
			return;
		if (requestCode == REQUEST_DATE)
		{
			Date date = (Date) data
					.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mCrime.setDate(date);
			updateDate();
		}
	}

	private void updateDate() {
		mDateButton
				.setText(DateFormat.format(MyDateFormat.df, mCrime.getDate()));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Change: instead of getting ID from intent, get it from Arguments
		// (Bundle)
		// UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(
		// EXTRA_CRIME_ID);

		UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
		mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_crime, parent, false);

		mTitleField = (EditText) v.findViewById(R.id.crime_title_edit_text);
		mTitleField.setText(mCrime.getTitle());
		mTitleField.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// Intentionally blank

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// Intentionally blank

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mCrime.setTitle(s.toString());

			}

		});

		mDateButton = (Button) v.findViewById(R.id.crime_date);
		// mDateButton.setText(DateFormat.format(df, mCrime.getDate()));
		updateDate(); // private helper function to avoid redundancy

		// mDateButton.setEnabled(false);
		mDateButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				// DatePickerFragment dialog = new DatePickerFragment();
				DatePickerFragment dialog = DatePickerFragment
						.newInstance(mCrime.getDate());
				dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_DATE);
			}
		});

		mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
		mSolvedCheckBox.setChecked(mCrime.isSolved());
		mSolvedCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						mCrime.setSolved(isChecked);
					}
				});

		mTimeButton = (Button) v.findViewById(R.id.crime_time);
		mTimeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				TimePickerFragment dialog = new TimePickerFragment();
				dialog.show(fm, DIALOG_TIME);

			}
		});

		return v;
	}

}
