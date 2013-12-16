package com.nathanielfoster.transform;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ImgBtnAdapter extends ArrayAdapter<Bitmap> {
	 private List<Bitmap> mEntries;
     private Context mContext;
     
	 public ImgBtnAdapter(Context context,int textViewResourceId, List<Bitmap> entries) { 
         super(context, textViewResourceId, entries);
         mContext=context;
         mEntries=entries;     
     }
	 
	 public ImageView getImageView(int position) {
		 ImageView imageView = null;
		 
		 return imageView;
	 }
	 @Override
     public View getView(int position, View view, ViewGroup parent) {
		  
		 if(view==null){
             LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             view = inflater.inflate(R.layout.btn_for_grid, parent, false);
		 }
		 Bitmap bitmap = mEntries.get(position);
		 //ImageView image = mEntries.get(position);
		 ImageView image = (ImageView)view.findViewById(R.id.btn_image);
		 image.setImageBitmap(bitmap);
		 
		 return image;
	 }
}
