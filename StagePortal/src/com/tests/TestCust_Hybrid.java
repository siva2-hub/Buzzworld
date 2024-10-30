package com.tests;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.pages.CustomPage;
import com.utils.OpenAppln;

public class TestCust_Hybrid extends OpenAppln {
	CustomPage pages = new CustomPage();

	@Test
	public void test() throws Exception {
		
		FileInputStream fi = new FileInputStream("/home/enterpi/git/Buzzworld/StagePortal/test_cases.xlsx");
		Workbook wb = new XSSFWorkbook(fi);
		Sheet ws = wb.getSheet("Sheet1");
		int l_r_num = ws.getLastRowNum();
		System.out.println("Last row number is " + l_r_num);

		for (int i = 1; i <= l_r_num; i++) {
			boolean res = false;
			String exe_key = ws.getRow(i).getCell(1).getStringCellValue();
			String tc_name = ws.getRow(i).getCell(0).getStringCellValue();
			if (exe_key.equals("y")) {
				System.out.println(tc_name + " is Selected for Execution");

				switch (tc_name) {
					case "test1_Requeste_For_Payterms":

						// add products, go to checkout page without login
						OpenAppln.openCart("false");
						CustomPage.checkout("false");
						// request for pay terms
						res = CustomPage.request_for_payterms();

					case "test2_Credit_Card_without_Login":

						// add products, go to checkout page without login
						OpenAppln.openCart("false");
						CustomPage.checkout("false");
						// credit card payment
						res = CustomPage.credit_card_or_net30("credit_card");

					case "test3_net30":

						// add products, go to checkout page without login
						OpenAppln.openCart("true");
						CustomPage.checkout("true");
						// request for pay terms
						res = CustomPage.credit_card_or_net30("NET 30");

					case "test4_Credit_Card_with_Login":

						// add products, go to checkout page without login
						OpenAppln.openCart("true");
						CustomPage.checkout("true");
						// credit card payment
						res = CustomPage.credit_card_or_net30("credit_card");
						
					case "test5_total_orders_value":

						res = CustomPage.verify_total_open_orders_value();
					default:
						System.out.println(tc_name + " is Not Selected for Execution");
						break;
				}
				if (res) {
					OpenAppln.set_cell_value(i, 2, "Pass");
				} else {
					OpenAppln.set_cell_value(i, 2, "Fail");
				}
			} else {
				System.out.println(tc_name + " is Not Selected for Execution");
				OpenAppln.set_cell_value(i, 2, "Not Executing...");
			}
		}
	}
}
