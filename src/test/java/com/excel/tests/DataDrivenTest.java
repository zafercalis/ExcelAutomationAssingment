package com.excel.tests;

import java.time.LocalDateTime;

import org.junit.Test;

import com.excel.utilities.ExcelUtils;

public class DataDrivenTest {

	String excelPath = "./src/test/resources/com/excel/test_data/calculator.xlsx";
	String resultString;
	String expectedResult;

	@Test
	public void test1() {

		ExcelUtils.openExcelFile(excelPath, "Sheet1");
		int rowCount = ExcelUtils.getUsedRowsCount();

		for (int i = 1; i < rowCount; i++) {
			String executionFlag = ExcelUtils.getCellData(i, 0);
			String operationMyVariable = ExcelUtils.getCellData(i, 1);

			String num1 = ExcelUtils.getCellData(i, 2);
			double num1Double = Double.parseDouble(num1);
			String num2 = ExcelUtils.getCellData(i, 3);
			double num2Double = Double.parseDouble(num2);

			 expectedResult = ExcelUtils.getCellData(i, 4);

			if (executionFlag.contains("Y")) {
				double calculationResult = Calculator.Operate(operationMyVariable, num1Double, num2Double);
				resultString = String.valueOf(calculationResult);
				ExcelUtils.setCellData(resultString, i, 5);
			} else {
				ExcelUtils.setCellData("Not calculated!", i, 5);
			}
//			if(resultString.equals(expectedResult)){}
			
			if(ExcelUtils.getCellData(i, 4).equals(ExcelUtils.getCellData(i, 5))){
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
