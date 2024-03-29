package com.dev.lungyu.homecontroller.webapi.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by lungyu on 2/24/18.
 */

public class ServiceConnector {
    private String urlString;
    private String response;

    private static final String USER_AGENT = "Mozilla/5.0";

    public ServiceConnector(String urlString){
        this.urlString = urlString;
    }

    public void  connect(){
        connect("");
    }
    public void  connect(final String parameterStr){
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            //wr.write("admin=21&pwd=123");
            wr.write(parameterStr);
            wr.flush();
            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Process line...
                sb.append(line);
            }
            wr.close();
            rd.close();
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        response = sb.toString();
    }

    public void connectGetByToken(final String token){
        StringBuilder sb = new StringBuilder();
        try{
            URL obj = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("X-Auth-Token", token);

            int responseCode = con.getResponseCode();
            //System.out.println("\nSending 'GET' request to URL : " + url);
            //System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();

        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response = sb.toString();
    }

    public String getResponse(){
        return this.response;
    }
}
