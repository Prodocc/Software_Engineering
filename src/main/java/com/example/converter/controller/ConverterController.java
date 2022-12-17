package com.example.converter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayDeque;
import java.util.Iterator;

@RestController
@RequestMapping("/converter")
public class ConverterController {

    private final RateParser rp = new RateParser();
    private final int maxRecentlyConvertedSize = 10;
    private final ArrayDeque<String> recentlyConverted = new ArrayDeque<>(maxRecentlyConvertedSize);
    private final StringBuilder sb = new StringBuilder();

    @GetMapping("/convert/XRPtoXMR/{XRP}")
    public String convertXRPtoXMR(@PathVariable(name = "XRP") String XRPvalue) {
        try {
            double[] rates = rp.getRates();
            double value = Double.parseDouble(XRPvalue) * rates[0];
            String resultStr = XRPvalue + " XRP = " + String.format("%.8f", value / rates[1]) + " XMR";
            if (recentlyConverted.size() == maxRecentlyConvertedSize) {
                recentlyConverted.removeLast();
            }
            recentlyConverted.push(resultStr);
            return resultStr;
        } catch (NumberFormatException e) {
            return "Only numbers are valid";
        }
    }

    @GetMapping("/convert/recentlyConverted")
    public String displayRecentlyConverted() {
        sb.setLength(0);
        Iterator<String> iterator = recentlyConverted.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append("<br>");
        }
        return sb.toString();
    }

    @GetMapping("/convert/XMRtoXRP/{XMR}")
    public String convertXMRtoXRP(@PathVariable(name = "XMR") String XMRvalue) {
        try {
            double[] rates = rp.getRates();
            double value = Double.parseDouble(XMRvalue) * rates[1];
            String resultStr = XMRvalue + " XMR = " + String.format("%.8f", value / rates[0]) + " XRP";
            if (recentlyConverted.size() == maxRecentlyConvertedSize) {
                recentlyConverted.removeLast();
            }
            recentlyConverted.push(resultStr);
            return resultStr;
        } catch (NumberFormatException e) {
            return "Only numbers are valid";
        }
    }
}
