package testcases;

import org.testng.annotations.Test;

import commonUtils.App;
import libraries.Permissions_QuotesPages;


public class PermissionsTestCases_QuotesModule extends App
{
	Permissions_QuotesPages quotes = new Permissions_QuotesPages();
	//	@Test(enabled = false)
	@Test(priority =1)
	public void testCase1() throws Exception {
		String env = "";
		if (driver.getCurrentUrl().contains("staging")) {
			env = "Stage Instance";
		} else {
			env = "QA Instance";
		}
		//Check the Quotes as None permission
		quotes.verifyQuotesPermissionAsNone("PERMNS_053_Verify_QuoteForParts_Permission_As_None", "Admin", "Users", "Quote for Parts/Repairs",3, 2, env);
		//Check the Quotes as None permission
		quotes.verifyQuotesPermissionAsNone("PERMNS_054_Verify_QuoteForParts_Permission_As_View", "Admin", "Users", "Quote for Parts/Repairs",3, 3, env);
		//Check the Pay Terms as No permission in Quotes
		quotes.verifyPayTermsPermissionAsYes_Quotes("PERMNS_067_Verify_Pay Terms_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs", 5, 2, env);
		//Check the Pay Terms as Yes permission in Quotes
		quotes.verifyPayTermsPermissionAsYes_Quotes("PERMNS_068_Verify_Pay Terms__Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs", 5, 1, env);
		//Check the GP < 23 % Approval permission as in No in quotes
		quotes.verify_lessthan_23_approval("PERMNS_0_Verify_GP < 23 % Approval_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",4, 2, env);
		//Check the GP < 23 % Approval permission as in yes in quotes
		quotes.verify_lessthan_23_approval("PERMNS_0_Verify_GP < 23 % Approval_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",4, 1, env);
		//Check the Edit IIDM Cost as No in Quotes
		quotes.verifyEditIIDMCostPermissionAsYes_Quotes("PERMNS_059_Verify_Edit_IIDM_Cost_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",3, 2, env);
		//Check the Edit IIDM Cost as Yes in Quotes
		quotes.verifyEditIIDMCostPermissionAsYes_Quotes("PERMNS_060_Verify_Edit_IIDM_Cost_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",3, 1, env);
		//Check the Revise Quote as No permission in quotes
		quotes.verifyReviseQuotePermissionAsYes_Quotes("PERMNS_063_Verify_ReviseQuote_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",9, 2, env);
		//Check the Revise Quote as Yes permission in quotes
		quotes.verifyReviseQuotePermissionAsYes_Quotes("PERMNS_064_Verify_ReviseQuote_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",9, 1, env);
		//Check the Quote approval limit as None permission in quotes
		quotes.verifyQuoteApprovalLimitPermissionAsNone_Quotes("PERMNS_069_Verify_Quote Approval Limit__Permission_As_None_Quotes", "Admin", "Users", "Quote for Parts/Repairs","None", env);
		//Check the Quote approval limit as above 50k permission in quotes
		quotes.verifyQuoteApprovalLimitPermissionAsNone_Quotes("PERMNS_070_Verify_Quote Approval Limit__Permission_As_$50k and above_Quotes", "Admin", "Users", "Quote for Parts/Repairs","$50k and above", env);
		//Check the Send to customer as No permission
		quotes.verifySendToCustomerPermissionAsYes_Quotes("PERMNS_065_Verify_Send To Customer_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",10, 2, env);
		//Check the Send to customer as Yes permission
		quotes.verifySendToCustomerPermissionAsYes_Quotes("PERMNS_066_Verify_Send To Customer_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",10, 1, env);
		//Check the Create sales order as no permission in quotes
		quotes.verifyCreateSalesOrderPermissionAsYes("PERMNS_055_Verify_CreateSalesOrder_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",2, 2, env);
		//Check the Create sales order as yes permission in quotes
		quotes.verifyCreateSalesOrderPermissionAsYes("PERMNS_056_Verify_CreateSalesOrder_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",2, 1, env);
		//Check the QuoteClose Permission As No in Quotes
		quotes.verifyQuoteClosePermissionAsYes_Quotes("PERMNS_057_Verify_QuoteClose_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",7, 2, env);
		//Check the QuoteClose Permission As Yes in Quotes
		quotes.verifyQuoteClosePermissionAsYes_Quotes("PERMNS_058_Verify_QuoteClose_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",7, 1, env);
		//Check the Re Open Permission As No in Quotes
		quotes.verifyQuoteReOpenPermissionAsYes_Quotes("PERMNS_061_Verify_ReOpen_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",8, 2, env);
		//Check the Re Open Permission As Yes in Quotes
		quotes.verifyQuoteReOpenPermissionAsYes_Quotes("PERMNS_062_Verify_ReOpen_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",8, 1, env);
	}

	@Test(enabled = false)
	//	@Test(priority = 2)
	public void testCase5() throws Exception 
	{
		String env = "";
		if (driver.getCurrentUrl().contains("staging")) {
			env = "Stage Instance";
		} else {
			env = "QA Instance";
		}
		//Check the QuoteClose Permission As No in Quotes
		quotes.verifyQuoteClosePermissionAsYes_Quotes("PERMNS_057_Verify_QuoteClose_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",7, 2, env);
		//Check the QuoteClose Permission As Yes in Quotes
		quotes.verifyQuoteClosePermissionAsYes_Quotes("PERMNS_058_Verify_QuoteClose_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",7, 1, env);
		//Check the Re Open Permission As No in Quotes
		quotes.verifyQuoteReOpenPermissionAsYes_Quotes("PERMNS_061_Verify_ReOpen_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",8, 2, env);
		//Check the Re Open Permission As Yes in Quotes
		quotes.verifyQuoteReOpenPermissionAsYes_Quotes("PERMNS_062_Verify_ReOpen_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",8, 1, env);
	}

	//	@Test(enabled = false)
	//	//	@Test(priority =2)
	//	public void testCase2() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyQuotesPermissionAsNone("PERMNS_054_Verify_QuoteForParts_Permission_As_View", "Admin", "Users", "Quote for Parts/Repairs",3, 3);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 3)
	//	public void testCase3() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyCreateSalesOrderPermissionAsYes("PERMNS_055_Verify_CreateSalesOrder_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",2, 2);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 4)
	//	public void testCase4() throws Exception 
	//	{
	//		App.login();
	//		boolean res = quotes.verifyCreateSalesOrderPermissionAsYes("PERMNS_056_Verify_CreateSalesOrder_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",2, 1);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//
	//	@Test(enabled = false)
	//	//	@Test(priority = 6)
	//	public void testCase6() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyQuoteClosePermissionAsYes_Quotes("PERMNS_058_Verify_QuoteClose_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",6, 1);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 7)
	//	public void testCase7() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyEditIIDMCostPermissionAsYes_Quotes("PERMNS_059_Verify_Edit_IIDM_Cost_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",3, 2);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 8)
	//	public void testCase8() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyEditIIDMCostPermissionAsYes_Quotes("PERMNS_060_Verify_Edit_IIDM_Cost_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",3, 1);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 9)
	//	public void testCase9() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyQuoteReOpenPermissionAsYes_Quotes("PERMNS_061_Verify_ReOpen_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",7, 2);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 10)
	//	public void testCase10() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyQuoteReOpenPermissionAsYes_Quotes("PERMNS_062_Verify_ReOpen_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",7, 1);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 11)
	//	public void testCase11() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyReviseQuotePermissionAsYes_Quotes("PERMNS_063_Verify_ReviseQuote_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",8, 2);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 12)
	//	public void testCase12() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyReviseQuotePermissionAsYes_Quotes("PERMNS_064_Verify_ReviseQuote_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",8, 1);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 13)
	//	public void testCase13() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifySendToCustomerPermissionAsYes_Quotes("PERMNS_065_Verify_Send To Customer_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs",9, 2);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 14)
	//	public void testCase14() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifySendToCustomerPermissionAsYes_Quotes("PERMNS_066_Verify_Send To Customer_Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs",9, 1);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 15)
	//	public void testCase15() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyPayTermsPermissionAsYes_Quotes("PERMNS_067_Verify_Pay Terms_Permission_As_No_Quotes", "Admin", "Users", "Quote for Parts/Repairs", 4, 2);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 16)
	//	public void testCase16() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyPayTermsPermissionAsYes_Quotes("PERMNS_068_Verify_Pay Terms__Permission_As_Yes_Quotes", "Admin", "Users", "Quote for Parts/Repairs", 4, 1);
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 17)
	//	public void testCase17() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyQuoteApprovalLimitPermissionAsNone_Quotes("PERMNS_069_Verify_Quote Approval Limit__Permission_As_None_Quotes", "Admin", "Users", "Quote for Parts/Repairs","None");
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//		App.logout();
	//	}
	//	@Test(enabled = false)
	//	//	@Test(priority = 18)
	//	public void testCase18() throws Exception {
	//		App.login();
	//		boolean res = quotes.verifyQuoteApprovalLimitPermissionAsNone_Quotes("PERMNS_070_Verify_Quote Approval Limit__Permission_As_$50k and above_Quotes", "Admin", "Users", "Quote for Parts/Repairs","$50k and above");
	//		if(res) {
	//		}else {
	//			App.logout();
	//		}
	//		Assert.assertTrue(res);
	//	}

}
