package check_and_update_drivers;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateAndDriverVersions {

	public static void main(String[] args) {
		// Get the version of ChromeDriver managed by WebDriverManager
		WebDriverManager chromeDriverVersion = WebDriverManager.chromedriver();

		System.out.println("ChromeDriver Version: " + chromeDriverVersion);
	}

}
