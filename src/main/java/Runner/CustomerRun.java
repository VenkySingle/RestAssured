package Runner;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@Test
@CucumberOptions(
		features ={ "./src/test/java/Features/1_Customer.feature",
				"./src/test/java/Features/2_Account.feature"
		},
		glue = "steps",
	    plugin = {
			            "pretty",
			            "html:target/customer.html"
	},
		publish = true,
		monochrome = true
		
		)

public class CustomerRun extends AbstractTestNGCucumberTests {
	
	
}
