package com.nathanielfoster.transform;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ImageActivity extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		
		Intent intent = getIntent();
		Bundle extraBundle = intent.getExtras();
		final Bitmap bitmap = (Bitmap)extraBundle.get(MainActivity.LARGE_BITMAP);
		
		ImageView imageView = (ImageView)findViewById(R.id.img_view_large);
		imageView.setImageBitmap(bitmap);
		
		Button btnSave = (Button)findViewById(R.id.btn_save_image);
		btnSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveBitmap(bitmap);
			}

			private void saveBitmap(Bitmap bitmap) {
				String foldername = "/UglyPixel";
				String filename = Utils.getUniqueImageFilename(); 
				try {
					String path = Environment.getExternalStorageDirectory().getAbsolutePath() + foldername;
					File dir = new File(path);
					if(!dir.exists()) dir.mkdirs();
					OutputStream fOut = null;
					File file = new File(dir, filename+".jpg");
					if(!file.exists()) file.createNewFile();
					fOut = new FileOutputStream(file);
					
					
					bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
					MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
					fOut.flush();
					fOut.close();

				} catch (Exception e) {
				       e.printStackTrace();
				}
			}
		});
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
}
