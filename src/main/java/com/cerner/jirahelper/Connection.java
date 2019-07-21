package com.cerner.jirahelper;


import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.*;

public class Connection {

    public static String postRequest(final String uri, final String json) {
        try {
            final URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", Resources.basicAuth);

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(json);
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in .readLine()) != null) {
                response.append(output);
            } in .close();
            if (con.getResponseCode() < 300) {
                System.out.println("Request Successful.");
            } else {
                System.out.println("Request Failed");
            }
            return response.toString();

        } catch (final MalformedURLException e) {
            System.out.println("Not a proper URL");

        } catch (final IOException e) {
            System.out.println("Unknown Issue.");
            System.exit(0);
        }
        return null;
    }

    public static String getRequest(String uri) throws IOException {
        InputStream inputStream;
        URL url = new URL(uri);
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", Resources.basicAuth);
            con.setRequestProperty("Accept", "application/json");
            inputStream = con.getInputStream();
            return IOUtils.toString(inputStream, "utf-8");
        } catch (SocketException e) {
            throw new SocketException("Connection issue : " + url.toString() + e.getMessage());
        }
    }




}