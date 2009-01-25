/*
 * Copyright 2009 Brian Pellin.
 *     
 * This file is part of KeePassDroid.
 *
 *  KeePassDroid is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  KeePassDroid is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with KeePassDroid.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.android.keepass;

import java.util.Vector;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.phoneid.keepassj2me.PwEntry;
import org.phoneid.keepassj2me.PwGroup;

public class PwListAdapter extends BaseAdapter {

	private Activity mAct;
	private Vector mGroupList;
	private Vector mEntryList;
	
	PwListAdapter(Activity act, Vector gl, Vector el) {
		mAct = act;
		mGroupList = gl;
		mEntryList = el;
		
	}
	
	@Override
	public int getCount() {
		
		return mGroupList.size() + mEntryList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int size = mGroupList.size();
		
		if ( position < size ) { 
			return createGroupView(position, convertView);
		} else {
			return createEntryView(position - size, convertView);
		}
	}

	private PwGroupView createGroupView(int position, View convertView) {
		PwGroupView gv;
		if (convertView == null || ! (convertView instanceof PwGroupView)) {
			PwGroup group = (PwGroup) mGroupList.elementAt(position);
			gv = new PwGroupView(mAct, group);
		} else {
			gv = (PwGroupView) convertView;
			gv.setGroup((PwGroup) mGroupList.elementAt(position));
		}
		return gv;
	}

	private PwEntryView createEntryView(int position, View convertView) {
		PwEntryView ev;
		if (convertView == null || ! (convertView instanceof PwEntryView) ) {
			ev = new PwEntryView(mAct, (PwEntry) mEntryList.elementAt(position));
		} else {
			ev = (PwEntryView) convertView;
			ev.setEntry((PwEntry) mEntryList.elementAt(position));
		}
		return ev;
	}

}