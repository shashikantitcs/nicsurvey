package com.example.survey.connections;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Utils {
public static String site_url = "http://10.21.87.9/survey/server/controller/";



public static final boolean isOnline(Context context) {
    ConnectivityManager cm =
            (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    return netInfo != null && netInfo.isConnectedOrConnecting();
}

public static final void nointernet(Context context){
    try {
//            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
//            alertDialog.setTitle("Info");
//            alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
//            alertDialog.setIcon(R.drawable.dialog_warning);
//            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                   // finish();
//                }
//            });
//
//            alertDialog.show();
    }
    catch(Exception e)
    {

        Log.d("no Internet", "Show Dialog: "+e.getMessage());
    }
}


}
