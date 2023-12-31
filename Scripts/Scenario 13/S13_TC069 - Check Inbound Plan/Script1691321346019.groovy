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
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.*

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : username
        , ('password') : password, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : company], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC069/button_Logistics'))

WebUI.click(findTestObject('Scenario 13/S13_TC069/li_Inbound Monitoring List'))

WebUI.setText(findTestObject('Scenario 13/S13_TC069/input_Inbound Monitor List_Search'), findTestData('Scenario 13/S13_TC052-Upload Outbound Form').getValue(
        'OutboundNo', 1))

planInboundNo = WebUI.getText(findTestObject('Scenario 13/S13_TC069/div_Plan Inbound No 1'))

CustomKeywords.'copyToExcel.exel'(planInboundNo, 1, 3, filePath, fileName, sheetName)

planInboundNo2 = WebUI.getText(findTestObject('Scenario 13/S13_TC069/div_Plan Inbound No 2'))

CustomKeywords.'copyToExcel.exel'(planInboundNo2, 2, 3, filePath, fileName, sheetName)

planInboundNo3 = WebUI.getText(findTestObject('Scenario 13/S13_TC069/div_Plan Inbound No 3'))

CustomKeywords.'copyToExcel.exel'(planInboundNo3, 3, 3, filePath, fileName, sheetName)

CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC069/input_Inbound Monitor List_Search'))

WebUI.setText(findTestObject('Scenario 13/S13_TC069/input_Inbound Monitor List_Search'), planInboundNo)

ETD1=CustomKeywords.'DateConversionLocal.convert_DateENG_Into_DateCHN_WithOutput'(ETD1, 'MMM d, yyyy', 'yyyy年MM月d日')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC069/div_ETD'), ETD1)

ETA1=CustomKeywords.'DateConversionLocal.convert_DateENG_Into_DateCHN_WithOutput'(ETA1, 'MMM d, yyyy', 'yyyy年MM月d日')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC069/div_ETA'), ETA1)

CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC069/input_Inbound Monitor List_Search'))

WebUI.setText(findTestObject('Scenario 13/S13_TC069/input_Inbound Monitor List_Search'), planInboundNo2)

ETD2=CustomKeywords.'DateConversionLocal.convert_DateENG_Into_DateCHN_WithOutput'(ETD2, 'MMM d, yyyy', 'yyyy年MM月d日')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC069/div_ETD'), ETD2)

ETA2=CustomKeywords.'DateConversionLocal.convert_DateENG_Into_DateCHN_WithOutput'(ETA2, 'MMM d, yyyy', 'yyyy年MM月d日')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC069/div_ETA'), ETA2)

CustomKeywords.'commonUtils.clearElementText'(findTestObject('Scenario 13/S13_TC069/input_Inbound Monitor List_Search'))

WebUI.setText(findTestObject('Scenario 13/S13_TC069/input_Inbound Monitor List_Search'), planInboundNo3)

ETD3=CustomKeywords.'DateConversionLocal.convert_DateENG_Into_DateCHN_WithOutput'(ETD3, 'MMM d, yyyy', 'yyyy年MM月d日')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC069/div_ETD'), ETD3)

ETA3=CustomKeywords.'DateConversionLocal.convert_DateENG_Into_DateCHN_WithOutput'(ETA3, 'MMM d, yyyy', 'yyyy年MM月d日')

WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC069/div_ETA'), ETA3)

WebUI.closeBrowser()

