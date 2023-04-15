package com.example.smarthome.Page_Samrt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smarthome.R;

import java.util.concurrent.Executor;

public class Monitoring extends AppCompatActivity {
     CardView open,close,alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);
        open=findViewById(R.id.open_the_door);
        close=findViewById(R.id.close_the_door);


        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BiometricPrompt.PromptInfo promptInfo=new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("请核实")
                        .setDescription("需要用户验证身份信息")
                        .setNegativeButtonText("取消")
                        .build();
                getPrompt().authenticate(promptInfo);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BiometricPrompt.PromptInfo promptInfo=new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("请核实")
                        .setDescription("需要用户验证身份信息")
                        .setNegativeButtonText("取消")
                        .build();
                getPrompt().authenticate(promptInfo);
            }
        });

    }

    private BiometricPrompt getPrompt(){
        Executor executor=ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback=new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyUser(errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                notifyUser("验证成功");
                Intent intent=new Intent(Monitoring.this, AdjustTheAirCondition.class);
                startActivity(intent);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                notifyUser("验证失败");
            }

        };
        BiometricPrompt biometricPrompt=new BiometricPrompt(this,executor,callback);
          return biometricPrompt;
}
    private void notifyUser(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }
}