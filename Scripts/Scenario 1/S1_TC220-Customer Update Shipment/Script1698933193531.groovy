import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.BAF_USERNAME_FATIN
        , ('password') : GlobalVariable.BAF_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.S1_BAF_CUS], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Logistics'))

WebUI.click(findTestObject('Navbar_Brivge/LogisticsMenu_Brivge/li_Shipping Detail List'))

WebUI.waitForElementPresent(findTestObject('Page_ShippingDetailList/h3_Shipping Detail List'), 0)

for (def rowNum = 1; rowNum <= 8; rowNum++) {
    def bookingNo = testData.getValue('BookingNo', rowNum)

    def containerNo = testData.getValue('ContainerNo', rowNum)

    if (bookingNo || containerNo) {
        // Check if either bookingNo or containerNo is not empty or null
        TestObject cargoStatusObject = findTestObject('Object Repository/Scenario 12/SC12_TC053/p_verifyCargoStatus', [('bookingNo') : bookingNo
                , ('containerNo') : containerNo])

        // Get the text content of the cargo status element
        String cargoStatusText = WebUI.getText(cargoStatusObject)

        // Verify that the cargo status text is empty using verifyMatch
        WebUI.verifyMatch(cargoStatusText, '', true, FailureHandling.STOP_ON_FAILURE)

        // If the verifyMatch fails, it will cause the script to fail
        KeywordUtil.logInfo("Cargo status is empty for BookingNo: $bookingNo, ContainerNo: $containerNo")
    }
    
    WebUI.click(findTestObject('Scenario 1/S1_TC124/Page_Shipping Detail List - Brivge/div_checkbox', [('row') : rowNum]))
}

WebUI.click(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/button_download'))

WebUI.click(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/li_Download'))

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/div_Download Shipping Details.The operation was successful'), 
    0)

latestFilePath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

WebUI.callTestCase(findTestCase('0-Common/Common-Scenario 1/S1_Cmn1-Write Info into Form Excel'), [('datafile') : testData
        , ('fileColumns') : fileColumns, ('startRowFormMinusOne') : 3, ('downloadedFormPath') : latestFilePath, ('downloadedFormSheetname') : 'Sheet1'], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/button_upload'))

CustomKeywords.'RobotUpload.uploadFile'(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/li_Upload Shipping Detail'), 
    latestFilePath)

WebUI.verifyElementPresent(findTestObject('Scenario 1/S1_TC090/Page_Shipping Detail List - Brivge/div_Upload Shipping Details.The operation was successful'), 
    0)

for (def rowNum2 = 1; rowNum2 <= 8; rowNum2++) {
    def bookingNo = testData.getValue('BookingNo', rowNum2)

    KeywordUtil.logInfo("Row $rowNum2 - BookingNo: $bookingNo")

    def containerNo = testData.getValue('ContainerNo', rowNum2)

    KeywordUtil.logInfo("Row $rowNum2 - ContainerNo: $containerNo")

    if (bookingNo || containerNo) {
        // Get the text content of the cargo status element
        cargoStatusText = WebUI.getText(findTestObject('Object Repository/Scenario 12/SC12_TC053/p_verifyCargoStatus', [
                    ('bookingNo') : bookingNo, ('containerNo') : containerNo]))

        WebUI.verifyMatch(cargoStatusText, 'Cargo Inbound', false, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo("Row $rowNum2 - BookingNo: $bookingNo, ContainerNo: $containerNo, Cargo Inbound")
    }
}

WebUI.closeBrowser()

