package inputOutputStructures

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class TrainInfoDetails {




	public  TrainInfoDetails() {
		chartStatus ="";
		trainStatus = "";
		expectedPfNo= "";
	}


	public String getChartStatus() {
		return chartStatus;
	}

	public void setChartStatus(String chartStatus) {
		this.chartStatus = chartStatus;
	}

	public String getTrainStatus() {
		return trainStatus;
	}

	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}

	public String getExpectedPfNo() {
		return expectedPfNo;
	}

	public void setExpectedPfNo(String expectedPfNo) {
		this.expectedPfNo = expectedPfNo;
	}

	private String chartStatus;
	private String trainStatus;
	private String expectedPfNo;
}
