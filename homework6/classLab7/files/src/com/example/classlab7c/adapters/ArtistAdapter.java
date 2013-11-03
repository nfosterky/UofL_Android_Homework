package com.example.classlab7c.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.classlab7c.R;
import com.example.classlab7c.model.Artist;
import com.example.classlab7c.model.Event;
import com.example.classlab7c.service.MusicListService;

public class ArtistAdapter extends ArrayAdapter<Artist> {
	private SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd"); 
	private Context mContext;
	private List<Artist> mEntries;
	
	public ArtistAdapter(Context context, int textViewResourceId,
			List<Artist> entries) {
		super(context, textViewResourceId, entries);
	    mContext=context;
	    mEntries=entries; 
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view==null){
			 LayoutInflater inflater = (LayoutInflater) mContext
					 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		     view = inflater.inflate(R.layout.listview_for_each_artist, parent, false);
		}
		Artist artist = mEntries.get(position);
		
		TextView textViewTitle = (TextView)view.findViewById(R.id.textViewArtistName);
		textViewTitle.setText(artist.getArtistName());
		
//		List<Event>events = MusicListService.getInstance(mContext).getAllEvents();
//		EventAdapter eventAdapter =
//				new EventAdapter(mContext, R.layout.listview_for_each_event, events);
//		ListView listViewEvents = (ListView) view.findViewById(R.id.listViewEvents);
//		listViewEvents.setAdapter(eventAdapter);
		
//		List<Song>songs = MusicListService.getInstance(getActivity()).getAllSongs();
//		SongAdapter songAdapter =
//				new SongAdapter(getActivity(), R.layout.listview_for_each_song, songs);
//		ListView listViewMusic = (ListView) view.findViewById(R.id.listViewArtistSongs);
//		listViewMusic.setAdapter(songAdapter);
		
		return view;
	}

}
