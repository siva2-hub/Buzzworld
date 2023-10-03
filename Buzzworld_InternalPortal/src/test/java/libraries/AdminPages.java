package libraries;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonUtils.App;

public class AdminPages extends Permissions
{
	WebDriverWait wait ;
	Actions act ;
	QuotePages quotes;
	RepairPages repair;
	public boolean addAndEditsInAdminPages(String itemName, String tabName, String tcCount, String env) throws Exception
	{
		wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		act = new Actions(driver);
		quotes = new QuotePages();
		price = new PricingPages();
		repair = new RepairPages(); Thread.sleep(1200);
		if (tabName.equals("Account Types")) {	
			App.click_xpath("//*[text()='"+itemName+"']", "click", ""); Thread.sleep(1200);
			App.spinner();
		} else {

		}
		act.moveToElement(driver.findElement(By.xpath("//*[text()='"+tabName+"']"))).build().perform();
		Thread.sleep(1200);
		App.click_xpath("//*[text()='"+tabName+"']", "click", "");
		App.clearFilter(); Thread.sleep(1200);
		App.clearTopSearch();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@src,'editicon')]")));
		Thread.sleep(1500);
		WebElement editIcon = driver.findElement(By.xpath("//*[contains(@src,'editicon')]"));
		editIcon.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Update']")));
		Thread.sleep(1400);
		List<WebElement> stats = driver.findElements(By.xpath("//*[contains(@class,'singleValue')]"));
		boolean res = false;
		for(int i=0; i<stats.size(); i++) 
		{
			if(stats.get(i).getText().equals("Active")) 
			{
				stats.get(i).click();
				String fName = "";
				if (tabName.equals("Zip Codes")) {
					fName = "zipcode";
				} else {
					fName = "name";
				}
				String name = driver.findElement(By.name(fName)).getAttribute("value");
				System.out.println("search with name is "+name);
				Thread.sleep(2000);
				quotes.selectDropDown("InActive");
				driver.findElement(By.xpath("//*[text()='Update']")).click();
				Thread.sleep(2000);
				if (driver.findElements(By.xpath("//*[contains(@role,'dialog')]")).size()==0) 
				{
					if (driver.findElement(By.xpath("//*[contains(@placeholder,'Search By')]")).getAttribute("value").length()==0) {

						driver.findElement(By.xpath("//*[contains(@placeholder,'Search By')]")).sendKeys(name);
						Thread.sleep(1600);
					} else {
						driver.findElement(By.xpath("//*[@class='Cross-svg close-icon-container']")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("//*[contains(@placeholder,'Search By')]")).sendKeys(name);
						Thread.sleep(1600);
					}
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@src,'editicon')]")));
					Thread.sleep(3000);
					editIcon = driver.findElement(By.xpath("//*[contains(@src,'editicon')]"));
					editIcon.click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Update']")));
					Thread.sleep(1400);
					stats = driver.findElements(By.xpath("//*[contains(@class,'singleValue')]"));
					String actText = stats.get(i).getText();
					String expText = "Active";
					//stats.get(i).click();
					if (tabName.equals("Classifications")||tabName.equals("Industries") ||tabName.equals("Quote Types") ||tabName.equals("Regions")
							||tabName.equals("Sales Potential")||tabName.equals("Vendors")||tabName.equals("Zip Codes")) 
					{
						App.click_react_dropdown(0);
					}else if(tabName.equals("Branches")) 
					{
						App.click_react_dropdown(2);
					}
					else if(tabName.equals("Territories")) 
					{ 
						App.click_react_dropdown(4);
					}else 
					{
						App.click_react_dropdown(1);
					}
					Thread.sleep(1200);
					quotes.selectDropDown("Active");
					driver.findElement(By.xpath("//*[text()='Update']")).click();
					if (actText.equals("InActive")) {
						res = true;
						Object status[] = {"ADMIN_"+tcCount+"_Verify_Update_"+tabName+" Name is "+name, "After Update "+actText,
								"Before Update "+expText, "AdminPage", "Passed", java.time.LocalDate.now().toString(), env};
						App.values1(status);
					} else {
						res = false;
						Object status[] = {"ADMIN_"+tcCount+"_Verify_Update_"+tabName+" Name is "+name, "After Update "+actText,
								"Before Update "+expText, "AdminPage", "Failed", java.time.LocalDate.now().toString(), env};
						App.values1(status);
					}
				} else {
					String serMsg = driver.findElement(By.className("server-msg")).getText();
					res = false;
					Object status[] = {"ADMIN_"+tcCount+"_Verify_Update_"+tabName, "Update Not working ", "Displayed Text is  "+serMsg
							, "AdminPage", "Failed", java.time.LocalDate.now().toString(), env};
					App.values1(status);
				}
				break;
			}else if(stats.get(i).getText().equals("InActive")) {
				stats.get(i).click();String fName = "";
				if (tabName.equals("Zip Codes")) {
					fName = "zipcode";
				} else {
					fName = "name";
				}
				String name = driver.findElement(By.name(fName)).getAttribute("value");
				Thread.sleep(2000);
				quotes.selectDropDown("Active");
				driver.findElement(By.xpath("//*[text()='Update']")).click();
				Thread.sleep(2000);
				if (driver.findElements(By.xpath("//*[contains(@role,'dialog')]")).size()==0) {
					if (driver.findElement(By.xpath("//*[contains(@placeholder,'Search By')]")).getAttribute("value").length()==0) {

						driver.findElement(By.xpath("//*[contains(@placeholder,'Search By')]")).sendKeys(name);
						Thread.sleep(1600);
					} else {
						driver.findElement(By.xpath("//*[@class='Cross-svg close-icon-container']")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("//*[contains(@placeholder,'Search By')]")).sendKeys(name);
						Thread.sleep(1600);
					}
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@src,'editicon')]")));
					Thread.sleep(3000);
					editIcon = driver.findElement(By.xpath("//*[contains(@src,'editicon')]"));
					editIcon.click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Update']")));
					Thread.sleep(1400);
					stats = driver.findElements(By.xpath("//*[contains(@class,'singleValue')]"));
					String actText = stats.get(i).getText();
					String expText = "InActive";
					if (tabName.equals("Classifications")||tabName.equals("Industries") ||tabName.equals("Quote Types") ||tabName.equals("Regions")
							||tabName.equals("Sales Potential")||tabName.equals("Vendors")||tabName.equals("Zip Codes")) 
					{
						App.click_react_dropdown(0);
					}else if(tabName.equals("Branches")) 
					{
						App.click_react_dropdown(2);
					}
					else if(tabName.equals("Territories")) 
					{ 
						App.click_react_dropdown(4);
					}else 
					{
						App.click_react_dropdown(1);
					}
					Thread.sleep(1300);
					quotes.selectDropDown("InActive"); Thread.sleep(1300);
					App.click_xpath("//*[text() = 'Update']", "click", "");
					App.spinner(); Thread.sleep(1200);
					if (actText.equals("Active")) {
						res = true;
						Object status[] = {"ADMIN_"+tcCount+"_Verify_Update_"+tabName+" Name is "+name, "After Update "+actText,
								"Before Update "+expText, "AdminPage", "Passed", java.time.LocalDate.now().toString(), env};
						App.values1(status);
					} else {
						res = false;
						Object status[] = {"ADMIN_"+tcCount+"_Verify_Update_"+tabName+" Name is "+name, "After Update "+actText,
								"Before Update "+expText, "AdminPage", "Failed", java.time.LocalDate.now().toString(), env};
						App.values1(status);
					}
				} else {
					String serMsg = driver.findElement(By.className("server-msg")).getText();
					res = false;
					Object status[] = {"ADMIN_"+tcCount+"_Verify_Update_"+tabName, "Update Not working ", "Displayed Text is  "+serMsg
							, "AdminPage", "Failed", java.time.LocalDate.now().toString(), env};
					App.values1(status);
				}
				break;
			}
			editIcon = driver.findElement(By.xpath("//*[contains(@src,'editicon')]"));
		}
		Thread.sleep(1200);
		return res;
	}
	
	public boolean verifyQuoteApproval(String itemName, String tabName, String tcCount, String btnName, String env, String website) throws Exception
	{
		Thread.sleep(1300);
		//this.headerMenu(itemName);
		Thread.sleep(1300);
		//this.adminLeftMenu(tabName);
		act.moveToElement(driver.findElement(By.xpath("//*[text()='"+tabName+"']"))).build().perform();
		Thread.sleep(1200);
		App.click_xpath("//*[text()='"+tabName+"']", "click", "");
		App.spinner();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='"+btnName+"']"))));
		boolean res = false;String message = "";
		Thread.sleep(223);
		WebElement chng_name = driver.findElement(By.xpath("//*[@name='"+website+"']"));
		String bef_updt_val = chng_name.getAttribute("value");
		chng_name.sendKeys("test1234");
		//click on Update/Approve button
		driver.findElement(By.xpath("//*[text()='"+btnName+"']")).click(); App.spinner();
		String aft_updt_val = chng_name.getAttribute("value");
		if (!bef_updt_val.equals(aft_updt_val)) {
			res = true;
			Object status[] = {"ADMIN_"+tcCount+"_Verify_Update_"+tabName, "before update val is "+bef_updt_val,
					"after update val is "+aft_updt_val, "AdminPage", "Passed", java.time.LocalDate.now().toString(), env};
			App.values1(status);
		} else {
			res = false;
			Object status[] = {"ADMIN_"+tcCount+"_Verify_Update_"+tabName, "before update val is "+bef_updt_val,
					"after update val is "+aft_updt_val, "AdminPage", "Failed", java.time.LocalDate.now().toString(), env};
			App.values1(status);
		}
		//Removing data text box field
		App.remove_text_box_data("//*[@name='"+website+"']");
		chng_name.sendKeys(bef_updt_val);
		driver.findElement(By.xpath("//*[text()='"+btnName+"']")).click(); App.spinner();
		return res;
	}
}
