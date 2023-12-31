package libraries;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonUtils.App;

public class Permissions_QuotesPages extends Permissions
{
	public String[] quoteForPartsPermissionAsNone(String itemName, String tabName, String labelName,  int tabCount ,int count) throws Exception
	{
		this.userTab(itemName, tabName);
		List<WebElement> labelsText = driver.findElements(By.xpath("//*[@class='permission-outer-border']")).get(tabCount).findElements(By.xpath("//*[@class='permission']"));
		String path = "";String  url = "";
		for(int i=0; i<labelsText.size(); i++) 
		{
			//			System.out.println(labelsText.get(i).findElements(By.tagName("span")).get(0).getText());// edit=4 :: view=3 :: none=2
			if(labelsText.get(i).findElements(By.tagName("span")).get(0).getText().equalsIgnoreCase(labelName)) 
			{
				System.out.println("label name "+labelsText.get(i).findElements(By.tagName("span")).get(0).getText());
				path = labelsText.get(i).findElement(By.tagName("input")).getAttribute("name");
				labelsText.get(i).findElements(By.tagName("span")).get(count).click();
				price.clickButton("Save");
				Thread.sleep(1500);
				price.clickButton("Accept");
				url = driver.getCurrentUrl();
				break;
			}
		}
		String vals[] = {url, path};
		return vals;
	}
	public boolean verifyQuotesPermissionAsNone(String tcName, String itemName, String tabName, String labelName,  int tabCount ,int count, String env) throws Exception
	{
		//Pop Up message
		App.displayPopUp(tcName);

		String actURL[] = this.quoteForPartsPermissionAsNone(itemName, tabName, labelName, tabCount, count);
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		System.out.println("special pricing url is "+actURL[0].replace("users", actURL[1]));
		Thread.sleep(2000);
		String message = "";String expText = "";
		System.out.println("paragraphs tags are "+driver.findElements(By.tagName("p")).size());
		if(count==2) {
			Thread.sleep(1000);
			message = driver.findElements(By.tagName("p")).get(0).getText();
			expText = "Sorry, you do not have permissions to access this page.";

		}else {
			message = driver.findElement(By.className("add-Icon")).getText();
			expText = "Filters";
		}
		boolean res = false;
		if (message.equalsIgnoreCase(expText)) {
			res = true;
			Object status[] = {tcName, message, "Top displayed text is "+message, "Quotes_Permissions", "Passed", "", env};
			App.values1(status);
		} else {
			res = false;
			Object status[] = {tcName, message, "Top displayed text is "+message, "Quotes_Permissions", "Failed", "", env};
			App.values1(status);
		}
		this.verifyAdminTabswithNonePermission(itemName, tabName, labelName, 4);
		return res;
	}
	public String[] createSalesOrderPermissionAsYes(String itemName, String tabName, String labelName, int childCount, int count) throws Exception
	{
		this.userTab(itemName, tabName);
		List<WebElement> labelsText = driver.findElements(By.xpath("//*[@class='permission-outer-border']")).get(3).findElements(By.xpath("//*[@class='permission']"));
		String path = "";String  url = "";String xpath1 = "";
		for(int i=0; i<labelsText.size(); i++) 
		{
			//			System.out.println(labelsText.get(i).findElements(By.tagName("span")).get(0).getText());// edit=4 :: view=3 :: none=2
			if(labelsText.get(i).findElements(By.tagName("span")).get(0).getText().equalsIgnoreCase(labelName)) 
			{
				Thread.sleep(1500);
				System.out.println("label name "+labelsText.get(i).findElements(By.tagName("span")).get(0).getText());
				path = labelsText.get(i).findElement(By.tagName("input")).getAttribute("name");
				xpath1 = "/html/body/div/div/div[4]/div[2]/div[2]/div/div/div/div[3]/div[1]/div/div/div[4]/div/div/div[2]/div/div["+childCount+"]/span[2]/div/div/div/label["+count+"]";
				String xpath = "/html/body/div/div/div[4]/div[2]/div[2]/div/div/div/div[3]/div[1]/div/div/div[4]/div/div/div[2]/div/div["+childCount+"]/span[2]/div/div/div/label["+count+"]";
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath(xpath)));
				Thread.sleep(500);
				driver.findElement(By.xpath(xpath)).click();
				price.clickButton("Save");
				Thread.sleep(1500);
				price.clickButton("Accept");
				url = driver.getCurrentUrl();
				break;
			}
		}
		String vals[] = {url, path, xpath1};
		return vals;
	}
	public boolean verifyCreateSalesOrderPermissionAsYes(String tcName, String itemName, String tabName, String labelName, int childCount, int count, String env) throws Exception
	{
		//Pop Up message
		App.displayPopUp(tcName);
		String actURL[] =this.createSalesOrderPermissionAsYes(itemName, tabName, labelName, childCount, count);
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		RepairPages repair = new RepairPages();
		if (count==2) {
			try {
				Thread.sleep(500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Approved']")));
				driver.findElement(By.xpath("//*[text() = 'Approved']")).click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(18));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Pay Terms']")));
			Thread.sleep(1600);
			driver.findElement(By.xpath("//*[@class='quote-num-and-status']")).findElement(By.tagName("button")).click();
			driver.findElement(By.xpath("//*[@role='menuitem']")).click();
			Thread.sleep(2500);
			driver.findElement(By.xpath("//*[text()='Won']")).click();
			Thread.sleep(300);
			repair.toastContainer("Proceed");
			Thread.sleep(500);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@style='animation-delay: 0ms; width: 50px; height: 50px;']")));
		}else {
			try {
				Thread.sleep(500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Won']")));
				driver.findElement(By.xpath("//*[text() = 'Won']")).click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Pay Terms']")));
		}
		Thread.sleep(1500);
		String message = ""; boolean sta = false;
		if(childCount==2) {
			message = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[2]")).getText();
			if(count==1) 
			{
				try {
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[text()='Create Sales Order']")).isDisplayed();
					sta = true;
				} catch (Exception e) {
					sta = false;
				}

			}else if(count==2) {
				try {
					Thread.sleep(1000);
					driver.findElement(By.xpath("//*[text()='Create Sales Order']")).isDisplayed();
					sta = false;
				} catch (Exception e) {
					sta = true;
				}
			}

		}else if(childCount==5) {
			message = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[2])")).getText();
			//			expText = "Filters";
		}
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		System.out.println("special pricing url is "+actURL[0].replace("users", actURL[1]));
		Thread.sleep(2000);
		System.out.println("paragraphs tags are "+driver.findElements(By.tagName("p")).size());
		boolean res = false;
		if (sta) {
			res = true;
			Object status[] = {tcName, message, "Top displayed text is "+message, "Quotes_Permissions", "Passed", "", env};
			App.values1(status);
		} else {
			res = false;
			Object status[] = {tcName, message, "Top displayed text is "+message, "Quotes_Permissions", "Failed", env};
			App.values1(status);
		}
		this.verifyAdminTabswithNonePermission(itemName, tabName, labelName, 4);
		return res;
	}
	public boolean verifyQuoteClosePermissionAsYes_Quotes(String tcName, String itemName, String tabName, String labelName, int childCount, int count, String env) throws Exception
	{
		//Warning Pop Up
		App.displayPopUp(tcName);
		String actURL[] =this.createSalesOrderPermissionAsYes(itemName, tabName, labelName, childCount, count);
		QuotePages quotes = new QuotePages();RepairPages repair = new RepairPages();
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		App.spinner(); Thread.sleep(1500);
		if (count==2) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Rejected']")));
				driver.findElement(By.xpath("//*[text() = 'Rejected']")).click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				quotes.submitForInternalApproval();
				PricingPages price = new PricingPages();
				Thread.sleep(1600);
				price.clickButton("Approve");
				repair.toastContainer("Decline");
				App.click_xpath("//*[text() = 'Proceed']", "click", "");
				wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//*[text() = 'Approve']"), "Approve"));
			}
		}else {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Rejected']")));
				driver.findElement(By.xpath("//*[text() = 'Rejected']")).click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Pay Terms']")));
		Thread.sleep(1500);
		String message = "";boolean sta = false;
		if (count==2) {
			message = driver.findElement(By.xpath("/html/body/div/div/div[3]/div[1]/div[2]")).getText();
			try {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[text()='Close']")).isDisplayed();
				sta = false;
			} catch (Exception e) {
				sta = true;
			}
		} else if(count==1) {
			message = driver.findElement(By.xpath("/html/body/div/div/div[3]/div[1]/div[2]")).getText();
			try {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[text()='Close']")).isDisplayed();
				message = "";
				sta = true;
			} catch (Exception e) {
				sta = false;
			}
		}
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		System.out.println("special pricing url is "+actURL[0].replace("users", actURL[1]));
		Thread.sleep(2000);
		System.out.println("paragraphs tags are "+driver.findElements(By.tagName("p")).size());
		boolean res = false;
		if (sta) {
			res = true;
			Object status[] = {tcName, message, "Top displayed text is "+message, "Quotes_Permissions", "Passed", "",env};
			App.values1(status);
		} else {
			res = false;
			Object status[] = {tcName, message, "Top displayed text is "+message, "Quotes_Permissions", "Failed", "", env};
			App.values1(status);
		}
		this.verifyAdminTabswithNonePermission(itemName, tabName, labelName, 4);
		return res;
	}
	public boolean verifyEditIIDMCostPermissionAsYes_Quotes(String tcName, String itemName, String tabName, String labelName, int childCount, int count, String env) throws Exception
	{
		//Pop Up message
		App.displayPopUp(tcName);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); Actions act = new Actions(driver);
		String actURL[] =this.createSalesOrderPermissionAsYes(itemName, tabName, labelName, childCount, count);
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		try {
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Open']")));
			driver.findElement(By.xpath("//*[text() = 'Open']")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Options']")));
		Thread.sleep(1000);
		act.moveToElement(driver.findElements(By.xpath("//*[contains(@src,'themecolorEdit')]")).get(1)).build().perform();
		act.click(driver.findElements(By.xpath("//*[contains(@src,'themecolorEdit')]")).get(1)).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Quote Price']")));
		Thread.sleep(1500);String expText = "";
		String actText = driver.findElement(By.name("iidm_cost")).getAttribute("disabled");
		driver.findElement(By.xpath("//*[@title='close']")).click();
		System.out.println("Status iidm cost is "+actText);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div[1]/div/div[3]/div[2]/button"), "Approve"));
		Thread.sleep(1500);boolean sta = false;
		if (count==2) {
			expText = "true";
			sta = actText.equals(expText);
		} else if(count==1) {
			expText = null;
			sta= (actText==expText);
		}
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		System.out.println("special pricing url is "+actURL[0].replace("users", actURL[1]));
		Thread.sleep(2000);
		System.out.println("paragraphs tags are "+driver.findElements(By.tagName("p")).size());
		boolean res = false;
		if (sta) {
			res = true;
			Object status[] = {tcName, actText, "Top displayed text is "+actText, "Quotes_Permissions", "Passed", "", env};
			App.values1(status);
		} else {
			res = false;
			Object status[] = {tcName, actText, "Top displayed text is "+actText, "Quotes_Permissions", "Failed", "", env};
			App.values1(status);
		}
		this.verifyAdminTabswithNonePermission(itemName, tabName, labelName, 4);
		return res;
	}
	public boolean verifyQuoteReOpenPermissionAsYes_Quotes(String tcName, String itemName, String tabName, String labelName, int childCount, int count, String env) throws Exception
	{
		//Warning Pop Up
		App.displayPopUp(tcName);

		String actURL[] =this.createSalesOrderPermissionAsYes(itemName, tabName, labelName, childCount, count);
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Rejected']")));
			driver.findElement(By.xpath("//*[text() = 'Rejected']")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Pay Terms']")));
		Thread.sleep(1500);
		String message = ""; boolean sta = false;
		if (count==2) {

			message = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[2]")).getText();
			try {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[text()='Re Open']")).isDisplayed();
				sta = false;
			} catch (Exception e) {
				sta = true;
			}
		} else if(count==1) {
			message = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[2]")).getText();
			try {
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[text()='Re Open']")).isDisplayed();
				sta = true;
			} catch (Exception e) {
				sta = false;
			}
		}
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		System.out.println("special pricing url is "+actURL[0].replace("users", actURL[1]));
		Thread.sleep(2000);
		System.out.println("paragraphs tags are "+driver.findElements(By.tagName("p")).size());
		boolean res = false;
		if (sta) {
			res = true;
			Object status[] = {tcName, message, "Top displayed text is "+message, "Quotes_Permissions", "Passed", "", env};
			App.values1(status);
		} else {
			res = false;
			Object status[] = {tcName, message, "Top displayed text is "+message, "Quotes_Permissions", "Failed", "", env};
			App.values1(status);
		}
		this.verifyAdminTabswithNonePermission(itemName, tabName, labelName, 4);
		return res;
	}
	public boolean verifyReviseQuotePermissionAsYes_Quotes(String tcName, String itemName, String tabName, String labelName, int childCount, int count, String env) throws Exception
	{
		//Pop Up message
		App.displayPopUp(tcName);
		String actURL[] =this.createSalesOrderPermissionAsYes(itemName, tabName, labelName, childCount, count);
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		if (count==2) {
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Open']")));
				driver.findElement(By.xpath("//*[text() = 'Open']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Items']")));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			this.submitForInternalApproval();
		} else {
			try {
				Thread.sleep(500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Open']")));
				driver.findElement(By.xpath("//*[text() = 'Open']")).click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			Thread.sleep(1500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Items']")));
			this.submitForInternalApproval();
		}
		Thread.sleep(1500);
		boolean message = false;boolean sta = false;
		if (count==2) {
			try {
				message = driver.findElement(By.xpath("//*[text()='Revise Quote']")).isDisplayed();
				sta = false;
			} catch (Exception e) {
				sta = true;
			}
		} else if(count==1) {
			try {
				message = driver.findElement(By.xpath("//*[text()='Revise Quote']")).isDisplayed();
				sta = true;
			} catch (Exception e) {
				sta = false;
			}
		}
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		System.out.println("special pricing url is "+actURL[0].replace("users", actURL[1]));
		Thread.sleep(2000);
		System.out.println("paragraphs tags are "+driver.findElements(By.tagName("p")).size());
		boolean res = false;
		if (sta) {
			res = true;
			Object status[] = {tcName, "Revise Quote is Displayed "+message, "", "Quotes_Permissions", "Passed", "", env};
			App.values1(status);
		} else {
			res = false;
			Object status[] = {tcName, "Revise Quote is Displayed "+message, "", "Quotes_Permissions", "Failed", "", env};
			App.values1(status);
		}
		this.verifyAdminTabswithNonePermission(itemName, tabName, labelName, 4);
		return res;
	}
	public void submitForInternalApproval() throws Exception
	{
		WebElement rfq = driver.findElement(By.xpath("//*[@title='RFQ Received Date']"));
		Thread.sleep(1500);
		Actions act = new Actions(driver);
		act.moveToElement(rfq).build().perform();
		Thread.sleep(1000);
		List<WebElement> edits = driver.findElement(By.xpath("//*[@id='repair-info-id']")).findElements(By.className("pi-label-edit-icon"));
		edits.get(1).click();
		driver.findElement(By.xpath("//*[@id='repair-info-id']")).findElement(By.tagName("button")).click();
		driver.findElement(By.xpath("//*[@title='Save Changes']")).click();
		Thread.sleep(2000);
		Thread.sleep(1700);
		WebElement qReqBy = driver.findElement(By.xpath("//*[@title='Quote Requested By']"));
		Actions action = new Actions(driver);
		action.moveToElement(qReqBy).build().perform();
		Thread.sleep(1000);
		edits.get(3).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(@class,'react-select__control')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(@class,'css-4mp3pp-menu')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@title='Save Changes']")).click();
		Thread.sleep(2500);
		act.moveToElement(driver.findElements(By.xpath("//*[contains(@src,'themecolorEdit')]")).get(1)).build().perform();
		act.moveToElement(driver.findElements(By.xpath("//*[contains(@src,'themecolorEdit')]")).get(1)).build().perform();
		act.click(driver.findElements(By.xpath("//*[contains(@src,'themecolorEdit')]")).get(1)).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@placeholder='Quote Price']")));
		Thread.sleep(1200);
		//Update source
		driver.findElements(By.xpath("//*[contains(@class,'dropdown-indicator')]")).get(1).click();
		Thread.sleep(1200);
		act.sendKeys(Keys.ENTER).build().perform();
		//Update leadTime
		driver.findElements(By.xpath("//*[contains(@class,'dropdown-indicator')]")).get(2).click();
		Thread.sleep(1200);
		act.sendKeys("Week(s)").build().perform();
		act.sendKeys(Keys.ENTER).build().perform();
		driver.findElement(By.xpath("//*[@name='lead_time_value']")).sendKeys("3");
		App.click_xpath("//*[text()='Save']", "click", "");
		App.spinner(); Thread.sleep(2500);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//*[contains(text() , 'Quote Items')]")));
		Thread.sleep(1300); String path = "";
		if (driver.getCurrentUrl().contains("web")) {
			path = "//*[contains(@name, 'checkbox')]";
		}else {
			path = "//*[contains(@style, 'checkbox-tick-color')]";
		}
		List<WebElement> check_boxes =driver.findElements(By.xpath(path));
		for(int i =0; i<check_boxes.size(); i++) 
		{
			check_boxes.get(i).click();	
		}
		App.click_xpath("//*[contains(@title, 'Edit Item')]", "click", "");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Source')]")));
		App.click_react_dropdown(0); Thread.sleep(1300);
		act.sendKeys(Keys.ENTER).build().perform();
		App.click_xpath("//*[text()='Save']", "click", "");
		App.spinner(); Thread.sleep(1400);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@src,'delete-icon')]")));
		Thread.sleep(1400); String btn_name = "";
		double total_price = Double.parseDouble(driver.findElements(By.xpath("//*[contains(@class ,'width-auto')]")).get(2).findElement(By.tagName("h4")).getText().replace("$", "").replace(",", ""));
		if(total_price<=10000) {
			driver.findElement(By.xpath("//*[text() = 'Approve']")).click();
			btn_name = "Approve";
			Thread.sleep(1200);
			rp.toastContainer("Approve");
			Thread.sleep(2000);
		}else {
			driver.findElement(By.xpath("//*[text() = 'Submit for Internal Approval']")).click();
			btn_name = "Submit for Internal Approval";
			Thread.sleep(2000);
			rp.toastContainer("Proceed");
		}
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//*[text() = '"+btn_name+"']"), btn_name));
	}
	public boolean verifySendToCustomerPermissionAsYes_Quotes(String tcName, String itemName, String tabName, String labelName, int childCount, int count, String env) throws Exception
	{
		//Pop Up message
		App.displayPopUp(tcName);

		String actURL[] =this.createSalesOrderPermissionAsYes(itemName, tabName, labelName, childCount, count);
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		RepairPages repair = new RepairPages(); QuotePages quotes = new QuotePages();
		if (count==2) {
			try {
				Thread.sleep(500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Approved']")));
				driver.findElement(By.xpath("//*[text() = 'Approved']")).click();
			} catch (Exception e) {
				quotes.quoteApprove();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Pay Terms']")));
		} else {
			try {
				Thread.sleep(500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Approved']")));
				driver.findElement(By.xpath("//*[text() = 'Approved']")).click();
			} catch (Exception e) {
				quotes.quoteApprove();
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Pay Terms']")));
		}

		boolean message = false; boolean sta = false;
		if (count==2) {
			try {
				message = driver.findElement(By.xpath("//*[text()='Submit for Customer Approval']")).isDisplayed();
				sta = false;
			} catch (Exception e) {
				sta = true;
			}
		} else if(count==1) {
			try {
				message = driver.findElement(By.xpath("//*[text()='Submit for Customer Approval']")).isDisplayed();
				sta = true;
			} catch (Exception e) {
				sta = false;
			}
		}
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		System.out.println("special pricing url is "+actURL[0].replace("users", actURL[1]));
		Thread.sleep(2000);
		System.out.println("paragraphs tags are "+driver.findElements(By.tagName("p")).size());
		boolean res = false;
		if (sta) {
			res = true;
			Object status[] = {tcName, "is Displayed Send To Customer ==> "+message, "", "Quotes_Permissions", "Passed", "", env};
			App.values1(status);
		} else {
			res = false;
			Object status[] = {tcName, "is Displayed Send To Customer ==> "+message, "", "Quotes_Permissions", "Failed", "", env};
			App.values1(status);
		}
		this.verifyAdminTabswithNonePermission(itemName, tabName, labelName, 4);
		return res;
	}
	public boolean verifyPayTermsPermissionAsYes_Quotes(String tcName, String itemName, String tabName, String labelName, int childCount, int count, String env) throws Exception
	{
		//Pop Up message
		App.displayPopUp(tcName);
		String actURL[] =this.createSalesOrderPermissionAsYes(itemName, tabName, labelName, childCount, count);
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		QuotePages quotes = new QuotePages();
		App.spinner(); Thread.sleep(2000);
		if (count==2) {
			try {
				driver.findElement(By.xpath("//*[text() = 'Open']")).isDisplayed();
				driver.findElement(By.xpath("//*[text() = 'Open']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Items']")));
			} catch (Exception e) {
				quotes.createQuote();
				quotes.selectItemToQuote();
			}
		} else {
			try {
				Thread.sleep(500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Open']")));
				driver.findElement(By.xpath("//*[text() = 'Open']")).click();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Pay Terms']")));
		}
		Thread.sleep(1600);
		Actions act = new Actions(driver);
		System.out.println("count of edit tags are "+driver.findElements(By.xpath("//*[@viewBox='0 0 12 12']")).size());
		Thread.sleep(1000);
		act.moveToElement(driver.findElement(By.xpath("//*[contains(@title,'Pay Terms')]"))).perform();
		driver.findElements(By.xpath("//*[contains(@class,'edit-icon')]")).get(6).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(@class,'react-select__indicators')]")).click();	
		Thread.sleep(1000);
		List<WebElement> drops = driver.findElement(By.xpath("//*[contains(@class,'css-4mp3pp-menu')]")).findElements(By.tagName("div"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div[1]/div/div[3]/div[2]/button"), "Approve"));
		Thread.sleep(1500);
		String message = "";;boolean sta = false;
		if (count==2) {
			message = String.valueOf(drops.size());
			sta = message.equals("6");

		} else if(count==1) {
			message = String.valueOf(drops.size());
			sta = !message.equals("6");
		}
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		System.out.println("special pricing url is "+actURL[0].replace("users", actURL[1]));
		Thread.sleep(2000);
		System.out.println("paragraphs tags are "+driver.findElements(By.tagName("p")).size());
		boolean res = false;
		if (sta) {
			res = true;
			Object status[] = {tcName, message, "Top displayed text is "+message, "Quotes_Permissions", "Passed", "", env};
			App.values1(status);
		} else {
			res = false;
			Object status[] = {tcName, message, "Top displayed text is "+message, "Quotes_Permissions", "Failed", "", env};
			App.values1(status);
		}
		this.verifyAdminTabswithNonePermission(itemName, tabName, labelName, 4);
		return res;
	}
	public void verify_lessthan_23_approval(String tcName, String itemName, String tabName, String labelName, int childCount, int count, String env) throws Exception{
		//Pop Up message
		App.displayPopUp(tcName); Actions act = new Actions(driver);
		String actURL[] =this.createSalesOrderPermissionAsYes(itemName, tabName, labelName, childCount, count);
		driver.navigate().to(actURL[0].replace("users", actURL[1]));
		QuotePages quotes = new QuotePages();
		App.spinner(); Thread.sleep(2300);
		try {
			driver.findElement(By.xpath("//*[text() = 'Open']")).isDisplayed();
			driver.findElement(By.xpath("//*[text() = 'Open']")).click();
		} catch (Exception e) {
			quotes.createQuote(); 
			quotes.selectItemToQuote();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Add Items']")));
		Thread.sleep(1400);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[text() = 'Internal Approvals']")));
		Thread.sleep(1400);
		double gp = Double.parseDouble(driver.findElements(By.xpath("//*[contains(@class, 'w-32')]")).get(0)
				.findElement(By.tagName("h4")).getText().replace("%", ""));
		double total_price = Double.parseDouble(driver.findElements(By.xpath("//*[contains(@class, 'width-auto')]"))
				.get(2).findElement(By.tagName("h4")).getText().replace("$", "").replace(",", ""));
		if (gp<23 && total_price<10000) {
		
		} else {
			driver.findElements(By.xpath("//*[contains(@src, 'themecolorEdit')]")).get(1).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Turn Around Time']")));
			App.click_react_dropdown(1);
			act.sendKeys(Keys.ENTER).build().perform();
			App.click_react_dropdown(2);
			act.sendKeys(Keys.ENTER).build().perform();
			//update the quote price if >10000
			act.doubleClick(driver.findElement(By.xpath("//*[@placeholder = 'Enter Gross Profit']"))).build().perform();
			act.sendKeys(Keys.BACK_SPACE).build().perform();
			App.click_xpath("//*[@placeholder = 'Enter Gross Profit']", "send_keys", "16");
			Thread.sleep(2000);
			App.click_xpath("//*[text() = 'Save']", "click", "");
			App.spinner(); Thread.sleep(2300);
		}
		gp = Double.parseDouble(driver.findElements(By.xpath("//*[contains(@class, 'w-32')]")).get(0)
				.findElement(By.tagName("h4")).getText().replace("%", ""));
		boolean is_approve = false;
		total_price = Double.parseDouble(driver.findElements(By.xpath("//*[contains(@class, 'width-auto')]"))
				.get(2).findElement(By.tagName("h4")).getText().replace("$", "").replace(",", ""));
		if (count==2) {
			try {
				driver.findElement(By.xpath("//*[text() = 'Approve']")).isDisplayed();
				is_approve = false;
			} catch (Exception e) {
				is_approve = true;
			}
		} else {
			try {
				driver.findElement(By.xpath("//*[text() = 'Approve']")).isDisplayed();
				is_approve = true;
			} catch (Exception e) {
				is_approve = false;
			}
		}
		//
		if (is_approve) {
			Object status[] = {tcName, "gp is "+gp, "total price is "+total_price, "Quotes_Permissions", "Passed", "", env};
			App.values1(status);
		}else {
			Object status[] = {tcName, "gp is "+gp, "total price is "+total_price, "Quotes_Permissions", "Failed", "", env};
			App.values1(status);
		}
	}
	public boolean verifyQuoteApprovalLimitPermissionAsNone_Quotes(String tcName, String itemName, String tabName, String labelName,String perName, String env) throws Exception
	{
		//Pop Up message
		App.displayPopUp(tcName);
		//QuotePages quotes = new QuotePages();
		this.userTab(itemName, tabName);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		List<WebElement> labelsText = driver.findElements(By.xpath("//*[@class='permission-outer-border']")).get(3).findElements(By.xpath("//*[@class='permission']"));
		String path = "";String  url = "";String xpath1 = "";
		for(int i=0; i<labelsText.size(); i++) 
		{
			//System.out.println(labelsText.get(i).findElements(By.tagName("span")).get(0).getText());// edit=4 :: view=3 :: none=2
			if(labelsText.get(i).findElements(By.tagName("span")).get(0).getText().equalsIgnoreCase(labelName)) 
			{
				App.spinner();
				js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[text() ='Quote Approval Limit']")));
				Thread.sleep(1500);
				System.out.println("label name "+labelsText.get(i).findElements(By.tagName("span")).get(0).getText());
				path = labelsText.get(i).findElement(By.tagName("input")).getAttribute("name");
				driver.findElement(By.xpath("//*[contains(@class,'react-select__indicators')]")).click();
				this.selectList(perName);
				price.clickButton("Save");
				Thread.sleep(1500);
				price.clickButton("Accept");
				url = driver.getCurrentUrl();
				break;
			}
		}
		String vals[] = {url, path, xpath1};
		driver.navigate().to(vals[0].replace("users", vals[1]));
		try {
			Thread.sleep(500);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Open']")));
			driver.findElement(By.xpath("//*[text() = 'Open']")).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Pay Terms']")));
		Thread.sleep(1600);
		String actText = ""; boolean sta = false;
		if (perName.equals("None")) {
			actText = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[2]")).getText();
			try {
				driver.findElement(By.xpath("//*[text()='Approve']")).isDisplayed();
				sta = false;
			} catch (Exception e) {
				sta = true;
			}
		} else {
			actText = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div[2]")).getText();
			try {
				driver.findElement(By.xpath("//*[text()='Approve']")).isDisplayed();
				sta = true;
			} catch (Exception e) {
				sta = false;
			}
		}
		driver.navigate().to(vals[0].replace("users", vals[1]));
		System.out.println("special pricing url is "+vals[0].replace("users", vals[1]));
		Thread.sleep(2000);	boolean res = false;
		if (sta) {
			res = true;
			Object status[] = {tcName, actText, "Top displayed text is "+actText, "Quotes_Permissions", "Passed", "", env};
			App.values1(status);
		} else {
			res = false;
			Object status[] = {tcName, actText, "Top displayed text is "+actText, "Quotes_Permissions", "Failed", "", env};
			App.values1(status);
		}
		this.verifyAdminTabswithNonePermission(itemName, tabName, labelName, 4);
		return res;
	}
	public void selectList(String text) {
		List<WebElement> drops = driver.findElement(By.xpath("//*[contains(@class,'css-4mp3pp-menu')]")).findElements(By.tagName("div"));
		for(int i = 0; i<drops.size(); i++) 
		{
			if(drops.get(i).getText().equals(text)) {
				drops.get(i).click();
				break;
			}
		}
	}
}
