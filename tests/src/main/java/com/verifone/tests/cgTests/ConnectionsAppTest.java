package com.verifone.tests.cgTests;

import com.verifone.tests.BaseTest;
import com.verifone.utils.DataDrivenUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.verifone.tests.steps.cgPortal.Steps.*;

public class ConnectionsAppTest extends BaseTest {
    static boolean firstTime = true;
    final String xlsxFile = System.getProperty("user.dir") + "\\src\\test\\resources\\applicationsInputValidation.xls";

    private String checkFieldsByDdt(String Host, String SandBox, String Protocol, String Timeout, String Error,
                                    String TestName, String RowNumber, String description, boolean normalCheck) {
        starTestLog("Check: " + TestName + " Row: " + RowNumber, description);
        if (firstTime) {
            firstTime = false;
            loginAndCheck();
            appPageNavigate();
            connectionsNavigate();
            return checkConnectionFields(Host, SandBox, Protocol, RowNumber, Error);
        } else {
            if (Integer.valueOf(RowNumber) == 4) {
                searchNewConnect(checkConnectionFields(Host, SandBox, Protocol, RowNumber, Error));
            }
            return checkConnectionFields(Host, SandBox, Protocol, RowNumber, Error);
        }

    }


    @DataProvider(name = "Connections")
    public Object[][] getDataMandatoryFields() throws Exception {
        Object[][] arrayObject = DataDrivenUtils.getExcelData(xlsxFile, "Connections");
        return arrayObject;
    }

    @Test(dataProvider = "Connections")
    public void checkMandatoryFieldsDDT(String Host, String SandBox, String Protocol, String Timeout, String Error,
                                        String TestName, String RowNumber) {
        checkFieldsByDdt(Host, SandBox, Protocol, Timeout, Error, TestName, RowNumber,
                "CG - PortalSite, check connections fields", true);
    }
}