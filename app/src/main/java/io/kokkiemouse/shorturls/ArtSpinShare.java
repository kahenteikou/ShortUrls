package io.kokkiemouse.shorturls;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import io.kokkiemouse.shorturls.ifces.ShortUrlsInterface;
import io.kokkiemouse.shorturls.shortservices.Artspin;
import io.kokkiemouse.shorturls.threads.MyThreadkun;

public class ArtSpinShare  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String Actionkun =getIntent().getAction();
        if(Intent.ACTION_SEND.equals(Actionkun)){
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                CharSequence extdata=extras.getCharSequence(Intent.EXTRA_TEXT);
                if(extdata != null) {
                    ShortUrlsInterface URLOBJ=new Artspin();
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
                    taskkun.execute(extdata.toString());
                }
            }
        }
    }
}
