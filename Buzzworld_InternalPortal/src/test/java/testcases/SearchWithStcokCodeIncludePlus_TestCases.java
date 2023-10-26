package testcases;

import org.testng.annotations.Test;

import commonUtils.App;
import libraries.AllModules;
import libraries.QuotePages;

public class SearchWithStcokCodeIncludePlus_TestCases extends App{
	QuotePages quotes = new QuotePages();
	AllModules all = new AllModules();

	//@Test(enabled = false)
	@Test(priority = 1)
	public void testCase0() throws Exception 
	{
		String env = "";
		if (driver.getCurrentUrl().contains("staging")) {
			env = "Stage Instance";
		} else {
			env = "QA Instance";
		}
		all = new AllModules();
		all.verify_search_with_plus_icon("MULTI00", "PARKER HANNIFIN-CMD", "955+8R0150", env);
	}
}
