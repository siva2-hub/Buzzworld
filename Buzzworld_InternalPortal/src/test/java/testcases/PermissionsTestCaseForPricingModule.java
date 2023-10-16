package testcases;

import org.testng.annotations.Test;

import commonUtils.App;
import libraries.Permissions_PricingPages;

public class PermissionsTestCaseForPricingModule extends App
{
	Permissions_PricingPages p = new Permissions_PricingPages();
	//	@Test(enabled = false)
	String env = "";
	@Test(priority = 35)
	public void PERMNS_035_Verify_Pricing_Permission_As_None() throws Exception{
		//Testing environment
		if (driver.getCurrentUrl().contains("staging")) {
			env = "stage Instance";
		} else {
			env = "QA Instance";
		}
		//Check the Pricing as None permission 
		boolean res = p.verifyPricingermissionsAsNone("PERMNS_035_Verify_Pricing_Permission_As_None", "Admin", "Users", "Pricing", 2, env);
		App.check_result(res);
		//		//Check the Discount code as None permission
		//		p.verifyPricingermissionsAsNone("PERMNS_036_Verify_Discount Codes_Permission_As_None", "Admin", "Users", "Discount Codes", 2, env);
		//		//Check the Non Standard Pricing as None permission
		//		p.verifyPricingermissionsAsNone("PERMNS_037_Verify_Non Standard Pricing_Permission_As_None", "Admin", "Users", "Non Standard Pricing", 2, env);
		//		//Check Export as Yes permission in Pricing
		//		p.verifyPricingExportImportPermissions("PERMNS_038_Verify_Export_Permission_As_Yes_In_Pricing", "Admin", "Users", "Pricing", 1, 3, "Export", 2, env);
		//		//Check Export as No permission in Pricing
		//		p.verifyPricingExportImportPermissions("PERMNS_039_Verify_Export_Permission_As_NoIn_Pricing", "Admin", "Users", "Pricing", 2, 3, "Export", 2, env);
		//		//Check the Import as Yes permission in pricing
		//		p.verifyPricingExportImportPermissions("PERMNS_040_Verify_Import_Permission_As_Yes_In_Pricing", "Admin", "Users", "Pricing", 1, 3, "Import", 3, env);
		//		//Check the Import as No permission in pricing
		//		p.verifyPricingExportImportPermissions("PERMNS_041_Verify_Import_Permission_As_No_In_Pricing", "Admin", "Users", "Pricing", 2, 3, "Import", 3, env);
		//		//Check Export as Yes permission in Discount codes
		//		p.verifyPricingExportImportPermissions("PERMNS_042_Verify_Export_Permission_As_Yes_In_Discount Codes", "Admin", "Users", "Discount Codes", 1, 1, "Export", 2, env);
		//		//Check Export as No permission in Discount codes
		//		p.verifyPricingExportImportPermissions("PERMNS_043_Verify_Export_Permission_As_No_In_Discount Codes", "Admin", "Users", "Discount Codes", 2, 1, "Export", 2, env);
		//		//Check Export as Yes permission in Non standard pricing
		//		p.verifyPricingExportImportPermissions("PERMNS_044_Verify_Export_Permission_As_Yes_In_Non Standard Pricing", "Admin", "Users", "Non Standard Pricing", 1, 2, "Export", 2, env);
		//		//Check Export as No permission in Non standard pricing
		//		p.verifyPricingExportImportPermissions("PERMNS_045_Verify_Export_Permission_As_No_In_Non Standard Pricing", "Admin", "Users", "Non Standard Pricing", 2, 2, "Export", 2, env);
		//		//Check the Pricing as View permission
		//		p.verifyPricingPermissionsAsView("PERMNS_046_Verify_Pricing_Permission_As_View", "Admin", "Users", "Pricing",3, env);
		//		//Check the Discount codes as View permission
		//		p.verifyPricingPermissionsAsView("PERMNS_047_Verify_Discount Codes_Permission_As_View", "Admin", "Users", "Discount Codes", 3, env);
		//		//Check the Non Standard Pricing as View permission
		//		p.verifyPricingPermissionsAsView("PERMNS_048_Verify_Non Standard Pricing_Permission_As_View", "Admin", "Users", "Non Standard Pricing", 3, env);

	}
	//@Test(enabled = false)
	@Test(priority = 36)
	public void PERMNS_036_Verify_Discount_Codes_Permission_As_None() throws Exception{
		
		boolean res = p.verifyPricingermissionsAsNone("PERMNS_036_Verify_Discount Codes_Permission_As_None", "Admin", "Users", "Discount Codes", 2, env);
		App.check_result(res);
	}

	//@Test(enabled = false)
	@Test(priority = 37)
	public void PERMNS_037_Verify_Non_Standard_Pricing_Permission_As_None() throws Exception{
		
		boolean res = p.verifyPricingermissionsAsNone("PERMNS_037_Verify_Non Standard Pricing_Permission_As_None", "Admin", "Users", "Non Standard Pricing", 2, env);
		App.check_result(res);
	}
	//@Test(enabled = false)
	@Test(priority = 38)
	public void PERMNS_038_Verify_Export_Permission_As_Yes_In_Pricing() throws Exception {
		
		boolean res = p.verifyPricingExportImportPermissions("PERMNS_038_Verify_Export_Permission_As_Yes_In_Pricing", "Admin", "Users", "Pricing", 1, 3, "Export", 2, env);
		App.check_result(res);
	}
	//@Test(enabled = false)
	@Test(priority = 39)
	public void PERMNS_039_Verify_Export_Permission_As_NoIn_Pricing() throws Exception {
		
		boolean res = p.verifyPricingExportImportPermissions("PERMNS_039_Verify_Export_Permission_As_NoIn_Pricing", "Admin", "Users", "Pricing", 2, 3, "Export", 2, env);
		App.check_result(res);
	}
	//@Test(enabled = false)
	@Test(priority = 40)
	public void PERMNS_040_Verify_Import_Permission_As_Yes_In_Pricing() throws Exception {
		
		boolean res = p.verifyPricingExportImportPermissions("PERMNS_040_Verify_Import_Permission_As_Yes_In_Pricing", "Admin", "Users", "Pricing", 1, 3, "Import", 3, env);
		App.check_result(res);
	}
	//@Test(enabled = false)
	@Test(priority = 41)
	public void PERMNS_041_Verify_Import_Permission_As_No_In_Pricing() throws Exception {
		
		boolean res = p.verifyPricingExportImportPermissions("PERMNS_041_Verify_Import_Permission_As_No_In_Pricing", "Admin", "Users", "Pricing", 2, 3, "Import", 3, env);
		App.check_result(res);
	}
	//@Test(enabled = false)
	@Test(priority = 42)
	public void PERMNS_042_Verify_Export_Permission_As_Yes_In_Discount() throws Exception {
		
		boolean res = p.verifyPricingExportImportPermissions("PERMNS_042_Verify_Export_Permission_As_Yes_In_Discount Codes", "Admin", "Users", "Discount Codes", 1, 1, "Export", 2, env);
		App.check_result(res);
	}
	//@Test(enabled = false)
	@Test(priority = 43)
	public void PERMNS_043_Verify_Export_Permission_As_No_In_Discount() throws Exception {
		
		boolean res = p.verifyPricingExportImportPermissions("PERMNS_043_Verify_Export_Permission_As_No_In_Discount Codes", "Admin", "Users", "Discount Codes", 2, 1, "Export", 2, env);
		App.check_result(res);
	}
	//@Test(enabled = false)
	@Test(priority =44)
	public void PERMNS_044_Verify_Export_Permission_As_Yes_In_Non() throws Exception {
		
		boolean res = p.verifyPricingExportImportPermissions("PERMNS_044_Verify_Export_Permission_As_Yes_In_Non Standard Pricing", "Admin", "Users", "Non Standard Pricing", 1, 2, "Export", 2, env);
		App.check_result(res);
	}
	//@Test(enabled = false)
	@Test(priority = 45)
	public void PERMNS_045_Verify_Export_Permission_As_No_In_Non() throws Exception {
		
		boolean res = p.verifyPricingExportImportPermissions("PERMNS_045_Verify_Export_Permission_As_No_In_Non Standard Pricing", "Admin", "Users", "Non Standard Pricing", 2, 2, "Export", 2, env);
		App.check_result(res);
	}
	//@Test(enabled = false)
	@Test(priority = 46)
	public void PERMNS_046_Verify_Pricing_Permission_As_View() throws Exception {
		
		boolean res = p.verifyPricingPermissionsAsView("PERMNS_046_Verify_Pricing_Permission_As_View", "Admin", "Users", "Pricing",3, env);
		App.check_result(res);
	}
	//@Test(enabled = false)
	@Test(priority = 47)
	public void PERMNS_047_Verify_Discount_Codes_Permission_As_View() throws Exception {
		
		boolean res = p.verifyPricingPermissionsAsView("PERMNS_047_Verify_Discount Codes_Permission_As_View", "Admin", "Users", "Discount Codes", 3, env);
		App.check_result(res);
	}
	//@Test(enabled = false)
	@Test(priority = 48)
	public void PERMNS_048_Verify_Non_Standard_Pricing_Permission_As_View() throws Exception {
		
		boolean res = p.verifyPricingPermissionsAsView("PERMNS_048_Verify_Non Standard Pricing_Permission_As_View", "Admin", "Users", "Non Standard Pricing", 3, env);
		App.check_result(res);
	}
}
