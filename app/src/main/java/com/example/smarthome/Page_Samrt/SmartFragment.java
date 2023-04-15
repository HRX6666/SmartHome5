package com.example.smarthome.Page_Samrt;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarthome.Activity.SetAllShow;
import com.example.smarthome.Adapter.AddModelAdapter2;
import com.example.smarthome.Adapter.AddSenseAdapter;
import com.example.smarthome.Adapter.RecyclerAdapter;
import com.example.smarthome.Database.AddDevice;
import com.example.smarthome.Database.AddModel;
import com.example.smarthome.Database.AddSense;
import com.example.smarthome.Helper.AddSmartHelper;
import com.example.smarthome.R;
import com.example.smarthome.View.DeleteRecyclerViewDiy.DividerGridItemDecoration;
import com.example.smarthome.View.DeleteRecyclerViewDiy.MoveDeleteTouchCallback;
import com.example.smarthome.View.DeleteRecyclerViewDiy.MoveTouchCallback;
import com.example.smarthome.View.DeleteRecyclerViewDiy.OnRecyclerItemClickListener;
import com.example.smarthome.View.DeleteRecyclerViewDiy.VibratorUtils;
import com.example.smarthome.animation.AddAnimationRotation;
import com.hb.dialog.myDialog.MyAlertInputDialog;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class SmartFragment extends Fragment {
    String name_m;
    RecyclerView addmedel, addsense;
    AddModelAdapter2 addModelAdapter2;
    AddSenseAdapter addSenseAdapter;
    ObjectAnimator animator;

    //smart
    private RecyclerAdapter recyclerAdapter;
    List<AddSmartHelper> lists;
    RecyclerView addsmart;
    private ItemTouchHelper itemTouchHelper;
    //end
    ImageView add, add_sense;
    List<AddModel> list = new ArrayList<>();
    List<AddSense> addSenseList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.smart_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        addsmart = getActivity().findViewById(R.id.add_smart);
        addmedel = getActivity().findViewById(R.id.add_medal);
        addsense = getActivity().findViewById(R.id.add_sence);
        add_sense = getActivity().findViewById(R.id.add_sense_iv);
        add_sense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Add_Sense.class);
                startActivity(intent);
            }
        });
        super.onActivityCreated(savedInstanceState);
        add = getActivity().findViewById(R.id.add_home);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator = ObjectAnimator.ofFloat(add, "rotation", 0f, 3600f, 0f);
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
                }, 1000);
                Connector.getDatabase();
            }
        });
        recyclerViewSmart();
//        recyclerView2();
        recyclerView3();
        recyclerViewSense();
        setListener();
    }


    private void recyclerViewSense() {
        addsense.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        List<AddSense> all_sense = LitePal.findAll(AddSense.class);
        addSenseAdapter = new AddSenseAdapter(all_sense);
        addsense.setAdapter(addSenseAdapter);
        addSenseAdapter.set0nItemClickListener(new AddSenseAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ShowSense.class);
                startActivity(intent);

            }
        });
    }

    private void recyclerView3() {
        addmedel.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        List<AddModel> all = LitePal.findAll(AddModel.class);
        addModelAdapter2 = new AddModelAdapter2(all);
        addmedel.setAdapter(addModelAdapter2);
        addModelAdapter2.set0nItemClickListener(new AddModelAdapter2.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), SetAllShow.class);
                startActivity(intent);
            }
        });


    }

    private void recyclerViewSmart() {
        lists = new ArrayList<>();
        lists.add(new AddSmartHelper(R.drawable.lights_smart, "灯光"));
        lists.add(new AddSmartHelper(R.drawable.air_condition_smart, "空调"));
        lists.add(new AddSmartHelper(R.drawable.curtain_smart, "窗帘"));
        lists.add(new AddSmartHelper(R.drawable.little_mentor, "门锁"));
        lists.add(new AddSmartHelper(R.drawable.music_smart, "音响"));


        recyclerAdapter = new RecyclerAdapter(R.layout.add_smart, lists);
        addsmart.setHasFixedSize(true);
        addsmart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        addsmart.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        itemTouchHelper = new ItemTouchHelper(new MoveTouchCallback(recyclerAdapter));
        itemTouchHelper.attachToRecyclerView(addsmart);

        itemTouchHelper = new ItemTouchHelper(new MoveDeleteTouchCallback(0, ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT,
                recyclerAdapter, lists));
        itemTouchHelper.attachToRecyclerView(addsmart);
        recyclerAdapter.setOnItemLongClickListener(new RecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                recyclerAdapter.onSwiped(position);
                lists.remove(position);
            }
        });

        addsmart.setAdapter(recyclerAdapter);


    }

    //控件的监听
    private void setListener() {


        //自定义的拖动效果监听(具体长按还是短按自己控制)
        addsmart.addOnItemTouchListener(new OnRecyclerItemClickListener(addsmart) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                //这里可以控制不可拖动的布局（此时的情况为最后一个不可拖动）

                itemTouchHelper.startDrag(vh);
                VibratorUtils.Vibrate(getActivity(), 70);   //震动70ms
                final MyAlertInputDialog myAlertInputDialog1 = new MyAlertInputDialog(getActivity());
                myAlertInputDialog1.builder();
                myAlertInputDialog1.setTitle("是真的要删除该设备吗");
                myAlertInputDialog1.setPositiveButton("对！！！", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerAdapter.del(vh.getPosition());
                        myAlertInputDialog1.dismiss();
                    }


                });
                myAlertInputDialog1.setNegativeButton("拒绝", new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "好吧", Toast.LENGTH_SHORT).show();

                    }
                });
                myAlertInputDialog1.show();



            }

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                AddSmartHelper addSmartHelper = lists.get(vh.getLayoutPosition());


                switch (vh.getPosition()) {
                    case 0:
                        Intent intent1 = new Intent(getActivity(), AdjustTheLights.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(getActivity(), AdjustTheAirCondition.class);
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
