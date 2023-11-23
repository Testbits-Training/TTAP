import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_SUP1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/span_Order'))

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/li_SO Monitoring List'))

WebUI.setText(findTestObject('Scenario 12/SC12_TC014/input_contractNo'), orderNo)

WebUI.click(findTestObject('Scenario 12/SC12_TC014/button_detailButton', [('contractNo') : orderNo]))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header-Parts Monitoring detailDisplay Monitor_d3694d'), 
    0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/button_Parts Monitoring detail_pop down'))

WebUI.scrollToElement(findTestObject('Scenario 13/S13_TC050 TC053/header_Parts No header list'), 0)

WebUI.click(findTestObject('Object Repository/Scenario 13/S13_TC050 TC053/header_Parts No header list'))

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/button_Download_SO Monitoring Detail'))

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/li_Download DR'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/div_Download DR For Supplier.The operation was successful'), 
    0)

supDR = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'excelUtils.unprotectExcelSheet'(supDR)

CustomKeywords.'excelUtils.clearOutValueExcel'(supDR, 10, 10)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn1-Write Info into Form Excel (Delivery Plan)'), [('datafile') : datafile_dr
        , ('fileColumns') : filecolumns_dr, ('startRowFormMinusOne') : 9, ('downloadedFormPath') : supDR, ('downloadedFormSheetname') : 'Delivery Plan'
        , ('columnHeaderQty') : 'newInboundQty', ('columnHeaderListIndex') : 'columnListIndex', ('dateExcelRelPath') : 'Excel Files/Scenario 1/S1_TestCases_Data.xlsx'
        , ('dateSheetName') : 'TC73-BU SO Delivery Plan (Date)'], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/button_Download_SO Monitoring Detail'))

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/li_Download Price'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/div_Download Price.The operation was successful'), 
    0)

supPrice = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

CustomKeywords.'excelUtils.unprotectExcelSheet'(supPrice)

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 9/S9_Cmn1-Write Info into Form Excel (Price)'), [('datafile') : datafile_price
        , ('fileColumns') : filecolumns_price, ('startRowFormMinusOne') : 9, ('downloadedFormPath') : supPrice, ('downloadedFormSheetname') : 'base'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/button_Upload BU SO Detail'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC050 TC053/li_Upload DR'), supDR)

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/div_Upload Delivery RequestThe operation was successful'), 
    0)

WebUI.click(findTestObject('Scenario 13/S13_TC050 TC053/button_Upload BU SO Detail'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 13/S13_TC050 TC053/li_Upload Price'), supPrice)

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC073/Page_SO Monitoring Detail - Brivge/div_Upload Price.The operation was successful'), 
    0)

for (def index : (1..datafile_date.getRowNumbers())) {
    def excelColumnSize = 2

    for (def index2 : (1..excelColumnSize)) {
        def dateValue = datafile_date.getValue('newPlanDate' + index2, index)

        if (dateValue != 'NULL') {
            def dateFormat = new SimpleDateFormat('MMM dd, yyyy')

            def parsedDate = dateFormat.parse(dateValue)

            if (parsedDate.date < 10) {
                def newDateFormat = new SimpleDateFormat('MMM d, yyyy')

                dateValue = newDateFormat.format(parsedDate)
            }
            
            WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/datePlanOutInDate', [('date') : dateValue]), 
                0)
        }
    }
}

for (def index : (1..datafile_dr.getRowNumbers())) {
    def excelColumnSize = 2

    def colNumStart = 15

    for (def index2 : (1..excelColumnSize)) {
        def planQtyValue = datafile_dr.getValue('newInboundQty' + index2, index)

        if (planQtyValue != 0) {
            WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/newPlanQty', [('row') : index, ('col') : colNumStart]), 
                planQtyValue)
        }
        
        colNumStart = (colNumStart + 2)
    }
}

for (def index : (1..datafile_price.getRowNumbers())) {
    def unitPriceValue = datafile_price.getValue('Price', index)

    def currencyValue = datafile_price.getValue('Currency', index)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/newUnitPrice', [('row') : index]), unitPriceValue)

    WebUI.verifyElementText(findTestObject('Scenario 13/S13_TC050 TC053/newCurrency', [('row') : index]), currencyValue)
}

CustomKeywords.'util.ScrollToElement.clickUsingJS'(findTestObject('Scenario 13/S13_TC050 TC053/button_Confirm'), 0)

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Confirm'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('Scenario 13/S13_TC050 TC053/div_Confirm Supplier SO.The operation was successful'), 
    0)

WebUI.verifyElementAttributeValue(findTestObject('Scenario 13/S13_TC050 TC053/input_Status_Monitoring Detail'), 'value', 
    'Confirmed', 0)

WebUI.closeBrowser()

