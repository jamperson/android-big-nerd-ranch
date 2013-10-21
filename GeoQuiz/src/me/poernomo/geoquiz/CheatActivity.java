package me.poernomo.geoquiz;

import android.app.Activity;
import android.os.Bundle;

public class CheatActivity extends Activity {

	private boolean mAnswerIsTrue;
	public static final String EXTRA_ANSWER_IS_TRUE = "me.poernomo.geoquiz.answer_is_true";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_cheat);
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
	}

}
