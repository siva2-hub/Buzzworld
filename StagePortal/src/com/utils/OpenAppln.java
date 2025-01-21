package com.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Collections;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenAppln {

	public static WebDriver driver;
	public static WebDriverWait wait;

	public static void open_app() throws Exception {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		// options.addArguments("--incognito");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		// options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(Configurator.readData("open_url"));
		// Actions act = new Actions(driver);
		// act.contextClick().perform();
		//
		// Thread.sleep(2000);
		// System.exit(0);
	}

	public static void login() throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		OpenAppln.menu();
		try {
			driver.findElement(By.xpath("//*[contains(text(), 'Login')]")).isDisplayed();
			driver.findElement(By.xpath("//*[contains(text(), 'Login')]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
			driver.findElement(By.id("username")).sendKeys("test.active@test.com");
			driver.findElement(By.id("password")).sendKeys("Enter@4321");
			driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/form/div[3]/button")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'sso-login-module custom-dropdown')]")));
			driver.findElement(By.xpath("//*[contains(@class, 'sso-login-module custom-dropdown')]")).click();
		} catch (Exception e) {
			try {
				driver.findElement(By.xpath("//*[contains(@class, 'sso-login-module custom-dropdown')]")).click();
			} catch (Exception e2) {

			}
			Thread.sleep(1300);
		}
	}

	public static void openCart(String is_login) throws Exception {

		System.out.println("with login");
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		double[] sizes = OpenAppln.get_screen_size();
		// verify is login or not from here
		if (is_login.equals("true")) {
			try {
				driver.findElement(By.xpath("//*[contains(text(), 'Login')]")).isDisplayed();
				OpenAppln.login();
			} catch (Exception e) {
				System.err.println("Login option not displayed..." + e);
			}
		} else {
			System.out.println("without login");
		}
		Actions act = new Actions(driver);
		OpenAppln.menu();
		driver.findElement(By.xpath("//*[@class = 'cart-icon']")).click();
		Thread.sleep(1800);
		try {
			driver.findElement(By.xpath("//*[text() = 'Add Products']")).isDisplayed();
			driver.findElement(By.xpath("//*[contains(@class, 'home')]")).click();
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'View all products')]")));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true)",
					driver.findElement(By.xpath("//*[contains(text(), 'Featured Products')]")));
			Thread.sleep(2000);
			act.moveToElement(driver.findElements(By.xpath("//*[contains(@class, 'product-thumb-top')]")).get(2))
					.build().perform();
			Thread.sleep(1000);
			driver.findElements(By.xpath("//*[@title = 'Add to Cart']")).get(1).click();
			act.moveToElement(driver.findElements(By.xpath("//*[contains(@class, 'product-thumb-top')]")).get(4))
					.build()
					.perform();
			driver.findElements(By.xpath("//*[@title = 'Add to Cart']")).get(3).click();
			if (sizes[0] == 1280. && sizes[1] == 720.0) {
				// driver.findElement(By.xpath("//*[@class = 'hamburger']")).click();
				// Thread.sleep(1000);
				driver.navigate().to("https://staging-store.iidm.com/index.php?route=checkout/cart");
				Thread.sleep(1600);
			} else {
				driver.findElement(By.xpath("//*[@class = 'cart-icon']")).click();
				Thread.sleep(1800);
			}
			js.executeScript("window.scrollBy(0,200)");
			Thread.sleep(1800);
			driver.findElement(By.xpath("//*[text() = 'Checkout']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//*[text() = 'Checkout']")).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='first_name']")));
	}

	public static void set_cell_value(int row_count, int cell_count, String data) throws Exception {
		String file = "/home/enterpi/git/Buzzworld/StagePortal/test_cases.xlsx";
		FileInputStream fi = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		Sheet ws = wb.getSheet("Sheet1");
		Row row = ws.getRow(row_count);

		Cell cel = row.createCell(cell_count);
		cel.setCellValue(data);
		CellStyle style = wb.createCellStyle();
		// if ("jfbhdsjf".equalsIgnoreCase("pass")) {
		// style.setFillBackgroundColor(IndexedColors.GREEN.index);
		// style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// } else {
		// style.setFillBackgroundColor(IndexedColors.RED.index);
		// style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// }
		cel.setCellStyle(style);
		FileOutputStream fo = new FileOutputStream(file);
		wb.write(fo);
	}

	public static double[] get_screen_size() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		double wdh = size.getWidth();
		double hgt = size.getHeight();
		double vals[] = { wdh, hgt };
		return vals;
	}

	public static void menu() throws Exception {
		double[] sizes = OpenAppln.get_screen_size();
		if (sizes[0] == 1280. && sizes[1] == 720.0) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class = 'hamburger']")));
			driver.findElement(By.xpath("//*[@class = 'hamburger']")).click();
			Thread.sleep(1000);
		} else {
		}
	}

	public static void close() {
		driver.close();
	}
}
