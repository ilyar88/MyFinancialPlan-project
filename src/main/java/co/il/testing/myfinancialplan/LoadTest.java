package co.il.testing.myfinancialplan;

import workflows.LoadTestFlow;
import java.io.IOException;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoadTesting {

	public LoadTesting() throws IOException {
		super();
	}
	
	@Severity(SeverityLevel.MINOR)
	@Description("Run a load test with 10 virtual users and verify that the request returns a 200 status code.")
	@Test(description = "Load test with 10 virtual users")
	void loadTest() throws Exception {
		LoadTestFlow.loadTest();
	}
}

