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

public class BookedTktPnrDtls {


	public  BookedTktPnrDtls() {
		testCaseId = '' ;
		trainNo = '' ;
		doj = '';
		jrnyCls = '' ;
		quota = '';
		fromStnCodeName = '' ;
		toStnCodeName = '';
		//		cashToBeCollected = '';
		//	voucherFare = '';
		//	totalFare = '';
		pnrNo = '';
		//	psgnDetailsList = '' ;
		errorMsg = '' ;
		executionStatus = '';
		recNo = '';
	}




	public String getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getJrnyCls() {
		return jrnyCls;
	}
	public void setJrnyCls(String jrnyCls) {
		this.jrnyCls = jrnyCls;
	}
	public String getQuota() {
		return quota;
	}
	public void setQuota(String quota) {
		this.quota = quota;
	}
	public String getFromStnCodeName() {
		return fromStnCodeName;
	}
	public void setFromStnCodeName(String fromStnCodeName) {
		this.fromStnCodeName = fromStnCodeName;
	}
	public String getToStnCodeName() {
		return toStnCodeName;
	}
	public void setToStnCodeName(String toStnCodeName) {
		this.toStnCodeName = toStnCodeName;
	}

	public String getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}
	public List<BookedTktSinglePsgnDtls> getPsgnDetailsList() {
		return psgnDetailsList;
	}
	public void setPsgnDetailsList(List<BookedTktSinglePsgnDtls> psgnDetailsList) {
		this.psgnDetailsList = psgnDetailsList;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getTestCaseId() {
		return testCaseId;
	}




	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getExecutionStatus() {
		return executionStatus;
	}

	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
	}


	public BookedTktFareDtls getBookedTktFareDtls() {
		return bookedTktFareDtls;
	}




	public void setBookedTktFareDtls(BookedTktFareDtls bookedTktFareDtls) {
		this.bookedTktFareDtls = bookedTktFareDtls;
	}
	public String getRecNo() {
		return recNo;
	}
	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	private String testCaseId ;


	private String trainNo ;
	private String  doj ;
	private String  jrnyCls ;
	private String  quota ;
	private String  fromStnCodeName ;
	private String  toStnCodeName ;


	//private String  cashToBeCollected;
	//private String  voucherFare;
	//private String  totalFare;
	private BookedTktFareDtls bookedTktFareDtls;
	private String pnrNo;
	List<BookedTktSinglePsgnDtls> psgnDetailsList = new ArrayList()
	private String errorMsg;
	private String executionStatus;


	private String recNo;
}
