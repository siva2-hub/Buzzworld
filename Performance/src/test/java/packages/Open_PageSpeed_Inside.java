package packages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Open_PageSpeed_Inside 
{
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static void openURL() 
	{
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		//options.addArguments("--incognito");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://pagespeed.web.dev/");
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Analyze']")));
	}
	public static void read_excel_data() throws Exception 
	{
		Open_PageSpeed_Inside.openURL();
		String file = "performance.xlsx";
		FileInputStream fi = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		Sheet ws = wb.getSheet("Sheet1");
		int row_count= ws.getLastRowNum();
		System.err.println("Urls count is "+(row_count-2));
		int rowCounts = 2;
		for(int i=0; i<row_count; i++)
		{
			Row row = ws.getRow(rowCounts);
			String pageName = row.getCell(0).getStringCellValue();
			String value = row.getCell(1).getStringCellValue();
			rowCounts = rowCounts+1;
			driver.manage().deleteAllCookies();
			driver.findElement(By.xpath("//*[@placeholder='Enter a web page URL']")).sendKeys(value);
			driver.findElement(By.xpath("//*[text() = 'Analyze']")).click();
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@class, 'lh-scores-header')])[1]/a[1]/div[2]")));
			} catch (Exception e) {
				driver.navigate().refresh();
				driver.findElement(By.xpath("//*[@placeholder='Enter a web page URL']")).clear();
				driver.findElement(By.xpath("//*[@placeholder='Enter a web page URL']")).sendKeys(value);
				driver.findElement(By.xpath("//*[text() = 'Analyze']")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@class, 'lh-scores-header')])[1]/a[1]/div[2]")));
			}
			String mobPerf = driver.findElement(By.xpath("(//*[contains(@class, 'lh-scores-header')])[1]/a[1]/div[2]")).getText();
			String mobAccess = driver.findElement(By.xpath("(//*[contains(@class, 'lh-scores-header')])[1]/a[2]/div[2]")).getText();
			String mobBP = driver.findElement(By.xpath("(//*[contains(@class, 'lh-scores-header')])[1]/a[3]/div[2]")).getText();
			String mobSEO = driver.findElement(By.xpath("(//*[contains(@class, 'lh-scores-header')])[1]/a[4]/div[2]")).getText();
			Open_PageSpeed_Inside.sc(pageName+"Mobile");
			Thread.sleep(1000); Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(By.xpath("//*[text()='computer']"))).perform();
			actions.click(driver.findElement(By.xpath("//*[text()='computer']"))).perform();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@class, 'lh-scores-header')])[2]/a[1]/div[2]")));
			Thread.sleep(1000);
			String deskPerf = driver.findElement(By.xpath("(//*[contains(@class, 'lh-scores-header')])[2]/a[1]/div[2]")).getText();
			String deskAccess = driver.findElement(By.xpath("(//*[contains(@class, 'lh-scores-header')])[2]/a[2]/div[2]")).getText();
			String deskBP = driver.findElement(By.xpath("(//*[contains(@class, 'lh-scores-header')])[2]/a[3]/div[2]")).getText();
			String deskSEO = driver.findElement(By.xpath("(//*[contains(@class, 'lh-scores-header')])[2]/a[4]/div[2]")).getText();
			Open_PageSpeed_Inside.sc(pageName+"Desktop");
			row.createCell(6).setCellValue(mobPerf);
			row.createCell(7).setCellValue(mobAccess);
			row.createCell(8).setCellValue(mobBP);
			row.createCell(9).setCellValue(mobSEO);
			row.createCell(2).setCellValue(deskPerf);
			row.createCell(3).setCellValue(deskAccess);
			row.createCell(4).setCellValue(deskBP);
			row.createCell(5).setCellValue(deskSEO);
			row.createCell(10).setCellValue(value);
			FileOutputStream fo = new FileOutputStream(file);
			wb.write(fo);
			driver.findElement(By.xpath("//*[@placeholder='Enter a web page URL']")).clear();
		}
	}
	public static void sc(String fileName) throws IOException {
		// Take a screenshot
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Define the destination for the screenshot
		File destination = new File("screenShots/"+fileName+".png");
		// Copy the screenshot to the destination
		FileHandler.copy(screenshot, destination);
	}
	@Test
	public static void test1() throws Exception {
		Open_PageSpeed_Inside.read_excel_data();
	}
}
