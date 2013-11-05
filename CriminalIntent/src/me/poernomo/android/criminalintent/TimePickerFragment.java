package me.poernomo.android.criminalintent;

import java.util.Calendar;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class TimePickerFragment extends DialogFragment {

	public static final String EXTRA_DATE = "me.poernomo.android.criminalintent.date";

	private Date mDate;

	public static TimePickerFragment newInstance(Date date)
	{
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);

		TimePickerFragment fragment = new TimePickerFragment();
		fragment.setArguments(args);
		return null;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{

		mDate = (Date) getArguments().getSerializable(EXTRA_DATE);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);

		Integer hour = calendar.get(Calendar.HOUR_OF_DAY) - 1;
		Integer minute = calendar.get(Calendar.MINUTE) - 2;

		View v = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_time, null);

		TimePicker timePicker = (TimePicker) v
				.findViewById(R.id.dialog_time_picker);
		timePicker.setCurrentHour(hour);
		timePicker.setCurrentMinute(minute);
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker arg0, int arg1, int arg2)
			{
				Calendar tempCal = Calendar.getInstance();
				tempCal.setTime(mDate);
				tempCal.set(Calendar.HOUR_OF_DAY, arg1);
				tempCal.set(Calendar.MINUTE, arg2);
				mDate = tempCal.getTime();
				getArguments().putSerializable(EXTRA_DATE, mDate);
			}

		});

		return new AlertDialog.Builder(getActivity()).setView(v)
				.setTitle(R.string.time_picker_title)
				.setPositiveButton(android.R.string.ok, null).create();
	}
}
