package com.alarmmanager.BasePackage;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

public class ConnectionCheck extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_CODE = 1;
    private static final int PERMISSION_CAMERA_REQUEST_CODE = 2;
    private static final int PERMISSION_READ_STORAGE_REQUEST_CODE = 3;
    private static final int PERMISSION_WRITE_STORAGE_REQUEST_CODE = 4;

    public final static int REQUEST_LOCATION = 200;





    public static boolean isInternetAvailable(final Context context) {

        ConnectivityManager conManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        return !((networkInfo == null) || (!networkInfo.isConnected()) || (!networkInfo.isAvailable()));
    }



    public static void showDialogForInternetConection(final Context mcontext) {

        AlertDialog.Builder alertDialogBuilder = new
                AlertDialog.Builder(mcontext);
        alertDialogBuilder
                .setMessage("Enable Internet")
                .setTitle("No Internet Connection")
                .setCancelable(true)
                .setPositiveButton("Enable",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                Intent dialogIntent = new
                                        Intent(Settings.ACTION_SETTINGS);

                                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                mcontext.startActivity(dialogIntent);
                            }
                        });


        alertDialogBuilder.setNegativeButton("Cancel", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
        Button buttonPositive = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        Button buttonNegative = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button buttonNeutral = alert.getButton(DialogInterface.BUTTON_NEUTRAL);

        buttonPositive.setTextColor(Color.BLACK);
        buttonNegative.setTextColor(Color.BLACK);
        buttonNeutral.setTextColor(Color.BLACK);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted, Now you can access location data", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Denied, You cannot access location data.", Toast.LENGTH_LONG).show();
                }
                break;
            case PERMISSION_CAMERA_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted, Now you can access camera", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Denied, You cannot access camera", Toast.LENGTH_LONG).show();
                }
                break;
            case PERMISSION_READ_STORAGE_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted, Now you can read data", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Denied, You cannot read data.", Toast.LENGTH_LONG).show();
                }
                break;
            case PERMISSION_WRITE_STORAGE_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted, Now you can write data", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Permission Denied, You cannot write data.", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_LOCATION:
                switch (resultCode) {
                    case Activity.RESULT_CANCELED: {
                        // The user was asked to change settings, but chose not to
                        finish();
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
        }

    }


}