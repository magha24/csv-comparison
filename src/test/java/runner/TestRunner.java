package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {"html:target/cucumber-html-report",
                "json:target/cucumber.json"},
        features = "src/test/resources/features",
        glue = "step_definitions",
        tags = "@testCase1 or @testCase2 or @testCase3 or @testCase4",
        dryRun = false,
        monochrome = true
)
public class TestRunner {
}
/*
 plugin = {"html:target/cucumber-html-report",
        "json:target/cucumber.json"},
        features="src/test/resources/ui_features",
        glue="ui_automation.step_definitions",
        tags={"@upload-download", "not @wip"}, // selenium 4 -> "@run not @wip"
        dryRun = false,
        monochrome = true
 */