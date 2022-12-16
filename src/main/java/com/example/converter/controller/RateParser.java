package com.example.converter.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RateParser {
    private final static String XRP = "https://api.coincap.io/v2/assets/xrp";
    private final static String XMR = "https://api.coincap.io/v2/assets/monero";

    public double[] getRates() {
        StringBuilder responseData = new StringBuilder();
        double[] rates = new double[2];
        JSONParser parse = new JSONParser();

        try {
            URL url = new URL(XRP);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    responseData.append(line);
                }
            }
            JSONObject data_obj = (JSONObject) parse.parse(responseData.toString());
            JSONObject obj = (JSONObject) data_obj.get("data");
            rates[0] = Double.parseDouble(obj.get("priceUsd").toString());
            con.disconnect();
            responseData.setLength(0);

            url = new URL(XMR);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            responseCode = con.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    responseData.append(line);
                }
            }
            data_obj = (JSONObject) parse.parse(responseData.toString());
            obj = (JSONObject) data_obj.get("data");
            rates[1] = Double.parseDouble(obj.get("priceUsd").toString());
            con.disconnect();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return rates;
    }
}
