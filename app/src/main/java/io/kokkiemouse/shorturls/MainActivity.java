package io.kokkiemouse.shorturls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import io.kokkiemouse.shorturls.ifces.ShortUrlsInterface;
import io.kokkiemouse.shorturls.shortservices.Artspin;
import io.kokkiemouse.shorturls.shortservices.YAMAD_1LIL;
import io.kokkiemouse.shorturls.threads.MyThreadkun;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<ShortUrlsInterface> UrlIFaces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UrlIFaces=new ArrayAdapter<ShortUrlsInterface>(this,R.layout.list_item_txtview,R.id.label_selector);
        UrlIFaces.add(new Artspin());
        UrlIFaces.add(new YAMAD_1LIL());
        Spinner spinnerselect=(Spinner)findViewById(R.id.servicespinner);
        spinnerselect.setAdapter(UrlIFaces);
        findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click_run_button(v);
            }
        });
        {
            String Actionkun =getIntent().getAction();
            if(Intent.ACTION_SEND.equals(Actionkun)){
                Bundle extras = getIntent().getExtras();
                if(extras != null){
                    CharSequence extdata=extras.getCharSequence(Intent.EXTRA_TEXT);
                    if(extdata != null) {
                        EditText txtkun = (EditText) findViewById(R.id.urlTextBox);
                        txtkun.setText(extdata);
                    }
                }
            }
        }
    }
    private void Click_run_button(View  v){
        EditText urltext=findViewById(R.id.urlTextBox);
        String urlkun=urltext.getText().toString();
        Spinner spinnerselect=(Spinner)findViewById(R.id.servicespinner);
        ShortUrlsInterface URLOBJ=(ShortUrlsInterface)spinnerselect.getSelectedItem();
        MyThreadkun taskkun=new MyThreadkun(URLOBJ);
        taskkun.setListener(new MyThreadkun.Listener() {
            @Override
            public void urlcalled(String shorturl) {
                /*CharSequence text = shorturl;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();*/
                Intent intent = new Intent(getApplication(),Generated_URL.class);
                intent.putExtra("GENERATED_URL",shorturl);
                startActivity(intent);
            }
        });
        taskkun.execute(urlkun);
    }
}