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

public class BookedTktSinglePsgnDtls {




	public  BookedTktSinglePsgnDtls() {
		psgnSrlNo = '';
		psgnName = '';
		psgnAge = '';
		psgnGender = '';
		psgnQuota = '';
		psgnBookingStatus = '';
		psgnBookingDtls = '';
		psgnCurStatus = '';
		psgnCurDtls = '';
		concessionCode = '';
	}




	public String getPsgnSrlNo() {
		return psgnSrlNo;
	}
	public void setPsgnSrlNo(String psgnSrlNo) {
		this.psgnSrlNo = psgnSrlNo;
	}
	public String getPsgnName() {
		return psgnName;
	}
	public void setPsgnName(String psgnName) {
		this.psgnName = psgnName;
	}
	public String getPsgnAge() {
		return psgnAge;
	}
	public void setPsgnAge(String psgnAge) {
		this.psgnAge = psgnAge;
	}
	public String getPsgnGender() {
		return psgnGender;
	}
	public void setPsgnGender(String psgnGender) {
		this.psgnGender = psgnGender;
	}
	public String getPsgnQuota() {
		return psgnQuota;
	}
	public void setPsgnQuota(String psgnQuota) {
		this.psgnQuota = psgnQuota;
	}
	public String getPsgnBookingStatus() {
		return psgnBookingStatus;
	}
	public void setPsgnBookingStatus(String psgnBookingStatus) {
		this.psgnBookingStatus = psgnBookingStatus;
	}
	public String getPsgnBookingDtls() {
		return psgnBookingDtls;
	}
	public void setPsgnBookingDtls(String psgnBookingDtls) {
		this.psgnBookingDtls = psgnBookingDtls;
	}
	public String getPsgnCurStatus() {
		return psgnCurStatus;
	}
	public void setPsgnCurStatus(String psgnCurStatus) {
		this.psgnCurStatus = psgnCurStatus;
	}
	public String getPsgnCurDtls() {
		return psgnCurDtls;
	}
	public void setPsgnCurDtls(String psgnCurDtls) {
		this.psgnCurDtls = psgnCurDtls;
	}

	public String getConcessionCode() {
		return concessionCode;
	}

	public void setConcessionCode(String concessionCode) {
		this.concessionCode = concessionCode;
	}


	private String psgnSrlNo;
	private String psgnName;
	private String psgnAge;
	private String psgnGender;
	private String psgnQuota;
	private String psgnBookingStatus;
	private String psgnBookingDtls;
	private String psgnCurStatus;
	private String psgnCurDtls;
	private String concessionCode;
}
