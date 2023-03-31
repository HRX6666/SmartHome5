package com.example.smarthome.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarthome.Database.AddModel;
import com.example.smarthome.Database.Home;
import com.example.smarthome.Helper.ExtendHeatHelper;
import com.example.smarthome.Page_Huiju.AddHome;
import com.example.smarthome.Page_Samrt.More;
import com.example.smarthome.R;

import java.util.ArrayList;
import java.util.List;

public class AddHomeAdapter extends  RecyclerView.Adapter<com.example.smarthome.Adapter.AddHomeAdapter.ViewHolder> {
        private Context mContext; // 声明一个上下文对象
        private List<Home> list; // 声明一个音频信息列表w
        com.example.smarthome.Adapter.AddHomeAdapter.OnItemClickListener onItemClickListener;//声明接口对象

        public void set0nItemClickListener(com.example.smarthome.Adapter.AddHomeAdapter.OnItemClickListener onItemClickListener){
            this.onItemClickListener=onItemClickListener;//传递接口
        }
        public interface OnItemClickListener{
            void OnItemClick(View view,int position);//设置接口
        }

        public AddHomeAdapter(List<Home> homeList) {
            list = homeList;
        }

        @NonNull
        @Override
        public AddHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_medal, parent, false);
             AddHomeAdapter.ViewHolder viewHolder = new AddHomeAdapter.ViewHolder(view);
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(parent.getContext().getApplicationContext(), AddHome.class);
                    String  homename = viewHolder.r_model.getText().toString().trim();
                    intent.putExtra("homename", homename);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    parent.getContext().startActivity(intent);
//                关于Activity跳转，Context中有一个startActivity方法，
//                Activity继承自Context，重载了startActivity方法。
//                如果使用Activity的startActivity方法，不会有任何限制，
//                而如果使用Context的startActivity方法的話，就需要开启一个新的的task，
//                遇到这个异常，是因为使用了Context的startActivity方法。解决办法是，加一个flag。
                }
            });

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.smarthome.Adapter.AddHomeAdapter.ViewHolder holder, int position) {
            Home home=list.get(position);
            holder.r_model.setText(home.getHomename());
            holder.itemView.setOnClickListener(v -> {
                onItemClickListener.OnItemClick(v,position);
            });

        }



        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView r_model;
            View view;
            public ViewHolder(@NonNull View itemView) {

                super(itemView);
                r_model=(TextView) itemView.findViewById(R.id.room_name);
                view=itemView;

            }
        }
        @Override
        public int getItemCount() {
            return list.size();
        }
    }


