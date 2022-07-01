package com.example.a19520113_lab6;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.ArrayList;
import java.util.Locale;

public class SmsReceiver extends BroadcastReceiver {

    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms forward broadcast receiver";
    private static final String SMS_RECEIVED         = "android.provider.Telephony.SMS_RECEIVED";
    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms forward broadcast receiver";


    private static ArrayList<SMSReceivedListner> smsListner = new ArrayList<SMSReceivedListner>();

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        final Bundle extras = intent.getExtras();

        String queryString = "Are you OK?".toLowerCase();

        if (action.equals(SmsReceiver.SMS_RECEIVED)) {
            final boolean smsValid = extras != null;

            if (smsValid) {
                //Create SMSMessages from PDUs in the Bundle
                final Object[] pdus = (Object[])extras.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                }

                //Assemble
                ArrayList<String> addresses = new ArrayList<String>();
                for (SmsMessage m : messages) {
                    if (m.getMessageBody().toLowerCase().contains(queryString)) {
                        addresses.add(m.getOriginatingAddress());
                    }
                }

                if (addresses.size() > 0) {
                    if (!Bai4Activity.isRunning) {

                    } else {
                        Intent iForwardBroadcastReceiver = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);
                        iForwardBroadcastReceiver.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                        context.sendBroadcast(iForwardBroadcastReceiver);
                    }
                }

                for (SmsMessage message : messages) {
                    for (SMSReceivedListner smsReceivedListner : smsListner )
                        smsReceivedListner.message(message);
                }
            }
        }
    }

    public static void addSMSListner(SMSReceivedListner listner){
        smsListner.add(listner);
    }

    public static void removeSMSListner(SMSReceivedListner listner){
        smsListner.remove(listner);
    }

    public interface SMSReceivedListner{
        public void message(SmsMessage message);
    }
}
