package com.apparel.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.logging.Logger;
import com.google.zxing.integration.android.IntentIntegrator;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import javax.xml.transform.Result;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission_group.CAMERA;

public class QrScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;


    String user_location,selected_gatename;



    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);


        setContentView(R.layout.code_scannerr);

        // Programmatically initialize the scanner view
        mScannerView = findViewById(R.id.code_scanner_view);
        // Set the scanner view as the content view


        Bundle extras = getIntent().getExtras();




        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                mScannerView.setResultHandler(QrScanner.this);
                mScannerView.startCamera();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

            }
        })
                .check();


    }

    @Override
    protected void onDestroy() {

        mScannerView.stopCamera();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }

  /*  @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        // Prints scan results
      /*  Logger.verbose("result", rawResult.getText());
        // Prints the scan format (qrcode, pdf417 etc.)
        Logger.verbose("result", rawResult.getBarcodeFormat().toString());
        //If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
        Intent intent = new Intent();
        intent.putExtra(AppConstants.KEY_QR_CODE, rawResult.getText());
        setResult(RESULT_OK, intent);
        finish();*/
    //}*/

    @Override
    public void handleResult(com.google.zxing.Result result) {

        Log.e("handler", result.getText()); // Prints scan results
        Log.e("handler", result.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        // show the scanner result into dialog box.

        Intent result_intent = new Intent();
        result_intent.putExtra("empID",result.getText());
        result_intent.putExtra("from_intent","QrCodeScannerDriver");
        setResult(RESULT_OK, result_intent);
        finish();
        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Vehicle Number: ");
        builder.setMessage(result.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
*/
        // If you would like to resume scanning, call this method below:

        if (mScannerView != null) {
            mScannerView.startCamera();
            mScannerView.setAutoFocus(true); //not necessary
            mScannerView.resumeCameraPreview(this);
        }

    }



    @Override
    public void onBackPressed() {

        //Code here for testing purposes, to be deleted, remind me please, if I forget.:):)
        /*Intent result_intent = new Intent();
        result_intent.putExtra("empID","8251");
        result_intent.putExtra("from_intent","QrCodeScannerDriver");
        setResult(RESULT_OK, result_intent);
        finish();*/
    }
}
