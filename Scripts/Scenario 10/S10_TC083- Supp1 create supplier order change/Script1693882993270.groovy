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

WebUI.callTestCase(findTestCase('0-Common/Login to Brivge'), [('url') : GlobalVariable.BRIVGE_URL, ('username') : GlobalVariable.PNA_USERNAME_SYAZWAN
        , ('password') : GlobalVariable.ADMIN_PWD, ('verificationCode') : GlobalVariable.VERIFICATION_CODE, ('company') : GlobalVariable.PNA_COMPANY_SUPPLIER1], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Navbar_Brivge/button_Order'))

WebUI.click(findTestObject('Scenario 12/SC12_TC033/li_Place Supplier Change'))

WebUI.setText(findTestObject('Scenario 10/S10_TC083/input_Supplier Order Change_search'), SOid)

WebUI.click(findTestObject('Page_OrderChangeCancel/div_Dt_ContractNo_Create', [('contractNo') : SOid]), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Scenario 12/SC12_TC033/h3_Create Supplier Order Change'), 0)

WebUI.click(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/button_Download'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

latestPath = CustomKeywords.'ManageFiles.getLatestFileFromDirectory'('excel')

partsNoRowIndices = CustomKeywords.'mapRowDatAndRowIndices.extractPartsWithIndices'(latestPath, 2, 16)

for (int index : (1..testData.getRowNumbers())) {
    def datafilePartNo = testData.getValue('Part No', index)

    def fileRowIndex = partsNoRowIndices[datafilePartNo]

    for (def pair : mapKeyandColIndex) {
        def columnName = pair.key

        def columnIndex = pair.value

        def dataValue = testData.getValue(columnName, index)

        CustomKeywords.'copyToExcel.exel3'(dataValue, fileRowIndex, columnIndex, latestPath, SOid)
    }
}

for (int index : (1..inboundDateNew.getRowNumbers())) {
    def dataValue = inboundDateNew.getValue('Date', index)

    def columnIndex = 23 + index

    CustomKeywords.'copyToExcel.exel3'(dataValue, 15, columnIndex, latestPath, SOid)
}

WebUI.uploadFile(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/input_UploadFile'), latestPath)

WebUI.verifyElementPresent(findTestObject('Scenario 12/SC12_TC033/div_Upload Supplier Order Change. The operation was successful'), 
    0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.click(findTestObject('Page_OrderChangeCancel/Page_CreateOrderChange/button_Issue'))

WebUI.waitForElementPresent(findTestObject('NotificationMsg_Brivge/div_ConfirmMsg_AreYouSureToDo_Issue'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/button_NotiMsg_CONFIRM'))

WebUI.verifyElementPresent(findTestObject('NotificationMsg_Brivge/p_The operation was successful'), 0)

WebUI.click(findTestObject('NotificationMsg_Brivge/svg_close notification'))

WebUI.closeBrowser()

