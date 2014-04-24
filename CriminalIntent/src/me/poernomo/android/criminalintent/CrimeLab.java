package me.poernomo.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CrimeLab {

	private ArrayList<Crime> mCrimes;

	private static CrimeLab sCrimeLab;
	private Context mAppContext;

	private CrimeLab(Context appContext) {
		mAppContext = appContext;
		mCrimes = new ArrayList<Crime>();
		
//		// dummy crime generator
//		for (int i = 0; i < 100; i++)
//		{
//			Crime c = new Crime();
//			mCrimes.add(c);	// experiment. what if we added the crime before setting title & solved?
//			c.setTitle("Crime #" + i);
//			c.setSolved(i % 2 == 0);
//		}
	}
	
	public void addCrime(Crime c){
		mCrimes.add(c);
	}

	public static CrimeLab get(Context c) {
		if (sCrimeLab == null)
		{
			sCrimeLab = new CrimeLab(c.getApplicationContext());
		}
		return sCrimeLab;
	}

	public ArrayList<Crime> getCrimes() {
		return mCrimes;
	}

	public Crime getCrime(UUID id) {
		if (id != null)
		{
			for (Crime c : mCrimes)
			{
				if (c.getId().equals(id))
				{
					return c;
				}
			}
		}
		return null;
	}

}
