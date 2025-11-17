package utilities;

import java.lang.reflect.Method;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;

public class TestData {
    
    @DataProvider(name = "globalProvider")
    public static Object[][] data(Method m) {
        switch (m.getName()) {
        /** Login details:
        * 1) Username
        * 2) Password
        * 3) Uri */
        case "resetPassword_TDD":
            return new Object[][]{
                {
                	new String[]{"ilyatest88@gmail.com"},                      
                	new String[]{"Welcome$","12345","12345678","Passwrd!","Welcome!38"}, 										   
                	new String[]{"/auth/new-password/","/auth/new-password/","/auth/new-password/",
                			"/auth/new-password/","/auth/login/"}  								       										                      
    	        }
            };
        case "userLoginTest_TDD":
            return new Object[][]{
                {"irahmilevich$gmail.com", "Welcome!", "/auth/login/"},
                {System.getenv("USERNAME"), "12345", "/auth/login/"},
                {"irahmilevich@gmail.c2m", "12345678", "/auth/login/"},
                {"irahmilevichgmail.com", "Welcome!12", "/auth/login/"},
                {System.getenv("USERNAME"), "Passwrd!", "/auth/login/"},
                {"", "", "/auth/login/"},
                {System.getenv("USERNAME"), System.getenv("PASSWORD"), "/auth/verify"}
            };
        case "otpTest_TDD":
            return new Object[][]{
                {"000000", "/auth/verify"},
                { "999999", "/auth/verify"},
                {"AAAAAA","/auth/verify"},
                {"!@#$%^", "/auth/verify"},
            	{"10 Minutes", "/auth/verify"},
            	{"11 Minutes", "/auth/verify"},
            	{"5 Minutes", "/auth/verify"}
            };
        /** Profile details:
        * 1) Marital status
        * 2) Gender
        * 3) Sole breadwinner
        * 4) Has children?
        * 5) Region
        * 6) City type
        * 7) Residence type
        * 8) Number of children
        * 9) Children’s ages (array) */
        case "createProfileTest":
        	return new Object[][]{
            {
            	new String[]{"Ilya","Rahmilevich","37"},                      
            	new String[]{"נשוי/ה","זכר","כן","כן","מרכז","עיר גדולה","מגורים בבעלות"}, 										   
            	new String[]{"1","1"}  								       										                      
	        }
        };
        /** Income details:
         * 1) Name of the income
         * 2) Amount */
        case "addIncomesTest":
        	return new Object[][]{
        		{"Work", "7000"},
        		{"Work 2", "2000"},
        		{"Capital market", "1000"},
        		{"Scholarships", "2000"},
        		{"Allowances", "2000"}
            };
            default:
                throw new SkipException("No data for method: " + m.getName());
        }
    }
}
