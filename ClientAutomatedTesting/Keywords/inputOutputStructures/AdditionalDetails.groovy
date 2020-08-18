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

public class AdditionalDetails {




	public  AdditionalDetails() {
		vikalpOptedYN = '';
		upgradationYN = '';
		tktLostMarkedYN = '';
		vipYN = '';
		mobileNoYN = '';
		pendingAmount = '';
	}





	public String getVikalpOptedYN() {
		return vikalpOptedYN;
	}
	public void setVikalpOptedYN(String vikalpOptedYN) {
		this.vikalpOptedYN = vikalpOptedYN;
	}
	public String getUpgradationYN() {
		return upgradationYN;
	}
	public void setUpgradationYN(String upgradationYN) {
		this.upgradationYN = upgradationYN;
	}
	public String getTktLostMarkedYN() {
		return tktLostMarkedYN;
	}
	public void setTktLostMarkedYN(String tktLostMarkedYN) {
		this.tktLostMarkedYN = tktLostMarkedYN;
	}
	public String getVipYN() {
		return vipYN;
	}
	public void setVipYN(String vipYN) {
		this.vipYN = vipYN;
	}
	public String getMobileNoYN() {
		return mobileNoYN;
	}
	public void setMobileNoYN(String mobileNoYN) {
		this.mobileNoYN = mobileNoYN;
	}
	public String getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(String pendingAmount) {
		this.pendingAmount = pendingAmount;
	}



	private String vikalpOptedYN;
	private String upgradationYN;
	private String tktLostMarkedYN;
	private String vipYN;
	private String mobileNoYN;
	private String pendingAmount;
}
