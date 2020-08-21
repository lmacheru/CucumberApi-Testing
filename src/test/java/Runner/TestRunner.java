package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/functional",
        glue = {"stepDefinitions"},
        plugin = {"pretty", "html:target/Destination"} ,
        monochrome = true,
        strict = true)
class TestRunner {
}
