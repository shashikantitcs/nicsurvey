package com.example.survey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.survey.Superviser.SuperviserDashboard;
import com.example.survey.connections.RetrofitClient;
import com.example.survey.connections.Utils;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    SharedPreferences sharedPref;
    Button btn_login;
    LinearLayout otplayout;
    EditText input_mobile;
    String usermobilenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_mobile=(EditText)findViewById(R.id.input_mobile);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logincheck();
            }
        });

    }
    public  void logincheck()
    {
        usermobilenumber=input_mobile.getText().toString();

        if (usermobilenumber.isEmpty() || usermobilenumber.length() > 10) {
            input_mobile.requestFocus();
            input_mobile.setError("Enter A valid mobile nnumber");
        }
        else{

            login("appLogin",usermobilenumber);
        }
    }
    public void login(final String choice,final String mobile) {

        if (!Utils.isOnline(this)) {
            Utils.nointernet(this);
        } else {
            Call<ResponseBody> call = RetrofitClient.getClient().logincheck(choice,mobile);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {

                        try {
                            String result = response.body().string();

                            JSONObject obj = new JSONObject(result);

                            String getstatus=  obj.getString("status");



                            if(getstatus.equals(("true")))
                            {
                                String success = obj.getString("success");
                                JSONObject subObject = obj.getJSONObject("success");
                                String full_name = subObject.getString("full_name");
                                String role_id = subObject.getString("role_id");
                                String emp_id = subObject.getString("emp_id");
                                sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editorlog = sharedPref.edit(); // Editor of shared
                                editorlog.putString("full_name", full_name);
                                editorlog.putString("role_id", role_id);
                                editorlog.putString("emp_id", emp_id);
                                editorlog.commit();
                                if(role_id.equals("1")) {
                                    sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editorlog1 = sharedPref.edit(); // Editor of shared
                                    editorlog1.putString("full_name", full_name);
                                    editorlog1.putString("role_id", role_id);
                                    editorlog.putString("emp_id", emp_id);
                                    editorlog1.commit();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                                else  if(role_id.equals("2")) {
                                    sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editorlog1 = sharedPref.edit(); // Editor of shared
                                    editorlog1.putString("full_name", full_name);
                                    editorlog1.putString("role_id", role_id);
                                    editorlog.putString("emp_id", emp_id);
                                    editorlog1.commit();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                                else
                                {
                                    sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editorlog1 = sharedPref.edit(); // Editor of shared
                                    editorlog1.putString("full_name", full_name);
                                    editorlog1.putString("role_id", role_id);
                                    editorlog.putString("emp_id", emp_id);
                                    editorlog1.commit();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                            }
                            else  if(getstatus.equals(("false")))
                            {
                                String error=  obj.getString("error");
                                Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
                            }
                            ;
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
                        Utils.nointernet(Login.this);
                    }
                }
            });
        }
    }


}
