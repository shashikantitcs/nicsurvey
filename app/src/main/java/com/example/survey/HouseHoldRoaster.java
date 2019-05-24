package com.example.survey;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.survey.connections.RetrofitClient;
import com.example.survey.connections.Utils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HouseHoldRoaster extends AppCompatActivity {
RelativeLayout relativeLayout1;
ImageView prevsnext;

EditText house_hold_roster,sexmf,age_in_year,physical_infirmity,educational_status,occupation,primary,secondary,working_our_perday,
        avg_working_days,during_session,during_off_season,school_goining_status,income_inhhs,saving_in_hhs,any_financial,
        productivity,pi_list,what_are_source,possiblites;

    String income_inhhs_value_sharepref;

String house_hold_roster_value,sexmf_value,age_in_year_value,physical_infirmity_value,educational_status_value,occupation_value,primary_value,secondary_value,working_our_perday_value,
        avg_working_days_value,during_session_value,during_off_season_value,school_goining_status_value,income_inhhs_value,saving_in_hhs_value,any_financial_value,
        productivity_value,pi_list_value,what_are_source_value,possiblites_value,asset_radio,putcategory;
    String location_value,location_global,social_value,econimic_value;
    String et_household_value,et_housenumber_identification_value,religion_value,religion_global,social_global,econimic_global;
        RadioGroup radio_group;
        RadioButton radio1,radio2;
        Button Submit;
    String water_value,water_value_global;
    String radiobutton_houseradio,radiobutton_houseradio_global;
    String house_value,house_global;
    String electriv_radio,electriv_radio_global;
    SharedPreferences sharedPref;
    String telephone_radio,telephone_radio_global;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_hold_roaster);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Take Survey");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setSubtitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        radio_group=(RadioGroup)findViewById(R.id.radio_group);
        radio1=(RadioButton)findViewById(R.id.radio1);
        radio2=(RadioButton)findViewById(R.id.radio2);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio1){

                    asset_radio = radio1.getText().toString();
                   // Toast.makeText(getApplicationContext(), ""+asset_radio, Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.radio2){

                    asset_radio = radio2.getText().toString();
                  // Toast.makeText(getApplicationContext(), ""+asset_radio, Toast.LENGTH_SHORT).show();
                }
            }
        });


        house_hold_roster=(EditText)findViewById(R.id.house_hold_roster);

        sexmf=(EditText)findViewById(R.id.sexmf);

        age_in_year=(EditText)findViewById(R.id.age_in_year);

        physical_infirmity=(EditText)findViewById(R.id.physical_infirmity);

        educational_status=(EditText)findViewById(R.id.educational_status);

        occupation=(EditText)findViewById(R.id.occupation);

        primary=(EditText)findViewById(R.id.primary);

        secondary=(EditText)findViewById(R.id.secondary);

        working_our_perday=(EditText)findViewById(R.id.working_our_perday);

        avg_working_days=(EditText)findViewById(R.id.avg_working_days);

        during_session=(EditText)findViewById(R.id.during_session);

        during_off_season=(EditText)findViewById(R.id.during_off_season);

        school_goining_status=(EditText)findViewById(R.id.school_goining_status);

        income_inhhs=(EditText)findViewById(R.id.income_inhhs);

        saving_in_hhs=(EditText)findViewById(R.id.saving_in_hhs);

        any_financial=(EditText)findViewById(R.id.any_financial);

        productivity=(EditText)findViewById(R.id.productivity);

        pi_list=(EditText)findViewById(R.id.pi_list);
        what_are_source=(EditText)findViewById(R.id.what_are_source);

        possiblites=(EditText)findViewById(R.id.possiblites);

        ////////////////////////////sharepreference getting data//////////////////////////////////////////////////////
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        String house_hold_roster_value_sharepref=sharedPref.getString("house_hold_roster_value","");

        String sexmf_value_sharepref=sharedPref.getString("sexmf_value","");

        String age_in_year_value_sharepref=sharedPref.getString("age_in_year_value","");

        String physical_infirmity_value_sharepref=sharedPref.getString("physical_infirmity_value","");

        String educational_status_value_sharepref=sharedPref.getString("educational_status_value","");

        String occupation_value_sharepref=sharedPref.getString("occupation_value","");

        String primary_value_sharepref=sharedPref.getString("primary_value","");

        String secondary_value_sharepref=sharedPref.getString("secondary_value","");

        String working_our_perday_value_sharepref=sharedPref.getString("working_our_perday_value","");

        String avg_working_days_value_sharepref=sharedPref.getString("avg_working_days_value","");

        String during_session_value_sharepref=sharedPref.getString("during_session_value","");

        String during_off_season_value_sharepref=sharedPref.getString("during_off_season_value","");

        String school_goining_status_value_sharepref=sharedPref.getString("school_goining_status_value","");

        income_inhhs_value_sharepref=sharedPref.getString("income_inhhs_value","");

        String saving_in_hhs_value_sharepref=sharedPref.getString("saving_in_hhs_value","");

        String any_financial_value_sharepref=sharedPref.getString("any_financial_value","");

        String productivity_value_sharepref=sharedPref.getString("productivity_value","");

        String pi_list_value_sharepref=sharedPref.getString("pi_list_value","");

        String what_are_source_value_sharepref=sharedPref.getString("what_are_source_value","");

       final String possiblites_value_sharepref=sharedPref.getString("possiblites_value","");
        String asset_radio_sharepref=sharedPref.getString("asset_radio","");
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


        if(asset_radio_sharepref.equals("Yes"))
        {
            radio1.setChecked(true);
        }
        if(asset_radio_sharepref.equals("No"))
        {
            radio2.setChecked(true);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String Cluster=sharedPref.getString("cluster","");
         religion_value=sharedPref.getString("religion_value","");
        final String cluster_category=sharedPref.getString("cat_spinner_value","");
        String selecteddistrict=sharedPref.getString("selecteddistrict","");
         location_value=sharedPref.getString("location_value","");
        String block_town_value=sharedPref.getString("block_town_value","");
        String panchyat_ward_value=sharedPref.getString("panchyat_ward_value","");

        String village_mohala_value=sharedPref.getString("village_mohala_value","");
         et_household_value=sharedPref.getString("et_household_value","");
         et_housenumber_identification_value=sharedPref.getString("et_housenumber_identification_value","");
        String et_majorcraft_value=sharedPref.getString("et_majorcraft_value","");
        String et_specific_value=sharedPref.getString("et_specific_value","");
         social_value=sharedPref.getString("social_value","");
         econimic_value=sharedPref.getString("econimic_value","");
         house_value=sharedPref.getString("house_value","");
         water_value=sharedPref.getString("water_value","");

         radiobutton_houseradio=sharedPref.getString("radiobutton_houseradio","");
         electriv_radio=sharedPref.getString("electriv_radio","");

         telephone_radio=sharedPref.getString("telephone_radio","");
        String asset_radio1=sharedPref.getString("asset_radio","");
        String motor_radio=sharedPref.getString("motor_radio","");
        String tele_radio=sharedPref.getString("tele_radio","");
        String et_other_value=sharedPref.getString("et_other_value","");




        Submit=(Button)findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Cluster", Cluster);
                map.put("cluster_category", cluster_category);
                Log.e("test", String.valueOf(map));
                String category_check=cluster_category;
                String location_check=location_value;
              //  Toast.makeText(getApplicationContext(), ""+location_check, Toast.LENGTH_SHORT).show();

                if(location_check.equals("Rural-1"))
                {
                    location_global="1";
                }
                if(location_check.equals("Urban-2"))
                {
                    location_global="2";
                }


                if(category_check.equals("Small"))
                {
                     putcategory="1";
                }
                if(category_check.equals("Mid Size"))
                {
                     putcategory="2";
                }
                if(category_check.equals("Export Orinted"))
                {
                     putcategory="3";
                }
                if(religion_value.equals("Hindu"))
                {
                    religion_global="1";
                }
                if(religion_value.equals("Muslim"))
                {
                    religion_global="2";
                }
                if(religion_value.equals("Chrisitian"))
                {
                    religion_global="3";
                }
                if(religion_value.equals("Other"))
                {
                    religion_global="4";
                }
                if(social_value.equals("SC"))
                {
                    social_global="1";
                }
                if(social_value.equals("ST"))
                {
                    social_global="2";
                }
                if(social_value.equals("OBC"))
                {
                    social_global="3";
                }
                if(social_value.equals("General"))
                {
                    social_global="4";
                }
                if(econimic_value.equals("APL"))
                {
                    econimic_global="1";
                }
                if(econimic_value.equals("BPL"))
                {
                    econimic_global="2";
                }
                if(econimic_value.equals("Antyodaya"))
                {
                    econimic_global="3";
                }
                if(house_value.equals("Kuchcha"))
                {
                    house_global="1";
                }
                if(house_value.equals("Semi Pucca"))
                {
                    house_global="2";
                }
                if(house_value.equals("Pucca"))
                {
                    house_global="3";
                }
                if(radiobutton_houseradio.equals("Yes"))
                {
                    radiobutton_houseradio_global="1";
                }
                if(radiobutton_houseradio.equals("No"))
                {
                    radiobutton_houseradio_global="2";
                }
                if(water_value.equals("Residence/Yard/Plot"))
                {
                    water_value_global="1";
                }
                if(water_value.equals("Shared/Public"))
                {
                    water_value_global="2";
                }
                if(water_value.equals("Hand pump/Bore-well"))
                {
                    water_value_global="3";
                }
                if(water_value.equals("Others"))
                {
                    water_value_global="4";
                }
                if(electriv_radio.equals("Yes"))
                {
                    electriv_radio="1";
                }
                if(electriv_radio.equals("No"))
                {
                    electriv_radio="2";
                }
                if(telephone_radio.equals("Yes"))
                {
                    telephone_radio_global="1";
                }
                if(telephone_radio.equals("No"))
                {
                    telephone_radio_global="2";
                }
                Toast.makeText(getApplicationContext(), ""+telephone_radio_global, Toast.LENGTH_SHORT).show();
                survayform("serveyDone","1","ggggg",putcategory,location_global,"",et_household_value,
                        et_housenumber_identification_value,"" ,"","",
                        "",religion_global,social_global,econimic_global,house_global,radiobutton_houseradio_global,water_value_global,"",electriv_radio,telephone_radio_global,
                        "",income_inhhs_value_sharepref);

            }
        });
//////////////////////////////////////////////////setting the value///////////////////////////////////////////////////
        house_hold_roster.setText(house_hold_roster_value_sharepref);

        sexmf.setText(sexmf_value_sharepref);

        age_in_year.setText(age_in_year_value_sharepref);

        physical_infirmity.setText(physical_infirmity_value_sharepref);

        educational_status.setText(educational_status_value_sharepref);

        occupation.setText(occupation_value_sharepref);

        primary.setText(primary_value_sharepref);

        secondary.setText(secondary_value_sharepref);

        working_our_perday.setText(working_our_perday_value_sharepref);

        avg_working_days.setText(avg_working_days_value_sharepref);

        during_session.setText(during_session_value_sharepref);

        during_off_season.setText(during_off_season_value_sharepref);

        school_goining_status.setText(school_goining_status_value_sharepref);

        income_inhhs.setText(income_inhhs_value_sharepref);

        saving_in_hhs.setText(saving_in_hhs_value_sharepref);

        any_financial.setText(any_financial_value_sharepref);


        productivity.setText(productivity_value_sharepref);

        pi_list.setText(pi_list_value_sharepref);

        what_are_source.setText(what_are_source_value_sharepref);
        possiblites.setText(possiblites_value_sharepref);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        prevsnext=(ImageView)findViewById(R.id.prevsnext);
        prevsnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                house_hold_roster_value=house_hold_roster.getText().toString();
                sexmf_value=sexmf.getText().toString();
                age_in_year_value=age_in_year.getText().toString();
                physical_infirmity_value=physical_infirmity.getText().toString();
                educational_status_value=educational_status.getText().toString();
                occupation_value=occupation.getText().toString();
                primary_value=primary.getText().toString();
                secondary_value=secondary.getText().toString();
                working_our_perday_value=working_our_perday.getText().toString();
                avg_working_days_value=avg_working_days.getText().toString();
                during_session_value=during_session.getText().toString();
                during_off_season_value=during_off_season.getText().toString();
                school_goining_status_value=school_goining_status.getText().toString();
                income_inhhs_value=income_inhhs.getText().toString();
                saving_in_hhs_value=saving_in_hhs.getText().toString();
                any_financial_value=any_financial.getText().toString();
                productivity_value=productivity.getText().toString();
                pi_list_value=pi_list.getText().toString();
                what_are_source_value=what_are_source.getText().toString();

                possiblites_value=possiblites.getText().toString();



 /////////////////////////////////////////////////////////////////////////////////////////////////////

                sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editorlog = sharedPref.edit(); // Editor of shared
                editorlog.putString("house_hold_roster_value", house_hold_roster_value);

                editorlog.putString("sexmf_value", sexmf_value);

                editorlog.putString("age_in_year_value", age_in_year_value);

                editorlog.putString("physical_infirmity_value", physical_infirmity_value);

                editorlog.putString("educational_status_value", educational_status_value);

                editorlog.putString("occupation_value", occupation_value);

                editorlog.putString("primary_value", primary_value);

                editorlog.putString("secondary_value", secondary_value);

                editorlog.putString("avg_working_days_value", avg_working_days_value);

                editorlog.putString("during_session_value", during_session_value);

                editorlog.putString("during_off_season_value", during_off_season_value);

                editorlog.putString("school_goining_status_value", school_goining_status_value);

                editorlog.putString("income_inhhs_value", income_inhhs_value);

                editorlog.putString("saving_in_hhs_value", saving_in_hhs_value);

                editorlog.putString("any_financial_value", any_financial_value);

                editorlog.putString("productivity_value", productivity_value);

                editorlog.putString("pi_list_value", pi_list_value);

                editorlog.putString("what_are_source_value", what_are_source_value);
                editorlog.putString("possiblites_value", possiblites_value);

                editorlog.putString("asset_radio", asset_radio);

                editorlog.commit();
                finish();
            }
        });
    }
    public void survayform(final String choice,
                           final String user_id,
                           final String Cluster,
                           final String cluster_category,
                           final String location,
                           final String subdistrict,
                           final String head_of_houshold,
                           final String house_number,
                           final String handicraft_practiced,
                           final String family_cccupation,
                           final String practiced_houshold,
                           final String seasonality_Pattern,
                           final String religion,
                           final String social_group,
                           final String economic_group,
                           final String house_type,
                           final String house_owned,
                           final String drinking_water,
                           final String drinking_water_source,
                           final String electric_connection,
                           final String telephone_conn,
                           final String ownership_of_assets,
                            final String income_of_hhs
                           )
    {

        if (!Utils.isOnline(this)) {
            Utils.nointernet(this);
        } else {
            Call<ResponseBody> call = RetrofitClient.getClient().sendarray(choice,user_id,Cluster,cluster_category,
                    location,subdistrict,head_of_houshold,house_number,handicraft_practiced,family_cccupation,
                    practiced_houshold,
                    seasonality_Pattern,
                    religion,social_group,
                    economic_group,house_type,
                    house_owned,drinking_water, drinking_water_source,
                    electric_connection,telephone_conn,ownership_of_assets,income_of_hhs);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {

                        try {
                            String result = response.body().string();

                            //Toast.makeText(getApplicationContext(), ""+result, Toast.LENGTH_SHORT).show();






                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), ""+t, Toast.LENGTH_SHORT).show();
                    if (t.getMessage().equals("connect timed out")) {
                        Utils.nointernet(HouseHoldRoaster.this);
                    }
                }
            });
        }
    }
}
