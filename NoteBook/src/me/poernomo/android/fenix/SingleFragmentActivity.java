package me.poernomo.android.fenix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class SingleFragmentActivity extends FragmentActivity {

	protected abstract Fragment getFragment();

	@Override
	protected void onCreate(Bundle savedInstanceVariable)
	{
		super.onCreate(savedInstanceVariable);
		setContentView(R.layout.activity_single_fragment);

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

		if (fragment == null)
		{
			fragment = getFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragment)
					.commit();
		}
	}
}
