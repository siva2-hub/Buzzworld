package libraries;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonUtils.App;

public class AllModules extends App
{
	QuotePages quotes = new QuotePages();   RepairPages repair = new RepairPages();   PricingPages price = new PricingPages();
	public void logoutCheckURLRedirectsOrNot(String env) throws Exception 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String url[] = {"quote_for_parts","organizations", "contacts", "pricing", "discount-codes", "special-pricing", "repair-request", "jobs", "orders"
				, "part-purchase", "inventory", "account-type", "branches", "classification", "contact_types", "industry", "po_min_qty", "product_class"
				, "qc_control", "quote-approval", "quote-type", "regions", "sales_potential", "terms-conditions", "territory", "users", "user_roles", 
				"vendors", "warehouse", "zipcodes", "past-due-invoices", "point-of-sales"};
		int count =1;
		for(int i=0;i<url.length; i++) 
		{
			//Warning Pop Up
			App.displayPopUp("Checking "+url[i]+" Page redirects or not after logout and login");

			String openURL = driver.getCurrentUrl().replace(driver.getCurrentUrl().substring(39,driver.getCurrentUrl().length()), url[i]);
			driver.navigate().to(openURL);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@viewBox='0 0 16 16']")));
			Thread.sleep(2500);
			driver.findElement(By.xpath("//*[@class='down-arrow']")).click();
			List<WebElement> btns = driver.findElements(By.xpath("//*[@role='menuitem']"));
			btns.get(1).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
			driver.findElement(By.id("username")).sendKeys(mail);
			driver.findElement(By.id("password")).sendKeys(pwd);
			price.clickButton("Sign In");
			App.spinner();
			Thread.sleep(1000);
			String openedURL = driver.getCurrentUrl();
			if (openURL.equals(openedURL)) {
				Object status[] = {"0"+count+"_Check_"+url[i].toUpperCase()+"_URLs_Redirects_To_Same_Or_Not_After_Logout_and_Login", "Actual URL is "+openedURL,
						"Expected URL is "+openURL,
						url[i].toUpperCase(), "Passed", java.time.LocalDateTime.now().toString(), env};
				quotes.values(status);
			} else {
				Object status[] = {"0"+count+"_Check_"+url[i].toUpperCase()+"_URLs_Redirects_To_Same_Or_Not_After_Logout_and_Login", "Actual URL is "+openedURL,
						"Expected URL is "+openURL,
						url[i].toUpperCase(), "Failed", java.time.LocalDateTime.now().toString(), env};
				quotes.values(status);
			}
			count++;
		}
	}

	public void linksRedirectsOrNot(String env) throws Exception 
	{
		QuotePages qp = new QuotePages();
		String url[] = {"organizations", "contacts", "pricing", "discount-codes", "special-pricing", "repair-request", "quote_for_parts", "jobs", "orders"
				, "part-purchase", "inventory", "account-type", "branches", "classification", "contact_types", "industry", "po_min_qty", "product_class"
				, "qc_control", "quote-approval", "quote-type", "regions", "sales_potential", "terms-conditions", "territory", "users", "user_roles", 
				"vendors", "warehouse", "zipcodes", "past-due-invoices", "point-of-sales"};
		String data = "quote_for_parts";int count =1;
		for(int i=0; i<url.length; i++) 
		{
			String expURL = driver.getCurrentUrl().replace(data, url[i]);
			driver.navigate().to(expURL);
			data = url[i];
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@viewBox='0 0 16 16']")));
			Thread.sleep(2500);
			String actURL = driver.getCurrentUrl();
			if (actURL.equals(expURL)) {
				Object status[] = {"Check_URLS_0"+count, "Actual URL is "+actURL, "Expected URL is "+expURL, url[i].toUpperCase(), "Passed",
						java.time.LocalDateTime.now().toString(), env};
				qp.values(status);
			} else {
				Object status[] = {"Check_URLS_0"+count, "Actual URL is "+actURL, "Expected URL is "+expURL, url[i].toUpperCase(), "Failed",
						java.time.LocalDateTime.now().toString(), env};
				qp.values(status);
			}
			count++;
		}
	}
	public void repairsModule(String stCode, String env) throws Exception
	{
		//Display All Items Check-box
		//Warning Pop Up
		App.displayPopUp("REPAIRS_001_Verify_Display All Items");
		driver.findElement(By.xpath("//*[text()='Repairs']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'All Repairs Requests']")));
		Thread.sleep(1300);
		driver.findElement(By.xpath("//*[text() = 'All Repairs Requests']")).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Filters']")));
		Thread.sleep(1500);
		App.clearTopSearch(); 
		App.spinner(); Thread.sleep(1300);
		App.clearFilter();
		App.spinner(); Thread.sleep(1200);
		boolean isSelected = false;
		try {

			if (driver.findElement(By.xpath("//*[@data-size = 'large']")).getAttribute("data-checked").equalsIgnoreCase("true")) {
				isSelected = false;
			}
		} catch (Exception e) {
			isSelected = true;
		}
		System.out.println("Is Selected status is "+isSelected);
		if (isSelected) {
			Object status[] = {"REPAIRS_001_Verify_Display All Items", "By default isSeleted status is "+isSelected, "", "RepairsPage", 
					"Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"REPAIRS_001_Verify_Display All Items", "By default isSeleted status is "+isSelected, "", "RepairsPage",
					"Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
			driver.findElement(By.xpath("//*[@data-size='large']")).click();
		}
		//Verify left menu tabs in repairs list view
		this.left_menu_tab_list(env);
		//verify My repair Request
		if (driver.getCurrentUrl().contains("staging")) {
			Object status[] = {"REPAIRS_009_Verify_My_Repair_Requests_Tab", "Not Executing in stage", 
					"", "RepairsPage", "Not Executed!", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			this.verify_my_repair_repair_request_tab(env);
		}
		//Filters In Repair List View
		//Warning Pop Up
		App.displayPopUp("REPAIRS_018_VerifyFilters");
		RepairPages repair = new RepairPages();
		repair.verifyFilters("123 E Doty Corporation", "Dallas House", "Receiving", env);
		//Filter's State Maintenance
		//Warning Pop Up
		App.displayPopUp("REPAIRS_028_VerifyFilterStateMaintanance");
		repair.verifyFilterStateMaintanance(env);
		//Create RMA
		//Warning Pop Up
		App.displayPopUp("REPAIRS_002_VerifyCreateRMA ");
		repair.createRMA();
		String expText = "Add Items";
		String repairId = driver.findElement(By.xpath("//*[@class ='id-num']")).getText().replace("#", "");
		String actText = driver.findElement(By.xpath("//*[text() ='Add Items']")).getText();
		if (actText.toLowerCase().contains(expText.toLowerCase())) {
			Object status[] = {"REPAIRS_002_VerifyCreateRMA "+repairId, actText, expText, "RepairsPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {

			Object status[] = {"REPAIRS_002_VerifyCreateRMA "+repairId, actText, expText, "RepairsPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//File Upload in Repair Detailed View
		//Warning Pop Up
		App.displayPopUp("REPAIRS_017_VerifyFileUpload_Repairs");
		repair.fileUpload(env);
		//Add New Item
		//Warning Pop Up
		App.displayPopUp("REPAIRS_020_VerifyAddNewItem");
		repair.verifyAddNewItem(env);
		//Delete Row option in Add New Item Page
		//Warning Pop Up
		App.displayPopUp("REPAIRS_021_Verify_Delete_Row_In Add New Items");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Add Items']")));
		driver.findElement(By.xpath("//*[text()='Add Items']")).click();
		driver.findElement(By.xpath("//*[text()='Add New Items']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Serial No']")));
		driver.findElement(By.xpath("//*[text()='Delete Row']")).click();
		boolean sta = false;
		try {
			driver.findElement(By.xpath("//*[@placeholder='Serial No']")).isDisplayed();
			sta = false;
		} catch (Exception e) {
			sta = true;
		}
		if (sta) {
			Object status[] = {"REPAIRS_021_Verify_Delete_Row_In Add New Items", "Row Deleted Successfully", "",
					"RepairsPage", "Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {

			Object status[] = {"REPAIRS_021_Verify_Delete_Row_In Add New Items", "Row Deleting Failed.!", "",
					"RepairsPage", "Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		Thread.sleep(1500);
		//Delete Row option in Add New Item Page
		//Warning Pop Up
		App.displayPopUp("REPAIRS_022_Verify_Add_Another_Row_In Add New Items");

		driver.findElement(By.xpath("//*[text()='Add another row']")).click();
		Thread.sleep(1000);
		try {
			driver.findElement(By.xpath("//*[@placeholder='Serial No']")).isDisplayed();
			sta = true;
		} catch (Exception e) {
			sta = false;
		}
		if (sta) {
			Object status[] = {"REPAIRS_022_Verify_Add_Another_Row_In Add New Items", "Row Added Successfully", "",
					"RepairsPage", "Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {

			Object status[] = {"REPAIRS_022_Verify_Add_Another_Row_In Add New Items", "Row Adding Failed.!", "",
					"RepairsPage", "Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		price.closeIcon();
		Thread.sleep(1600);
		//Add Items To Repair
		//Warning Pop Up
		App.displayPopUp("REPAIRS_003_VerifySelectItemToRepair");
		actText = driver.findElement(By.id("repair-items")).findElement(By.tagName("h4")).getText();
		driver.findElement(By.id("repair-items")).findElement(By.className("button-icon-text")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='side-drawer open']")));
		driver.findElement(By.xpath("//*[@placeholder='Search By Part Number']")).sendKeys(stCode);
		App.spinner(); Thread.sleep(0);
		try {
			Thread.sleep(4500);
			driver.findElement(By.xpath("//*[text() = '? Click here to add them']")).isDisplayed();
			Thread.sleep(400);
			driver.findElement(By.xpath("//*[text() = '? Click here to add them']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Part Number']")));
			Thread.sleep(500);
			driver.findElement(By.id("async-select-example")).sendKeys("WAGO");
			Thread.sleep(1200);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'WAGO')]")));
			quotes.selectDropDown("WAGO CORPORATION"); Thread.sleep(500);
			driver.findElement(By.xpath("//*[@placeholder='Part Number']")).sendKeys(stCode);
			driver.findElement(By.xpath("//*[@placeholder='Serial No']")).sendKeys(java.time.LocalTime.now().toString().substring(0, 8).replace(":", ""));
			driver.findElement(By.xpath("//*[text()='Add New Part']")).click();
			Thread.sleep(1500);
		} catch (Exception e) {
			System.out.println("Error message is "+e.getMessage());

			if (driver.getCurrentUrl().contains("stage")) 
			{	
				driver.findElements(By.xpath("//*[contains(@style, '--checkbox-background-color);')]")).get(1).click();
				App.click_xpath("//*[text() = 'Add Selected 1 Parts']", "click", "");
				App.spinner(); Thread.sleep(1300);
			} else {
				Actions act = new Actions(driver);
				driver.findElement(By.xpath("//*[@placeholder='Search By Part Number']")).click();
				act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
				act.sendKeys(Keys.SPACE).build().perform();
				Thread.sleep(1000);
				List<WebElement> btn = driver.findElement(By.xpath("//*[@class='side-drawer open']")).findElements(By.tagName("button"));
				for(int i=0;i<btn.size();i++) 
				{
					if(btn.get(i).getText().toLowerCase().contains("Add Selected".toLowerCase())) {
						btn.get(i).click();
						break;
					}
				}
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("repair-items")));
		Thread.sleep(2600);
		expText = driver.findElement(By.id("repair-items")).findElement(By.tagName("h4")).getText();
		if (!actText.equals(expText)) {
			Object status[] = {"REPAIRS_003_VerifySelectItemToRepair", actText, expText, "RepairsPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"REPAIRS_003_VerifySelectItemToRepair", actText, expText, "RepairsPage", "Failed",
					java.time.LocalDateTime.now().toString(),env};
			App.values1(status);
		}
		//Assign Location
		//Warning Pop Up
		App.displayPopUp("REPAIRS_004_VerifyAssignLocation");
		Actions act = new Actions(driver);
		repair.assignLocation();
		Thread.sleep(1200);
		expText = "CHECKED-IN";
		actText = driver.findElement(By.xpath("//*[@style = 'white-space: nowrap; max-width: 100%; text-overflow: ellipsis;']")).getText();
		if (actText.toLowerCase().contains(expText.toLowerCase())) {
			Object status[] = {"REPAIRS_004_VerifyAssignLocation", actText, expText, "RepairsPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"REPAIRS_004_VerifyAssignLocation", actText, expText, "RepairsPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//Assign Technician
		//Warning Pop Up
		App.displayPopUp("REPAIRS_005_VerifyAssignTechnician");
		repair.assignTechnician();
		expText = "PENDING EVALUATION";
		actText = driver.findElement(By.xpath("//*[@style = 'white-space: nowrap; max-width: 100%; text-overflow: ellipsis;']")).getText();
		if (actText.toLowerCase().contains(expText.toLowerCase())) {
			Object status[] = {"REPAIRS_005_VerifyAssignTechnician", actText, expText, "RepairsPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"REPAIRS_005_VerifyAssignTechnician", actText, expText, "RepairsPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//Item Evaluation
		//Warning Pop Up
		App.displayPopUp("REPAIRS_006_VerifyEvaluateItem");
		repair.evaluateItem();
		expText = "PENDING QUOTE";
		actText = driver.findElement(By.xpath("//*[@style = 'white-space: nowrap; max-width: 100%; text-overflow: ellipsis;']")).getText();
		if (actText.toLowerCase().contains(expText.toLowerCase())) {
			Object status[] = {"REPAIRS_006_VerifyEvaluateItem", actText, expText, "RepairsPage", "Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"REPAIRS_006_VerifyEvaluateItem", actText, expText, "RepairsPage", "Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//Add Repairable Items To Quote
		//Warning Pop Up
		App.displayPopUp("REPAIRS_007_VerifyAddRepairableItemToQuote");
		Thread.sleep(1600);
		driver.findElement(By.xpath("//*[contains(@class,'check_box')]")).findElement(By.tagName("label")).click();
		price.clickButton("Add items to quote");
		Thread.sleep(1000);
		repair.toastContainer("Accept");
		App.spinner();
		Thread.sleep(1500);
		expText = "Add Options";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Options']")));
		actText = driver.findElement(By.xpath("//*[text() = 'Add Options']")).getText();
		if (actText.toLowerCase().contains(expText.toLowerCase())) {
			Object status[] = {"REPAIRS_007_VerifyAddRepairableItemToQuote", actText, expText, "RepairsPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"REPAIRS_007_VerifyAddRepairableItemToQuote", actText, expText, "RepairsPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//Go to Quotes for Repair Module
		//Warning Pop Up
		App.displayPopUp("REPAIRS_008_VerifyCreateQuoteFromRepair");
		expText = "OPEN";
		actText = driver.findElement(By.xpath("//*[@class='quote-num-and-status']")).getText();
		if (actText.toLowerCase().contains(expText.toLowerCase())) {
			Object status[] = {"REPAIRS_008_VerifyCreateQuoteFromRepair", actText, expText, "RepairsPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"REPAIRS_008_VerifyCreateQuoteFromRepair", actText, expText, "RepairsPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		Thread.sleep(2000);
		//Update quote price
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@src,'delete-icon')]")));
		act.moveToElement(driver.findElements(By.xpath("//*[contains(@src,'themecolorEdit')]")).get(1)).build().perform();
		act.moveToElement(driver.findElements(By.xpath("//*[contains(@src,'themecolorEdit')]")).get(1)).build().perform();
		act.click(driver.findElements(By.xpath("//*[contains(@src,'themecolorEdit')]")).get(1)).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Quote Price']")));
		Thread.sleep(1200); String qp = "1795";
		if(driver.findElement(By.name("quote_price")).getAttribute("value").equals("")) 
		{
			driver.findElement(By.xpath("//*[@placeholder='Quote Price']")).sendKeys(qp);
			act.click(driver.findElement(By.xpath("//*[text()='Save']"))).build().perform();
			App.spinner(); Thread.sleep(1500);
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@src,'delete-icon')]")));
		Thread.sleep(1000);
		//price.clickButton("Submit for internal approval");
		String btn_name = ""; String toast_btn = "";
		if (Integer.parseInt(qp)>10000) {
			btn_name = "Submit for Internal Approval";
			toast_btn = "Proceed";
		} else {
			btn_name = "Approve"; toast_btn = "Approve";
		}
		act.moveToElement(driver.findElement(By.xpath("//*[text()='"+btn_name+"']"))).build().perform();
		act.click(driver.findElement(By.xpath("//*[text()='"+btn_name+"']"))).build().perform();
		Thread.sleep(2000);
		repair.toastContainer(toast_btn);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//*[text()='"+btn_name+"']"), btn_name));
		Thread.sleep(1700);
		//		price.clickButton("Approve");
		if (Integer.parseInt(qp)>10000) {
			act.moveToElement(driver.findElement(By.xpath("//*[text()='Approve']"))).build().perform();
			act.click(driver.findElement(By.xpath("//*[text()='Approve']"))).build().perform();
			Thread.sleep(1200);
			repair.toastContainer("Approve");
		}else {
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Submit for Customer Approval']")));
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@class='quote-num-and-status']")).findElement(By.tagName("button")).click();
		driver.findElement(By.xpath("//*[@role='menuitem']")).click();
		App.spinner();
		Thread.sleep(1300);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Won']")));
		App.click_xpath("//*[text() = 'Won']", "click", "");
		Thread.sleep(1200);
		repair.toastContainer("Proceed");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Create Sales Order']")));
		App.spinner(); Thread.sleep(1200);
		String jobId = "";
		//Create Sales Order from Repair
		//click on create sales order button
		if (driver.getCurrentUrl().contains("staging")) {
			//Warning Pop Up
			App.displayPopUp("REPAIRS_014_VerifyCreateSalesOrder_FromRepair");
			App.click_xpath("//*[text() = 'Create Sales Order']", "click", "");
			App.spinner();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customer_po_number")));
			Thread.sleep(1500); boolean test = false;
			try {
				Thread.sleep(1500);
				driver.findElement(By.xpath("//*[contains(@src, 'addIcon')]")).isDisplayed();
				test = true;
			} catch (Exception e) {
			}
			driver.findElement(By.name("customer_po_number")).sendKeys("PO1234");
			if (test) {
				driver.findElement(By.xpath("//*[contains(@src, 'addIcon')]")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder = 'Enter Stock Description']")));
				String list_price = driver.findElement(By.xpath("//*[@placeholder = 'Enter List Price']")).getAttribute("value");
				if (list_price.contains("0.")) {
					for (int i=0; i<list_price.length(); i++) {
						driver.findElement(By.xpath("//*[@placeholder = 'Enter List Price']")).sendKeys(Keys.BACK_SPACE);
					}
					driver.findElement(By.xpath("//*[@placeholder = 'Enter List Price']")).sendKeys("1795");
				} else {}
				App.click_react_dropdown(1); App.spinner(); Thread.sleep(1200);
				quotes.selectDropDown("Made in"); Thread.sleep(2500);
				App.click_xpath("//*[text() = 'Add']", "click", "");
				App.spinner(); Thread.sleep(2100);
				driver.findElements(By.xpath("//*[text() = 'Create']")).get(1).click();
				App.spinner();
				Thread.sleep(1600);
			} else {
				Thread.sleep(1300);
				driver.findElements(By.xpath("//*[text() = 'Create']")).get(1).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@style = 'animation-delay: 0ms;']")));
				Thread.sleep(1600);
			}
			String serverMsg = "";boolean server = false;
			try {
				Thread.sleep(1600);
				driver.findElement(By.className("server-msg")).isDisplayed();
				serverMsg= driver.findElement(By.className("server-msg")).getText();
				server = true;
			} catch (Exception e) {
				server = false;
			}
			if(server) {
				Object status[] = {"REPAIRS_014_VerifyCreateSalesOrder_FromRepair", serverMsg, "", "RepairsPage", "Failed", 
						java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
				price.takesScreenShot("create_sales_order.png");
				driver.findElement(By.xpath("//*[@title='close']")).click();
			}else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Sales Order Information']")));
				String ord_id = driver.findElement(By.xpath("//*[@class = 'id-num']")).getText().replace("#", "");
				Object status1[] = {"REPAIRS_014_VerifyCreateSalesOrder_FromRepair", "Sales order created with ID "+ord_id, "", "RepairsPage", "Passed", 
						java.time.LocalDateTime.now().toString(), env};
				App.values1(status1);
				App.displayPopUp("REPAIRS_016_VerifyCreateJobFromRepair");
				List<WebElement> tabIds = driver.findElement(By.xpath("//*[@style = 'margin-left: 24px;']")).findElements(By.tagName("h4"));
				if (tabIds.size()==3) {
					tabIds.get(2).click();
					App.spinner(); Thread.sleep(1500);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Job Information']")));//*[text() = '224290']
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = '"+ord_id+"']")));
					if (driver.findElement(By.xpath("//*[text() = 'Job Information']")).isDisplayed()) {
						jobId = driver.findElement(By.xpath("//*[@class = 'quote-num-and-status']")).getText().replace("#", "");
						Object status[] = {"REPAIRS_016_VerifyCreateJobFromRepair", "Job created with Job Id is "+jobId, "", "RepairsPage", "Passed", 
								java.time.LocalDateTime.now().toString(), env};
						App.values1(status);
						App.click_xpath("//*[text() = '"+repairId+"']", "click", "");
						App.spinner(); Thread.sleep(1500);
					} else {
						Object status[] = {"REPAIRS_016_VerifyCreateJobFromRepair", "Jon Not Created", "", "RepairsPage", "Failed", 
								java.time.LocalDateTime.now().toString(), env};
						App.values1(status);
					}
				} else {
					Object status[] = {"REPAIRS_016_VerifyCreateJobFromRepair", "Jon Not Created", "count of ids is"+tabIds.size(), "RepairsPage", "Failed", 
							java.time.LocalDateTime.now().toString(), env};
					App.values1(status);
				}
			}
			//Back To Repair Module for check the Parts Purchase Icon is displayed or not
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='repair-items']")));
			boolean partsPurchseIcon = false;
			Thread.sleep(2300);
			try {
				driver.findElement(By.xpath("//*[contains(@src,'partspurchase')]")).isDisplayed();
				partsPurchseIcon = true;
			} catch (Exception e) {
				partsPurchseIcon = false;
			}
			//Warning Pop Up
			App.displayPopUp("REPAIRS_015_Verify_Parts_Purchase_Icon_isDisplayed_OrNot");
			if (partsPurchseIcon) {
				driver.findElement(By.xpath("//*[contains(@src,'partspurchase')]")).click();
				Thread.sleep(1200);
				//				price.closeIcon();
				Object status[] = {"REPAIRS_015_Verify_Parts_Purchase_Icon_isDisplayed_OrNot", "Parts Purchase Icon is Displayed", "",
						"RepairsPage", "Passed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
				this.createPartsPurchase("REPAIRS_024_VerifyCreate_PartsPurchase", jobId,1,env);
			} else {
				Object status[] = {"REPAIRS_015_Verify_Parts_Purchase_Icon_isDisplayed_OrNot", "Parts Purchase Icon is not Displayed ", "",
						"RepairsPage", "Failed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}
			//updating pp status to received and completed
			App.click_xpath("//*[@title = 'Edit']", "click", "");
			App.click_react_dropdown(0);
			act.sendKeys("Received and Completed").build().perform();
			act.sendKeys(Keys.ENTER).build().perform();
			App.click_xpath("//*[@title = 'Save Changes']", "click", "");
			App.spinner(); Thread.sleep(1400); boolean rep_in_prog = false;
			//verifying Repair In Progress
			driver.findElement(By.xpath("//*[text() = '"+repairId+"']")).click();
			App.spinner(); Thread.sleep(1500);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//*[text()='Related to']")));
			Thread.sleep(1200);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Repair In Progress']")));
			App.click_xpath("//*[text() = 'Repair In Progress']", "click", "");
			repair.toastContainer("Accept"); App.spinner(); Thread.sleep(1400);
			try {
				driver.findElement(By.xpath("//*[contains(@src, 'repair_summary')]")).isDisplayed();
				rep_in_prog = true;
			} catch (Exception e) {
				rep_in_prog = false;
			}
			if (rep_in_prog) {
				Object status[] = {"REPAIRS_023_Verify_Repair_In_Progress", "Repair Summary is displayed", "",
						"RepairsPage", "Passed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			} else {
				Object status[] = {"REPAIRS_023_Verify_Repair_In_Progress", "Repair Summary is not displayed", "",
						"RepairsPage", "Failed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}
			App.spinner(); Thread.sleep(1400);
			//Repair Summary
			driver.findElement(By.xpath("//*[contains(@src, 'repair_summary')]")).click();
			App.spinner(); Thread.sleep(2000);
			driver.findElement(By.xpath("//*[contains(@class,'dropdown-indicator')]")).click();
			act.sendKeys(Keys.ENTER).build().perform();
			driver.findElement(By.xpath("//*[text() = 'Save']")).click();
			//Assign to QC
			//Warning Pop Up
			App.displayPopUp("REPAIRS_011_VerifyAssignToQC");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Assign to QC']")));
			act.moveToElement(driver.findElement(By.xpath("//*[text() = 'Assign to QC']"))).build().perform();
			Thread.sleep(1500);
			driver.findElement(By.xpath("//*[text() = 'Assign to QC']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[contains(@class,'dropdown-indicator')]")).click();
			act.sendKeys(Keys.ENTER).build().perform();
			driver.findElement(By.xpath("//*[text() = 'Assign']")).click();
			//		repair.toastContainer("Accept");
			Thread.sleep(2200);
			expText = "PENDING QC";
			actText = driver.findElement(By.xpath("//*[@style = 'white-space: nowrap; max-width: 100%; text-overflow: ellipsis;']")).getText();
			if (actText.toLowerCase().contains(expText.toLowerCase())) {
				Object status[] = {"REPAIRS_011_VerifyAssignToQC", actText, expText, "RepairsPage", "Passed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			} else {
				Object status[] = {"REPAIRS_011_VerifyAssignToQC", actText, expText, "RepairsPage", "Failed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}
			//QC CheckList status Fail
			//Warning Pop Up
			App.displayPopUp("REPAIRS_010_VerifyQCCheckList_as_Fail");

			repair.verifyQCCheckList("Fail", env);
			//QC CheckList
			//Warning Pop Up
			App.displayPopUp("REPAIRS_012_VerifyQCCheckList_as_Pass");

			Thread.sleep(1500);
			driver.findElement(By.xpath("//*[contains(@src,'qc_checklist')]")).click();
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[text()='Save']")));
			Thread.sleep(1300);
			driver.findElements(By.xpath("//*[contains(@class,'react-select__indicator')]")).get(0).click();
			act.sendKeys("QC Control Report");
			Thread.sleep(1000);
			act.sendKeys(Keys.ENTER).build().perform();
			//		quotes.selectDropDown("Quality control report");
			App.spinner();
			Thread.sleep(1000);
			driver.findElements(By.xpath("//*[contains(@class,'react-select__indicator')]")).get(2).click();
			//		act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(1000);
			//		quotes.selectDropDown("Pass");
			driver.findElement(By.xpath("//*[@class='side-drawer open']")).findElement(By.xpath("//*[@type='submit']")).click();
			Thread.sleep(1400);
			expText = "PENDING INVOICE";
			actText = driver.findElement(By.xpath("//*[@style = 'white-space: nowrap; max-width: 100%; text-overflow: ellipsis;']")).getText();
			if (actText.toLowerCase().contains(expText.toLowerCase())) {

				Object status[] = {"REPAIRS_012_VerifyQCCheckList_as_Pass", actText, expText, "RepairsPage", "Passed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			} else {
				Object status[] = {"REPAIRS_012_VerifyQCCheckList_as_Pass", actText, expText, "RepairsPage", "Failed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}
			//Repair Report
			//Warning Pop Up
			App.displayPopUp("REPAIRS_013_Verify_Repair_Report");

			driver.findElement(By.xpath("//*[@style='padding-left: 8px;']")).findElement(By.tagName("button")).click();

			Thread.sleep(3500);
			Set<String> wCount = driver.getWindowHandles();
			Object[] ids = wCount.toArray();
			if (wCount.size()==2) {
				driver.switchTo().window(ids[1].toString());
				Thread.sleep(1500);
				price.takesScreenShot("repair_report.png");
				driver.close();
				Object status[] = {"REPAIRS_013_Verify_Repair_Report", "Repair Report Working!", "Opened Tab count is "+wCount.size(),
						"RepairsPage", "Passed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
				driver.switchTo().window(ids[0].toString());
				Thread.sleep(1700);
			} else {
				Object status[] = {"REPAIRS_013_Verify_Repair_Report", "Repair Report Not Working!", "Opened Tab count is "+wCount.size(),
						"RepairsPage", "Failed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}
			//verify Completed button in repair detailed view
			App.click_xpath("//*[text() = 'Completed']", "click", "");
			Thread.sleep(1500);
			repair.toastContainer("Accept");
			App.spinner(); Thread.sleep(1500);
			String act_comp_text = driver.findElement(By.xpath("//*[@style = 'width: 22%; overflow: unset;']")).getText();
			String exp_comp_text = "completed";
			if (act_comp_text.toLowerCase().equals(exp_comp_text.toLowerCase())) {
				Object status[] = {"REPAIRS_025_Verify_Completed_Button", act_comp_text, exp_comp_text,
						"RepairsPage", "Passed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			} else {
				Object status[] = {"REPAIRS_025_Verify_Completed_Button", act_comp_text, exp_comp_text,
						"RepairsPage", "Failed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}
			System.out.println(wCount.size());
		} else {

			Object status11[] = {"REPAIRS_010_VerifyQCCheckList_as_Fail", "Not executing in QA environment", "", "RepairsPage", "Not Executed..", 
					java.time.LocalDateTime.now().toString(),env};
			App.values1(status11);
			Object status12[] = {"REPAIRS_012_VerifyQCCheckList_as_Pass", "Not executing in QA environment", "", "RepairsPage", "Not Executed..", 
					java.time.LocalDateTime.now().toString(),env};
			App.values1(status12);
			Object status13[] = {"REPAIRS_011_VerifyAssignToQC", "Not executing in QA environment", "", "RepairsPage", "Not Executed..", 
					java.time.LocalDateTime.now().toString(),env};
			App.values1(status13);
			Object status14[] = {"REPAIRS_013_Verify_Repair_Report", "Not executing in QA environment", "", "RepairsPage", "Not Executed..", 
					java.time.LocalDateTime.now().toString(),env};
			App.values1(status14);
			Object status[] = {"REPAIRS_014_VerifyCreateSalesOrder_FromRepair", "Not executing in QA environment", "", "RepairsPage", "Not Executed..", 
					java.time.LocalDateTime.now().toString(),env};
			App.values1(status);
			Object status1[] = {"REPAIRS_015_Verify_Parts_Purchase_Icon_isDisplayed_OrNot", "Not executing in QA environment", "", "RepairsPage", "Not Executed..", 
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status1);
			Object status2[] = {"REPAIRS_016_VerifyCreateJobFromRepair", "Not executing in QA environment", "", "RepairsPage", "Not Executed..",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status2);
			Object status3[] = {"REPAIRS_031_Verify_Repair_In_Progress", "Not executing in QA environment", "", "RepairsPage", "Not Executed..",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status3);
			Object status4[] = {"REPAIRS_032_VerifyCreate_PartsPurchase", "Not executing in QA environment", "", "RepairsPage", "Not Executed..",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status4);
			Object status5[] = {"REPAIRS_033_Verify_Completed_Button", "Not executing in QA environment", "", "RepairsPage", "Not Executed..",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status5);
		}
	}
	public void clearButtonTopSearch() throws Exception 
	{
		try {
			driver.findElement(By.xpath("//*[@style = 'padding: 10px 10px 10px 0px; display: flex; align-items: center; cursor: pointer;']")).isDisplayed();
			driver.findElement(By.xpath("//*[@style = 'padding: 10px 10px 10px 0px; display: flex; align-items: center; cursor: pointer;']")).click();
			App.spinner();
		} catch (Exception e) {
			System.out.println(e.getStackTrace()[0]);
		}
		App.spinner(); Thread.sleep(1500);
	}
	public void quotesModule(String leadTime, String leadValue, String discount, String env) throws Exception
	{
		//All Quotes count
		App.displayPopUp("QUOTES_033_VerifyAllQuotes_with_Count");
		App.click_xpath("//*[text() ='Quotes']", "click", ""); App.spinner(); Thread.sleep(1300);
		App.clearTopSearch(); Thread.sleep(1300);
		App.click_xpath("//*[text() = 'All Quotes']", "click", "");
		App.spinner(); Thread.sleep(1300);
		String total_count = driver.findElements(By.xpath("//*[contains(@class, 'summary-panel-number')]")).get(2).getText().replace(",", "");
		int all_quotes_count = Integer.parseInt(total_count);
		//Parts Quotes Count
		App.click_xpath("//*[text() = 'Parts Quotes']", "click", ""); App.spinner(); Thread.sleep(1300);
		App.clearFilter(); App.spinner(); Thread.sleep(1200);
		total_count = driver.findElements(By.xpath("//*[contains(@class, 'summary-panel-number')]")).get(2).getText().replace(",", "");
		int parts_quotes_count = Integer.parseInt(total_count);
		//Repair Quotes Count
		App.click_xpath("//*[text() = 'Repair Quotes']", "click", ""); App.spinner(); Thread.sleep(1300);
		App.clearFilter(); App.spinner(); Thread.sleep(1200);
		total_count = driver.findElements(By.xpath("//*[contains(@class, 'summary-panel-number')]")).get(2).getText().replace(",", "");
		int rep_quotes_count = Integer.parseInt(total_count);
		//System Quotes Count
		App.click_xpath("//*[text() = 'System Quotes']", "click", ""); App.spinner(); Thread.sleep(1300);
		App.clearFilter(); App.spinner(); Thread.sleep(1200);
		total_count = driver.findElements(By.xpath("//*[contains(@class, 'summary-panel-number')]")).get(2).getText().replace(",", "");
		int sys_quotes_count = Integer.parseInt(total_count);
		int quotes_count = (parts_quotes_count+rep_quotes_count+sys_quotes_count);
		if (all_quotes_count== quotes_count) {
			Object status[] = {"QUOTES_033_VerifyAllQuotes_with_Count", "All Quotes count in grid is "+all_quotes_count, "Parts, Repair and System Quotes count in grid is "+quotes_count,
					"QuotesPage", "Passed",	java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"QUOTES_033_VerifyAllQuotes_with_Count", "All Quotes count in grid is "+all_quotes_count, "Parts, Repair and System Quotes count in grid is "+quotes_count,
					"QuotesPage", "Failed",	java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//Search Functionality
		try {
			driver.findElement(By.xpath("//*[text() = 'Clear']")).isDisplayed();
			driver.findElement(By.xpath("//*[text() = 'Clear']")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//Search with Quote Id
		//Warning Pop Up
		this.clearButtonTopSearch();
		App.displayPopUp("QUOTES_014_VerifySearchByQuoteId");
		quotes.verifyTopSearchInQuoteListView("2023053100074", 1, env);
		this.clearButtonTopSearch();
		//Search with Company Name
		//Warning Pop Up
		App.displayPopUp("QUOTES_015_VerifySearchByCompanyName");
		quotes.verifyTopSearchInQuoteListView("123 E Doty Corporation", 2, env);
		this.clearButtonTopSearch();
		//Search with Sales Person
		//Warning Pop Up
		App.displayPopUp("QUOTES_016_VerifySearchBySalesPersonName");
		quotes.verifyTopSearchInQuoteListView("Frontier", 3, env);
		this.clearButtonTopSearch();
		//Search with Email
		//Warning Pop Up
		App.displayPopUp("QUOTES_017_VerifySearchByEmail");
		quotes.verifyTopSearchInQuoteListView("pete.soto@motion-ind.com", 4, env);
		driver.findElement(By.xpath("//*[text() = 'Quotes']")).click();
		App.spinner();
		this.clearButtonTopSearch(); App.spinner(); Thread.sleep(1200);
		//Filters In Quote List View
		//Warning Pop Up
		App.displayPopUp("QUOTES_018_VerifyFiltersInQuotesListView");
		quotes.verifyFiltersInQuoteListView("Applied Industrial-Warren", "Dallas House", "Won", "sivakrishna Epi", 1, env);
		//Filter's State Maintenance
		//Warning Pop Up
		App.displayPopUp("QUOTES_019_VerifyFiltersStateMaintanance");
		quotes.verifyFiltersStateMaintanance("Zummo Meat Co Inc", "Jeremy Morgan", "Approved", "Swetha Epi", 1, env);
		//Reset and Clear Buttons in Filter's Page
		//Warning Pop Up
		App.displayPopUp("QUOTES_020_VerifyResetandClearButtonInFiltersPage");
		quotes.verifyResetandClearButtonInFiltersPage("Zummo Meat Co Inc", 1, env);
		//Create Quote
		//Warning Pop Up
		App.displayPopUp("QUOTES_001_VerifyCreateQuote");
		quotes.createQuote();
		App.spinner(); Thread.sleep(1300);
		String org_acc_number = driver.findElements(By.xpath("//*[contains(@class, 'calc-width-33')]")).get(5).findElement(By.tagName("p")).getText();
		String actText = driver.findElement(By.xpath("//*[@class='quote-num-and-status']")).getText();
		String expText = "OPEN";
		if (actText.toLowerCase().contains(expText.toLowerCase())) 
		{
			Object status[] = {"QUOTES_001_VerifyCreateQuote", actText, expText, "QuotesPage", "Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"QUOTES_001_VerifyCreateQuote", actText, expText, "QuotesPage", "Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		List<WebElement> edits = driver.findElement(By.xpath("//*[@id='repair-info-id']")).findElements(By.className("pi-label-edit-icon"));
		//delete quote option
		quotes.delete_quote_option(env);
		//update Quote Type 
		if (driver.getCurrentUrl().contains("staging")) {
			Object status[] = {"QUOTES_032_VerifyUpdateQuoteType", "not executing in stage environment", "",
					"QuotesPage", "Not Executed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			String bef_quo_type = driver.findElements(By.xpath("//*[contains(@class, 'calc-width-33')]")).get(1).findElement(By.tagName("p")).getText();
			edits.get(0).click(); Thread.sleep(1200);
			App.click_xpath("//*[text() = 'System Quote']", "click", ""); Thread.sleep(1300);
			App.click_xpath("//*[text() = 'Parts Quote']", "click", ""); Thread.sleep(1200);
			App.click_xpath("//*[@title = 'Save Changes']", "click", ""); Thread.sleep(1200);
			App.spinner(); Thread.sleep(2000);
			String aft_quo_type = driver.findElements(By.xpath("//*[contains(@class, 'calc-width-33')]")).get(1).findElement(By.tagName("p")).getText();
			if (actText!=expText) {
				Object status[] = {"QUOTES_032_VerifyUpdateQuoteType", "before update quote type is "+bef_quo_type, "After update quote type is "+aft_quo_type,
						"QuotesPage", "Passed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			} else {
				Object status[] = {"QUOTES_032_VerifyUpdateQuoteType", "before update quote type is "+bef_quo_type, "After update quote type is "+aft_quo_type,
						"QuotesPage", "Failed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}
		}
		//Add(Select) Items To Quote
		//Warning Pop Up
		App.displayPopUp("QUOTES_023_VerifyDeleteIconInQuoteItems");
		actText = driver.findElement(By.id("repair-items")).findElement(By.tagName("h4")).getText();
		this.select_items(1, "1234");
		//Delete Icon
		quotes.verifyDeleteIcon(1, env);
		actText = driver.findElement(By.id("repair-items")).findElement(By.tagName("h4")).getText();
		//Warning Pop Up
		App.displayPopUp("QUOTES_002_VerifySelectItemToQuote");
		this.select_items(1, "1234");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Options']")));
		expText = driver.findElement(By.id("repair-items")).findElement(By.tagName("h4")).getText();
		if (actText!=expText) {
			Object status[] = {"QUOTES_002_VerifySelectItemToQuote", actText, expText, "QuotesPage", "Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"QUOTES_002_VerifySelectItemToQuote", actText, expText, "QuotesPage", "Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		String ven_name_quote_del = driver.findElement(By.xpath("//*[contains(@class, 'm-0 manufacter')]")).getText();
		String stockCode = driver.findElement(By.xpath("//*[@class=' width-25 flexed']")).findElement(By.tagName("h4")).getText();
		//verify GP calculation
		double act_gp_value = Double.parseDouble(driver.findElements(By.xpath("//*[contains(@class, 'w-32')]")).get(0).findElement(By.tagName("h4")).getText().replace("%", ""));
		double act_dis_value = Double.parseDouble(driver.findElements(By.xpath("//*[contains(@class,'d-flex align-center g-8 ')]")).get(1).findElement(By.tagName("h4")).getText().replace("%", ""));

		double qp = Double.parseDouble(driver.findElements(By.xpath("//*[contains(@class, 'item-value-ellipsis')]")).get(2).getText().replace("$", "").replace(",", ""));
		double lp = Double.parseDouble(driver.findElements(By.xpath("//*[contains(@class, 'item-value-ellipsis')]")).get(7).getText().replace("$", "").replace(",", ""));
		double iidm_cost = Double.parseDouble(driver.findElements(By.xpath("//*[contains(@class, 'item-value-ellipsis')]")).get(4).getText().replace("$", "").replace(",", ""));
		DecimalFormat decfor = new DecimalFormat("0.00");
		double gp_value = Double.parseDouble(decfor.format(((qp-iidm_cost)/qp)*100));
		double dis_value = Double.parseDouble(decfor.format(((lp-qp)/lp)*100));
		if (act_gp_value==gp_value) {
			Object status[] = {"QUOTES_039_Verify_GP_Value", "actual gp "+act_gp_value, "expected gp "+gp_value, "QuotesPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"QUOTES_039_Verify_GP_Value", "actual gp "+act_gp_value, "expected gp "+gp_value, "QuotesPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//verify discount calculation
		if (act_dis_value==dis_value) {
			Object status[] = {"QUOTES_034_Verify_discount_Value", "actual discount "+act_dis_value, "expected discount "+dis_value, "QuotesPage",
					"Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"QUOTES_034_Verify_discount_Value", "actual discount "+act_dis_value, "expected discount "+dis_value, "QuotesPage",
					"Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		System.out.println("act gp value "+act_gp_value);
		System.out.println("exp gp value "+gp_value);
		//Print and Download
		//Warning Pop Up
		if (driver.getCurrentUrl().contains("staging")) {
			Object status[] = {"QUOTES_013_VerifyPrintFunctionality", "Not executing in stage environment", "",
					"QuotesPage", "Not Executed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			App.displayPopUp("QUOTES_013_VerifyPrintFunctionality");
			Thread.sleep(2100);
			quotes.verifyPrintDownLoad(env);
		}
		//Check the Lead Time Displayed Or Not
		//Warning Pop Up
		App.displayPopUp("QUOTES_003_VerifyLeadTimeDisplayedOrNot");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Add Options']")));
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElements(By.xpath("//*[contains(@src,'themecolorEdit')]")).get(1)).build().perform();
		act.click(driver.findElements(By.xpath("//*[contains(@src,'themecolorEdit')]")).get(1)).build().perform();
		Thread.sleep(1500);
		String quote_price = driver.findElement(By.xpath("//*[@name = 'quote_price']")).getAttribute("value");
		driver.findElements(By.xpath("//*[contains(@class,'dropdown-indicator')]")).get(1).click();
		Thread.sleep(1200);
		act.sendKeys(Keys.ENTER).build().perform();
		//Click on Save button in Edit Quote Items page
		price.clickButton("Save");
		App.spinner(); Thread.sleep(1400);
		App.scroll_to_vertical("//*[text() = 'Add Options']");
		actText = driver.findElements(By.xpath("//*[@class='d-flex align-center g-16 ']")).get(1).findElement(By.tagName("h4")).getText();
		expText = "TBD";
		if (actText.contains(expText)) {
			Object status[] = {"QUOTES_003_VerifyLeadTimeDisplayedOrNot", actText, expText, "QuotesPage", "Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"QUOTES_003_VerifyLeadTimeDisplayedOrNot", actText, expText, "QuotesPage", "Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//Edit Icon 
		//Warning Pop Up
		App.displayPopUp("QUOTES_004_VerifyBulkEdit"); 
		quotes.verifyDeleteIcon(2, env);
		//Bulk Edit
		driver.findElement(By.xpath("//*[contains(@class,'check_box')]")).findElement(By.tagName("label")).click();
		driver.findElement(By.xpath("//*[@class='quote-option-del-icon edit-icon']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("discount"))); Thread.sleep(1800);
		driver.findElement(By.name("discount")).sendKeys(discount);
		price.clickButton("Save"); Thread.sleep(2000);
		actText = driver.findElements(By.xpath("//*[contains(@class,'d-flex align-center g-8 ')]")).get(1).findElement(By.tagName("h4")).getText();
		expText = discount+" %";
		if (actText.equals(expText)) 
		{
			Object status[] = {"QUOTES_004_VerifyBulkEdit", "Displayed Discount is "+actText, "Applied Discount is "+expText, "QuotesPage",
					"Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {

			Object status[] = {"QUOTES_004_VerifyBulkEdit", "Displayed Discount is "+actText, "Applied Discount is "+expText, "QuotesPage",
					"Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[text() = 'Internal Approvals']")));
		Thread.sleep(2000);
		String total_price_detail = driver.findElements(By.xpath("//*[contains(@class, 'width-auto')]")).get(2).findElement(By.tagName("h4")).getText().replace("$", "").replace(",", "");		
		double total_value = Double.parseDouble(total_price_detail);
		System.out.println("total value in int is "+total_value); 
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@title='RFQ Received Date']")));
		//Proceed To Submit For Internal Approval
		//Warning Pop Up
		if (total_value>10000) {
			App.displayPopUp("QUOTES_005_VerifySubmitForInternalApproval");	
		} else {
			
		}
		edits = driver.findElement(By.xpath("//*[@id='repair-info-id']")).findElements(By.className("pi-label-edit-icon"));
		Thread.sleep(2400);
		WebElement rfq = driver.findElement(By.xpath("//*[@title='RFQ Received Date']"));
		Thread.sleep(1500);
		act.moveToElement(rfq).build().perform();
		Thread.sleep(1000);
		edits.get(1).click();
		driver.findElement(By.xpath("//*[@id='repair-info-id']")).findElement(By.tagName("button")).click();
		driver.findElement(By.xpath("//*[@title='Save Changes']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Quote Requested By']")));
		Thread.sleep(1700);
		WebElement qReqBy = driver.findElement(By.xpath("//*[@title='Quote Requested By']"));
		Actions action = new Actions(driver);
		action.moveToElement(qReqBy).build().perform();
		Thread.sleep(1000);
		edits.get(3).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(@class,'react-select__control')]")).click();
		App.spinner(); Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(@class,'css-4mp3pp-menu')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@title='Save Changes']")).click();
		App.spinner(); Thread.sleep(2500);
		if (total_value>=10000) {		
			driver.findElement(By.xpath("//*[text() ='Submit for Internal Approval']")).click();
			Thread.sleep(2000);
			repair.toastContainer("Proceed");
			App.spinner(); Thread.sleep(1200);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text() ='Submit for Internal Approval']")));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='quote-num-and-status']")));
			Thread.sleep(2500);
			actText = driver.findElement(By.xpath("//*[@class='quote-num-and-status']")).getText();
			expText = "PENDING APPROVAL";
			if (actText.toLowerCase().contains(expText.toLowerCase())) {
				
				Object status[] = {"QUOTES_005_VerifySubmitForInternalApproval", actText, expText, "QuotesPage",
						"Passed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			} else {
				
				Object status[] = {"QUOTES_005_VerifySubmitForInternalApproval", actText, expText, "QuotesPage",
						"Failed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}
		} else {
			String si_apr = "SubmitForInternalApproval";
			Object status[] = {"QUOTES_005_Verify"+si_apr, "If total price is <=10000 we are't displaying "+si_apr, ""
					+ "total price is "+total_value, "QuotesPage", "Not Executing", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//Approve The Quote
		//Warning Pop Up
		App.displayPopUp("QUOTES_007_VerifyApproveButton");
		Thread.sleep(1500);
		//below calling redirects toApproval Questions method
		if (total_value>=10000) {	
			this.approval_questions(total_value);
		} else {

		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() ='Approve']")));
		Thread.sleep(2000);
		App.click_xpath("//*[text() = 'Approve']", "click", "");
		Thread.sleep(1200);
		repair.toastContainer("Approve");
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//*[text() = 'Approve']"), "Approve"));
		Thread.sleep(2800);
		actText = driver.findElement(By.xpath("//*[@class='quote-num-and-status']")).getText();
		expText = "APPROVED";
		if (actText.toLowerCase().contains(expText.toLowerCase())) {
			Object status[] = {"QUOTES_007_VerifyApproveButton", actText, expText, "QuotesPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"QUOTES_007_VerifyApproveButton", actText, expText, "QuotesPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//Display the Revise Quote When Quote As Pending Approval
		//Warning Pop Up
		App.displayPopUp("QUOTES_006_Verify_Display_TheReviseQuote_When_Quote_After_Open_Status");
		boolean revise = false;
		try {
			revise = driver.findElement(By.xpath("//*[text()='Revise Quote']")).isDisplayed();	
		} catch (Exception e) {
			revise = false;
		}
		System.out.println("Revise Quote button is Displayed ..? "+revise);
		if (revise) {

			Object status[] = {"QUOTES_006_Verify_Display_TheReviseQuote_When_Quote_After_Open_Status",
					"Revise Quote button is Displayed ..? "+revise, "", "QuotesPage", "Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {

			Object status[] = {"QUOTES_006_Verify_Display_TheReviseQuote_When_Quote_After_Open_Status",
					"Revise Quote button is Displayed ..? "+revise, "", "QuotesPage", "Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//Send To Customer
		//Warning Pop Up
		App.displayPopUp("QUOTES_008_VerifySubmitForCustomerApproval");
		Thread.sleep(1500);
		driver.findElement(By.xpath("//*[@class='quote-num-and-status']")).findElement(By.tagName("button")).click();
		driver.findElement(By.xpath("//*[@role='menuitem']")).click();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='quote-num-and-status']")));
		Thread.sleep(2500);
		actText = driver.findElement(By.xpath("//*[@class='quote-num-and-status']")).getText();
		expText = "DELIVERED TO CUSTOMER";
		if (actText.toLowerCase().contains(expText.toLowerCase())) {

			Object status[] = {"QUOTES_008_VerifySubmitForCustomerApproval", actText, expText, "QuotesPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {

			Object status[] = {"QUOTES_008_VerifySubmitForCustomerApproval", actText, expText, "QuotesPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//Quote Won
		//Warning Pop Up
		App.displayPopUp("QUOTES_009_VerifyQuoteWon");
		repair.wonOrLostButton("Won");
		Thread.sleep(1200);
		repair.toastContainer("Proceed");
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//*[text() = 'Won']"), "Won"));
		Thread.sleep(1900);
		expText = "WON";
		actText = driver.findElement(By.xpath("//*[@class='quote-num-and-status']")).getText();
		if (actText.toLowerCase().contains(expText.toLowerCase())) {

			Object status[] = {"QUOTES_009_VerifyQuoteWon", actText, expText, "QuotesPage", "Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {

			Object status[] = {"QUOTES_009_VerifyQuoteWon", actText, expText, "QuotesPage", "Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		//Create Sales Order
		//Warning Pop Up
		String jobId = "";
		App.displayPopUp("QUOTES_010_VerifyCreateSalesOrder_FromQuote");
		if (driver.getCurrentUrl().contains("staging")) {
			//Warning Pop Up
			App.click_xpath("//*[text() = 'Create Sales Order']", "click", "");
			App.spinner();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customer_po_number")));
			Thread.sleep(1500); boolean test = false;
			try {
				Thread.sleep(1500);
				driver.findElement(By.xpath("//*[contains(@src, 'addIcon')]")).isDisplayed();
				test = true;
			} catch (Exception e) {}
			driver.findElement(By.name("customer_po_number")).sendKeys("PO1234");
			if (test) {
				driver.findElement(By.xpath("//*[contains(@src, 'addIcon')]")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder = 'Enter Stock Description']")));
				String list_price = driver.findElement(By.xpath("//*[@placeholder = 'Enter List Price']")).getAttribute("value");
				if (list_price.contains("0.")) {
					for (int i=0; i<list_price.length(); i++) {
						driver.findElement(By.xpath("//*[@placeholder = 'Enter List Price']")).sendKeys(Keys.BACK_SPACE);
					}
					driver.findElement(By.xpath("//*[@placeholder = 'Enter List Price']")).sendKeys("1795");
				} else {}
				App.click_react_dropdown(1); App.spinner(); Thread.sleep(1200);
				quotes.selectDropDown("Made in"); Thread.sleep(2500);
				App.click_xpath("//*[text() = 'Add']", "click", "");
				App.spinner(); Thread.sleep(2100);
				driver.findElements(By.xpath("//*[text() = 'Create']")).get(1).click();
				App.spinner();
				Thread.sleep(1600);
			} else {
				Thread.sleep(1300);
				driver.findElements(By.xpath("//*[text() = 'Create']")).get(1).click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@style = 'animation-delay: 0ms;']")));
				Thread.sleep(1600);
			}
			String serverMsg = "";boolean server = false;
			try {
				Thread.sleep(1600);
				driver.findElement(By.className("server-msg")).isDisplayed();
				serverMsg= driver.findElement(By.className("server-msg")).getText();
				server = true;
			} catch (Exception e) {
				server = false;
			}
			if(server) {
				Object status[] = {"QUOTES_010_VerifyCreateSalesOrder_From_Quote", serverMsg, "", "QuotesPage", "Failed", 
						java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
				price.takesScreenShot("create_sales_order.png");
				driver.findElement(By.xpath("//*[@title='close']")).click();
			}else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Sales Order Information']")));
				String order_num_status = driver.findElement(By.xpath("//*[@class = 'quote-num-and-status']")).getText();
				if (order_num_status.toLowerCase().contains("open order")) {
					Object status[] = {"QUOTES_010_VerifyCreateSalesOrder_From_Quote", "order created with "+act_dis_value, "", "QuotesPage", "Passed", 
							java.time.LocalDateTime.now().toString(), env};
				} else {
					Object status[] = {"QUOTES_010_VerifyCreateSalesOrder_From_Quote", "order not created", "", "QuotesPage", "Failed", 
							java.time.LocalDateTime.now().toString(), env};
					App.values1(status);
				}
				App.displayPopUp("QUOTES_011_VerifyCreateJob_From_Quote");
				List<WebElement> tabIds = driver.findElement(By.xpath("//*[@style = 'margin-left: 24px;']")).findElements(By.tagName("h4"));
				if (tabIds.size()==3) {
					tabIds.get(2).click();
					App.spinner(); Thread.sleep(1500);
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text() = 'Job Information']")));

					if (tabIds.size()==3) {
						jobId = tabIds.get(2).getText();
						Object status[] = {"QUOTES_011_VerifyCreateJob_From_Quote", "Job created with Job Id is "+jobId, "", "QuotesPage", "Passed", 
								java.time.LocalDateTime.now().toString(), env};
						App.values1(status);
						tabIds.get(0).click();
						App.spinner(); Thread.sleep(1500);
					} else {
						Object status[] = {"QUOTES_011_VerifyCreateJob_From_Quote", "Jon Not Created", "", "QuotesPage", "Failed", 
								java.time.LocalDateTime.now().toString(), env};
						App.values1(status);
					}
				} else {
					Object status[] = {"QUOTES_011_VerifyCreateJob_From_Quote", "Jon Not Created", "count of ids is"+tabIds.size(), "QuotesPage", "Failed", 
							java.time.LocalDateTime.now().toString(), env};
					App.values1(status);
				}
			}

		} else {

			Object status[] = {"QUOTES_010_VerifyCreateSalesOrder_From_Quote", "Not executing in QA environment", "", "QuotesPage", "Not Executed..", 
					java.time.LocalDateTime.now().toString(),env};
			App.values1(status);
			Object status1[] = {"QUOTES_011_VerifyCreateJob_From_Quote", "Not executing in QA environment", "", "QuotesPage", "Not Executed..", 
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status1);
		}
	}
	public void approval_questions(double total_value) throws Exception 
	{
		Actions act = new Actions(driver);

		driver.findElement(By.xpath("//*[text() = 'Approval Questions']")).isDisplayed();
		App.click_xpath("//*[text() = 'Approval Questions']", "click", "");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = '$10k+ Questions']")));
		Thread.sleep(2000);
		if(total_value>=50000) {
			System.out.println("under 50k");
			//$10k+ Approval Questions
			driver.findElement(By.xpath("//*[contains(@class, 'indicatorContainer')]")).click();
			act.sendKeys(Keys.ENTER).build().perform();
			App.click_xpath("//*[@placeholder = 'Enter Competition']", "send_keys", "Test Competetion");
			App.click_xpath("//*[@placeholder = 'Enter Budgetary Amount']", "send_keys", "149.12");
			driver.findElements(By.xpath("//*[text() = 'Save']")).get(0).click();
			//$25k+ Approval Questions
			Thread.sleep(2300);
			driver.findElements(By.xpath("//*[contains(@class, 'indicatorContainer')]")).get(1).click();
			act.sendKeys(Keys.ENTER).build().perform();
			//entering Pain
			App.click_xpath("//*[@placeholder = 'Enter Pain']", "send_keys", "Test Pain");
			//entering decision making process
			App.click_xpath("//*[@placeholder = 'Enter Decision Making Process']", "send_keys", "Test Decision Making Process");
			Thread.sleep(1300);
			//selecting PO-date
			act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.ARROW_LEFT).build().perform(); act.sendKeys(Keys.ARROW_RIGHT).build().perform();
			act.sendKeys(Keys.ENTER).build().perform();
			driver.findElements(By.xpath("//*[text() = 'Save']")).get(1).click();
			Thread.sleep(2400);
			//$50k+ Approval Questions
			App.click_xpath("//*[@placeholder = 'Enter Reasons']", "send_keys", "for testing Approval approval questions at");
			driver.findElements(By.xpath("//*[text() = 'Save']")).get(2).click();
			Thread.sleep(2000);
		} else if(total_value>=25000){
			System.out.println("under 25k");
			//$10k+ Approval Questions
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = '$10k+ Questions']")));
			driver.findElement(By.xpath("//*[contains(@class, 'indicatorContainer')]")).click();
			act.sendKeys(Keys.ENTER).build().perform();
			App.click_xpath("//*[@placeholder = 'Enter Competition']", "send_keys", "Test Competetion");
			App.click_xpath("//*[@placeholder = 'Enter Budgetary Amount']", "send_keys", "149.12");
			driver.findElements(By.xpath("//*[text() = 'Save']")).get(1).click();
			Thread.sleep(2000);
			//$25k+ Approval Questions
			Thread.sleep(1400);
			driver.findElements(By.xpath("//*[contains(@class, 'indicatorContainer')]")).get(1).click();
			act.sendKeys(Keys.ENTER).build().perform();
			App.click_xpath("//*[@placeholder = 'Enter Pain']", "send_keys", "Test Pain");
			App.click_xpath("//*[@placeholder = 'decision_making_process']", "send_keys", "Test Decision Making Process");
			driver.findElements(By.xpath("//*[text() = 'Save']")).get(1).click();
			Thread.sleep(2000);
			Thread.sleep(1400);

		}else if (total_value>=10000) {
			System.out.println("under 10k");
			//$10k+ Approval Questions
			driver.findElements(By.xpath("//*[contains(@class, 'indicatorContainer')]")).get(0).click();
			System.out.println("type");
			act.sendKeys(Keys.ENTER).build().perform();
			System.out.println("enter");
			App.click_xpath("//*[@placeholder = 'Enter Competition']", "send_keys", "Test Competetion");
			System.out.println("competition");
			App.click_xpath("//*[@placeholder = 'Enter Budgetary Amount']", "send_keys", "149.12");
			driver.findElements(By.xpath("//*[text() = 'Save']")).get(0).click();
			Thread.sleep(2000);
		}
		else {
			System.out.println("None of the above");
		}
	}
	public void verify_my_repair_repair_request_tab(String env) throws Exception
	{
		//create RMA
		//display pop up message
		App.displayPopUp("Verify pending quote status, should't display in My repair request");
		repair.createRMA();
		//Add Items to Repair
		this.select_items(1, "1234");
		//Reading RMA number into a variable
		String rma_number = driver.findElement(By.xpath("//*[@class = 'id-num']")).getText().replace("#", "");
		//Assign Location
		repair.assignLocation();
		//Assign Technician
		Thread.sleep(1200); Actions act = new Actions(driver);
		driver.findElement(By.xpath("//*[text()='Assign Technician']")).click();
		Thread.sleep(2000);
		App.click_react_dropdown(0);
		act.sendKeys("sivakrishna").build().perform();
		App.spinner();
		act.sendKeys(Keys.ENTER).build().perform();
		driver.findElement(By.xpath("//textarea[@placeholder= 'Type here']")).sendKeys("Internal Item Notes While Assign Technician");
		App.click_xpath("//*[text() = 'Assign']", "click", "");
		App.spinner(); Thread.sleep(1300);
		String url_repair= driver.getCurrentUrl();
		//
		App.click_xpath("//*[text() = 'Repairs']", "click", "");
		App.spinner(); Thread.sleep(1300); String exp_text = "Pending Evaluation";
		App.click_xpath("//*[text() = 'My Repair Requests']", "click", "");
		App.spinner(); Thread.sleep(1300); boolean result = false; String act_text = "";
		try {
			driver.findElement(By.xpath("//*[text()='Pending Evaluation']")).isDisplayed();
			act_text = driver.findElement(By.xpath("//*[text()='Pending Evaluation']")).getText();
			result = true;
		} catch (Exception e) {
			result = false;
		}
		if (result) {
			Object status[] = {"REPAIRS_009_Verify_My_Repair_Requests_Tab", "Actual displayed test is "+act_text+" "+rma_number, 
					"expected displayed text is "+exp_text+" "+rma_number, "QuotesPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"REPAIRS_009_Verify_My_Repair_Requests_Tab", "Actual displayed test is "+act_text+" "+rma_number, 
					"expected displayed text is "+exp_text+" "+rma_number, "QuotesPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		String url_my_repair= driver.getCurrentUrl();
		//Redirects to Repair detailed view
		driver.navigate().to(url_repair); App.spinner(); Thread.sleep(1300);
		repair.evaluateItem();
		//Redirect to My repair request tab list view
		driver.navigate().to(url_my_repair); App.spinner(); Thread.sleep(1300);
		try {
			driver.findElement(By.xpath("//*[text()='"+rma_number+"']")).isDisplayed();
			result = false;
			exp_text = "Pending Evaluation";
		} catch (Exception e) {
			result = true;
			exp_text = "Not Display that in My Repair Requests";
		}
		if (result) {
			try {
				driver.findElement(By.xpath("//*[text() = '"+rma_number+"']")).isDisplayed();
				result = false;
			} catch (Exception e) {
				result = true;
			}
			if (result) 
			{
				Object status[] = {"REPAIRS_025_Verify_Quote_Stattus_Pending_Quote_Should not_display_in_My_Repair_Requests_Tab", "Actual displayed RMA is "+rma_number, 
						"expected displayed RMA is "+rma_number, "QuotesPage", "Passed",
						java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}else {
				Object status[] = {"REPAIRS_025_Verify_Quote_Stattus_Pending_Quote_Should not_display_in_My_Repair_Requests_Tab", "Actual displayed RMA is "+rma_number, 
						"expected displayed RMA is "+rma_number, "QuotesPage", "Failed",
						java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}
		} else {
			Object status[] = {"REPAIRS_025_Verify_Quote_Stattus_Pending_Quote_Should not_display_in_My_Repair_Requests_Tab", "Actual displayed RMA is "+rma_number, 
					"expected displayed RMA is "+rma_number, "QuotesPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
	}
	public void verify_total_price_grid_detail_view(String env) throws Exception {
		//create Quote
		// Display warning Pop UP
		App.displayPopUp("QUOTES_027_VerifyPrices_In_Grid_and_Detailed_View_");
		try {
			driver.findElement(By.xpath("//*[text() = 'Open']")).isDisplayed();
			driver.findElement(By.xpath("//*[text() = 'Open']")).click();
		} catch (Exception e) {
			// TODO: handle exception		
			this.create_quote(); Thread.sleep(1300);
			this.select_items(3, "1234");
		}
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Items']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Options']"))); 
		Thread.sleep(2500);
		String total_price_det = driver.findElements(By.xpath("//*[contains(@class, 'width-auto')]")).get(2).findElement(By.tagName("h4")).getText();
		String quote_number = driver.findElement(By.className("id-num")).getText().replace("#", "");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//*[text()='Internal Approvals']")));
		Thread.sleep(2000);
		List<WebElement> stock_codes = driver.findElements(By.xpath("//*[@class = ' width-25 flexed']"));
		ArrayList<String> line_order_bef_clone = new ArrayList<String>();
		System.out.println("Before Clone the Quote Items line order");
		for(int i=0; i<stock_codes.size(); i++) {
			line_order_bef_clone.add(stock_codes.get(i).findElement(By.tagName("h4")).getText());
			System.out.println(line_order_bef_clone.get(i));
		}
		String items_count_bef_clone = String.valueOf(stock_codes.size());
		System.out.println("before clone the quote items count is "+items_count_bef_clone);
		//Click on Quotes module on header
		App.click_xpath("//*[text() = 'Quotes']", "click", "");
		App.spinner(); Thread.sleep(1300);
		//clear the Top Search
		App.clearTopSearch(); App.spinner(); Thread.sleep(1300);
		//Search with Quote id in quotes
		App.click_xpath("//*[contains(@placeholder, 'Quote ID / Company Name')]", "send_keys", quote_number);
		App.spinner(); Thread.sleep(2300);
		//read the Grand total in list view
		String total_price_list_view = App.getGridData(8);
		if (total_price_list_view.equals(total_price_det)) {
			Object status[] = {"QUOTES_027_VerifyPrices_In_Grid_and_Detailed_View_"+quote_number, "In grid price "+total_price_list_view, 
					"In detail view price "+total_price_det, "QuotesPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"QUOTES_027_VerifyPrices_In_Grid_and_Detailed_View_"+quote_number, "In grid price "+total_price_list_view, 
					"In detail view price "+total_price_det, "QuotesPage", "Failed", 
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		// Display warning Pop UP
		App.displayPopUp("QUOTES_030_VerifyPrices_In_Grid_and_Detailed_View_After_Clone_");
		//click on quote which in grid to redirects to detailed view
		App.click_xpath("//*[@class ='ag-react-container']", "click", "");
		App.spinner(); Thread.sleep(1300);
		App.click_xpath("//*[contains(@src, 'clone')]", "click", ""); Thread.sleep(1300);
		App.click_xpath("//*[text() = 'Search By Account ID or Company Name']", "click", "");
		String org_acc_number = driver.findElements(By.xpath("//*[contains(@class, 'calc-width-33')]")).get(5).findElement(By.tagName("p")).getText();
		act.sendKeys(org_acc_number).build().perform();
		Thread.sleep(3000); act.sendKeys(Keys.ENTER).build().perform();
		driver.findElements(By.xpath("//*[text() ='Proceed']")).get(1).click();
		Thread.sleep(1500);
		App.click_xpath("//*[text() ='Proceed']", "click", "");
		App.spinner(); Thread.sleep(1400);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Add Options')]")));

		String aft_clone_quote_number = driver.findElement(By.className("id-num")).getText().replace("#", "");
		Thread.sleep(2500);
		String aft_clone_total_price_det = driver.findElements(By.xpath("//*[contains(@class, 'width-auto')]")).get(2).findElement(By.tagName("h4")).getText();
		Thread.sleep(1300);
		App.click_xpath("//*[text() = 'Quotes']", "click", "");
		App.spinner(); Thread.sleep(1300);
		App.clearTopSearch(); App.spinner(); Thread.sleep(1300);
		//search with quote Id
		App.click_xpath("//*[contains(@placeholder, 'Quote ID / Company Name')]", "send_keys", aft_clone_quote_number);
		App.spinner(); Thread.sleep(2500);
		String aft_clone_total_price_list = App.getGridData(8);
		if (aft_clone_total_price_list.equals(aft_clone_total_price_det)) {
			Object status[] = {"QUOTES_030_VerifyPrices_In_Grid_and_Detailed_View_After_Clone_"+aft_clone_quote_number, "In grid price "+aft_clone_total_price_list, 
					"In detail view price "+aft_clone_total_price_det, "QuotesPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"QUOTES_030_VerifyPrices_In_Grid_and_Detailed_View_After_Clone_"+aft_clone_quote_number, "In grid price "+aft_clone_total_price_list, 
					"In detail view price "+aft_clone_total_price_det, "QuotesPage", "Failed", 
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
		// Display warning Pop UP
		App.displayPopUp("QUOTES_028_Verify_Items_Line_Order_After_Clone");
		Thread.sleep(1400);
		App.click_xpath("//*[@class ='ag-react-container']", "click", "");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Add Options')]")));
		List<WebElement> aft_clone_stock_codes = driver.findElements(By.xpath("//*[@class = ' width-25 flexed']"));
		ArrayList<String> aft_clone_line_order = new ArrayList<String>();
		System.out.println("After Clone the Quote Items line order");
		for(int i=0; i<stock_codes.size(); i++) {
			aft_clone_line_order.add(aft_clone_stock_codes.get(i).findElement(By.tagName("h4")).getText());
			System.out.println(aft_clone_line_order.get(i));
			//			aft_clone_stock_codes = driver.findElements(By.xpath("//*[@class = ' width-25 flexed']"));
		}
		String aft_clone_items_count_bef_clone = String.valueOf(aft_clone_stock_codes.size());
		System.out.println("After clone the quote items count is "+aft_clone_items_count_bef_clone);
		boolean res = false;
		for(int c =0; c<aft_clone_stock_codes.size(); c++) 
		{
			if(aft_clone_line_order.get(c).equals(line_order_bef_clone.get(c))) {
				res = true;
			}else {
				res = false;
			}
		}
		if (res) {
			Object status[] = {"QUOTES_028_Verify_Items_Line_Order_After_Clone", "after clone order is "+aft_clone_line_order, 
					"before clone order is "+line_order_bef_clone, "QuotesPage", "Passed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"QUOTES_028_Verify_Items_Line_Order_After_Clone", "after clone order is "+aft_clone_line_order, 
					"before clone order is "+line_order_bef_clone, "QuotesPage", "Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}

		if (aft_clone_total_price_det.equals(total_price_det)) 
		{
			Object status[] = {"QUOTES_029_VerifyPrices_In_Grid_and_Detailed_View_After and Before_Clone", "after clone total price in det "+aft_clone_total_price_det, 
					"before clone total price in detail view "+total_price_det, "QuotesPage", "Passed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		} else {
			Object status[] = {"QUOTES_029_VerifyPrices_In_Grid_and_Detailed_View_After and Before_Clone", "after clone total price in det "+aft_clone_total_price_det, 
					"before clone total price in detail view "+total_price_det, "QuotesPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
	}
	public boolean create_quote() throws Exception 
	{	
		QuotePages quotes = new QuotePages();	
		driver.findElement(By.xpath("//*[text() = 'Quotes']")).click();
		App.spinner(); Thread.sleep(1400);
		App.clearTopSearch(); Thread.sleep(1300);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			App.clearTopSearch(); Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace()[0]);
		}
		App.spinner(); boolean create = false; String quo_status = "Open1";
		try {
			driver.findElement(By.xpath("//*[text() = '"+quo_status+"']")).isDisplayed();
			driver.findElement(By.xpath("//*[text() = '"+quo_status+"']")).click();
			create = false;
		} catch (Exception e) 
		{
			String custName = "Motion Industries - Grand Prairie";
			driver.findElement(By.xpath("//*[@class='button-icon-text ']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='async-select-example']")));
			driver.findElement(By.xpath("//*[@id='async-select-example']")).sendKeys(custName);
			App.spinner();
			Thread.sleep(1200);
			driver.findElement(By.xpath("//*[contains(@class, 'css-4mp3pp-menu')]")).click();
			App.spinner();Thread.sleep(1000);
			driver.findElement(By.name("project_name")).sendKeys("Test");
			driver.findElement(By.xpath("//*[contains(@id,'react-select')]")).sendKeys("System Quote");
			Thread.sleep(2500);
			quotes.selectDropDown("System Quote");
			Thread.sleep(1500);
			driver.findElement(By.xpath("//*[@class='side-drawer open']")).findElement(By.tagName("button")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Items']")));
			Thread.sleep(1600);
			create = true;
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Items']")));
		Thread.sleep(1600);
		return create;
	}
	public void org_search(String org_name) throws Exception {
		driver.findElements(By.xpath("//*[text()='Organizations']")).get(0).click();
		driver.findElements(By.xpath("//*[text()='Organizations']")).get(1).click();
		App.spinner(); Thread.sleep(1200);
		try {
			driver.findElement(By.xpath("//*[@style='padding: 10px 10px 10px 0px; display: flex; align-items: center; cursor: pointer;']")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		App.spinner(); Thread.sleep(1200);
		driver.findElement(By.xpath("//*[contains(@placeholder, 'Account Number')]")).sendKeys(org_name);
		App.spinner(); Thread.sleep(1300);
	}
	public Object[] createJob(String orderId) throws Exception
	{
		Actions act = new Actions(driver); PricingPages price = new PricingPages();
		driver.findElement(By.xpath("//*[text()= 'Jobs']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='button-icon-text']")));
		driver.findElement(By.xpath("//*[@class= 'button-icon-text']")).click();
		Thread.sleep(1500);
		driver.findElements(By.xpath("//*[contains(@class,'dropdown-indicator')]")).get(0).click();
		act.sendKeys(orderId).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(). '"+orderId+"')]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@style = 'animation-delay: 0ms;']")));
		act.sendKeys(Keys.ENTER).build().perform();
		//		quotes.selectDropDown(orderId);
		Thread.sleep(500);
		driver.findElements(By.xpath("//*[contains(@class,'dropdown-indicator')]")).get(1).click();
		Thread.sleep(1300);
		act.sendKeys(Keys.ENTER).build().perform();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@style = 'animation-delay: 0ms;']")));
		Thread.sleep(1600);
		driver.findElement(By.name("job_description")).sendKeys("Test Job Description");
		driver.findElement(By.name("job_description")).click();
		act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.ARROW_RIGHT).build().perform();
		act.sendKeys(Keys.ENTER).build().perform();
		price.clickButton("Create Job");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@style = 'animation-delay: 0ms;']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@style = 'animation-delay: 0ms;']")));
		boolean createJob = false; String serverMsg = "";
		try {
			driver.findElement(By.xpath("//*[@class='side-drawer open']")).isDisplayed();
			price.takesScreenShot("CreateJob_Failed.png");
			driver.findElement(By.xpath("//*[@class='server-msg']")).isDisplayed();
			serverMsg = driver.findElement(By.xpath("//*[@class='server-msg']")).getText();
			createJob = false;
		} catch (Exception e) {
			createJob = true;
		}
		Object[] vals = {serverMsg, createJob};
		return vals;
	}
	public void select_items(int count, String item) throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Items']")));
		Thread.sleep(1300);
		Actions act = new Actions(driver);
		App.click_xpath("//*[text() = 'Add Items']", "click", "");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='side-drawer open']")));
		Thread.sleep(1300);
		App.click_xpath("//*[@placeholder='Search By Part Number']", "send_keys", item);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name = 'quantity_0']")));
		Thread.sleep(2000);
		App.click_xpath("//*[@placeholder='Search By Part Number']", "click", "");

		switch (count) {
		case 1:
			act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			break;
		case 2:
			act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			break;
		case 3:
			act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			break;
		case 4:
			act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			break;
		case 5:
			act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			break;
		case 6:
			act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			break;
		case 7:
			act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			break;
		case 8:
			act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			break;
		case 9:
			act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			break;
		case 10:
			act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			act.sendKeys(Keys.TAB).build().perform(); act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.SPACE).build().perform();
			break;
		}
		String tab = "";
		if (driver.getCurrentUrl().contains("quote")) {
			tab = "Items";
		} else {
			tab = "Parts";
		}
		App.click_xpath("//*[text() = 'Add Selected "+count+" "+tab+"']", "click", "");
		App.spinner();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Items']")));
		Thread.sleep(1300);
	}
	public void check_change_item_line_order(String env) throws Exception
	{
		//Display Pop Up
		App.displayPopUp("QUOTES_031_Verify_Change_Items_Order");
		//create Quote
		App.clearTopSearch(); Thread.sleep(1300);
		boolean create = this.create_quote();
		Actions act = new Actions(driver);
		if(create) 
		{
			this.select_items(3, "1234");
		}else {}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Options']"))); 
		Thread.sleep(2000);
		System.out.println("before changing the order, items order is ");
		List<WebElement> bef_items_ord = driver.findElements(By.xpath("//*[@class = ' width-25 flexed']"));
		ArrayList<String> bef_array = new ArrayList<String>();
		for(int b=0; b<bef_items_ord.size(); b++) 
		{
			String sc = bef_items_ord.get(b).findElement(By.tagName("h4")).getText();
			bef_array.add(sc);
		}
		App.click_xpath("//*[contains(@src, 'repeat')]", "click", "");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Part Number']")));
		Thread.sleep(1400);
		List<WebElement> items_chng_order = driver.findElements(By.xpath("//*[@class = 'drop-item']"));
		ArrayList<String> array1 = new ArrayList<String>();
		//		System.out.println("items count in grid is "+items_chng_order.size());
		System.out.println("items order before change order");
		for(int i=0; i<items_chng_order.size(); i++) 
		{
			String sc = items_chng_order.get(i).findElements(By.tagName("h4")).get(1).getText();
			if(sc.equals(bef_array.get(i))) 
			{				
				array1.add(sc);
			}
			System.out.println(1+i+" item is "+array1.get(i));
		}
		WebElement source = items_chng_order.get(1);
		WebElement target = items_chng_order.get(2);
		act.moveToElement(source).build().perform();
		act.clickAndHold(source).build().perform();
		Thread.sleep(1200);
		act.moveToElement(target).build().perform();
		act.release(source).build().perform();
		Thread.sleep(2000);
		List<WebElement> aft_items_chng_order = driver.findElements(By.xpath("//*[@class = 'drop-item']"));
		ArrayList<String> array2 = new ArrayList<String>();
		//		System.out.println("items count in grid is "+items_chng_order.size());
		System.out.println("items order after change order");
		for(int i=0; i<aft_items_chng_order.size(); i++) 
		{
			String sc = aft_items_chng_order.get(i).findElements(By.tagName("h4")).get(1).getText();
			array2.add(sc);
			System.out.println(1+i+" item is "+array2.get(i));
		}
		App.click_xpath("//*[text() = 'Save']", "click", "");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Options']")));
		Thread.sleep(2500);
		List<WebElement> aft_items_ord = driver.findElements(By.xpath("//*[@class = ' width-25 flexed']"));
		ArrayList<String> aft_array = new ArrayList<String>();
		for(int b=0; b<aft_items_ord.size(); b++) 
		{
			String sc = aft_items_ord.get(b).findElement(By.tagName("h4")).getText();
			aft_array.add(sc);
		}
		Thread.sleep(1200);
		if (array2.get(0).equals(aft_array.get(0))) 
		{	
			if (array2.get(1).equals(aft_array.get(1))) 
			{
				if (array2.get(2).equals(aft_array.get(2))) 
				{
					Object status[] = {"QUOTES_031_Verify_Change_Items_Order", "before change the items order "+bef_array, 
							"after change the items order "+aft_array, "QuotesPage", "Passed",
							java.time.LocalDateTime.now().toString(), env};
					App.values1(status);
				} else 
				{
					Object status[] = {"QUOTES_031_Verify_Change_Items_Order", "before change the items order "+bef_array, 
							"after change the items order "+aft_array, "QuotesPage", "Failed",
							java.time.LocalDateTime.now().toString(), env};
					App.values1(status);
				}	
			} else
			{
				Object status[] = {"QUOTES_031_Verify_Change_Items_Order", "before change the items order "+bef_array, 
						"after change the items order "+aft_array, "QuotesPage", "Failed",
						java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}	
		} else
		{
			Object status[] = {"QUOTES_031_Verify_Change_Items_Order", "before change the items order "+bef_array, 
					"after change the items order "+aft_array, "QuotesPage", "Failed",
					java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
		}
	}
	public void left_menu_tab_list(String env) throws Exception
	{
		//click receiving
		ResultSet rs  = App.adminTabs("SELECT * FROM repairs_left_menu_tabs;");
		System.err.println(rs); 
		String tabName = ""; String stas = ""; int tcCount =0;
		while(rs.next()) {
			//display Pop up message
			App.displayPopUp("REPAIRS_0"+tcCount+"_Verify_"+tabName+"_Tab_in_left_menu");

			System.out.println(rs.getString(1));
			tcCount = rs.getInt("tc_count");
			tabName = rs.getString("tab_name");
			stas = rs.getString("status");
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//*[text() = '"+tabName+"']"))).build().perform();
			Thread.sleep(1300);
			App.click_xpath("//*[text() = '"+tabName+"']", "click", ""); String act_sta = "";
			App.spinner(); Thread.sleep(1300); boolean res = false;
			try {
				WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(10));
				wb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@src, 'vendor_logo')]")));
				driver.findElement(By.xpath("//*[@class = 'ag-react-container']")).isDisplayed();
				List<WebElement> receiving_list = driver.findElements(By.xpath("//*[@class = 'ag-react-container']"));
				for(int i =0; i <receiving_list.size(); i++) 
				{
					act_sta = receiving_list.get(i).getText();
					if (stas.toLowerCase().contains(act_sta.toLowerCase())) {
						res = true;
					} else {
						res = false;
						break;
					}
				}
			}catch (Exception e) {
				act_sta = "Grid is empty!";
				res = false;
			}
			if (res) {
				Object status[] = {"REPAIRS_0"+tcCount+"_Verify_"+tabName+"_Tab_in_left_menu", "actual status "+act_sta, 
						"expected status "+stas, "RepairsPage", "Passed",
						java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			} else {
				Object status[] = {"REPAIRS_0"+tcCount+"_Verify_"+tabName+"_Tab_in_left_menu", "actual status "+act_sta, 
						"expected status "+stas, "RepairsPage", "Failed",
						java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			}
		}
	}
	public boolean verifyCreateJob(String tcName ,String salesOrderId, int count, String env) throws Exception
	{
		String orderId = "";
		if (count==1) {
			driver.findElement(By.xpath("//*[text()= 'Orders']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@placeholder,'Sales Order ID')]")));
			driver.findElement(By.xpath("//*[contains(@placeholder,'Sales Order ID')]")).sendKeys(salesOrderId);
			Thread.sleep(1500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='OPEN ORDER']")));
			Thread.sleep(1500);
			driver.findElement(By.xpath("//*[text()='OPEN ORDER']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='id-num']")));
			orderId = driver.findElement(By.xpath("//*[@class='id-num']")).getText().replace("#", "");
		} else {
			orderId = salesOrderId;
		}
		Object val[] = this.createJob(orderId); boolean res = false;
		if ((Boolean) val[1]) {
			String actJobStatus = driver.findElement(By.xpath("//*[@title='[object Object]']")).getText();
			String expJobStatus = "Completed";
			if (actJobStatus.toLowerCase().equals(expJobStatus.toLowerCase())) {
				res = true;
				Object status[] = {tcName, actJobStatus, expJobStatus, "JobsPage", "Passed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			} else {
				res = false;
				Object status[] = {tcName, actJobStatus, expJobStatus, "JobsPage", "Failed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
				price.closeIcon();
			}
		} else {
			res = false;
			Object status[] = {tcName, val[0], "", "JobsPage", "Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
			price.closeIcon();
		}
		return res;
	}
	public void createPartsPurchase(String tcName, String jobId, int count, String env) throws Exception 
	{
		Actions act = new Actions(driver);

		driver.findElement(By.xpath("//*[text()= 'Next']")).click();
		App.spinner(); Thread.sleep(2000);
		App.click_react_dropdown(2);
		act.sendKeys("ENTERPI").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ENTERPI')]")));
		act.sendKeys(Keys.ENTER).build().perform();
		driver.findElement(By.name("vendor_contact_name")).sendKeys("raghuvardhan");
		driver.findElements(By.xpath("//*[text()= 'Next']")).get(1).click();
		App.spinner(); Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@placeholder = 'Enter Cost']")).sendKeys("167");
		driver.findElement(By.xpath("//*[@placeholder = 'Enter Vendor Part Number']")).sendKeys("VPN1234!@#");
		Thread.sleep(1500);
		driver.findElements(By.xpath("//*[text() = 'Create']")).get(1).click();
		App.spinner(); Thread.sleep(1500); String pp_id = "";
		boolean createPp = false; String serverMsg = "";
		try {
			driver.findElement(By.xpath("//*[@class='side-drawer open']")).isDisplayed();
			price.takesScreenShot("CreatePartsPurchase_Failed.png");
			driver.findElement(By.xpath("//*[@style='color: red;']")).isDisplayed();
			serverMsg = driver.findElement(By.xpath("//*[@style='color: red;']")).getText();
			createPp = false;
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Requestor Information']")));
			driver.findElement(By.xpath("//*[text() = 'Requestor Information']")).isDisplayed();
			createPp = true;
		}
		if (createPp) {
			pp_id = driver.findElement(By.xpath("//*[@class='id-num']")).getText().replace("#", "");
			String expPpStatus = driver.findElement(By.xpath("//*[@class='quote-num-and-status']")).getText();
			if (expPpStatus.toLowerCase().contains("requested")) {
				Object status[] = {tcName, "Parts purchase created with Id"+pp_id, expPpStatus, "PartsPurchasePage", "Passed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
			} else {
				Object status[] = {tcName, "Parts Purchase is not created!", "", "PartsPurchasePage", "Failed", java.time.LocalDateTime.now().toString(), env};
				App.values1(status);
				price.closeIcon();
			}
		} else {
			Object status[] = {tcName, serverMsg, "", "PartsPurchasePage", "Failed", java.time.LocalDateTime.now().toString(), env};
			App.values1(status);
			price.closeIcon();
		}
		
	}
}
