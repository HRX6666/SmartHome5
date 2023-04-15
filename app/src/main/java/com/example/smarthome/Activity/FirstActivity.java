package com.example.smarthome.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
//import org.litepal.LitePal;
//import org.litepal.tablemanager.Connector;
import com.example.smarthome.MQTT.ClientMQTT;
import com.example.smarthome.Page_Samrt.AdjustTheLights;
import com.example.smarthome.R;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.litepal.tablemanager.Connector;

public class FirstActivity extends AppCompatActivity {
    Animation topAnimation,bottomAnimation,middleAnimation;
    View first,second,third,fourth,fifth,sixth;
    TextView app_name,tagLine;
    private ClientMQTT clientMQTT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnimation= AnimationUtils.loadAnimation(this,R.anim.middle_animation);
        app_name=findViewById(R.id.app_name);
        tagLine=findViewById(R.id.tagLine);
       app_name.setAnimation(middleAnimation);
       tagLine.setAnimation(bottomAnimation);
//        clientMQTT=new ClientMQTT("light");
//        try {
//            clientMQTT.Mqtt_innit();
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//        clientMQTT.startReconnect(FirstActivity.this);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent=new Intent(FirstActivity.this,BottomSmartHome.class);
               startActivity(intent);
               finish();
           }
       },2000);
        Connector.getDatabase();
    }

}