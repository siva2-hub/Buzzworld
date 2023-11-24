package demo;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

public class OpenBuzzworld 
{
	public static WebDriver driver;
	public static void main(String[] args) throws Exception{
		// we add the chromed river to our project through terminal by using below command
		//mv<space>path of the chrome driver<space>path of the our project/drivers(folder)
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		System.out.println("chrome driver started successfully.");
		driver.manage().window().maximize();
		//		driver.get("https://buzzworld-web-iidm.enterpi.com/user-profile");
		//		Thread.sleep(2000);
		//		OpenBuzzworld.windows();
		// verify service type
		OpenBuzzworld.login();
		System.out.println("before deleting service type....");
		OpenBuzzworld.verify_service_type("Neurology");
		OpenBuzzworld.delete_service_type("Neurology");
		
		driver.close();
	}
	public static void windows() throws Exception{
		//Here i working with two Tabs in same window
		//here i opened the google sign in page another tab from same window
		//below working for both  two Tabs and two Windows
		driver.switchTo().newWindow(WindowType.WINDOW);
		driver.get("https://mail.google.com/mail/u/0/?tab=rm&ogbl#inbox");
		Thread.sleep(2000);
		Set<String> ids = driver.getWindowHandles();
		Object[] idss = ids.toArray(); String first = idss[0].toString(); String second = idss[1].toString();
		System.err.println("count opened Tabs in same window "+ids.size());
		driver.switchTo().window(first); Thread.sleep(2000);
		try {
			//			File  src= driver.findElement(By.xpath("//*[@id='identifierId']")).ta
			TakesScreenshot ts = (TakesScreenshot)driver;
			File  src =ts.getScreenshotAs(OutputType.FILE);
			File dest = new File("drivers/email.png");
			FileHandler.copy(src, dest);
			Thread.sleep(2000);
			System.out.println("selenium working on second opened window");
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[@id='username']")).sendKeys("sifjdhsbfjdsbf");
			//			File  src= driver.findElement(By.xpath("//*[@id='username']")).getScreenshotAs(OutputType.FILE);
			TakesScreenshot ts = (TakesScreenshot)driver;
			File  src =ts.getScreenshotAs(OutputType.FILE);
			File dest = new File("drivers/email.png");
			FileHandler.copy(src, dest);
			Thread.sleep(2000);
			System.out.println("selenium working on first opened window");
		}
		driver.switchTo().window(second); Thread.sleep(2000);
		driver.close();
	}
	public static void login() {
		driver.get("https://demo.openmrs.org/openmrs/login.htm");
		driver.findElement(By.xpath("//*[@placeholder = 'Enter your username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//*[@placeholder = 'Enter your password']")).sendKeys("Admin123");
		driver.findElement(By.xpath("//*[text() = 'Inpatient Ward']")).click();
		driver.findElement(By.xpath("//*[@value = 'Log In']")).click();
		driver.findElement(By.xpath("//*[contains(@href,'appointment')]")).click();
		driver.findElement(By.xpath("//*[contains(@href,'manageAppointmentType')]")).click();
	}
	public static void verify_service_type(String type) {
		
		List<WebElement> rows = driver.findElement(By.xpath("//*[@id = 'appointmentTypesTable']")).findElement(By.tagName("tBody"))
				.findElements(By.tagName("tr")); boolean res = false;
				for(int i=0; i<rows.size(); i++) 
				{
					if (rows.get(i).getText().contains(type)) {
						res = true;
						System.out.println(type+" is displayed at row "+(i+1));
						break;
					}else {
						res = false;
					}
				}
				System.out.println(type+" is displayed "+res);
	}
	public static void delete_service_type(String type) throws Exception{
		
		List<WebElement> rows = driver.findElement(By.xpath("//*[@id = 'appointmentTypesTable']")).findElement(By.tagName("tBody"))
				.findElements(By.tagName("tr")); boolean res = false;
				for(int i=0; i<rows.size(); i++) 
				{
					if (rows.get(i).getText().contains(type)) {
						res = true;
						driver.findElements(By.xpath("//*[contains(@class, 'deleteAppointmentType')]")).get(i).click();
						Thread.sleep(2000);
						driver.findElements(By.xpath("//*[@class = 'confirm right']")).get(20).click();
						Thread.sleep(2000);
						System.out.println(type+" is displayed at row "+(i+1));
						break;
					}else {
						res = false;
					}
				}
				System.out.println("after deleting service type....");
				OpenBuzzworld.verify_service_type(type);
	}
}
