package com.example.customerside;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    ProgressDialog progressDialog;
    ImageButton btnNearMechanic, btnFindMechanic, btnCarParts, btnBikeParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();

        btnNearMechanic = (ImageButton) findViewById(R.id.btnNearMechanic);
        btnFindMechanic = (ImageButton) findViewById(R.id.btnFindMechanic);
        btnCarParts = (ImageButton) findViewById(R.id.btnCarParts);
        btnBikeParts = (ImageButton) findViewById(R.id.btnBikeParts);



        btnNearMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(MainMenu.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                Thread timer=new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(1000);
                            Intent intent = new Intent(getApplicationContext(),FindNearMechanic.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                            // finish();
                            super.run();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                };
                timer.start();
            }
        });


        btnFindMechanic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainMenu.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
                Thread timer=new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(1000);
                            Intent intent = new Intent(getApplicationContext(),Home.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                            // finish();
                            super.run();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                };
                timer.start();
            }
        });


        btnCarParts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(MainMenu.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
                Thread timer=new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(1000);
                            Intent intent = new Intent(getApplicationContext(),CarSpareParts.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                            // finish();
                            super.run();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }

                    }
                };
                timer.start();
            }
        });


        btnBikeParts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(MainMenu.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progress_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );
                Thread timer=new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(1000);
                            Intent intent = new Intent(getApplicationContext(),BikeSpareParts.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                            // finish();
                            super.run();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                };
                timer.start();
            }
        });

    }
}

