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

public class PnrDetails {


	public void setpnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}

	public String getPnrNo() {
		return pnrNo;
	}

	public String setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public int getNoOfPsgn() {
		return noOfPsgn;
	}
	public void setNoOfPsgn(int noOfPsgn) {
		this.noOfPsgn = noOfPsgn;
	}
	public List<PsgnDetails> getPsgnDetailsList() {
		return psgnDetailsList;
	}

	public void setPsgnDetailsList(List<PsgnDetails> psgnDetailsList) {
		this.psgnDetailsList = psgnDetailsList;
	}


	public void setPaidFareDetails(PaidFareDetails paidFareDetails) {
		this.paidFareDetails = paidFareDetails;
	}
	public PaidFareDetails  getPaidFareDetails() {
		return this.paidFareDetails ;
	}

	public void setTrainInfoDetails(TrainInfoDetails trainInfoDetails) {
		this.trainInfoDetails = trainInfoDetails;
	}
	public TrainInfoDetails  getTrainInfoDetails() {
		return this.trainInfoDetails ;
	}

	public AdditionalDetails getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(AdditionalDetails additionalDetails) {
		this.additionalDetails = additionalDetails;
	}


	public JourneyDetails getJourneyDetails() {
		return journeyDetails;
	}


	public void setJourneyDetails(JourneyDetails journeyDetails) {
		this.journeyDetails = journeyDetails;
	}

	public  PnrDetails() {
		pnrNo = "";
		errorMsg = "";
		noOfPsgn = 0;
		paidFareDetails = new PaidFareDetails();
		trainInfoDetails = new TrainInfoDetails ();
		additionalDetails = new AdditionalDetails ();
		journeyDetails = new JourneyDetails ();
	}







	private String pnrNo;
	private String errorMsg;
	private int noOfPsgn;
	List<PsgnDetails> psgnDetailsList = new ArrayList()
	private String chartStatus;
	private PaidFareDetails paidFareDetails;
	private TrainInfoDetails  trainInfoDetails;
	private AdditionalDetails additionalDetails;
	private JourneyDetails journeyDetails;
}
