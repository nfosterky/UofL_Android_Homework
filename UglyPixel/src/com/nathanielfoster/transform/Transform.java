package com.nathanielfoster.transform;
// source: Pete Houston, http://xjaphx.wordpress.com/learning/tutorials/

import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Transform {
	public static Bitmap doInvert(Bitmap src) {
        // create new bitmap with the same settings as source bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // color info
        int A, R, G, B;
        int pixelColor;
        // image size
        int height = src.getHeight();
        int width = src.getWidth();
     
        // scan through every pixel
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                // get one pixel
                pixelColor = src.getPixel(x, y);
                // saving alpha channel
                A = Color.alpha(pixelColor);
                // inverting byte for each R/G/B channel
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                // set newly-inverted pixel to output image
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
     
        // return final bitmap
        return bmOut;
    }
	public static Bitmap doGreyscale(Bitmap src) {
	    // constant factors
	    final double GS_RED = 0.299;
	    final double GS_GREEN = 0.587;
	    final double GS_BLUE = 0.114;
	 
	    // create output bitmap
	    Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
	    // pixel information
	    int A, R, G, B;
	    int pixel;
	 
	    // get image size
	    int width = src.getWidth();
	    int height = src.getHeight();
	 
	    // scan through every single pixel
	    for(int x = 0; x < width; ++x) {
	        for(int y = 0; y < height; ++y) {
	            // get one pixel color
	            pixel = src.getPixel(x, y);
	            // retrieve color of all channels
	            A = Color.alpha(pixel);
	            R = Color.red(pixel);
	            G = Color.green(pixel);
	            B = Color.blue(pixel);
	            // take conversion up to one single value
	            R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
	            // set new pixel color to output bitmap
	            bmOut.setPixel(x, y, Color.argb(A, R, G, B));
	        }
	    }
	 
	    // return final image
	    return bmOut;
	}
	public static Bitmap doGamma(Bitmap src, double red, double green, double blue) {
	    // create output image
	    Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
	    // get image size
	    int width = src.getWidth();
	    int height = src.getHeight();
	    // color information
	    int A, R, G, B;
	    int pixel;
	    // constant value curve
	    final int    MAX_SIZE = 256;
	    final double MAX_VALUE_DBL = 255.0;
	    final int    MAX_VALUE_INT = 255;
	    final double REVERSE = 1.0;
	 
	    // gamma arrays
	    int[] gammaR = new int[MAX_SIZE];
	    int[] gammaG = new int[MAX_SIZE];
	    int[] gammaB = new int[MAX_SIZE];
	 
	    // setting values for every gamma channels
	    for(int i = 0; i < MAX_SIZE; ++i) {
	        gammaR[i] = (int)Math.min(MAX_VALUE_INT,
	                (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / red)) + 0.5));
	        gammaG[i] = (int)Math.min(MAX_VALUE_INT,
	                (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / green)) + 0.5));
	        gammaB[i] = (int)Math.min(MAX_VALUE_INT,
	                (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / blue)) + 0.5));
	    }
	 
	    // apply gamma table
	    for(int x = 0; x < width; ++x) {
	        for(int y = 0; y < height; ++y) {
	            // get pixel color
	            pixel = src.getPixel(x, y);
	            A = Color.alpha(pixel);
	            // look up gamma
	            R = gammaR[Color.red(pixel)];
	            G = gammaG[Color.green(pixel)];
	            B = gammaB[Color.blue(pixel)];
	            // set new color to output bitmap
	            bmOut.setPixel(x, y, Color.argb(A, R, G, B));
	        }
	    }
	 
	    // return final image
	    return bmOut;
	}	
	public static Bitmap doColorFilter(Bitmap src, double red, double green, double blue) {
        // image size
        int width = src.getWidth();
        int height = src.getHeight();
        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        // color information
        int A, R, G, B;
        int pixel;
 
        // scan through all pixels
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                // apply filtering on each channel R, G, B
                A = Color.alpha(pixel);
                R = (int)(Color.red(pixel) * red);
                G = (int)(Color.green(pixel) * green);
                B = (int)(Color.blue(pixel) * blue);
                // set new color pixel to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
 
        // return final image
        return bmOut;
    }
	public static Bitmap createSepiaToningEffect(Bitmap src, int depth, double red, double green, double blue) {
	    // image size
	    int width = src.getWidth();
	    int height = src.getHeight();
	    // create output bitmap
	    Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
	    // constant grayscale
	    final double GS_RED = 0.3;
	    final double GS_GREEN = 0.59;
	    final double GS_BLUE = 0.11;
	    // color information
	    int A, R, G, B;
	    int pixel;
	 
	    // scan through all pixels
	    for(int x = 0; x < width; ++x) {
	        for(int y = 0; y < height; ++y) {
	            // get pixel color
	            pixel = src.getPixel(x, y);
	            // get color on each channel
	            A = Color.alpha(pixel);
	            R = Color.red(pixel);
	            G = Color.green(pixel);
	            B = Color.blue(pixel);
	            // apply grayscale sample
	            B = G = R = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
	 
	            // apply intensity level for sepid-toning on each channel
	            R += (depth * red);
	            if(R > 255) { R = 255; }
	 
	            G += (depth * green);
	            if(G > 255) { G = 255; }
	 
	            B += (depth * blue);
	            if(B > 255) { B = 255; }
	 
	            // set new pixel color to output image
	            bmOut.setPixel(x, y, Color.argb(A, R, G, B));
	        }
	    }
	 
	    // return final image
	    return bmOut;
	}
	public static Bitmap decreaseColorDepth(Bitmap src, int bitOffset) {
	    // get image size
	    int width = src.getWidth();
	    int height = src.getHeight();
	    // create output bitmap
	    Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
	    // color information
	    int A, R, G, B;
	    int pixel;
	 
	    // scan through all pixels
	    for(int x = 0; x < width; ++x) {
	        for(int y = 0; y < height; ++y) {
	            // get pixel color
	            pixel = src.getPixel(x, y);
	            A = Color.alpha(pixel);
	            R = Color.red(pixel);
	            G = Color.green(pixel);
	            B = Color.blue(pixel);
	 
	            // round-off color offset
	            R = ((R + (bitOffset / 2)) - ((R + (bitOffset / 2)) % bitOffset) - 1);
	            if(R < 0) { R = 0; }
	            G = ((G + (bitOffset / 2)) - ((G + (bitOffset / 2)) % bitOffset) - 1);
	            if(G < 0) { G = 0; }
	            B = ((B + (bitOffset / 2)) - ((B + (bitOffset / 2)) % bitOffset) - 1);
	            if(B < 0) { B = 0; }
	 
	            // set pixel color to output bitmap
	            bmOut.setPixel(x, y, Color.argb(A, R, G, B));
	        }
	    }
	 
	    // return final image
	    return bmOut;
	}
	public static Bitmap createContrast(Bitmap src, double value) {
	    // image size
	    int width = src.getWidth();
	    int height = src.getHeight();
	    // create output bitmap
	    Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
	    // color information
	    int A, R, G, B;
	    int pixel;
	    // get contrast value
	    double contrast = Math.pow((100 + value) / 100, 2);
	 
	    // scan through all pixels
	    for(int x = 0; x < width; ++x) {
	        for(int y = 0; y < height; ++y) {
	            // get pixel color
	            pixel = src.getPixel(x, y);
	            A = Color.alpha(pixel);
	            // apply filter contrast for every channel R, G, B
	            R = Color.red(pixel);
	            R = (int)(((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
	            if(R < 0) { R = 0; }
	            else if(R > 255) { R = 255; }
	 
	            G = Color.red(pixel);
	            G = (int)(((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
	            if(G < 0) { G = 0; }
	            else if(G > 255) { G = 255; }
	 
	            B = Color.red(pixel);
	            B = (int)(((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
	            if(B < 0) { B = 0; }
	            else if(B > 255) { B = 255; }
	 
	            // set new pixel color to output bitmap
	            bmOut.setPixel(x, y, Color.argb(A, R, G, B));
	        }
	    }
	 
	    // return final image
	    return bmOut;
	}
	public static Bitmap boost(Bitmap src, int type, float percent) {
	    int width = src.getWidth();
	    int height = src.getHeight();
	    Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
	 
	    int A, R, G, B;
	    int pixel;
	 
	    for(int x = 0; x < width; ++x) {
	        for(int y = 0; y < height; ++y) {
	            pixel = src.getPixel(x, y);
	            A = Color.alpha(pixel);
	            R = Color.red(pixel);
	            G = Color.green(pixel);
	            B = Color.blue(pixel);
	            if(type == 1) {
	                R = (int)(R * (1 + percent));
	                if(R > 255) R = 255;
	            }
	            else if(type == 2) {
	                G = (int)(G * (1 + percent));
	                if(G > 255) G = 255;
	            }
	            else if(type == 3) {
	                B = (int)(B * (1 + percent));
	                if(B > 255) B = 255;
	            }
	            bmOut.setPixel(x, y, Color.argb(A, R, G, B));
	        }
	    }
	    return bmOut;
	}
	public static Bitmap applySaturationFilter(Bitmap source, int level) {
	    // get image size
	    int width = source.getWidth();
	    int height = source.getHeight();
	    int[] pixels = new int[width * height];
	    float[] HSV = new float[3];
	    // get pixel array from source
	    source.getPixels(pixels, 0, width, 0, 0, width, height);
	 
	    int index = 0;
	    // iteration through pixels
	    for(int y = 0; y < height; ++y) {
	        for(int x = 0; x < width; ++x) {
	            // get current index in 2D-matrix
	            index = y * width + x;
	            // convert to HSV
	            Color.colorToHSV(pixels[index], HSV);
	            // increase Saturation level
	            HSV[1] *= level;
	            HSV[1] = (float) Math.max(0.0, Math.min(HSV[1], 1.0));
	            // take color back
	            pixels[index] |= Color.HSVToColor(HSV);
	        }
	    }
	    // output bitmap
	    Bitmap bmOut = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
	    bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
	    return bmOut;
	}
	
	// custom functions written by Nathaniel Foster
	public static Bitmap Average(List<Bitmap> bitmaps){
		Bitmap src = bitmaps.get(0);
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // color info
        int A = 0, R = 0, G = 0, B = 0;
        int pixelColor;
        // image size
        int height = src.getHeight();
        int width = src.getWidth();
        int numBitmaps = bitmaps.size();
        // scan through every pixel
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
            	for (int z = 0; z < numBitmaps; z++){
            		pixelColor = bitmaps.get(z).getPixel(x, y);
                    // saving alpha channel
                    A += Color.alpha(pixelColor);
                    // inverting byte for each R/G/B channel
                    R += Color.red(pixelColor);
                    G += Color.green(pixelColor);
                    B += Color.blue(pixelColor);	
            	}
            	A = A / numBitmaps;
                R = R / numBitmaps;
                G = G / numBitmaps;
                B = B / numBitmaps;
                // set newly-inverted pixel to output image
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
     
        // return final bitmap
        return bmOut;
		
	}
	public static Bitmap Mix(List<Bitmap> bitmaps){		
		Bitmap src = bitmaps.get(0);
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // color info
        int A = 0, R = 0, G = 0, B = 0;
        int pixelColor;
        // image size
        int height = src.getHeight();
        int width = src.getWidth();
        int numBitmaps = bitmaps.size();
        int bitmapIndex = 0;
        // scan through every pixel
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
            	Random rand = new Random();
            	//rand.nextInt(numBitmaps);
            	//if (bitmapIndex < numBitmaps) bitmapIndex++;
            	//else bitmapIndex = 0;
        		pixelColor = bitmaps.get(rand.nextInt(numBitmaps)).getPixel(x, y);
                // saving alpha channel
                A = Color.alpha(pixelColor);
                // inverting byte for each R/G/B channel
                R = Color.red(pixelColor);
                G = Color.green(pixelColor);
                B = Color.blue(pixelColor);	
            
                // set newly-inverted pixel to output image
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
     
        // return final bitmap
        return bmOut;
		
	}
	public static Bitmap Horizontal(List<Bitmap> bitmaps){		
		Bitmap src = bitmaps.get(0);
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // color info
        int A = 0, R = 0, G = 0, B = 0;
        int pixelColor;
        // image size
        int height = src.getHeight();
        int width = src.getWidth();
        int numBitmaps = bitmaps.size();
        int bitmapIndex = 0;
    	Random rand = new Random();
        // scan through every pixel
        for (int y = 0; y < height; y++)
        {
        	int imgIndex = rand.nextInt(numBitmaps);
            for (int x = 0; x < width; x++)
            {

            	//rand.nextInt(numBitmaps);
            	//if (bitmapIndex < numBitmaps) bitmapIndex++;
            	//else bitmapIndex = 0;
        		pixelColor = bitmaps.get(imgIndex).getPixel(x, y);
                // saving alpha channel
                A = Color.alpha(pixelColor);
                // inverting byte for each R/G/B channel
                R = Color.red(pixelColor);
                G = Color.green(pixelColor);
                B = Color.blue(pixelColor);	
            
                // set newly-inverted pixel to output image
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
     
        // return final bitmap
        return bmOut;
		
	}
}
