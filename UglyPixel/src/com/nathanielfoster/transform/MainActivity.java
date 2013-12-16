package com.nathanielfoster.transform;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

public class MainActivity extends Activity {
	final int GET_IMAGE = 0;
	final int TAKE_IMAGE = 1;
	final int NUM_IMG_BUTTONS = 9;
	private Uri imageUri;
    private static final int SELECT_PICTURE = 0;
	public static final String LARGE_BITMAP = "com.nathanielfoster.transform.largeBitmap";
	private Uri outputFileUri;
	final List<Bitmap> imgViews = new ArrayList<Bitmap>();
	final List<Bitmap> selectedBitmaps = new ArrayList<Bitmap>();
	Hashtable<Integer, String> customTransformFunctions = new Hashtable<Integer, String>();
	Spinner spnTransform = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button loadImage = (Button)findViewById(R.id.btn_load_image);
        loadImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_PICTURE);
			}
		});
        
        customTransformFunctions.put(0, "Average");
        
        spnTransform = (Spinner)findViewById(R.id.spn_transform_functions);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bitmaps_transform_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTransform.setAdapter(adapter);
        spnTransform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				Object item = parent.getItemAtPosition(position);
				item.toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
            	if(data != null)
                {       
            		Uri selectedImageUri = data.getData();
            		//String filestring = selectedImageUri.getPath();
            		
            		ContentResolver cr = getContentResolver();
                    InputStream in;
                    Bitmap thumb = null;
					try {
						in = cr.openInputStream(selectedImageUri);
						BitmapFactory.Options options = new BitmapFactory.Options();
	                    options.inSampleSize=8;
	                    thumb = BitmapFactory.decodeStream(in,null,options);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					loadGridView(thumb);
			        
			        Button btnTransform = (Button)findViewById(R.id.btn_transform);
			        btnTransform.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							Bitmap bitmap = null;
							if(selectedBitmaps.size() > 0) {
								String transformFunction = spnTransform.getSelectedItem().toString();
								
								if (transformFunction.contentEquals("Average")) {
									Log.d("Transform Function", "Average test");
									bitmap = Transform.Average(selectedBitmaps);
								}
								else if (transformFunction.contentEquals("Mix")) {
									Log.d("Transform Function", "Mix test");
									bitmap = Transform.Mix(selectedBitmaps);
								}
								else if (transformFunction.contentEquals("Horizontal")) {
									bitmap = Transform.Horizontal(selectedBitmaps);
								}
								else {
									Log.d("Transform Function", "default test");
									bitmap = selectedBitmaps.get(0);
								}
								//else if (transformFunction == "Mix") bitmap = Transform.Mix(selectedBitmaps);
								
								loadGridView(bitmap);
							}
						}
						
					});
                }
            }
        }
    }

	private void loadGridView(Bitmap thumb) {
		imgViews.clear();
		selectedBitmaps.clear();
		imgViews.add(thumb);
		imgViews.add(Transform.doInvert(thumb));
		imgViews.add(Transform.applySaturationFilter(thumb, 2));
		imgViews.add(Transform.boost(thumb, 1, (float) 0.5));
		imgViews.add(Transform.createContrast(thumb, 40));
		imgViews.add(Transform.createSepiaToningEffect(thumb, 4, 20.8, .2, .3));
		imgViews.add(Transform.decreaseColorDepth(thumb, 20));
		imgViews.add(Transform.doGreyscale(thumb));
		
		ImgBtnAdapter adapter = new ImgBtnAdapter(this.getApplicationContext(), R.layout.btn_for_grid, imgViews);
		GridView imageGrid = (GridView)findViewById(R.id.img_grid);
		imageGrid.setAdapter(adapter);
		imageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Bitmap clickedBitmap = imgViews.get(position);
				if(selectedBitmaps.contains(clickedBitmap)){
					v.setBackgroundColor(getResources().getColor(R.color.img_btn_default));
					selectedBitmaps.remove(clickedBitmap);
				} else {
					v.setBackgroundColor(getResources().getColor(R.color.img_btn_selected));
					selectedBitmaps.add(clickedBitmap);
				}
			}
			
		});
		
		imageGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View v,
					int position, long id) {
				Log.d("Long Click", String.valueOf(position));
				Bitmap bitmap = (Bitmap)parent.getItemAtPosition(position);
				showLargeBitmap(bitmap);
	
				return false;
			}

			
		});
	}
	private void showLargeBitmap(Bitmap bitmap) {
		Intent intent = new Intent(this, ImageActivity.class);
		intent.putExtra(LARGE_BITMAP, bitmap);
		startActivity(intent);
	}

    
    
    public Uri getImageUri(Context inContext, Bitmap inImage) {
    	  ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    	  inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
    	  String path = Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
    	  return Uri.parse(path);
    	}
}
