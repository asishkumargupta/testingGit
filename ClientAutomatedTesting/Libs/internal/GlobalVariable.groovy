package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p>Profile default : for handling to and from date
Profile Production : for handling to and from date</p>
     */
    public static Object noOfDayspastFuture
     
    /**
     * <p>Profile default : Default wait&#47;sleep  time
Profile Production : Default wait&#47;sleep  time</p>
     */
    public static Object sleepTimeInSec
     
    /**
     * <p>Profile default : Seconds to wait for element to be clickable
Profile Production : Seconds to wait for element to be clickable</p>
     */
    public static Object waitForElementClickableSec
     
    /**
     * <p>Profile default : URL of APOS CLIENT
Profile Production : URL of APOS CLIENT</p>
     */
    public static Object url
     
    /**
     * <p></p>
     */
    public static Object clientUserid
     
    /**
     * <p></p>
     */
    public static Object clientPasswd
     
    /**
     * <p>Profile default : Second to wait for Visibility of Element
Profile Production : Second to wait for Visibility of Element</p>
     */
    public static Object waitForElementVisibleSec
     
    /**
     * <p>Profile default : Delay to get toast Error Message
Profile Production : Delay to get toast Error Message</p>
     */
    public static Object toastErrorMsgDelay
     
    /**
     * <p>Profile default : Sec to wait to get Availability
Profile Production : Sec to wait to get Availability</p>
     */
    public static Object waitForAvailabilitySec
     
    /**
     * <p>Profile default : maximum Class that can be in the train
Profile Production : maximum Class that can be in the train</p>
     */
    public static Object maxCls
     
    /**
     * <p>Profile default : Class Count start with 1
Profile Production : Class Count start with 1</p>
     */
    public static Object classCountStart
     
    /**
     * <p></p>
     */
    public static Object ticketNumber
     
    /**
     * <p></p>
     */
    public static Object imprestCash
     
    /**
     * <p></p>
     */
    public static Object personalCash
     
    /**
     * <p></p>
     */
    public static Object oldEnvFileName
     
    /**
     * <p></p>
     */
    public static Object newEnvFileName
     
    /**
     * <p></p>
     */
    public static Object maxQuota
     
    /**
     * <p></p>
     */
    public static Object quotaCountStart
     
    /**
     * <p></p>
     */
    public static Object maxPsgn
     
    /**
     * <p></p>
     */
    public static Object psgnCountStart
     
    /**
     * <p></p>
     */
    public static Object genderCountStart
     
    /**
     * <p></p>
     */
    public static Object maxGenderCount
     
    /**
     * <p></p>
     */
    public static Object paymentCountStart
     
    /**
     * <p></p>
     */
    public static Object maxPaymentCount
     
    /**
     * <p>Profile default : Seconds to wait for element to Be Present
Profile Production : Seconds to wait for element to Be Present</p>
     */
    public static Object waitForElementPresent
     
    /**
     * <p></p>
     */
    public static Object clientSupervisorId
     
    /**
     * <p></p>
     */
    public static Object clientSupervisorPasswd
     
    /**
     * <p></p>
     */
    public static Object totalGSTPos
     
    /**
     * <p>Profile default : e.getMesage
Profile Production : e.getMesage</p>
     */
    public static Object exceptionErrorMsglength
     
    /**
     * <p></p>
     */
    public static Object waitForElementPresentSec
     
    /**
     * <p>Profile default : officePcIp</p>
     */
    public static Object officePcIp
     
    /**
     * <p></p>
     */
    public static Object berthPreferenceCountStart
     
    /**
     * <p></p>
     */
    public static Object maxBerthPreferenceCount
     
    /**
     * <p></p>
     */
    public static Object idCardCountStart
     
    /**
     * <p></p>
     */
    public static Object maxIdCardType
     
    /**
     * <p>Profile default : Child Age Limit</p>
     */
    public static Object chileAgeLimit
     
    /**
     * <p></p>
     */
    public static Object noOfDays
     
    /**
     * <p></p>
     */
    public static Object cateringCountStart
     
    /**
     * <p></p>
     */
    public static Object maxCatering
     
    /**
     * <p></p>
     */
    public static Object errorMsgLength
     
    /**
     * <p></p>
     */
    public static Object waitForEnquiryAvailabilitySec
     
    /**
     * <p>Profile default : Wait for output after submit button is clicked</p>
     */
    public static Object waitForOutput
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += RunConfiguration.getOverridingParameters()
    
            noOfDayspastFuture = selectedVariables['noOfDayspastFuture']
            sleepTimeInSec = selectedVariables['sleepTimeInSec']
            waitForElementClickableSec = selectedVariables['waitForElementClickableSec']
            url = selectedVariables['url']
            clientUserid = selectedVariables['clientUserid']
            clientPasswd = selectedVariables['clientPasswd']
            waitForElementVisibleSec = selectedVariables['waitForElementVisibleSec']
            toastErrorMsgDelay = selectedVariables['toastErrorMsgDelay']
            waitForAvailabilitySec = selectedVariables['waitForAvailabilitySec']
            maxCls = selectedVariables['maxCls']
            classCountStart = selectedVariables['classCountStart']
            ticketNumber = selectedVariables['ticketNumber']
            imprestCash = selectedVariables['imprestCash']
            personalCash = selectedVariables['personalCash']
            oldEnvFileName = selectedVariables['oldEnvFileName']
            newEnvFileName = selectedVariables['newEnvFileName']
            maxQuota = selectedVariables['maxQuota']
            quotaCountStart = selectedVariables['quotaCountStart']
            maxPsgn = selectedVariables['maxPsgn']
            psgnCountStart = selectedVariables['psgnCountStart']
            genderCountStart = selectedVariables['genderCountStart']
            maxGenderCount = selectedVariables['maxGenderCount']
            paymentCountStart = selectedVariables['paymentCountStart']
            maxPaymentCount = selectedVariables['maxPaymentCount']
            waitForElementPresent = selectedVariables['waitForElementPresent']
            clientSupervisorId = selectedVariables['clientSupervisorId']
            clientSupervisorPasswd = selectedVariables['clientSupervisorPasswd']
            totalGSTPos = selectedVariables['totalGSTPos']
            exceptionErrorMsglength = selectedVariables['exceptionErrorMsglength']
            waitForElementPresentSec = selectedVariables['waitForElementPresentSec']
            officePcIp = selectedVariables['officePcIp']
            berthPreferenceCountStart = selectedVariables['berthPreferenceCountStart']
            maxBerthPreferenceCount = selectedVariables['maxBerthPreferenceCount']
            idCardCountStart = selectedVariables['idCardCountStart']
            maxIdCardType = selectedVariables['maxIdCardType']
            chileAgeLimit = selectedVariables['chileAgeLimit']
            noOfDays = selectedVariables['noOfDays']
            cateringCountStart = selectedVariables['cateringCountStart']
            maxCatering = selectedVariables['maxCatering']
            errorMsgLength = selectedVariables['errorMsgLength']
            waitForEnquiryAvailabilitySec = selectedVariables['waitForEnquiryAvailabilitySec']
            waitForOutput = selectedVariables['waitForOutput']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}
