package me.poernomo.android.fenix;

import android.support.v4.app.Fragment;

public class NoteListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment getFragment()
	{
		return new NoteListFragment();
	}

}
