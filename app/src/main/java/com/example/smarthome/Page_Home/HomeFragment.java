package com.example.smarthome.Page_Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smarthome.Activity.Test;
import com.example.smarthome.R;

public class HomeFragment extends Fragment {
    TextView home_set_voice,home_scene,home_wifi,home_wangguan,home_set,enroll,home_test;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        home_set_voice=getActivity().findViewById(R.id.home_set_voice);
        home_scene=getActivity().findViewById(R.id.home_scene);
        home_wifi=getActivity().findViewById(R.id.home_wife);
        home_wangguan=getActivity().findViewById(R.id.home_wangguan);
        home_set=getActivity().findViewById(R.id.home_set);
        enroll=getActivity().findViewById(R.id.enroll);
        home_test=getActivity().findViewById(R.id.home_test);
        home_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getActivity(), FindDevices.class);
                startActivity(intent1);
            }
        });
        home_set_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent1=new Intent(getActivity(), SetVoice.class);
              startActivity(intent1);
            }
        });
        home_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(), Set.class);
                startActivity(intent1);
            }
        });
        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(), enter.class);
                startActivity(intent1);
            }
        });
        home_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(), HomeScene.class);
                startActivity(intent1);
            }
        });
        home_wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(), Test.class);
                startActivity(intent1);
            }
        });
        home_wangguan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getActivity(), WangGuan.class);
                startActivity(intent1);
            }
        });


    }

}
