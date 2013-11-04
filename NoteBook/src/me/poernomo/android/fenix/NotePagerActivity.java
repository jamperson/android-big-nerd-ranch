package me.poernomo.android.fenix;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class NotePagerActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private ArrayList<Note> mNotes;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);

		mNotes = NoteBinder.get(this).getNotes();
		FragmentManager fm = getSupportFragmentManager();

		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

			@Override
			public Fragment getItem(int arg0)
			{
				Note note = mNotes.get(arg0);
				return NoteFragment.newInstance(note.getId());
			}

			@Override
			public int getCount()
			{
				return mNotes.size();
			}

		});

		UUID noteId = (UUID) this.getIntent().getSerializableExtra(
				NoteFragment.EXTRA_NOTE_ID);
		for (int i = 0; i < mNotes.size(); i++)
		{
			if (mNotes.get(i).getId().equals(noteId))
			{
				mViewPager.setCurrentItem(i);
				setTitle(mNotes.get(i).getTitle());
				break;
			}
		}

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0)
			{
				// intentionally blank

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{
				// intentionally blank

			}

			@Override
			public void onPageSelected(int arg0)
			{
				Note note = mNotes.get(arg0);
				if (note.getTitle() != null)
				{
					setTitle(note.getTitle());
				}

			}

		});
	}

}
