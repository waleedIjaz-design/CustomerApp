package com.example.customerside;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customerside.Auto.MainAdapter;
import com.example.customerside.Auto.MainModel;

import java.util.ArrayList;

public class CarSpareParts extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_spare_parts);

        recyclerView = (RecyclerView)findViewById(R.id.partsList);

        Integer[] carPartsList = {R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5
                ,R.drawable.p6,R.drawable.p7,R.drawable.p8,R.drawable.p9,R.drawable.p10,R.drawable.p11,
                R.drawable.p12,R.drawable.p13,R.drawable.p14,R.drawable.p15,R.drawable.p16,R.drawable.p17,
                R.drawable.p18,R.drawable.p19,R.drawable.p20};

        String[] carPartsName = {"Simota Air Intake Filter","Efi Condenser Denso","Oil Filter",
                "Genuine Air Filter","A6 SMD Headlights Bulbs","DRL LED Daytime Light",
                "Genuine Front Brake Pads","Honda Italian Horns","LED HeadLamps Pair","Side Mirror Chrome",
                "Corolla Body Kit", "Fog Lamps With Cover",
                "Toyota Headlight Bulbs","Fog Lamp With LED Cover","Nike Style Headlights","Clutch Kit",
                "A/C Compressors", "Car Neck Back Rest Long Cushion"
                ,"NGK Standard Spark Plug","HB-50 AGS Hybrid Battery"};

        String[] carPartsPrice = {"Rs2400","Rs6300","Rs400","Rs600","Rs1500","Rs950","Rs8500",
                "Rs1800","Rs6200","Rs1360","Rs20000", "Rs7500","Rs1000","Rs4800","Rs30000","Rs1600","Rs16200"
                ,"Rs1150","Rs2080","Rs6810"};


        mainModels = new ArrayList<>();
        for (int i = 0 ; i<carPartsList.length; i++){
            MainModel model = new MainModel(carPartsList[i],carPartsName[i],carPartsPrice[i]);
            mainModels.add(model);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                CarSpareParts.this,LinearLayoutManager.VERTICAL,false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mainAdapter = new MainAdapter(CarSpareParts.this,mainModels);
        //Set main adapter to recycler view
        recyclerView.setAdapter(mainAdapter);

    }

}