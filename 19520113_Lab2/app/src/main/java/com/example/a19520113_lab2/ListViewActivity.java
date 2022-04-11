package com.example.a19520113_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends AppCompatActivity {

    private ListView lvPerson;
    private TextView tvSelection;
    private final String arr[] = {"Teo", "Ty", "Bin", "Bo"};

    private void initUi()
    {
        lvPerson = (ListView)findViewById(R.id.lv_person);
        tvSelection = (TextView)findViewById(R.id.tv_selection);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initUi();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);

        lvPerson.setAdapter(adapter);

        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?>arg0, View arg1, int arg2, long arg3) {
                        //đối số arg2 là vị trí phần tử trong Data Source (arr)
                        tvSelection.setText("position :" + arg2 + "; value =" + arr[arg2]);
                    }
                });
    }
}