package com.example.administrator.myclock;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.clockview.ClockView;

public class MainActivity extends AppCompatActivity {

    private ClockView clockView;
    private EditText hourTime,minuteTime;
    private Button mButton;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clockView = (ClockView) findViewById(R.id.myClock);
        hourTime = (EditText) findViewById(R.id.hourTime);
        minuteTime = (EditText) findViewById(R.id.minuteTime);
        mButton = (Button) findViewById(R.id.setClock);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clockView.setClock(Float.parseFloat(hourTime.getText().toString()),Float.parseFloat(minuteTime.getText().toString()));
                Log.d(TAG,""+Float.parseFloat(hourTime.getText().toString()));
            }
        });

    }



}
