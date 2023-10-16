package testcases;

import org.testng.annotations.Test;

import commonUtils.App;
import libraries.Permissions_Organizations;

public class PermissionsTestCases_Organizations extends App
{
	Permissions_Organizations org = new Permissions_Organizations();
	String env = "";
	//	@Test(enabled = false)
	@Test(priority =78)
	public void PERMNS_078_Verify_Organizations_Permission_As_None() throws Exception {
		//Testing environment
		if (driver.getCurrentUrl().contains("staging")) {
			env = "stage Instance";
		} else {
			env = "QA Instance";
		}
		//Check the Organizations as None permission
		boolean res = org.verifyOrganizationsPermissionsAsNone("PERMNS_078_Verify_Organizations_Permission_As_None", "Admin", "Users", "Organizations",2, env);
		App.check_result(res);
		//
		org.verifyOrganizationsPermissionsAsNone("PERMNS_079_Verify_Organizations_Permission_As_View", "Admin", "Users", "Organizations",3, env);
		//
		org.verifyOrganizationsPermissionsAsNone("PERMNS_080_Verify_Contacts_Permission_As_None", "Admin", "Users", "Contacts",2, env);
		//
		org.verifyOrganizationsPermissionsAsNone("PERMNS_081_Verify_Contacts_Permission_As_View", "Admin", "Users", "Contacts",3, env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_082_Verify_Export_Permission_As_Yes_Contacts", "Admin", "Users", "contacts_export","contacts", "Export", "1", env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_083_Verify_Export_Permission_As_No_Contacts", "Admin", "Users", "contacts_export","contacts", "Export", "0", env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_084_Verify_Export_Permission_As_No_Organizations", "Admin", "Users", "organizations_export","organizations", "Export", "0", env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_085_Verify_Export_Permission_As_Yes_Organizations", "Admin", "Users", "organizations_export","organizations", "Export", "1", env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_086_Verify_Sync_Permission_As_Yes_Contacts", "Admin", "Users", "contacts_sync","contacts", "Sync", "1", env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_087_Verify_Sync_Permission_As_No_Contacts", "Admin", "Users", "contacts_sync","contacts", "Sync", "0", env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_088_Verify_Sync_Permission_As_Yes_Organizations", "Admin", "Users", "organizations_sync","organizations", "Sync", "1", env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_089_Verify_Sync_Permission_As_No_Organizations", "Admin", "Users", "organizations_sync","organizations", "Sync", "0", env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_090_Verify_Sync_Permission_As_No_Warehouse", "Admin", "Users", "warehouse_sync","warehouse", "Sync", "0", env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_091_Verify_Sync_Permission_As_Yes_Warehouse", "Admin", "Users", "warehouse_sync","warehouse", "Sync", "1", env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_092_Verify_Sync_Permission_As_No_ProductClass", "Admin", "Users", "product_class_sync","product_class", "Sync", "0", env);
		//
		org.exportPermissionAsYesInContacts("PERMNS_093_Verify_Sync_Permission_As_Yes_ProductClass", "Admin", "Users", "product_class_sync","product_class", "Sync", "1", env);
		//

	}
		@Test(enabled = false)
		//	@Test(priority = 79)
		public void PERMNS_079_Verify_Organizations_Permission_As_View() throws Exception {
			
			boolean res = org.verifyOrganizationsPermissionsAsNone("PERMNS_079_Verify_Organizations_Permission_As_View", "Admin", "Users", "Organizations",3, env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 80)
		public void PERMNS_080_Verify_Contacts_Permission_As_None() throws Exception {
			
			boolean res = org.verifyOrganizationsPermissionsAsNone("PERMNS_080_Verify_Contacts_Permission_As_None", "Admin", "Users", "Contacts",2, env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 81)
		public void PERMNS_081_Verify_Contacts_Permission_As_View() throws Exception {
			
			boolean res = org.verifyOrganizationsPermissionsAsNone("PERMNS_081_Verify_Contacts_Permission_As_View", "Admin", "Users", "Contacts",3, env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 82)
		public void PERMNS_082_Verify_Export_Permission_As_Yes_Contacts() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_082_Verify_Export_Permission_As_Yes_Contacts", "Admin", "Users", "contacts_export","contacts", "Export", "1", env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 83)
		public void PERMNS_083_Verify_Export_Permission_As_No_Contacts() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_083_Verify_Export_Permission_As_No_Contacts", "Admin", "Users", "contacts_export","contacts", "Export", "0", env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 84)
		public void PERMNS_084_Verify_Export_Permission_As_No_Organizations() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_084_Verify_Export_Permission_As_No_Organizations", "Admin", "Users", "organizations_export","organizations", "Export", "0", env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 85)
		public void PERMNS_085_Verify_Export_Permission_As_Yes_Organizations() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_085_Verify_Export_Permission_As_Yes_Organizations", "Admin", "Users", "organizations_export","organizations", "Export", "1", env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 86)
		public void PERMNS_086_Verify_Sync_Permission_As_Yes_Contacts() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_086_Verify_Sync_Permission_As_Yes_Contacts", "Admin", "Users", "contacts_sync","contacts", "Sync", "1", env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 87)
		public void PERMNS_087_Verify_Sync_Permission_As_No_Contacts() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_087_Verify_Sync_Permission_As_No_Contacts", "Admin", "Users", "contacts_sync","contacts", "Sync", "0", env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 88)
		public void PERMNS_088_Verify_Sync_Permission_As_Yes_Organizations() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_088_Verify_Sync_Permission_As_Yes_Organizations", "Admin", "Users", "organizations_sync","organizations", "Sync", "1", env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 89)
		public void PERMNS_089_Verify_Sync_Permission_As_No_Organizations() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_089_Verify_Sync_Permission_As_No_Organizations", "Admin", "Users", "organizations_sync","organizations", "Sync", "0", env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 90)
		public void PERMNS_090_Verify_Sync_Permission_As_No_Warehouse() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_090_Verify_Sync_Permission_As_No_Warehouse", "Admin", "Users", "warehouse_sync","warehouse", "Sync", "0", env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 91)
		public void PERMNS_091_Verify_Sync_Permission_As_Yes_Warehouse() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_091_Verify_Sync_Permission_As_Yes_Warehouse", "Admin", "Users", "warehouse_sync","warehouse", "Sync", "1", env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 92)
		public void PERMNS_092_Verify_Sync_Permission_As_No_ProductClass() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_092_Verify_Sync_Permission_As_No_ProductClass", "Admin", "Users", "product_class_sync","product_class", "Sync", "0", env);
			App.check_result(res);
		}
		@Test(enabled = false)
		//	@Test(priority = 93)
		public void PERMNS_093_Verify_Sync_Permission_As_Yes_ProductClass() throws Exception {
			
			boolean res = org.exportPermissionAsYesInContacts("PERMNS_093_Verify_Sync_Permission_As_Yes_ProductClass", "Admin", "Users", "product_class_sync","product_class", "Sync", "1", env);
			App.check_result(res);
		}
}
