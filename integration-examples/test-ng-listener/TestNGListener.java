package com.yourpackage.listeners;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;



public class TestListener implements  ISuiteListener, ITestListener {

	@Override
	public void onFailure(StepExecutionTracker stepExecutionTracker) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestSuccess(ITestResult testResult) {
		setDetails("Pass", testResult);
	}

	@Override
	public void onTestFailure(ITestResult testResult) {
		setDetails("Fail", testResult);
	}

	@Override
	public void onTestSkipped(ITestResult testResult) {
		setDetails("Skip", testResult);
	}

	@Override
	public void onFinish(ISuite arg0) {
		try {
			System.out.println(SplunkHelper.getCollector().commitSplunk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onStart(ISuite arg0) {
		SplunkHelper.setSplunk();
	}

	@Override
	public void onTestStart(ITestResult testResult) {
		
		SplunkHelper.getCollector().testExecutionStart();
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		SplunkHelper.setSplunk();
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void setDetails(String result, ITestResult testResult) {

		String failMsg = "";
		String finalMsg = "";
		SplunkHelper.getCollector().getTestResults().put("testStatus", result);

		

		if (!result.equalsIgnoreCase("Skip")) {
			
			
			Map<String,Object> runtimeconfig = new HashMap<String,Object>();
			runtimeconfig.put("methodName", testResult.getMethod().getMethodName());
			runtimeconfig.put("runFactoryClass", testResult.getMethod().getInstance().getClass().getName());
			runtimeconfig.put("allCapabilities",
					Utils.getriver().getCapabilities());
			runtimeconfig.put("runtimeParams", runPrams);
			
			
			
			Map<String,String> jenkinsVals = new HashMap<String, String>();
			jenkinsVals.put("jenkinsBuildNum", System.getProperty("jenkinBuildNum"));
			jenkinsVals.put("jenkinsJobName", System.getProperty("jenkinsJobName") );
			jenkinsVals.put("jenkinsBuildId", System.getProperty("jenkinsBuildId"));
			jenkinsVals.put("jenkinsBuildUrl", System.getProperty("jenkinsBuildUrl"));
			jenkinsVals.put("jenkinsWorkspace", System.getProperty("jenkinsWorkspace"));
			
			Map<String,Object> environment = new HashMap<String,Object>();
			environment.put("project", getBundle().getString("project.release"));
			environment.put("appEnvironment", getBundle().getString("app.environment"));
			
			environment.put("appVersion", getAppVersion(Utils.getDriver().getCapabilities().getCapability("app").toString()));
			
			
			environment.put("model",
					Utils.getDriver().getCapabilities().getCapability("deviceManufacturer") + " "
							+ Utils.getDriver().getCapabilities().getCapability("deviceModel"));
			environment.put("jenkins", jenkinsVals);
			
			try {
				environment.put("hostName", InetAddress.getLocalHost().getHostName());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			runtimeconfig.put("tags",tags);
			SplunkHelper.getCollector().reporting.put("configuration", runtimeconfig);
			SplunkHelper.getCollector().reporting.put("environment", environment);
			
			
			
			
		}

		if (result.equalsIgnoreCase("Fail")) {
			if (testResult.getThrowable() != null)
				if (testResult.getThrowable().getStackTrace() != null) {
					StringWriter sw = new StringWriter();
					SplunkHelper.getCollector().getTestResults().put("stackTrace", finalMsg);
					
				}
		}

		if (!result.equalsIgnoreCase("Skip")) {
			SplunkHelper.getCollector().testExecutionEnd();
		}

		long startt=SplunkHelper.getCollector().getTestExecutionStart();

		SplunkHelper.getCollector().getTimeValues().put("dateUTC", formatDate(startt,"yyyy-MM-dd","UTC"));
		SplunkHelper.getCollector().getTimeValues().put("yearUTC", formatDate(startt,"yyyy","UTC"));
		SplunkHelper.getCollector().getTimeValues().put("monthUTC", formatDate(startt,"MM","UTC"));
		SplunkHelper.getCollector().getTimeValues().put("timeUTC", formatDate(startt,"HH:mm:ss","UTC"));
		SplunkHelper.getCollector().getTimeValues().put("hourUTC", formatDate(startt,"HH","UTC"));

		SplunkHelper.getCollector().getTimeValues().put("dateLocal", formatDate(startt,"yyyy-MM-dd",ZoneId.systemDefault().toString()));
		SplunkHelper.getCollector().getTimeValues().put("yearLocal", formatDate(startt,"yyyy",ZoneId.systemDefault().toString()));
		SplunkHelper.getCollector().getTimeValues().put("monthLocal", formatDate(startt,"MM",ZoneId.systemDefault().toString()));
		SplunkHelper.getCollector().getTimeValues().put("timeLocal", formatDate(startt,"HH:mm:ss",ZoneId.systemDefault().toString()));
		SplunkHelper.getCollector().getTimeValues().put("hourLocal", formatDate(startt,"HH",ZoneId.systemDefault().toString()));


		

		SplunkHelper.getCollector().submitReporting(testResult.getMethod().getMethodName());

		

	}

	public String formatDate(long miliseconds, String dateFormat, String ZoneID) {
		DateTimeFormatter datef = DateTimeFormatter.ofPattern(dateFormat);

		ZonedDateTime d = Instant.ofEpochMilli(miliseconds).atZone(ZoneId.of(ZoneID));
		return datef.format(d);
	}
}