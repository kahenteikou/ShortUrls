package io.kokkiemouse.shorturls.shortservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import io.kokkiemouse.shorturls.ifces.ShortUrlsInterface;

public class YAMAD_1LIL implements ShortUrlsInterface {
    public String getName(){
        return "1lil.li";
    }
    public String getIconPath(){
        return "";
    }
    public String getUrl(String url){
        try{
            URL requrl=new URL("https://1lil.li/p/");
            HttpURLConnection con = (HttpURLConnection)requrl.openConnection();
            con.setRequestMethod("POST");
            con.setInstanceFollowRedirects(false);
            con.setDoOutput(true);
            con.connect();
            String send_cont="l=" + url;
            try(
                OutputStream os=con.getOutputStream()){
                os.write(send_cont.getBytes(StandardCharsets.UTF_8));
                os.flush();
            }catch (IOException e){
                e.printStackTrace();
            }
            int responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_CREATED){
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
