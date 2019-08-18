package com.dev.lungyu.homecontroller.webapi.base;

import java.io.DataOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by lungyu on 2/24/18.
 */

public class WebAPITools {
    private static final String USER_AGENT = "Mozilla/5.0";

    public static int send_post(String url,String urlParameters,String token) throws Exception {

        //String url = "https://algdb.csie.ncyu.edu.tw:8080/api/login?user=admin&pwd=123";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("X-Auth-Token", token);

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        return responseCode;
    }
}
