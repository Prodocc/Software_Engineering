package com.example.converter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/converter")
public class ConverterController {

    @GetMapping("/convert/XRPtoXMR/{XRP}")
    public String convertXRPtoXMR(@PathVariable(name = "XRP") String XRPvalue) {
        double rate0 = 10;
        double rate1 = 5;
        double value = Double.parseDouble(XRPvalue) * rate0;
        return XRPvalue + " XRP = " + String.format("%.8f", value / rate1) + " XMR";
    }

    @GetMapping("/convert/XMRtoXRP/{XMR}")
    public String convertXMRtoXRP(@PathVariable(name = "XMR") String XMRvalue) {
        double rate0 = 10;
        double rate1 = 5;
        double value = Double.parseDouble(XMRvalue) * rate1;
        return XMRvalue + " XMR = " + String.format("%.8f", value / rate0) + " XRP";
    }
}
