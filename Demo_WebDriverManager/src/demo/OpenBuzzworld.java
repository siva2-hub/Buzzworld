package demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OpenBuzzworld 
{
	public static void main(String[] args) {
		// we add the chromed river to our project through terminal by using below command
		//mv<space>path of the chrome driver<space>path of the our project/drivers(folder)
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		System.out.println("chrome driver started successfully.");
		driver.get("https://buzzworld-web-iidm.enterpi.com/user-profile");
	}
}