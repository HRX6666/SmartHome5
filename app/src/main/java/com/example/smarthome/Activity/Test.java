package com.example.smarthome.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthome.Page_Samrt.ChsngeTm;
import com.example.smarthome.Page_Samrt.Test22;
import com.example.smarthome.R;
import com.example.smarthome.View.CustomRangeSeekBar;
import com.example.smarthome.View.PickerView;
import com.hb.dialog.myDialog.MyAlertInputDialog;

import java.util.ArrayList;
import java.util.List;


public class Test extends AppCompatActivity {
    CustomRangeSeekBar mSeekbar;
    TextView max,min,min_nub,max_nub;
    String tm_2; String tm_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        min=findViewById(R.id.min);
        max=findViewById(R.id.max);
        min_nub=findViewById(R.id.min_num);
        max_nub=findViewById(R.id.max_nub);
        inite();
    }

    private void inite() {
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Test.this, ChsngeTm.class);
                startActivity(intent1);

            }
        });
        Intent intent=getIntent();

        tm_1=intent.getStringExtra("tm");
        min_nub.setText(tm_1);
        max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Test.this, Test22.class);
                startActivity(intent);
            }
        });
        Intent intent_max=getIntent();
        tm_2=intent_max.getStringExtra("tm_max");
        max_nub.setText(tm_2);

    }


}

