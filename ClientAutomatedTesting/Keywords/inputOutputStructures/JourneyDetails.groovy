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

public class JourneyDetails {




	public  JourneyDetails() {
		trainNo = '';
		trainName = '';
		dateOfBoarding = '';
		jrnyClsCode = '';
		jrnyQuotaCode = '';
		jrnyFromCode = '';
		jrnyToCode = '';
		boardingStnCode = '';
		resvUptoCode = '';
	}


	public String getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getDateOfBoarding() {
		return dateOfBoarding;
	}
	public void setDateOfBoarding(String dateOfBoarding) {
		this.dateOfBoarding = dateOfBoarding;
	}
	public String getJrnyClsCode() {
		return jrnyClsCode;
	}
	public void setJrnyClsCode(String jrnyClsCode) {
		this.jrnyClsCode = jrnyClsCode;
	}
	public String getJrnyQuotaCode() {
		return jrnyQuotaCode;
	}
	public void setJrnyQuotaCode(String jrnyQuotaCode) {
		this.jrnyQuotaCode = jrnyQuotaCode;
	}
	public String getJrnyFromCode() {
		return jrnyFromCode;
	}
	public void setJrnyFromCode(String jrnyFromCode) {
		this.jrnyFromCode = jrnyFromCode;
	}
	public String getJrnyToCode() {
		return jrnyToCode;
	}
	public void setJrnyToCode(String jrnyToCode) {
		this.jrnyToCode = jrnyToCode;
	}
	public String getBoardingStnCode() {
		return boardingStnCode;
	}
	public void setBoardingStnCode(String boardingStnCode) {
		this.boardingStnCode = boardingStnCode;
	}
	public String getResvUptoCode() {
		return resvUptoCode;
	}
	public void setResvUptoCode(String resvUptoCode) {
		this.resvUptoCode = resvUptoCode;
	}


	private String trainNo;
	private String trainName;
	private String dateOfBoarding;
	private String jrnyClsCode;
	private String jrnyQuotaCode ;
	private String jrnyFromCode ;
	private String jrnyToCode;
	private String boardingStnCode;
	private String resvUptoCode;
}
