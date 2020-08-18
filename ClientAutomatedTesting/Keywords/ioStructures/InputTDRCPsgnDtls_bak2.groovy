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
import quickBook.BookedTktPnrDtls
import internal.GlobalVariable

public class InputTDRCPsgnDtls_bak2 {


	public  InputTDRCPsgnDtls_bak2() {
		testCaseId = '';
		trainNo = '';
		doj = '';
		srcStnCode = '';
		destnStnCode = '';
		classCode = '';
		quotaCode = '';
		psgnCount = '';
		boardingStnCode = '';
		resvnUptoStnCode = '';
		coachChoice = '';
		cdaAccntNo = '';
		idCardNo = '';
		ruleAuthority = '';
		returnDate = '';
		returnCls = '';
		mobileNo = '';
		paymentType = '';
		rtsaFlag = '';
		concessionalFlag = '';
		ticketType = '' ;
		vipFlag = '' ;
	}






	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
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
	public String getSrcStnCode() {
		return srcStnCode;
	}
	public void setSrcStnCode(String srcStnCode) {
		this.srcStnCode = srcStnCode;
	}
	public String getDestnStnCode() {
		return destnStnCode;
	}
	public void setDestnStnCode(String destnStnCode) {
		this.destnStnCode = destnStnCode;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getQuotaCode() {
		return quotaCode;
	}
	public void setQuotaCode(String quotaCode) {
		this.quotaCode = quotaCode;
	}
	public String getPsgnCount() {
		return psgnCount;
	}
	public void setPsgnCount(String psgnCount) {
		this.psgnCount = psgnCount;
	}
	public String getBoardingStnCode() {
		return boardingStnCode;
	}
	public void setBoardingStnCode(String boardingStnCode) {
		this.boardingStnCode = boardingStnCode;
	}
	public String getResvnUptoStnCode() {
		return resvnUptoStnCode;
	}
	public void setResvnUptoStnCode(String resvnUptoStnCode) {
		this.resvnUptoStnCode = resvnUptoStnCode;
	}
	public String getCoachChoice() {
		return coachChoice;
	}

	public void setCoachChoice(String coachChoice) {
		this.coachChoice = coachChoice;
	}


	public List<InputSinglePsgnDtls> getPsgnDtlsList() {
		return psgnDtlsList;
	}
	public void setPsgnDtlsList(List<InputSinglePsgnDtls> psgnDtlsList) {
		this.psgnDtlsList = psgnDtlsList;
	}
	public String getCdaAccntNo() {
		return cdaAccntNo;
	}
	public void setCdaAccntNo(String cdaAccntNo) {
		this.cdaAccntNo = cdaAccntNo;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getRuleAuthority() {
		return ruleAuthority;
	}
	public void setRuleAuthority(String ruleAuthority) {
		this.ruleAuthority = ruleAuthority;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getReturnCls() {
		return returnCls;
	}
	public void setReturnCls(String returnCls) {
		this.returnCls = returnCls;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}


	public String getRtsaFlag() {
		return rtsaFlag;
	}


	public void setRtsaFlag(String rtsaFlag) {
		this.rtsaFlag = rtsaFlag;
	}


	public String getConcessionalFlag() {
		return concessionalFlag;
	}


	public void setConcessionalFlag(String concessionalFlag) {
		this.concessionalFlag = concessionalFlag;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public String getVipFlag() {
		return vipFlag;
	}

	public void setVipFlag(String vipFlag) {
		this.vipFlag = vipFlag;
	}

	private String testCaseId;
	private String trainNo ;
	private String  doj ;
	private String  srcStnCode ;
	private String  destnStnCode ;
	private String  classCode ;
	private String  quotaCode ;
	private String  psgnCount;
	private String  boardingStnCode;
	private String  resvnUptoStnCode;
	private String  coachChoice;






	List<InputSinglePsgnDtls> psgnDtlsList = new ArrayList()
	private String  cdaAccntNo;
	private String idCardNo ;
	private String ruleAuthority;
	private String returnDate;
	private String returnCls;
	private String mobileNo;
	private String paymentType;



	private String rtsaFlag;
	private String concessionalFlag;
	private String ticketType = '' ;
	private String vipFlag = '' ;
}
