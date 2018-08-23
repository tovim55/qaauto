package com.verifone.tests.cgTests;

import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.cgPortal.Steps.*;

public class ApplicationsPageTest extends BaseTest {

    static boolean firstTime = true;
    final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\applicationsInputValidation.xls";

    private String checkFieldsByDdt(String applicationsID, String Version, String Name, String Status, String Access,
                                    String ThrottlingMaxRequestCount, String Error, String TestName, String RowNumber,
                                    String description, boolean normalCheck) {
        starTestLog("Check: " + TestName + " Row: " + RowNumber, description);
        if (firstTime) {
            firstTime = false;
            loginAndCheck();
            appNavigate();
            return checkAppFields(applicationsID, Version, Name, Status, Access, ThrottlingMaxRequestCount, Error,
                    normalCheck);
        } else {
            return checkAppFields(applicationsID, Version, Name, Status, Access, ThrottlingMaxRequestCount, Error,
                    normalCheck);
        }
    }

    @DataProvider(name = "A_MandatoryFields")
    public Object[][] getDataMandatoryFields() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "A_MandatoryFields");
        return arrayObject;
    }

    @Test(dataProvider = "A_MandatoryFields", dataProviderClass = ApplicationsPageTest.class)
    public void checkMandatoryFieldsDDT(String applicationsID, String Version, String Name, String Status,
                                        String Access, String ThrottlingMaxRequestCount, String Error, String TestName,
                                        String RowNumber) {
        checkFieldsByDdt(applicationsID, Version, Name, Status, Access, ThrottlingMaxRequestCount, Error, TestName,
                RowNumber, "CG - PortalSite, check mandatory fields", true);
    }

    @DataProvider(name = "B_TypeValidation")
    public Object[][] getDataTypeValidations() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "B_TypeValidation");
        return arrayObject;
    }

    @Test(dataProvider = "B_TypeValidation", dataProviderClass = ApplicationsPageTest.class)
    public void checkTypeValidationDDT(String applicationsID, String Version, String Name, String Status, String Access,
                                       String ThrottlingMaxRequestCount, String Error, String TestName,
                                       String RowNumber) {
        checkFieldsByDdt(applicationsID, Version, Name, Status, Access, ThrottlingMaxRequestCount, Error, TestName,
                RowNumber, "CG - PortalSite, check type validation", true);
    }

    @DataProvider(name = "C_ErrorHandling")
    public Object[][] getDataErrorHandling() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "C_ErrorHandling");
        return arrayObject;
    }

    @Test(dataProvider = "C_ErrorHandling", dataProviderClass = ApplicationsPageTest.class)
    public void checkErrorHandleDDT(String applicationsID, String Version, String Name, String Status, String Access,
                                    String ThrottlingMaxRequestCount, String Error, String TestName,
                                    String RowNumber) {
        checkFieldsByDdt(applicationsID, Version, Name, Status, Access, ThrottlingMaxRequestCount, Error, TestName,
                RowNumber, "CG - PortalSite, check error handling and max/min length", true);
    }

    @DataProvider(name = "D_CheckCancelButton")
    public Object[][] getButtonData() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "D_CheckCancelButton");
        return arrayObject;
    }

    @Test(dataProvider = "D_CheckCancelButton", dataProviderClass = ApplicationsPageTest.class)
    public void checkCancelButtonDDT(String applicationsID, String Version, String Name, String Status, String Access,
                                     String ThrottlingMaxRequestCount, String Error, String TestName,
                                     String RowNumber) {
        checkFieldsByDdt(applicationsID, Version, Name, Status, Access, ThrottlingMaxRequestCount, Error, TestName,
                RowNumber, "CG - PortalSite, check error handling and max/min length", false);
        checkCancelBtn();
    }

    @DataProvider(name = "General")
    public Object[][] getGeneralData() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "General");
        return arrayObject;
    }

    @Test(dataProvider = "General", dataProviderClass = ApplicationsPageTest.class)
    public void checkSaveButtonDDT(String applicationsID, String Version, String Name, String Status, String Access,
                                   String ThrottlingMaxRequestCount, String Error, String TestName,
                                   String RowNumber) {
        String newAppId = checkFieldsByDdt(applicationsID, Version, Name, Status, Access, ThrottlingMaxRequestCount,
                Error, TestName, RowNumber, "CG - PortalSite, check error handling and max/min length",
                false);
        checkSaveBtn(newAppId);
    }


}
