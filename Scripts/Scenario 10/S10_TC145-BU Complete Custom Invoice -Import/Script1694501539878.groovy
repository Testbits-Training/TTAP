import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_BU], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Customs Invoice List(Import)'))

WebUI.waitForElementPresent(findTestObject('Page_CustomsInvoice_Import/h3_Customs Invoice(Import)'), 0)

for (int row=1;row<=testData.getRowNumbers();row++) {
    
	String invoiceNo=testData.getValue('InvoiceNo',row)
	
	WebUI.click(findTestObject('Scenario 12/SC12_TC065/p_editButton', [('invoiceNo') : invoiceNo]))

    WebUI.click(findTestObject('Scenario 12/SC12_TC065/svg_Input Cargo Status_lcbm-MuiSvgIcon-root'))

    WebUI.click(findTestObject('Scenario 12/SC12_TC065/li_Imp clearance completed'))

    WebUI.click(findTestObject('Scenario 12/SC12_TC065/button_update', [('invoiceNo') : invoiceNo]))

    WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC065/div_Update Cargo Status.The operation was successful'), 
        0)

    WebUI.click(findTestObject('Scenario 12/SC12_TC016/svg_closeNotification'))

    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC063/p_verifyCargoStatus', [('invoiceNo') : invoiceNo]), 'Imp clearance completed')

    WebUI.click(findTestObject('Scenario 12/SC12_TC065/p_completeButton', [('invoiceNo') : invoiceNo]))

    WebUI.click(findTestObject('Scenario 12/SC12_TC065/button_CONFIRM'))

    WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC065/div_Completed Customs Clearance.The operation was successful'), 
        0)

    WebUI.click(findTestObject('Scenario 12/SC12_TC016/svg_closeNotification'))

    WebUI.delay(2)

    WebUI.verifyElementText(findTestObject('Scenario 12/SC12_TC063/p_verifyStatus', [('invoiceNo') : invoiceNo]), 'Customs Clearance Completed')
}

WebUI.takeFullPageScreenshot()

WebUI.closeBrowser()

