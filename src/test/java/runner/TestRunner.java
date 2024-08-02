package com.framework.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.framework.stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true,
        publish = false
)
public class TestRunner {
}
