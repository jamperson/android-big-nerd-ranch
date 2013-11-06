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
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimePickerFragment extends DialogFragment {

	public static final String EXTRA_DATE = "me.poernomo.android.criminalintent.date";
	private Calendar mDate;

	public static TimePickerFragment newInstance(Calendar date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);

		TimePickerFragment fragment = new TimePickerFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		Calendar myDate = (Calendar) getArguments().getSerializable(EXTRA_DATE);

		mDate = (Calendar) myDate.clone();

		int hour = mDate.get(Calendar.HOUR_OF_DAY);
		int minute = mDate.get(Calendar.MINUTE);

		View v = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_time, null);

		TimePicker timePicker = (TimePicker) v
				.findViewById(R.id.dialog_time_picker);
		timePicker.setCurrentHour(hour);
		timePicker.setCurrentMinute(minute);
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker arg0, int arg1, int arg2) {
				mDate.set(Calendar.HOUR_OF_DAY, arg1);
				mDate.set(Calendar.MINUTE, arg2);
				getArguments().putSerializable(EXTRA_DATE, mDate);
			}

		});

		return new AlertDialog.Builder(getActivity())
				.setView(v)
				.setTitle(R.string.time_picker_title)
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								sendResults(Activity.RESULT_OK);

							}

						}).create();
	}

	private void sendResults(int resultCode) {
		if (getTargetFragment() == null)
			return;
		Intent i = new Intent();
		i.putExtra(EXTRA_DATE, mDate);
		getTargetFragment().onActivityResult(this.getTargetRequestCode(),
				resultCode, i);

	}

}
