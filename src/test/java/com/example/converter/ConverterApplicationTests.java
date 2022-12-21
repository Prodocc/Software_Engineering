package com.example.converter;

import com.example.converter.controller.ConverterController;
import com.example.converter.controller.RateParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConverterApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    public void correctInputTest() {
        ConverterController cc = new ConverterController();

        String errorMessage = cc.convertXMRtoXRP("not number");
        Assertions.assertEquals("Only numbers are valid", errorMessage);
    }

    @Test
    public void recentlyConvertedHistoryTest() {
        ConverterController cc = new ConverterController();

        cc.convertXMRtoXRP("5");
        String firstQueryStart = "5 XMR";
        Assertions.assertEquals("5 XMR", cc.displayRecentlyConverted().substring(0, 5));
        cc.convertXRPtoXMR("3");
        String secondQueryEnd = "3 XRP";
        Assertions.assertEquals("3 XRP", cc.displayRecentlyConverted().substring(0, 5));
    }
}
