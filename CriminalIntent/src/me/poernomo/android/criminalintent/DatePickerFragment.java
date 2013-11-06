package me.poernomo.android.criminalintent;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DatePickerFragment extends DialogFragment {

	public static final String EXTRA_DATE = "me.poernomo.android.criminalintent.date";
	private Calendar mDate;

	public static DatePickerFragment newInstance(Calendar date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);

		DatePickerFragment fragment = new DatePickerFragment();
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Calendar myDate = (Calendar) (getArguments()
				.getSerializable(EXTRA_DATE));

		mDate = (Calendar) myDate.clone(); // using clone so we don't change the
											// values within Crime directly

		int year = mDate.get(Calendar.YEAR);
		int month = mDate.get(Calendar.MONTH);
		int day = mDate.get(Calendar.DATE);

		View v = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_date, null);

		DatePicker datePicker = (DatePicker) v
				.findViewById(R.id.dialog_date_datePicker);
		datePicker.init(year, month, day, new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year, int month,
					int day) {
				// mDate = new GregorianCalendar(year, month, day).getTime();
				mDate.set(Calendar.YEAR, year);
				mDate.set(Calendar.MONTH, month);
				mDate.set(Calendar.DAY_OF_MONTH, day);
				getArguments().putSerializable(EXTRA_DATE, mDate);
			}

		});

		return new AlertDialog.Builder(getActivity())
				.setView(v)
				.setTitle(R.string.date_picker_title)
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								sendResult(Activity.RESULT_OK);
							}

						}).create();
	}

	private void sendResult(int resultCode) {
		if (getTargetFragment() == null)
			return; // no target! aborting...
		Intent i = new Intent();
		i.putExtra(EXTRA_DATE, mDate);
		getTargetFragment().onActivityResult(getTargetRequestCode(),
				resultCode, i);
	}
}
