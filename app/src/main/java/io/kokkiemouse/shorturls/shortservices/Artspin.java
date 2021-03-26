package io.kokkiemouse.shorturls.shortservices;

import android.util.Xml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import io.kokkiemouse.shorturls.ifces.ShortUrlsInterface;

public class Artspin implements ShortUrlsInterface {
    public String getName(){
        return "ArtSpin";
    }
    public String getIconPath(){
        return "";
    }
    public String getUrl(String url){
        try{
            URL requrl=new URL("https://u.artspin.jp/p.php?url=" + URLEncoder.encode(url, "UTF_8"));
            HttpURLConnection con = (HttpURLConnection)requrl.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream in= con.getInputStream();
                String encoding=con.getContentEncoding();
                if(encoding == null){
                    encoding="UTF-8";
                }
                StringBuffer result=new StringBuffer();
                final InputStreamReader ir=new InputStreamReader(in,encoding);
                final BufferedReader bufr=new BufferedReader(ir);
                String line=null;
                while((line = bufr.readLine()) != null){
                    result.append(line);
                }
                bufr.close();
                ir.close();
                in.close();
                return result.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }
    @Override
    public String toString(){
        return getName();
    }
}
