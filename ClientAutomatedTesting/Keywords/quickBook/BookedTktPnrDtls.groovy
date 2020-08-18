package quickBook

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
import quickBook.BookedTktPnrDtls
import internal.GlobalVariable

public class BookedTktPnrDtls {


	public  BookedTktPnrDtls() {

		trainNo = '' ;
		doj = '';
		jrnyCls = '' ;
		quota = '';
		fromStnCodeName = '' ;
		toStnCodeName = '';
		cashToBeCllected = '';
		voucherFare = '';
		totalFare = '';
		pnrNo = '';
		//	psgnDetailsList = '' ;
		errorMsg = '' ;
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
	public String getCashToBeCllected() {
		return cashToBeCllected;
	}
	public void setCashToBeCllected(String cashToBeCllected) {
		this.cashToBeCllected = cashToBeCllected;
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



	private String trainNo ;
	private String  doj ;
	private String  jrnyCls ;
	private String  quota ;
	private String  fromStnCodeName ;
	private String  toStnCodeName ;
	private String  cashToBeCllected;
	private String  voucherFare;
	private String  totalFare;
	private String pnrNo;
	List<BookedTktSinglePsgnDtls> psgnDetailsList = new ArrayList()
	private String errorMsg;
}
