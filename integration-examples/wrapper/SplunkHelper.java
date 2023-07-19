package com.yourpackage.listeners;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.splunk.ReportingCollectorFactory;
import com.splunk.SplunkReportingCollector;

public abstract class SplunkHelper {

	public static SplunkReportingCollector getCollector() {
		return ((SplunkReportingCollector) getBundle().getObject("splunkCollector"));
	}

	public static <YOURDRIVER> getDriver() {
		return new getDriver();
	}

	public static void testStepStart(String stepName, String stepDesc) throws Exception {
		getCollector().startTransaction(  stepName, stepDesc);
	}

	public static void testStepEnd(long sla, String stepName, Long duration) throws Exception {
		getCollector().endTransaction(sla, stepName, duration);
	}

	public static void setSplunk() {
		SplunkReportingCollector reporting;
		try {
			if (!((String) getBundle().getProperty("splunk.Channel")).equalsIgnoreCase("")) {
				reporting = ReportingCollectorFactory.createInstance(
						Long.parseLong((String) getBundle().getProperty("splunk.globalSLA")),
						(String) getBundle().getProperty("splunk.Schema"),
						(String) getBundle().getProperty("splunk.Host"), (String) getBundle().getProperty("splunk.Port"),
						(String) getBundle().getProperty("splunk.Token"),
						(String) getBundle().getProperty("splunk.Channel"));
			} else {
				reporting = ReportingCollectorFactory.createInstance(
						Long.parseLong((String) getBundle().getProperty("splunk.globalSLA")),
						(String) getBundle().getProperty("splunk.Schema"),
						(String) getBundle().getProperty("splunk.Host"), (String) getBundle().getProperty("splunk.Port"),
						(String) getBundle().getProperty("splunk.Token"));

			}
		} catch (Exception ex) {
			reporting = ReportingCollectorFactory.createInstance(
					Long.parseLong((String) getBundle().getProperty("splunk.globalSLA")),
					(String) getBundle().getProperty("splunk.Schema"), (String) getBundle().getProperty("splunk.Host"),
					(String) getBundle().getProperty("splunk.Port"), (String) getBundle().getProperty("splunk.Token"));
		}

		ReportingCollectorFactory.setReporting(reporting);		

		getBundle().setProperty("splunkCollector", reporting);
	}

}