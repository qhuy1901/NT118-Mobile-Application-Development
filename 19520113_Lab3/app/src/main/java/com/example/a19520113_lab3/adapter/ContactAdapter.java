package com.example.a19520113_lab3.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.a19520113_lab3.R;
import com.example.a19520113_lab3.database.DatabaseHandler;
import com.example.a19520113_lab3.model.Contact;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private Activity context;

    public ContactAdapter(Activity context, int layoutID, List<Contact> objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact, null, false);
        }

        // Get item
        Contact contact = getItem(position);

        // Get view
        TextView tvId = (TextView) convertView.findViewById(R.id.item_contact_tv_contact_id);
        TextView tvName = (TextView) convertView.findViewById(R.id.item_contact_tv_contact_name);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.item_contact_tv_contact_phone_number);
        ConstraintLayout clParent = (ConstraintLayout) convertView.findViewById(R.id.item_contact_cl_parent);

        tvId.setText("Mã liên hệ: "+ contact.getId());
        tvName.setText("Tên liên hệ: " + contact.getName());
        tvPhone.setText("Số điện thoại: " + contact.getPhoneNumber());

        if (position%2==0)
        {
            clParent.setBackgroundResource(R.color.white);
        }
        else{
            clParent.setBackgroundResource(R.color.light_blue);
        }
        return convertView;
    }
}
