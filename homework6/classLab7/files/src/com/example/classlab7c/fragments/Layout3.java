package com.example.classlab7c.fragments;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.classlab7c.R;
import com.example.classlab7c.adapters.EventAdapter;
import com.example.classlab7c.model.Event;
import com.example.classlab7c.service.MusicListService;

public class Layout3 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout3, container, false);
		
		List<Event>events = MusicListService.getInstance(getActivity()).getAllEvents();
		
		EventAdapter adapter =
				new EventAdapter(getActivity(), R.layout.listview_for_each_event, events);
		ListView listViewEvents = (ListView) view.findViewById(R.id.listViewEvents);
		listViewEvents.setAdapter(adapter);
		
		return view;
	}

}
