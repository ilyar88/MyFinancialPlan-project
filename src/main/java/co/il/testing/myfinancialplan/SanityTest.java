package co.il.testing.myfinancialplan;
import java.io.IOException;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import utilities.TestData;
import workflows.*;
import base.BasePage;

public class SanityTest extends BasePage {
	
	public SanityTest() throws IOException {
		super();
	}

	@Feature("Registration")
	@Severity(SeverityLevel.CRITICAL)
	@Description("User register to the website with credentials.")
	@Ignore("Skip user registration.")
	@Test(description = "User register")
	public void userRegisterTest() {
		RegistrationFlow.userRegister(prop.getProperty("username"),prop.getProperty("password"));		
	}	
	
	@Feature("Login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("User login to the website with credentials.")
	@Test(priority = 1, description = "User login to website")
	public void userLoginTest() {
		LoginFlow.userLogin(prop.getProperty("username"),prop.getProperty("password"), "/auth/verify");	
		OtpFlow.typePassword("","/introduction"); //Verify redirection from the OTP page to the introduction page.
	}
	
	@Feature("Profile")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Create profile with personal details.")
	@Ignore("Skip create profile")
	@Test(priority = 2, description = "Create profile", dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void createProfileTest(String[] profile, String[] dropdowns, String[] kids) {
	    ProfileCreationFlow.createProfile(profile,dropdowns,kids);
	}
	
	@Feature("Introduction")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Open and close introduction goals.")
	@Test(priority = 3, dependsOnMethods = "userLoginTest", description = "Introduction goals")
	public void introductionTest() {
	    IntroductionFlow.introduction();
	}
	
	@Feature("Incomes")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Add incomes.")
	@Test(priority = 4, dependsOnMethods = "introductionTest", description = "Incomes", dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void addIncomesTest(String[] names, String[] amounts) {
	    IncomesFlow.addIncomes(names, amounts);
	}
	
	@Feature("Expenses")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Add month and year expenses.")
	@Test(priority = 5, dependsOnMethods = "addIncomesTest", description = "Period expenses", dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void periodExpensesTest(String categories, String[] names, String[] amounts) {
	    ExpensesFlow.addExpenses(categories, names, amounts);
	}
	
	@Feature("Expenses")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Add emergency fund.")
	@Ignore("Skip emergency fund.")
	@Test(priority = 6, description = "Emergency fund", dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void emergencyFundTest(String amount, String currentAmount, String liquidAmount, String month) {
	    ExpensesFlow.emergencyFund(amount, currentAmount, liquidAmount, month);
	}
	
	@Feature("Expenses")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Add recurring expense.")
	@Test(priority = 7, description = "recurring expense", dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void recurringExpenseTest(String[] name, String[] targetAmount, String[] currentAmount, String[] liquidAmount,
    		String[] years) {
	    ExpensesFlow.recurringExpense(name, targetAmount, currentAmount, liquidAmount,years);
	}
	
	@Feature("Expenses")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Add financial goals.")
	@Ignore("Skip financial goals.")
	@Test(priority = 8, description = "financial goals", dataProvider = "globalProvider", dataProviderClass = TestData.class)
	public void financialGoalsTest(String[] name, String[] targetAmount, String[] currentAmount, String[] liquidAmount,
    		String[] years) {
	    ExpensesFlow.financialGoals(name, targetAmount, currentAmount, liquidAmount,years);
	}
}