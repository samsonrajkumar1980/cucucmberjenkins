package runnerClass;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions

(
		features="src/test/resources/features/postApi.feature",
		plugin="com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		glue="stepDef",
		tags="@Regression",dryRun=false)


public class cucumberRunner {

}