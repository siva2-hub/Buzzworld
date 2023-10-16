package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import commonUtils.App;
import libraries.Permissions;

public class PermissionsTestCases_AdminTabs extends App
{
	Permissions p = new Permissions(); String env = "";
	//	@Test(enabled = false)
	@Test(priority = 1)
	public void PERMNS_001_Verify_AccountType_Permission_As_None() throws Exception
	{
		//Testing environment
		if (driver.getCurrentUrl().contains("staging")) {
			env = "stage Instance";
		} else {
			env = "QA Instance";
		} boolean result = false;
		//Check the Account Types as None permission
		result = p.verifyAdminTabs_None("PERMNS_001_Verify_AccountType_Permission_As_None", "Admin", "Users", "Account Type", 2, env);
		this.check_result(result);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_002_Verify_Branches_Permission_As_None", "Admin", "Users", "Branches", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_003_Verify_Classification_Permission_As_None", "Admin", "Users", "Classification", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_004_Verify_Industry_Permission_As_None", "Admin", "Users", "Industry", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_005_Verify_Po Min Qty_Permission_As_None", "Admin", "Users", "Po Min Qty", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_006_Verify_Product Class_Permission_As_None", "Admin", "Users", "Product Class", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_007_Verify_QC Control_Permission_As_None", "Admin", "Users", "QC Control", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_008_Verify_Quote Approval_Permission_As_None", "Admin", "Users", "Quote Approval", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_009_Verify_Quote Types_Permission_As_None", "Admin", "Users", "Quote Types", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_010_Verify_Regions_Permission_As_None", "Admin", "Users", "Regions", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_011_Verify_Sales Potential_Permission_As_None", "Admin", "Users", "Sales Potential", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_012_Verify_Terms & Conditions_Permission_As_None", "Admin", "Users", "Terms & Conditions", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_013_Verify_Territories_Permission_As_None", "Admin", "Users", "Territories", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_014_Verify_User Roles_Permission_As_None", "Admin", "Users", "User Roles", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_015_Verify_Vendors_Permission_As_None", "Admin", "Users", "Vendors", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_016_Verify_Warehouse_Permission_As_None", "Admin", "Users", "Warehouse", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_017_Verify_Zip Codes_Permission_As_None", "Admin", "Users", "Zip Codes", 2, env);
		//		//
		//		p.verifyAdminTabs_None("PERMNS_096_Verify_Product Category_Permission_As_None", "Admin", "Users", "Product Category", 2, env);
		//
		//		//Check the Account Types as View permission
		//		p.adminTabwithViewPermission("PERMNS_018_Verify_Account Type_Permission_As_View","Admin", "Users", "Account Type", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_019_Verify_Branches_Permission_As_View","Admin", "Users", "Branches", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_020_Verify_Regions_Permission_As_View","Admin", "Users", "Regions", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_021_Verify_Territories_Permission_As_View","Admin", "Users", "Territories", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_022_Verify_Zip Codes_Permission_As_View","Admin", "Users", "Zip Codes", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_023_Verify_Warehouse_Permission_As_View","Admin", "Users", "Warehouse", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_024_Verify_Classifications_Permission_As_View","Admin", "Users", "Classification", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_025_Verify_Contact Types_Permission_As_View","Admin", "Users", "Contact Types", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_026_Verify_Industries_Permission_As_View","Admin", "Users", "Industry", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_027_Verify_PO Min Qty_Permission_As_View","Admin", "Users", "PO Min Qty", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_028_Verify_Quote Types_Permission_As_View","Admin", "Users", "Quote Types", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_029_Verify_Sales Potential_Permission_As_View","Admin", "Users", "Sales Potential", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_030_Verify_Vendors_Permission_As_View","Admin", "Users", "Vendors", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_031_Verify_Quote Approval_Permission_As_View","Admin", "Users", "Quote Approval", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_032_Verify_User Roles_Permission_As_View","Admin", "Users", "User Roles", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_033_Verify_Terms & Conditions_Permission_As_View","Admin", "Users", "Terms & Conditions", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_034_Verify_QC Control_Permission_As_View","Admin", "Users", "QC Control", 3, env);
		//		//
		//		p.adminTabwithViewPermission("PERMNS_094_Verify_Product Category_Permission_As_View","Admin", "Users", "Product Category", 3, env);
	}
	//	@Test(enabled = false)
	@Test(priority = 2)
	public void PERMNS_002_Verify_Branches_Permission_As_None() throws Exception{

		boolean res= p.verifyAdminTabs_None("PERMNS_002_Verify_Branches_Permission_As_None", "Admin", "Users", "Branches", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 3)
	public void PERMNS_003_Verify_Classification_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_003_Verify_Classification_Permission_As_None", "Admin", "Users", "Classification", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 4)
	public void PERMNS_004_Verify_Industry_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_004_Verify_Industry_Permission_As_None", "Admin", "Users", "Industry", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 5)
	public void PERMNS_005_Verify_PoMinQty_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_005_Verify_Po Min Qty_Permission_As_None", "Admin", "Users", "Po Min Qty", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 6)
	public void PERMNS_006_Verify_Product_Class_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_006_Verify_Product Class_Permission_As_None", "Admin", "Users", "Product Class", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)

	@Test(priority = 7)
	public void PERMNS_007_Verify_QC_Control_Permission_As_None() throws Exception{

		boolean res= p.verifyAdminTabs_None("PERMNS_007_Verify_QC Control_Permission_As_None", "Admin", "Users", "QC Control", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 8)
	public void PERMNS_008_Verify_Quote_Approval_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_008_Verify_Quote Approval_Permission_As_None", "Admin", "Users", "Quote Approval", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 9)
	public void PERMNS_009_Verify_Quote_Types_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_009_Verify_Quote Types_Permission_As_None", "Admin", "Users", "Quote Types", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 10)
	public void PERMNS_010_Verify_Regions_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_010_Verify_Regions_Permission_As_None", "Admin", "Users", "Regions", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 11)
	public void PERMNS_011_Verify_Sales_Potential_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_011_Verify_Sales Potential_Permission_As_None", "Admin", "Users", "Sales Potential", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 12)
	public void PERMNS_012_Verify_Terms_and_Conditions_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_012_Verify_Terms & Conditions_Permission_As_None", "Admin", "Users", "Terms & Conditions", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 13)
	public void PERMNS_013_Verify_Territories_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_013_Verify_Territories_Permission_As_None", "Admin", "Users", "Territories", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 14)
	public void PERMNS_014_Verify_User_Roles_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_014_Verify_User Roles_Permission_As_None", "Admin", "Users", "User Roles", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 15)
	public void PERMNS_015_Verify_Vendors_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_015_Verify_Vendors_Permission_As_None", "Admin", "Users", "Vendors", 2, env);
		App.check_result(res);
	}
//	@Test(enabled = false)
	@Test(priority = 16)
	public void PERMNS_016_Verify_Warehouse_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_016_Verify_Warehouse_Permission_As_None", "Admin", "Users", "Warehouse", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 17)
	public void PERMNS_017_Verify_Zip_Codes_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_017_Verify_Zip Codes_Permission_As_None", "Admin", "Users", "Zip Codes", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 96)
	public void PERMNS_096_Verify_Product_Category_Permission_As_None() throws Exception{
		boolean res= p.verifyAdminTabs_None("PERMNS_096_Verify_Product Category_Permission_As_None", "Admin", "Users", "Product Category", 2, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 18)
	public void PERMNS_018_Verify_Account_Type_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_018_Verify_Account Type_Permission_As_View","Admin", "Users", "Account Type", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 19)
	public void PERMNS_019_Verify_Branches_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_019_Verify_Branches_Permission_As_View","Admin", "Users", "Branches", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 20)
	public void PERMNS_020_Verify_Regions_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_020_Verify_Regions_Permission_As_View","Admin", "Users", "Regions", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 21)
	public void PERMNS_021_Verify_Territories_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_021_Verify_Territories_Permission_As_View","Admin", "Users", "Territories", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 22)
	public void PERMNS_022_Verify_Zip_Codes_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_022_Verify_Zip Codes_Permission_As_View","Admin", "Users", "Zip Codes", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 23)
	public void PERMNS_023_Verify_Warehouse_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_023_Verify_Warehouse_Permission_As_View","Admin", "Users", "Warehouse", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 24)
	public void PERMNS_024_Verify_Classifications_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_024_Verify_Classifications_Permission_As_View","Admin", "Users", "Classification", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 25)
	public void PERMNS_025_Verify_Contact_Types_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_025_Verify_Contact Types_Permission_As_View","Admin", "Users", "Contact Types", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 26)
	public void PERMNS_026_Verify_Industries_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_026_Verify_Industries_Permission_As_View","Admin", "Users", "Industry", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 27)
	public void PERMNS_027_Verify_PO_Min_Qty_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_027_Verify_PO Min Qty_Permission_As_View","Admin", "Users", "PO Min Qty", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 28)
	public void PERMNS_028_Verify_Quote_Types_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_028_Verify_Quote Types_Permission_As_View","Admin", "Users", "Quote Types", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 29)
	public void PERMNS_029_Verify_Sales_Potential_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_029_Verify_Sales Potential_Permission_As_View","Admin", "Users", "Sales Potential", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 30)
	public void PERMNS_030_Verify_Vendors_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_030_Verify_Vendors_Permission_As_View","Admin", "Users", "Vendors", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 31)
	public void PERMNS_031_Verify_Quote_Approval_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_031_Verify_Quote Approval_Permission_As_View","Admin", "Users", "Quote Approval", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 32)
	public void PERMNS_032_Verify_User_Roles_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_032_Verify_User Roles_Permission_As_View","Admin", "Users", "User Roles", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 33)
	public void PERMNS_033_Verify_Terms_and_Conditions_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_033_Verify_Terms & Conditions_Permission_As_View","Admin", "Users", "Terms & Conditions", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 34)
	public void PERMNS_034_Verify_QC_Control_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_034_Verify_QC Control_Permission_As_View","Admin", "Users", "QC Control", 3, env);
		App.check_result(res);
	}
	//	@Test(enabled = false)
	@Test(priority = 35)
	public void PERMNS_094_Verify_Product_Category_Permission_As_View() throws Exception{
		boolean res= p.adminTabwithViewPermission("PERMNS_094_Verify_Product Category_Permission_As_View","Admin", "Users", "Product Category", 3, env);
		App.check_result(res);
	}
}
