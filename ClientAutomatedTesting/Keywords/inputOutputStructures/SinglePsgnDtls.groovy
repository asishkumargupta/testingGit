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

public class SinglePsgnDtls {




	public  SinglePsgnDtls() {
		psgnName = '';
		psgnGender = '';
		psgnAge = '';
		psgnBerthPreference = '';
		psgnFoodPreference = '';
	}



	public String getPsgnName() {
		return psgnName;
	}
	public void setPsgnName(String psgnName) {
		this.psgnName = psgnName;
	}
	public String getPsgnGender() {
		return psgnGender;
	}
	public void setPsgnGender(String psgnGender) {
		this.psgnGender = psgnGender;
	}
	public String getPsgnAge() {
		return psgnAge;
	}
	public void setPsgnAge(String psgnAge) {
		this.psgnAge = psgnAge;
	}
	public String getPsgnBerthPreference() {
		return psgnBerthPreference;
	}
	public void setPsgnBerthPreference(String psgnBerthPreference) {
		this.psgnBerthPreference = psgnBerthPreference;
	}
	public String getPsgnFoodPreference() {
		return psgnFoodPreference;
	}
	public void setPsgnFoodPreference(String psgnFoodPreference) {
		this.psgnFoodPreference = psgnFoodPreference;
	}



	private String psgnName;
	private String psgnGender;
	private String psgnAge;
	private String psgnBerthPreference;
	private String psgnFoodPreference;
}
