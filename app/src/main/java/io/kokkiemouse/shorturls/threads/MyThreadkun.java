package io.kokkiemouse.shorturls.threads;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import io.kokkiemouse.shorturls.ifces.ShortUrlsInterface;

public class MyThreadkun extends AsyncTask<String,Integer,String> {
    private ShortUrlsInterface ifkun;
    private String result_d;
    public interface Listener {
        void urlcalled(String shorturl);
    }
    private Listener listener;


    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public MyThreadkun(ShortUrlsInterface ifk){
        super();
        ifkun=ifk;
    }

    @Override
    protected String doInBackground(String... strings) {

        return ifkun.getUrl(strings[0]);
        //return null;
    }

    @Override
    protected void onPostExecute(String s) {

        //Context context = getApplicationContext();
        if(listener != null){
            listener.urlcalled(s);
        }
    }
}
