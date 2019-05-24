package com.example.survey;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


public class TakeSurvay extends AppCompatActivity {
    String[] categorydrop = { "Select Category","Small", "Mid Size", "Export Orinted"};
    String[] district = { "Select District","Anantnag", "Badgam", "Bandipora", "Baramulla", "Doda","Ganderbal","Jammu","Kargil","Kathua","Kishtwar","Kulgam","Kupwara","Leh",
            "Poonch","Pulwama","Rajouri","Ramban","Reasi","Samba","Shopian","Srinagar","Udhampur"};
    String[] religiongroup = { "Select Religion","Hindu", "Muslim", "Chrisitian","Other"};
    String[] social = { "Select Social Group","SC", "ST", "OBC","General"};
    String[] economicgroup = { "Select Economic Group ","APL", "BPL", "Antyodaya"};
    String[] housegroup = { "Select Tyoe of House ","Kuchcha", "Semi Pucca", "Pucca"};
    String[] watergroup = { "Select Drinking Water ","Residence/Yard/Plot", "Shared/Public","Hand pump/Bore-well","Others"};
    ImageView firsnext;
    AutoCompleteTextView userlist;
    RadioGroup location_radio_group,houseradio,electric,teliphone,asset,motor,television;
    RadioButton rural,urban,house_yes,house_no,electric_yes,electric_no,telephone_yes,telephone_no,asset_yes,asset_no,motor_yes,
            motor_no,tele_yes,tele_no;
    SharedPreferences sharedPref;
    Spinner cat_spinnerspinner,religion,socialgroup,econimic,water,house;


    EditText cluster,block_town,panchyat_ward,village_mohala,et_household,et_housenumber_identification,et_majorcraft,et_specific,et_other;

    String clusterValue,cat_spinner_value,selecteddistrict,rural_radio_value,location_value,block_town_value,panchyat_ward_value,village_mohala_value,et_household_value,
            et_housenumber_identification_value,et_majorcraft_value,et_specific_value,religion_value,social_value,econimic_value,house_value,
    water_value,radiobutton_houseradio,electriv_radio,telephone_radio,asset_radio,motor_radio,tele_radio,et_other_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.takesurvay);
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

        et_specific=(EditText)findViewById(R.id.et_specific);
        et_majorcraft=(EditText)findViewById(R.id.et_majorcraft);
        et_housenumber_identification=(EditText)findViewById(R.id.et_housenumber_identification);
        et_household=(EditText)findViewById(R.id.et_household);
        village_mohala=(EditText)findViewById(R.id.village_mohala);
        panchyat_ward=(EditText)findViewById(R.id.panchyat_ward);
        block_town=(EditText)findViewById(R.id.block_town);
        cluster=(EditText)findViewById(R.id.cluster);
        cat_spinnerspinner = (Spinner)findViewById(R.id.cat_spinnerspinner);
        et_other=(EditText)findViewById(R.id.et_other);


        television=(RadioGroup)findViewById(R.id.television);
        tele_yes=(RadioButton)findViewById(R.id.tele_yes);
        tele_no=(RadioButton)findViewById(R.id.tele_no);

        television.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.tele_yes){

                    tele_radio = tele_yes.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.tele_no){

                    tele_radio = tele_no.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
            }
        });




        motor=(RadioGroup)findViewById(R.id.motor);
        motor_yes=(RadioButton)findViewById(R.id.motor_yes);
        motor_no=(RadioButton)findViewById(R.id.motor_no);
        motor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.motor_yes){

                    motor_radio = motor_yes.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.motor_no){

                    motor_radio = motor_no.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
            }
        });





        asset=(RadioGroup)findViewById(R.id.asset);
        asset_yes=(RadioButton)findViewById(R.id.asset_yes);
        asset_no=(RadioButton)findViewById(R.id.asset_no);
        asset.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.asset_yes){

                    asset_radio = asset_yes.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.asset_no){

                    asset_radio = asset_no.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
            }
        });





        teliphone=(RadioGroup)findViewById(R.id.teliphone);
        telephone_yes=(RadioButton)findViewById(R.id.telephone_yes);
        telephone_no=(RadioButton)findViewById(R.id.telephone_no);
        teliphone.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.telephone_yes){

                    telephone_radio = telephone_yes.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.telephone_no){

                    telephone_radio = telephone_no.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
            }
        });


        electric=(RadioGroup)findViewById(R.id.electric);
        electric_yes=(RadioButton)findViewById(R.id.electric_yes);
        electric_no=(RadioButton)findViewById(R.id.electric_no);

        electric.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.electric_yes){

                    electriv_radio = electric_yes.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.electric_no){

                    electriv_radio = electric_no.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
            }
        });

        houseradio=(RadioGroup)findViewById(R.id.houseradio);
        house_yes = (RadioButton) findViewById(R.id.house_yes);
        house_no = (RadioButton) findViewById(R.id.house_no);
        houseradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.house_yes){

                    radiobutton_houseradio = house_yes.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.house_no){

                    radiobutton_houseradio = house_no.getText().toString();
                    // Toast.makeText(getApplicationContext(), ""+radiobutton_houseradio, Toast.LENGTH_SHORT).show();
                }
            }
        });

        location_radio_group = (RadioGroup) findViewById(R.id.location_radio_group);
        rural=(RadioButton)findViewById(R.id.rural);
        urban=(RadioButton)findViewById(R.id.urban);

        location_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rural){

                    location_value= rural.getText().toString();

                }
                if (checkedId == R.id.urban){

                    location_value = urban.getText().toString();

                }
            }
        });

        ////////////////////////////sharepreference getting data//////////////////////////////////////////////////////
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String cluster_sharepref=sharedPref.getString("cluster","");
        String cat_spinner_valu_shareprefe=sharedPref.getString("cat_spinner_value","");
        String religion_value_share_pref=sharedPref.getString("religion_value","");
        String social_value_share_pref=sharedPref.getString("social_value","");
        String econimic_value_share_pref=sharedPref.getString("econimic_value","");
        String house_value_share_pref=sharedPref.getString("house_value","");
        String water_value_share_pref=sharedPref.getString("water_value","");
 //////////////////////////////////////setting data ///////////////////////////////////////////////////////////////////
         cluster.setText(cluster_sharepref);


///////////////////////////////category Spinner/////////////////////////////////////////////////////////////////////////

        ArrayAdapter aa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,categorydrop);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cat_spinnerspinner.setAdapter(aa);
        int spinnerPosition = aa.getPosition(cat_spinner_valu_shareprefe);
        cat_spinnerspinner.setSelection(spinnerPosition);
////////////////////////Religion Spinner////////////////////////////////////////////////////////////////////////////////
        religion = (Spinner)findViewById(R.id.religion);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter religion_aa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,religiongroup);
        religion_aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        religion.setAdapter(religion_aa);

        int religionPosition = religion_aa.getPosition(religion_value_share_pref);
        religion.setSelection(religionPosition);

///////////////////////////Social Spinner//////////////////////////////////////////////////////////////////////////////////
        socialgroup = (Spinner)findViewById(R.id.socialgroup);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter socialaa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,social);
        socialaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        socialgroup.setAdapter(socialaa);
        int socialPosition = socialaa.getPosition(social_value_share_pref);
        socialgroup.setSelection(socialPosition);

////////////////////////////////economic////////////////////////////////////////////////////////////////////////////////////
        econimic = (Spinner)findViewById(R.id.econimic);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter econimiclaa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,economicgroup);
        econimiclaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        econimic.setAdapter(econimiclaa);
        int ecnomicPosition = econimiclaa.getPosition(econimic_value_share_pref);
        econimic.setSelection(ecnomicPosition);
///////////////////////////////////////House Spinner///////////////////////////////////////////
        house = (Spinner)findViewById(R.id.house);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter houselaa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,housegroup);
        houselaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        house.setAdapter(houselaa);

        int housePosition = houselaa.getPosition(house_value_share_pref);
        house.setSelection(housePosition);

//////////////////////////////////////////////////////water Spinner/////////////////////////////////////////////////////////////

        water = (Spinner)findViewById(R.id.water);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter waterlaa = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,watergroup);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        water.setAdapter(waterlaa);

        int waterPosition = waterlaa.getPosition(water_value_share_pref);
        water.setSelection(waterPosition);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, district);
        //Getting the instance of AutoCompleteTextView
        userlist = (AutoCompleteTextView) findViewById(R.id.dis_spinnerspinner);
        userlist.setThreshold(1);//will start working from first character
        userlist.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        userlist.setTextColor(Color.BLACK);
        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selecteddistrict = (String) parent.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), ""+selecteddistrict, Toast.LENGTH_SHORT).show();
            }
        });
//////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////
        firsnext=(ImageView)findViewById(R.id.firsnext);
        firsnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_housenumber_identification_value=et_housenumber_identification.getText().toString();
                clusterValue=cluster.getText().toString();
                cat_spinner_value = cat_spinnerspinner.getSelectedItem().toString();
                religion_value = religion.getSelectedItem().toString();
                social_value=socialgroup.getSelectedItem().toString();
                econimic_value=econimic.getSelectedItem().toString();
               house_value=house.getSelectedItem().toString();
                water_value=water.getSelectedItem().toString();


                et_other_value=et_other.getText().toString();
                block_town_value=block_town.getText().toString();
                panchyat_ward_value=panchyat_ward.getText().toString();
                village_mohala_value=village_mohala.getText().toString();
                et_household_value=et_household.getText().toString();
                et_majorcraft_value=et_majorcraft.getText().toString();
                et_specific_value=et_specific.getText().toString();

/////////////////////////////////////store value in share preference///////////////////////////////////////////////////
                sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editorlog = sharedPref.edit(); // Editor of shared
                editorlog.putString("cluster", clusterValue);
                editorlog.putString("religion_value", religion_value);
                editorlog.putString("cat_spinner_value", cat_spinner_value);
                editorlog.putString("selecteddistrict", selecteddistrict);
                editorlog.putString("location_value", location_value);
                editorlog.putString("block_town_value", block_town_value);
                editorlog.putString("panchyat_ward_value", panchyat_ward_value);
                editorlog.putString("village_mohala_value", village_mohala_value);
                editorlog.putString("et_household_value", et_household_value);
                editorlog.putString("et_housenumber_identification_value", et_housenumber_identification_value);
                editorlog.putString("et_majorcraft_value", et_majorcraft_value);
                editorlog.putString("et_specific_value", et_specific_value);
                editorlog.putString("social_value", social_value);
                editorlog.putString("econimic_value", econimic_value);
                editorlog.putString("house_value", house_value);
                editorlog.putString("water_value", water_value);
                editorlog.putString("radiobutton_houseradio", radiobutton_houseradio);
                editorlog.putString("electriv_radio", electriv_radio);
                editorlog.putString("telephone_radio", telephone_radio);
                editorlog.putString("asset_radio", asset_radio);
                editorlog.putString("motor_radio", motor_radio);
                editorlog.putString("tele_radio", tele_radio);
                editorlog.putString("et_other_value", et_other_value);

                editorlog.commit();

                startActivity(new Intent(getApplicationContext(),HouseHoldRoaster.class));
            }
        });




    }
}
