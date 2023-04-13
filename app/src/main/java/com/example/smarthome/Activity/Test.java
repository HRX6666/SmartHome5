package com.example.smarthome.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.smarthome.R;
import com.example.smarthome.View.CustomRangeSeekBar;


public class Test extends AppCompatActivity {
    CustomRangeSeekBar mSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        mSeekbar = (CustomRangeSeekBar) findViewById(R.id.seekbar_time);
//        findViewById(R.id.btn_restore).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSeekbar.restorePercentSelectedMinValue();
//                mSeekbar.restorePercentSelectedMaxValue();
//            }
//        });

    }

}