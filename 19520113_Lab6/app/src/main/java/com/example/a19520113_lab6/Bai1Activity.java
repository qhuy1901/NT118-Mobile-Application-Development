package com.example.a19520113_lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Bai1Activity extends AppCompatActivity {
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        initBroadcastReceiver();
    }
    private void initBroadcastReceiver(){
        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceive(context, intent);
            }
        };
    }
    public void processReceive(Context context, Intent intent) {
        Toast.makeText(context, getString(R.string.you_have_a_new_message),
                Toast.LENGTH_LONG).show();
        TextView tvContent = (TextView) findViewById(R.id.tv_content);
        final String SMS_EXTRA = "pdus";
        Bundle bundle = intent.getExtras();
        Object[] messages = (Object[]) bundle.get(SMS_EXTRA);
        String sms = "";

        SmsMessage smsMsg;
//        for (int i = 0; i < messages.length; i++) {
//            if (android.os.Build.VERSION.SDK_INT >= 23) {
//                smsMsg = SmsMessage.createFromPdu((byte[]) messages[i], "");
//                 Log.i("smsMeg", String.valueOf(messages));
//
//            } else {
//                smsMsg = SmsMessage.createFromPdu((byte[]) messages[i]);
//                String msgBody = smsMsg.getMessageBody();
//                String address = smsMsg.getDisplayOriginatingAddress();
//                sms += address + ":\n" + msgBody + "\n";
//            }
//
//        }
        SmsMessage[] msgs = null;
        String msg_from = null;
        try{
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for(int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                msg_from = msgs[i].getOriginatingAddress();
                String msgBody = msgs[i].getMessageBody();
                sms += msg_from + ":\n" + msgBody + "\n";
            }
        }catch(Exception e){
            Log.d("Exception caught",e.getMessage());
        }
        tvContent.setText(sms);

    }


    @Override
    protected void onResume(){
        super.onResume();

        if(broadcastReceiver == null) initBroadcastReceiver();

        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}