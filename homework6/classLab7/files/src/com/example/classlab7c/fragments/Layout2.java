package com.example.classlab7c.fragments;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.classlab7c.R;
import com.example.classlab7c.adapters.ArtistAdapter;
import com.example.classlab7c.adapters.EventAdapter;
import com.example.classlab7c.adapters.SongAdapter;
import com.example.classlab7c.model.Artist;
import com.example.classlab7c.model.Event;
import com.example.classlab7c.model.Song;
import com.example.classlab7c.service.MusicListService;

public class Layout2 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.layout2, container, false);
		
		
		List<Artist>artists = MusicListService.getInstance(getActivity()).getAllArtists();
		
		ArtistAdapter artistAdapter =
				new ArtistAdapter(getActivity(), R.layout.listview_for_each_artist, artists);
		ListView listViewArtists = (ListView) view.findViewById(R.id.listViewArtists);
		listViewArtists.setAdapter(artistAdapter);
		
		return view;
	}

}
