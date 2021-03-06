package com.masenf.core;

import com.masenf.core.data.BaseEntry;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public abstract class EntryClickHandler implements OnItemClickListener, OnItemLongClickListener {
	private static final String TAG = "EntryClickHandler";
	public abstract Activity getActivity();
	@Override
	public void onItemClick(AdapterView<?> adView, View target, int pos, long id) {
		Log.v(TAG,"onItemClick() - " + target.getTag().toString());
		BaseEntry entry = (BaseEntry) target.getTag();
		entry.handleClick(this);
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View target, int pos, long id) {
		Log.v(TAG,"onItemClick() - " + target.getTag().toString());
		BaseEntry entry = (BaseEntry) target.getTag();
		return entry.handleLongClick(this);
	}
}
