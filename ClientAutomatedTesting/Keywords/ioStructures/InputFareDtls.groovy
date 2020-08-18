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

public class InputFareDtls {




	public  InputFareDtls() {
		cashToBeCollected = '';
		voucherFare = '';
		totalFare = '';

		baseFare = '';
		supCharge = '';
		melaCharge = '';
		safetyCharge = '';
		tiruCharge = '';
		clericalCharge = '';
		resFee = '';
		dynFare = '';
		enresFeee = '';
		pilgrimTax = '';
		rtsaCharge = '';
		bedRollCharge = '';
		tourPackageCharge = '';
		splCharge1 = '';
		ceiledGST = '';
	}












	public String getCashToBeCollected() {
		return cashToBeCollected;
	}
	public void setCashToBeCollected(String cashToBeCollected) {
		this.cashToBeCollected = cashToBeCollected;
	}
	public String getVoucherFare() {
		return voucherFare;
	}
	public void setVoucherFare(String voucherFare) {
		this.voucherFare = voucherFare;
	}
	public String getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(String totalFare) {
		this.totalFare = totalFare;
	}

	public String getBaseFare() {
		return baseFare;
	}




	public void setBaseFare(String baseFare) {
		this.baseFare = baseFare;
	}




	public String getSupCharge() {
		return supCharge;
	}



	public void setSupCharge(String supCharge) {
		this.supCharge = supCharge;
	}



	public String getMelaCharge() {
		return melaCharge;
	}



	public void setMelaCharge(String melaCharge) {
		this.melaCharge = melaCharge;
	}


	public String getSafetyCharge() {
		return safetyCharge;
	}



	public void setSafetyCharge(String safetyCharge) {
		this.safetyCharge = safetyCharge;
	}



	public String getTiruCharge() {
		return tiruCharge;
	}


	public void setTiruCharge(String tiruCharge) {
		this.tiruCharge = tiruCharge;
	}


	public String getClericalCharge() {
		return clericalCharge;
	}


	public void setClericalCharge(String clericalCharge) {
		this.clericalCharge = clericalCharge;
	}

	public String getResFee() {
		return resFee;
	}


	public void setResFee(String resFee) {
		this.resFee = resFee;
	}

	public String getDynFare() {
		return dynFare;
	}


	public void setDynFare(String dynFare) {
		this.dynFare = dynFare;
	}


	public String getEnresFeee() {
		return enresFeee;
	}


	public void setEnresFeee(String enresFeee) {
		this.enresFeee = enresFeee;
	}


	public String getPilgrimTax() {
		return pilgrimTax;
	}


	public void setPilgrimTax(String pilgrimTax) {
		this.pilgrimTax = pilgrimTax;
	}


	public String getRtsaCharge() {
		return rtsaCharge;
	}


	public void setRtsaCharge(String rtsaCharge) {
		this.rtsaCharge = rtsaCharge;
	}


	public String getBedRollCharge() {
		return bedRollCharge;
	}

	public void setBedRollCharge(String bedRollCharge) {
		this.bedRollCharge = bedRollCharge;
	}

	public String getTourPackageCharge() {
		return tourPackageCharge;
	}


	public void setTourPackageCharge(String tourPackageCharge) {
		this.tourPackageCharge = tourPackageCharge;
	}

	public String getSplCharge1() {
		return splCharge1;
	}

	public void setSplCharge1(String splCharge1) {
		this.splCharge1 = splCharge1;
	}
	public String getCeiledGST() {
		return ceiledGST;
	}

	public void setCeiledGST(String ceiledGST) {
		this.ceiledGST = ceiledGST;
	}

	private String cashToBeCollected;
	private String voucherFare;
	private String totalFare;
	private String baseFare;
	private String supCharge;
	private String melaCharge;
	private String safetyCharge;
	private String tiruCharge;
	private String clericalCharge;
	private String resFee;
	private String dynFare;
	private String enresFeee;
	private String pilgrimTax;
	private String rtsaCharge;
	private String bedRollCharge;
	private String tourPackageCharge;
	private String splCharge1;
	private String ceiledGST;
}
