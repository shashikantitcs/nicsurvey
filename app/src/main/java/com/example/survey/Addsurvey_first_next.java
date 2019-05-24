package com.example.survey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Addsurvey_first_next extends AppCompatActivity{



    ImageView prenext;

    String[] religiongroup = { "Select Religion","Hindu", "Muslim", "Chrisitian","Other"};
    String[] social = { "Select Social Group","SC", "ST", "OBC","General"};
    String[] economicgroup = { "Select Economic Group ","APL", "BPL", "Antyodaya"};
    String[] housegroup = { "Select Tyoe of House ","Kuchcha", "Semi Pucca", "Pucca"};
    String[] watergroup = { "Select Drinking Water ","Residence/Yard/Plot", "Shared/Public","Hand pump/Bore-well","Others "};
     SharedPreferences sharedPref;
     Spinner spinnerValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsurvey_first_next);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Take Survey ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setSubtitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });






        prenext=(ImageView)findViewById(R.id.prenext);
        prenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TakeSurvay.class));
                try {
                    testdemo();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });




    }
    public void testdemo() throws JSONException {

        JSONArray jsonArray = new JSONArray("[{EditText:et_household},{EditText:et_housenumber_identification,EditText:et_housenumber_identification},{EditText:et_majorcraft},{RadioGroup:rg}," +
                ",{RadioButton:rb_coldfusion},{RadioButton:rb_flex},{RadioGroup:rg},{Spinner:religion}]");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Iterator<String> keys = json.keys();
                String key = keys.next();
            String edittextidyouset = (String) json.get(key);
             final int resID = getResources().getIdentifier(edittextidyouset,"id", getPackageName());
            while (keys.hasNext()) {
             if(key.equals("EditText")) {

                 EditText xyx = (EditText) findViewById(resID);
                 String pp=  xyx.getText().toString();
                 System.out.println("value :"+pp);
             }
             else  if(key.equals("RadioGroup")) {

                 System.out.println("value :"+"RadioGroup");
             }

             else  if(key.equals("RadioButton")) {
                final RadioButton rb1 = (RadioButton) findViewById(resID);
                rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked)
                        {
                            if (buttonView.getId() == resID) {
                                rb1.setChecked(true);
                            }
                        }
                    }
                });
             }
             else if(key.equals("Spinner"))
             {

                 spinnerValue= (Spinner)findViewById(resID);
                 ArrayAdapter aa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,religiongroup);
                 aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                 spinnerValue.setAdapter(aa);

             }
                

            }

        }


    }

}
