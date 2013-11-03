package com.example.classlab7c.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.classlab7c.R;
import com.example.classlab7c.model.Event;

public class EventAdapter extends ArrayAdapter<Event> {
	private SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd"); 
	private Context mContext;
	private List<Event> mEntries;
	
	public EventAdapter(Context context, int resource, List<Event> entries) {
		super(context, resource, entries);
		mContext=context;
	    mEntries=entries;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view==null){
			 LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		     view = inflater.inflate(R.layout.listview_for_each_event, parent, false);
		}
		
		Event event = mEntries.get(position);
		
		TextView textViewName = (TextView)view.findViewById(R.id.textViewEventName);
		textViewName.setText(event.getEventName());
		
		TextView textViewDate = (TextView)view.findViewById(R.id.textViewEventDate);
		textViewDate.setText(event.getDate().toString());
		
		TextView textViewLocation = (TextView)view.findViewById(R.id.textViewEventLocation);
		textViewLocation.setText(event.getLocation());
		
		return view;
	}

}
