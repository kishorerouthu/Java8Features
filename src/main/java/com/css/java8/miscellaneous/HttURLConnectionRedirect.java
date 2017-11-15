package com.css.java8.miscellaneous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * If a server is redirected from the original URL to another URL, the response code should be
 * 301: Moved Permanently or 302: Temporary Redirect. And you can get the new redirected url by
 * reading the "Location" header of HTTP response headers.
 *
 * @author Kishore Routhu on 15/11/17 11:16 AM.
 */
public class HttURLConnectionRedirect {

    public static void main(String[] args) throws IOException {
        String url = "http://external.connectpoint.flydubai.com/v3/ConnectPoint.Reservation.svc?xsd=xsd7";

        URL connectionURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) connectionURL.openConnection();
        connection.setReadTimeout(5000);
        connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
        connection.addRequestProperty("User-Agent", "Mozilla");
        connection.addRequestProperty("Referer", "google.com");

        System.out.println("Request URL ... " + url);

        int status = connection.getResponseCode();
        boolean redirect = (status != HttpURLConnection.HTTP_OK) &&  (status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_MOVED_TEMP);

        if (redirect) {
            //get the redirection url from the location header
            String newUrl = connection.getHeaderField("Location");

            //get the cookie if need, for login
            String cookie = connection.getHeaderField("Set-Cookie");

            //open the connection again with new url
            connection = (HttpURLConnection) new URL(newUrl).openConnection();
            System.out.println("Redirecting to URL : " + newUrl);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder xmlBuilder = new StringBuilder();
        while ((line = br.readLine()) != null) {
            xmlBuilder.append(line);
        }

        System.out.println("XML Content : \n " + xmlBuilder.toString());
        System.out.println("Done..!");

    }
}
