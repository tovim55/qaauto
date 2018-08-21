package com.verifone.tests.cgTests;

import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.cgPortal.Steps.*;

public class ApplicationsPageTest extends BaseTest {

    static boolean firstTime = true;
    final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\applicationsInputValidation.xls";

    @DataProvider(name = "A_MandatoryFields")
    public Object[][] getDataMandatoryFields() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "A_MandatoryFields");
        return arrayObject;
    }

    @Test(dataProvider = "A_MandatoryFields", dataProviderClass = ApplicationsPageTest.class)
    public void checkMandatoryFieldsDDT(String applicationsID, String Version, String Name, String Status, String Access,
                                        String ThrottlingMaxRequestCount, String Error, String TestName, String RowNumber) {
        checkFieldsByDdt(applicationsID, Version, Name, Status, Access, ThrottlingMaxRequestCount, Error, TestName, RowNumber, "CG - PortalSite, check mandatory fields");
    }

    private void checkFieldsByDdt(String applicationsID, String Version, String Name, String Status, String Access,
                                  String ThrottlingMaxRequestCount, String Error, String TestName, String RowNumber,
                                  String s) {
        starTestLog("Check: " + TestName + " Row: " + RowNumber, s);
        if (firstTime) {
            openChromeBrowser();
            loginAndCheck();
            appNavigate();
            checkAppFields(applicationsID, Version, Name, Status, Access, ThrottlingMaxRequestCount, Error);
            firstTime = false;
        } else {
            checkAppFields(applicationsID, Version, Name, Status, Access, ThrottlingMaxRequestCount, Error);
        }
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
                RowNumber, "CG - PortalSite, check type validation");
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
                RowNumber, "CG - PortalSite, check error handling and max/min length");
    }




}
