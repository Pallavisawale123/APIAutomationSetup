package com.Automation.APIAutomation.automationReport;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

/**
 * @author psawale
 */
public class BaseTestExtentReport {
    @BeforeSuite(alwaysRun = true)
    public void config() throws IOException {

        //Create the path in which we will create folder to keep html reports
        String subfolderpath = System.getProperty("user.dir") + "/reports/" + BaseTestExtentReportHelper.Timestamp();
        //create sub folder
        BaseTestExtentReportHelper.CreateFolder(subfolderpath);
        ExtentReport.initialize(subfolderpath + "/" + "API_Execution_Automation.html");
    }

    @AfterMethod(alwaysRun = true)

    public void getResult(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {

            ExtentReport.extentlog.log(LogStatus.PASS, "Test Case : " + result.getName() + " is passed ");

        } else if (result.getStatus() == ITestResult.FAILURE) {

            ExtentReport.extentlog.log(LogStatus.FAIL, "Test case : " + result.getName() + " is failed ");

            ExtentReport.extentlog.log(LogStatus.FAIL, "Test case is failed due to:  " + result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {

            ExtentReport.extentlog.log(LogStatus.SKIP, "Test case is Skiped " + result.getName());

        }
        ExtentReport.extentreport.endTest(ExtentReport.extentlog);
    }

    @AfterSuite(alwaysRun = true)
    public void endReport() {

        ExtentReport.extentreport.close();

    }
}

