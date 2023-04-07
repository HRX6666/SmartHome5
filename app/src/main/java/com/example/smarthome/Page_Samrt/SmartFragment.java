package com.example.smarthome.Page_Samrt;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarthome.Activity.BottomSmartHome;
import com.example.smarthome.Activity.FirstActivity;
import com.example.smarthome.Activity.SetAllShow;
import com.example.smarthome.Adapter.AddMedalAdapter;
import com.example.smarthome.Adapter.AddModelAdapter2;
import com.example.smarthome.Adapter.AddSenseAdapter;
import com.example.smarthome.Adapter.AddSmartAdapter;
import com.example.smarthome.Database.AddModel;
import com.example.smarthome.Database.AddSense;
import com.example.smarthome.Database.Model;
import com.example.smarthome.Helper.AddMedalHelper;
import com.example.smarthome.Helper.AddSmartHelper;
import com.example.smarthome.R;
import com.example.smarthome.View.CircularAnim;
import com.example.smarthome.animation.AddAnimationRotation;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;
import org.litepal.util.Const;

import java.util.ArrayList;
import java.util.List;

public class SmartFragment extends Fragment{
    String name_m;
    RecyclerView addsmart,addmedel,addsense;
    AddSmartAdapter rvadapter;
    AddModelAdapter2 addModelAdapter2;
    AddSenseAdapter addSenseAdapter;
    ObjectAnimator animator;

    ImageView add,add_sense;
    List<AddModel> list = new ArrayList<>();
    List<AddSense> addSenseList=new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.smart_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        addsmart=getActivity().findViewById(R.id.add_smart);
        addmedel=getActivity().findViewById(R.id.add_medal);
        addsense=getActivity().findViewById(R.id.add_sence);
        add_sense=getActivity().findViewById(R.id.add_sense_iv);
        add_sense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Add_Sense.class);
                startActivity(intent);
            }
        });
        super.onActivityCreated(savedInstanceState);
        add=getActivity().findViewById(R.id.add_home);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator=ObjectAnimator.ofFloat(add,"rotation",0f,3600f,0f);
                animator.setDuration(2400);
                animator.setInterpolator(new AddAnimationRotation());
                animator.start();
//                CircularAnim.show(add).go();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent3 = new Intent(getActivity(), More.class);
                        startActivity(intent3);
                    }
                },1000);
                Connector.getDatabase();
            }
        });
        recyclerView();
//        recyclerView2();
        recyclerView3();
        recyclerViewSense();
    }

    private void recyclerViewSense() {
        addsense.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        List<AddSense> all_sense=LitePal.findAll(AddSense.class);
        addSenseAdapter=new AddSenseAdapter(all_sense);
        addsense.setAdapter(addSenseAdapter);
        addSenseAdapter.set0nItemClickListener(new AddSenseAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),ShowSense.class);
                startActivity(intent);

            }
        });
    }

    private void recyclerView3() {
        addmedel.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        List<AddModel> all = LitePal.findAll(AddModel.class);
        addModelAdapter2= new AddModelAdapter2(all);
        addmedel.setAdapter(addModelAdapter2);
        addModelAdapter2.set0nItemClickListener(new AddModelAdapter2.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), SetAllShow.class);
                startActivity(intent);
            }
        });


//        addModelAdapter2.set0nItemClickListener((view, position) -> {
////            currnentPlayPosition = position;
//            AddMedalHelper addModel = addMedalHelpers.get(position);
//
//        });
    }


    //private void recyclerView2() {
//    addmedel.setHasFixedSize(true);
//    addmedel.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//    ArrayList<AddMedalHelper> addMedalHelpers = new ArrayList<>();
//    addMedalHelpers .add(new AddMedalHelper(R.drawable.leave_home, "离家模式"));
//    addMedalHelpers .add(new AddMedalHelper(R.drawable.back_home, "回家模式"));
//    addMedalHelpers .add(new AddMedalHelper(R.drawable.night, "夜间模式"));
//    addMedalHelpers .add(new AddMedalHelper(R.drawable.more, "更多模式"));
//    medalAdapter = new AddMedalAdapter(addMedalHelpers);
//    addmedel.setAdapter(medalAdapter);
//    medalAdapter.setOnItemLongClickListener(new AddMedalAdapter.OnItemLongClickListener() {
//        @Override
//        public void onItemLongClick(View view, int position) {
//            switch (position) {
//                case 0:
//                    Intent intent0 = new Intent(getActivity(), GoOff.class);
//                    startActivity(intent0);
//                    break;
//                case 1:
//                    Intent intent1 = new Intent(getActivity(), GoHome.class);
//                    startActivity(intent1);
//                    break;
//                case 2:
//                    Intent intent2 = new Intent(getActivity(), Night.class);
//                    startActivity(intent2);
//                    break;
//            }
//        }
//    });
//    medalAdapter.setOnItemClickListener(new AddMedalAdapter.OnItemClickListener() {
//        @Override
//        public void OnItemClickListener(View view, int position) {
//            switch (position){
//                case 0:
//                    break;
//                case 1:
//                    break;
//                case 2:
//                    break;
//                case 3:
//                    Intent intent3 = new Intent(getActivity(), More.class);
//                    startActivity(intent3);
//                    break;
//            }
//        }
//    });
//
//
//}
    private void recyclerView() {
        addsmart.setHasFixedSize(true);
        addsmart.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        ArrayList<AddSmartHelper> addSmartHelpers = new ArrayList<>();
        addSmartHelpers.add(new AddSmartHelper(R.drawable.lights_smart, "灯光"));
        addSmartHelpers.add(new AddSmartHelper(R.drawable.air_condition_smart, "空调"));
        addSmartHelpers.add(new AddSmartHelper(R.drawable.curtain_smart, "窗帘"));
        addSmartHelpers.add(new AddSmartHelper(R.drawable.little_mentor, "门锁"));
        addSmartHelpers.add(new AddSmartHelper(R.drawable.music_smart,"音响"));
        rvadapter = new AddSmartAdapter(addSmartHelpers);
        addsmart.setAdapter(rvadapter);
        rvadapter.setOnItemClickListener(new AddSmartAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                switch (position) {
                    case 0:
                        Intent intent1 = new Intent(getActivity(), AdjustTheLights.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(),   AdjustTheAirCondition.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(getActivity(), AdustTheCurtain.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(getActivity(), Monitoring.class);
                        startActivity(intent4);
                        break;
                    case 4:
                        Intent intent5 = new Intent(getActivity(), AdjustTheMusic.class);
                        startActivity(intent5);
                        break;
                }
            }
        });

    }

}
