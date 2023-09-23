package runner;
//junit imports
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue ="cucumber",
        tags ="",
        plugin ={
                "pretty",
                "html:build/reports/cucumber/test-result.html",
                "json:build/reports/cucumber/test-result.json"
        }
)


public class RunWebTest {
}
