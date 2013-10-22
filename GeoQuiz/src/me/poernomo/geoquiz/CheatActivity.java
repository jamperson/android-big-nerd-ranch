package me.poernomo.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView, mVersionTextView;
	private Button mShowAnswerButton;
	private boolean mCheated = false;
	private String mAnswerText="";

	public static final String EXTRA_ANSWER_IS_TRUE = "me.poernomo.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "me.poernomo.geoquiz.answer_shown";
	private static final String KEY_ANSWER = "answer";
	private static final String KEY_CHEATER = "cheater";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		mAnswerIsTrue = getIntent()
				.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
		mVersionTextView = (TextView) findViewById(R.id.version_text_view);
		mVersionTextView.setText("API Level " + Build.VERSION.SDK_INT);

		mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
		mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
		
		if (savedInstanceState != null)
		{
			mCheated = savedInstanceState.getBoolean(KEY_CHEATER);
			mAnswerText = savedInstanceState.getString(KEY_ANSWER);
			mAnswerTextView.setText(mAnswerText);
		}
		setAnswerShownResult(mCheated); 

		mShowAnswerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v)
			{
				mCheated = true;
				setAnswerShownResult(mCheated); 
				if (mAnswerIsTrue)
					mAnswerTextView.setText(R.string.true_button);
				else
					mAnswerTextView.setText(R.string.false_button);
				mAnswerText = (String) mAnswerTextView.getText();
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putBoolean(KEY_CHEATER, mCheated);
		outState.putString(KEY_ANSWER, mAnswerText);
	}

	private void setAnswerShownResult(boolean answerShown)
	{
		Intent i = new Intent();
		i.putExtra(EXTRA_ANSWER_SHOWN, answerShown);
		setResult(RESULT_OK, i);
	}
}
