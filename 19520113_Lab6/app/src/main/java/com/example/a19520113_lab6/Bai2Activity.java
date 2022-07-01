package com.example.a19520113_lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class Bai2Activity extends AppCompatActivity {
    private IntentFilter filter, filter2;
    private BroadcastReceiver broadcastReceiver, broadcastReceiver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        initBroadcastReceiver();
        initBroadcastReceiver2();
    }

    private void initBroadcastReceiver(){
        filter = new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
                    Toast.makeText(context, "Power connected", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Power disconnected", Toast.LENGTH_LONG).show();
                }

            }
        };
    }
    private void initBroadcastReceiver2(){
        filter2 = new IntentFilter("android.intent.action.ACTION_POWER_DISCONNECTED");

        broadcastReceiver2 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Power disconnected", Toast.LENGTH_LONG).show();
            }
        };
    }
    public void processReceive(Context context, Intent intent) {
        if(context == null) return;
        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            Toast.makeText(context, "Power connected", Toast.LENGTH_LONG).show();
        }
        if(intent.getAction().endsWith(Intent.ACTION_POWER_DISCONNECTED)){
            Toast.makeText(context, "Power disconnected", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        if(broadcastReceiver == null) initBroadcastReceiver();

        registerReceiver(broadcastReceiver, filter);
        registerReceiver(broadcastReceiver2, filter2);
    }

    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}