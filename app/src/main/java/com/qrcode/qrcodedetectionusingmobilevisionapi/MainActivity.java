package com.qrcode.qrcodedetectionusingmobilevisionapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.qrcode.QRCodeCaptureActivity;

public class MainActivity extends AppCompatActivity {
    private final int QRCODE_ACTIVITY_REQUEST_ID = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button = (Button) findViewById(R.id.scan_qrcode_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBarCodeCaptureActivity();
            }
        });

    }

    void launchBarCodeCaptureActivity() {
        Intent intent = new Intent(this, QRCodeCaptureActivity.class);
        intent.putExtra(QRCodeCaptureActivity.AutoFocus, true);
        intent.putExtra(QRCodeCaptureActivity.UseFlash, false);
        startActivityForResult(intent, QRCODE_ACTIVITY_REQUEST_ID);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QRCODE_ACTIVITY_REQUEST_ID) {
            if (data != null) {
                Barcode barcode = data.getParcelableExtra("BAR_CODE_OBJECT");
                TextView textView = (TextView) findViewById(R.id.qrcode);
                textView.setText("QRCode is " + barcode.rawValue);
                Toast.makeText(this, "QRCode is " + barcode.rawValue, Toast.LENGTH_LONG);
            } else {
                Toast.makeText(this, "Somthing went wrong", Toast.LENGTH_LONG);
            }

        }
    }
}
