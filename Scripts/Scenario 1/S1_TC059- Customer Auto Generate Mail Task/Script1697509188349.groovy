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
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

def sup1ActualText = CustomKeywords.'getEmail.extract'('ttaptesternasa@outlook.com', 'dyfpmobqkjkyvpud', 'Order Revised')

def sup2ActualText = CustomKeywords.'GetLatestEmailByDesiredIndex.extract'('ttaptesternasa@outlook.com', 'dyfpmobqkjkyvpud', 
    'Order Revised', 1)

//Validate actualText against expectedText
def sup1ExpectedText = "Dear PK-CUS by Upload, You have received change/cancel request for below order: $customerOrderNo1 Please login to the system to check and approve the request. Thank you for your continued support! Best regards. and Expected: Dear PK-SUP-POC, The following order have been propose new, please note it.  Please login to the system to check and approve the request. Thank you for your continued support! Best regards."

def sup2ExpectedText = "Dear PK-CUS by Upload, You have received change/cancel request for below order: $customerOrderNo2 Please login to the system to check and approve the request. Thank you for your continued support! Best regards. and Expected: Dear PK-SUP-POC, The following order have been propose new, please note it.  Please login to the system to check and approve the request. Thank you for your continued support! Best regards."

KeywordUtil.logInfo("Actual: $sup1ActualText and Expected: $sup1ExpectedText")

KeywordUtil.logInfo("Actual: $sup2ActualText and Expected: $sup2ExpectedText")

if ((sup1ActualText == sup1ExpectedText) && (sup2ActualText == sup2ExpectedText)) {
    println('actualText and expectedText match!')
} else {
    throw new Error('actualText and expectedText do not match!')
}

