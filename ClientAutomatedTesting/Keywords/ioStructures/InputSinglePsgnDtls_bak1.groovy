package ioStructures

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

public class InputSinglePsgnDtls_bak1 {




	public  InputSinglePsgnDtls_bak1() {
		psgnName = '';
		psgnGender = '';
		psgnAge = '';
		berthPreference = '';
		foodPreference = '';
		nationality = '';
		idCardType = '';
		idCardNo = '';
		concession = '';
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
	public String getBerthPreference() {
		return berthPreference;
	}
	public void setBerthPreference(String berthPreference) {
		this.berthPreference = berthPreference;
	}
	public String getFoodPreference() {
		return foodPreference;
	}
	public void setFoodPreference(String foodPreference) {
		this.foodPreference = foodPreference;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getIdCardType() {
		return idCardType;
	}
	public void setIdCardType(String idCardType) {
		this.idCardType = idCardType;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getConcession() {
		return concession;
	}
	public void setConcession(String concession) {
		this.concession = concession;
	}







	private String psgnName;
	private String psgnGender;
	private String psgnAge;
	private String berthPreference;
	private String foodPreference;
	private String nationality;
	private String idCardType;
	private String idCardNo;
	private String concession;
}
