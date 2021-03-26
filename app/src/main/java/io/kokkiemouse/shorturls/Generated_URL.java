package io.kokkiemouse.shorturls;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;

public class Generated_URL extends AppCompatActivity {
    Bitmap bitmap=null;
    String shorturl =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated__u_r_l);
        Intent intent = getIntent();
        shorturl = intent.getStringExtra("GENERATED_URL");
        CharSequence text = shorturl;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
        toast.show();
        TextView viewURLNIKI=(TextView)findViewById(R.id.urlBoxShort);
        viewURLNIKI.setText(shorturl);
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        int size= (int) (180 * metrics.density);
        try{
            BarcodeEncoder be=new BarcodeEncoder();
            bitmap=be.encodeBitmap(shorturl, BarcodeFormat.QR_CODE,size,size);
            ImageView imageViewQrCode =(ImageView)findViewById(R.id.Image_QRView);
            imageViewQrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        findViewById(R.id.Image_QRView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Share_kun();
            }
        });
        findViewById(R.id.Share_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Share_kun();
            }
        });
    }
    private void Share_kun(){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,shorturl);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, null));
    }
}