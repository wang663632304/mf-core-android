package com.masenf.core;

import android.annotation.TargetApi;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.util.Log;

// TODO: convert this to an API 11 compatible library
@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class GenericTabListener<T extends Fragment> implements TabListener {

	private static final String TAG = "GenericTabListener";
	private final Activity mAct;
	private final String mTag;
	private final Class<T> mClz;
	
	public GenericTabListener(Activity act, Class<T> fclass) {
		mAct = act;
		mTag = fclass.getName();
		mClz = fclass;
	}
	
	@Override
	public void onTabReselected(Tab t, FragmentTransaction ft) {

	}

	@Override
	public void onTabSelected(Tab t, FragmentTransaction ft) {
		Fragment mFrag = mAct.getFragmentManager().findFragmentByTag(mTag);
		if (mFrag == null) {
			Log.v(TAG, "onTabSelected() - instantiating " + mTag);
			mFrag = Fragment.instantiate(mAct, mClz.getName());
			ft.add(R.id.main_fragment_placeholder, mFrag, mTag);
		} else {
			Log.v(TAG, "onTabSelected() - reattaching " + mTag);
			ft.attach(mFrag);
		}
	}

	@Override
	public void onTabUnselected(Tab t, FragmentTransaction ft) {
		Log.v(TAG, "onTabUnselected() - detaching " + mTag);
		Fragment mFrag = mAct.getFragmentManager().findFragmentByTag(mTag);
		ft.detach(mFrag);
	}

}
