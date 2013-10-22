package me.poernomo.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class CrimeFragment extends Fragment {

	private Crime mCrime;
	private EditText mTitleField;
	private Button mDateButton;
	private CheckBox mSolvedCheckBox;

	// private DateFormat df;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		mCrime = new Crime();

		// df = DateFormat.getDateInstance(DateFormat.LONG);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_crime, parent, false);

		mTitleField = (EditText) v.findViewById(R.id.crime_title_edit_text);
		mTitleField.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s)
			{
				// Intentionally blank

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
				// Intentionally blank

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				mCrime.setTitle(s.toString());

			}

		});

		mDateButton = (Button) v.findViewById(R.id.crime_date);

		CharSequence df = "EEEE, d MMM, yyy"; // hardcoded stuff here, have to
												// consult SimpleDateFormat
		mDateButton.setText(DateFormat.format(df, mCrime.getDate()));

		// mDateButton.setText(df.format(mCrime.getDate()));
		mDateButton.setEnabled(false);

		mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
		mSolvedCheckBox
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked)
					{
						mCrime.setSolved(isChecked);
					}
				});

		return v;
	}

}
