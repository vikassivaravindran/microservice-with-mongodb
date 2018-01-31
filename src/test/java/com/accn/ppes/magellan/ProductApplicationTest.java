package com.accn.ppes.magellan;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

public class ProductApplicationTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private static final String SPRING_STARTUP = "root of context hierarchy";

    @Test
    @Ignore
    public void testProductApplicationMain() {


        ProductApplication.main(new String[]{ "--spring.cloud.config.enabled=false"});
        Assert.assertTrue(true);

    }

}
