package com.example.customerside;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.customerside.Auto.MainAdapterBike;

import com.example.customerside.Auto.MainModelBike;

import java.util.ArrayList;

public class BikeSpareParts extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MainModelBike> mainModelsBike;
    MainAdapterBike mainAdapterBike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_spare_parts);

        recyclerView = (RecyclerView)findViewById(R.id.bikePartsList);



        Integer[] bikePartsList = {R.drawable.bp1,R.drawable.bp2,R.drawable.bp3,R.drawable.bp4,R.drawable.bp5,
                R.drawable.bp6,R.drawable.bp7,R.drawable.bp8,R.drawable.bp9,R.drawable.bp10,R.drawable.bp11,
                R.drawable.bp12,R.drawable.bp13,R.drawable.bp14,R.drawable.bp15,R.drawable.bp16,R.drawable.bp17,
                R.drawable.bp18,R.drawable.bp19,R.drawable.bp20};

        String[] bikePartsName = {"AHL Genuine Honda Tyre","Air CLeaner","Battery","Brake Shoe Set","Cable Front Brake","CD 70 Spark Plug",
                "Chain Cover Set","Chain Sprocket Kit","Cushion Assy Rear","Disk Clutch Friction Set","Headlight",
                "Front Wheel Hub","Muffler Exhaust","Piston Kit","Seat Assy Double","Connecting Rod Kit","Speedometer","Spoke & Nipple Set Front","Side Winkers Assy"
        ,"Back Light For Unique 70cc"};

        String[] bikePartsPrice = {"Rs1200","Rs300","Rs650","Rs500","Rs420","Rs200",
                "Rs1800","Rs1100","Rs150","Rs170","Rs1300",
                "Rs300","Rs270","Rs300","Rs1200","Rs120","Rs2000","Rs250","Rs330","Rs400"};

        mainModelsBike = new ArrayList<>();
        for (int i = 0 ; i<bikePartsList.length; i++){
            MainModelBike model = new MainModelBike(bikePartsList[i],bikePartsName[i],bikePartsPrice[i]);
            mainModelsBike.add(model);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                BikeSpareParts.this,LinearLayoutManager.VERTICAL,false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mainAdapterBike = new MainAdapterBike(BikeSpareParts.this,mainModelsBike);
        //Set main adapter to recycler view
        recyclerView.setAdapter(mainAdapterBike);

    }
}