package com.cis490.njfoster.ch01_geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends Activity {
	private Button btnTrue;
	private Button btnFalse;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    
        btnTrue = (Button)findViewById(R.id.btnTrue);
        btnTrue.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(QuizActivity.this, R.string.tstIncorrect, Toast.LENGTH_SHORT).show();
			}
		});
        
        btnFalse = (Button)findViewById(R.id.btnFalse);
        btnFalse.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(QuizActivity.this, R.string.tstCorrect, Toast.LENGTH_SHORT).show();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }
    
}
