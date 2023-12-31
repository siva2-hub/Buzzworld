package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonUtils.App;
import libraries.AdminPages;

public class AdminPages_TestCases extends App
{
	AdminPages admin = new AdminPages();
	String env = "";
	//@Test(enabled = false)
	@Test(priority = 1)
	public void testCase1() throws Exception 
	{
		if (driver.getCurrentUrl().contains("staging")) {
			env = "Stage Instance";
		} else {
			env = "QA Instance";
		}
		admin.addAndEditsInAdminPages("Admin", "Account Types", "001", env);
		admin.addAndEditsInAdminPages("Admin", "Branches", "002", env);
		admin.addAndEditsInAdminPages("Admin", "Classifications", "003", env);
		//admin.addAndEditsInAdminPages("Admin", "Contact Types", "004", env);
		admin.addAndEditsInAdminPages("Admin", "Industries", "005", env);
		admin.addAndEditsInAdminPages("Admin", "Quote Types", "006", env);
		admin.addAndEditsInAdminPages("Admin", "Regions", "007", env);
		admin.addAndEditsInAdminPages("Admin", "Sales Potential", "008", env);
		admin.addAndEditsInAdminPages("Admin", "Territories", "009", env);
		admin.addAndEditsInAdminPages("Admin", "Vendors", "010", env);
		admin.addAndEditsInAdminPages("Admin", "Zip Codes", "011", env);
		admin.verifyQuoteApproval("Admin", "Quote Approval", "012", "Approve", env, "competitions");
		admin.verifyQuoteApproval("Admin", "Terms Conditions", "013", "Update", env, "website");
	}
	@Test(enabled = false)
//	@Test(priority = 2)
	public void testCase2() throws Exception {
		App.login();
		boolean res = admin.addAndEditsInAdminPages("Admin", "Branches", "002", env);
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
		App.logout();
	}
	@Test(enabled = false)
//	@Test(priority = 3)
	public void testCase3() throws Exception {
		App.login();
		boolean res = admin.addAndEditsInAdminPages("Admin", "Classifications", "003", env);
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
		App.logout();
	}
	@Test(enabled = false)
//	@Test(priority = 4)
	public void testCase4() throws Exception {
		App.login();
		boolean res = admin.addAndEditsInAdminPages("Admin", "Contact Types", "004", env);
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
		App.logout();
	}
	@Test(enabled = false)
//	@Test(priority = 5)
	public void testCase5() throws Exception {
		App.login();
		boolean res = admin.addAndEditsInAdminPages("Admin", "Industries", "005", env);
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
		App.logout();
	}
	@Test(enabled = false)
//	@Test(priority = 6)
	public void testCase6() throws Exception {
		App.login();
		boolean res = admin.addAndEditsInAdminPages("Admin", "Quote Types", "006", env);
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
		App.logout();
	}
	@Test(enabled = false)
//	@Test(priority = 7)
	public void testCase7() throws Exception {
		App.login();
		boolean res = admin.addAndEditsInAdminPages("Admin", "Regions", "007", env);
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
		App.logout();
	}
	@Test(enabled = false)
//	@Test(priority = 8)
	public void testCase8() throws Exception {
		App.login();
		boolean res = admin.addAndEditsInAdminPages("Admin", "Sales Potential", "008", env);
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
		App.logout();
	}
	@Test(enabled = false)
//	@Test(priority = 9)
	public void testCase9() throws Exception {
		App.login();
		boolean res = admin.addAndEditsInAdminPages("Admin", "Territories", "009", env);
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
		App.logout();
	}
	@Test(enabled = false)
//	@Test(priority = 10)
	public void testCase10() throws Exception {
		App.login();
		boolean res = admin.addAndEditsInAdminPages("Admin", "Vendors", "010", env);
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
		App.logout();
	}
	@Test(enabled = false)
//	@Test(priority = 11)
	public void testCase11() throws Exception {
		App.login();
		boolean res = admin.addAndEditsInAdminPages("Admin", "Zip Codes", "011", env);
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
	}
	@Test(enabled = false)
//	@Test(priority = 12)
	public void testCase12() throws Exception {
		App.login();
		boolean res = admin.verifyQuoteApproval("Admin", "Quote Approval", "012", "Approve", env, "competitions");
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
	}
	@Test(enabled = false)
//	@Test(priority = 13)
	public void testCase13() throws Exception {
		//			App.login();
		boolean res = admin.verifyQuoteApproval("Admin", "Terms Conditions", "013", "Update", env, "website");
		if (res) 
		{} else 
		{
			App.logout();
		}
		Assert.assertTrue(res);
	}
}
