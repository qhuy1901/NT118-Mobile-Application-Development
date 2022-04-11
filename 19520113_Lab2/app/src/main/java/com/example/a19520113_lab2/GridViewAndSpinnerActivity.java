package com.example.a19520113_lab2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a19520113_lab2.adapter.DishAdapter;
import com.example.a19520113_lab2.adapter.EmployeeAdapter;
import com.example.a19520113_lab2.adapter.ThumbnailAdapter;
import com.example.a19520113_lab2.model.Dish;
import com.example.a19520113_lab2.model.Employee;
import com.example.a19520113_lab2.model.Thumbnail;

import java.util.ArrayList;
import java.util.EnumSet;

public class GridViewAndSpinnerActivity extends AppCompatActivity {
    private GridView gvDish;
    private Button btnSubmit;
    private EditText etName;
    private CheckBox ckbIsPromotion;
    private ArrayList<Dish> dishes;
    private ArrayList<Thumbnail> thumbnails;
    private Spinner spnEnterThumbnail;
    private ThumbnailAdapter thumbnailAdapter;


    private void initUi()
    {
        gvDish = (GridView )findViewById(R.id.gv_dish);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        etName = (EditText)  findViewById(R.id.etEnterName);
        ckbIsPromotion = (CheckBox)  findViewById(R.id.ckb_isPromotion);
        spnEnterThumbnail = (Spinner) findViewById(R.id.spnEnterThumbnail);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_and_spinner);
        initUi();
        dishes = new ArrayList<Dish>();
        DishAdapter adapter = new DishAdapter(this, android.R.layout.simple_list_item_1, dishes);
        gvDish.setAdapter(adapter);

        // Get all item in thumbnails class
        thumbnails = new ArrayList<Thumbnail>(EnumSet.allOf(Thumbnail.class));

        thumbnailAdapter = new ThumbnailAdapter(this, R.layout.item_thumbnail, thumbnails);
        spnEnterThumbnail.setAdapter(thumbnailAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //Thêm dữ liệu mới vào arraylist
                addNewDish(spnEnterThumbnail.getSelectedItemPosition());
                Toast.makeText(getApplicationContext(),"Added successfully", Toast.LENGTH_SHORT).show();
                //Cập nhật dữ liệu mới lên giao diên
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void addNewDish(int thumbnailPosition) {
        Dish dish = new Dish();
        boolean isPromotion = ckbIsPromotion.isChecked();
        String name = etName.getText().toString();
        Thumbnail newDishThumbnail = thumbnailAdapter.getItem(thumbnailPosition);

        dish.setDishName(name);
        dish.setPromotion(isPromotion);
        dish.setThumbnail(newDishThumbnail);
        dishes.add(dish);
    }
}