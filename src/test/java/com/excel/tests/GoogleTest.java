package com.excel.tests;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.excel.pages.CalculatorPage;
import com.excel.pages.HomePage;
import com.excel.utilities.ConfigurationReader;
import com.excel.utilities.Driver;
import com.excel.utilities.ExcelUtils;

public class GoogleTest {
	WebDriver driver = Driver.getInstance();
	String excelPath = "./src/test/resources/com/excel/test_data/calculator.xlsx";
	HomePage homePage = new HomePage();
	CalculatorPage calculatorPage = new CalculatorPage();
	String expectedResult;
	String resultString;
	double googleDouble;

	@Before
	public void setUp() {
		driver.get(ConfigurationReader.getProperty("url"));
		homePage.searchField.sendKeys(ConfigurationReader.getProperty("value") + Keys.ENTER);
		Assert.assertTrue(driver.getTitle().contains("calculator"));
	}

	@Test
	public void googleCal() {
		calculatorPage.calculatorField.sendKeys("22");
		calculatorPage.addition.click();
		calculatorPage.calculatorField.sendKeys("11");
		calculatorPage.equals.click();
		System.out.println(calculatorPage.calculatorField.getText());
	}

	@Test
	public void dataDriverGoogleTest() {
		ExcelUtils.openExcelFile(excelPath, "Sheet1");
		int rowCount = ExcelUtils.getUsedRowsCount();

		for (int i = 1; i < rowCount; i++) {
			String executionFlag = ExcelUtils.getCellData(i, 0);
			String operationMyVariable = ExcelUtils.getCellData(i, 1);

			String num1 = ExcelUtils.getCellData(i, 2);
			// double num1Double = Double.parseDouble(num1);
			String num2 = ExcelUtils.getCellData(i, 3);
			// double num2Double = Double.parseDouble(num2);

			if (executionFlag.contains("Y")) {

				switch (operationMyVariable) {

				case "addition":
					calculatorPage.calculatorField.sendKeys(num1);
					calculatorPage.addition.click();
					calculatorPage.calculatorField.sendKeys(num2);
					calculatorPage.equals.click();
					break;

				case "subtraction":
					calculatorPage.calculatorField.sendKeys(num1);
					calculatorPage.subtraction.click();
					calculatorPage.calculatorField.sendKeys(num2);
					calculatorPage.equals.click();
					break;
					
				case "multiplication":
					calculatorPage.calculatorField.sendKeys(num1);
					calculatorPage.multiplication.click();
					calculatorPage.calculatorField.sendKeys(num2);
					calculatorPage.equals.click();
					break;
					
				case "division":
					calculatorPage.calculatorField.sendKeys(num1);
					calculatorPage.division.click();
					calculatorPage.calculatorField.sendKeys(num2);
					calculatorPage.equals.click();
					break;
				}
				String googleReadValue = calculatorPage.calculatorField.getText();
				ExcelUtils.setCellData(googleReadValue, i, 5);
			} else {
				ExcelUtils.setCellData("Not calculated!", i, 5);
			}
			expectedResult = ExcelUtils.getCellData(i, 4).replace(".0", "");
			System.out.println(expectedResult);
			if(expectedResult.equals(ExcelUtils.getCellData(i, 5))){
				ExcelUtils.setCellData("Pass", i, 6);
			}else if(ExcelUtils.getCellData(i, 0).equals("N")){
				ExcelUtils.setCellData("Skipped", i, 6);
			}else{
				ExcelUtils.setCellData("Fail", i, 6);
			}
			String timeStamp=LocalDateTime.now().toString();
			ExcelUtils.setCellData(timeStamp, i, 7);
		}
	}

}
