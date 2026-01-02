package co.il.testing.myfinancialplan;

import workflows.LoadTestFlow;
import java.io.IOException;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoadTest {

	public LoadTest() throws IOException {
		super();
	}
	
	@Severity(SeverityLevel.MINOR)
	@Description("Execute a load test with 10 virtual users and verify that all requests return a 200 status code.")
	@Test(description = "Load test with 10 virtual users")
	void loadTest() throws Exception {
		LoadTestFlow.loadTest();
	}
}

