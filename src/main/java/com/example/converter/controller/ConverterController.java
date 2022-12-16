package com.example.converter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/converter")
public class ConverterController {

    private final RateParser rp = new RateParser();

    @GetMapping("/convert/XRPtoXMR/{XRP}")
    public String convertXRPtoXMR(@PathVariable(name = "XRP") String XRPvalue) {
        try {
            double[] rates = rp.getRates();
            double value = Double.parseDouble(XRPvalue) * rates[0];
            return XRPvalue + " XRP = " + String.format("%.8f", value / rates[1]) + " XMR";
        } catch (NumberFormatException e) {
            return "Only numbers are valid";
        }
    }

    @GetMapping("/convert/XMRtoXRP/{XMR}")
    public String convertXMRtoXRP(@PathVariable(name = "XMR") String XMRvalue) {
        try {
            double[] rates = rp.getRates();
            double value = Double.parseDouble(XMRvalue) * rates[1];
            return XMRvalue + " XMR = " + String.format("%.8f", value / rates[0]) + " XRP";
        } catch (NumberFormatException e) {
            return "Only numbers are valid";
        }
    }
}
