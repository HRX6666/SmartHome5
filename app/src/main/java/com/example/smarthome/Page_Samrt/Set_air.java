package com.example.smarthome.Page_Samrt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smarthome.Adapter.AddModelAdapter2;
import com.example.smarthome.Database.AddModel;
import com.example.smarthome.R;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class Set_air extends AppCompatActivity {
    RecyclerView set_rv;
    AddModelAdapter2 addModelAdapter2;
    List<AddModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_air);


    }




}
