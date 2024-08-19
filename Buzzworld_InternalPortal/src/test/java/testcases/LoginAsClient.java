import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.util.Assert;

import commonUtils.App;

public class LoginAsClient extends App{
	@Test
	public void loginAsClientTest() {
		LoginAsClientPage lp = new LoginAsClientPage();
		App.login();
		boolean res = lp.loginAsClient();
		Assertion.assertTrue(res);
	}
}
