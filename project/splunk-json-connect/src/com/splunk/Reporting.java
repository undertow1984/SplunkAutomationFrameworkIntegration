
package com.splunk;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class Reporting {

	private long testexecutionstartmilli;
	private long testexecutionendmilli;
	public HashMap<String, Object> steps = new HashMap<String, Object>();
	public HashMap<String, Object> reporting = new HashMap<String, Object>();
	public HashMap<String, HashMap> reportingResults = new HashMap<String, HashMap>();
	public Map<String, Object> timeValues = new HashMap<String, Object>();
	public Map<String, String> testResults = new HashMap<String, String>();
	public ArrayList<Object> stepCollector = new ArrayList<Object>();
	private long sla = 999999999;
	public SplunkConnect splunk = null;

	public Reporting(long sla, String splunkScheme, String splunkHost, String splunkPort, String splunkToken) {
		setSla(sla);
		splunk = new SplunkConnect(splunkScheme, splunkHost, splunkPort, splunkToken);
	}
	
	public Reporting(long sla, String splunkScheme, String splunkHost, String splunkPort, String splunkToken, Proxy proxy) {
		setSla(sla);
		splunk = new SplunkConnect(splunkScheme, splunkHost, splunkPort, splunkToken, proxy);
	}
	
	public Reporting(long sla, String splunkScheme, String splunkHost, String splunkPort, String splunkToken, String splunkChannel) {
		setSla(sla);
		splunk = new SplunkConnect(splunkScheme, splunkHost, splunkPort, splunkToken, splunkChannel);
	}
	
	public Reporting(long sla, String splunkScheme, String splunkHost, String splunkPort, String splunkToken, String splunkChannel, Proxy proxy) {
		setSla(sla);
		splunk = new SplunkConnect(splunkScheme, splunkHost, splunkPort, splunkToken, splunkChannel, proxy);
	}

	public long getSla() {
		return this.sla;
	}

	public void setSla(long sla) {
		this.sla = sla;
	}

	public long getTestExecutionStart() {
		return this.testexecutionstartmilli;
	}
	
	public Map<String,Object> getTimeValues()
	{
		return this.timeValues;
	}
	
	public Map<String,String> getTestResults()
	{
		return this.testResults;
	}
	
	

	// sets test start time
	public void testExecutionStart() {
		this.testexecutionstartmilli = System.currentTimeMillis();
		this.timeValues.put("testExecutionStart", new Date(this.testexecutionstartmilli));
	}

	public long getTestExecutionEnd() {
		return this.testexecutionendmilli;
	}

	// sets test end time and calculates the test duration
	public void testExecutionEnd() {
		this.testexecutionendmilli = System.currentTimeMillis();
		
		this.timeValues.put("testExecutionEnd", new Date(getTestExecutionEnd()));
		this.timeValues.put("testExecutionDuration", ((this.getTestExecutionEnd() - this.getTestExecutionStart()) / 1000));
	}

	// sets the values for the start of a transaction
	public void startTransaction(String step, String text) {
		String status = "Fail";
		HashMap<String, Object> stepDetails = new HashMap<String, Object>();
		stepDetails.put("step", step);
		stepDetails.put("stepStatus", status);
		stepDetails.put("stepDescription", text);
		stepDetails.put("stepTimer", 0);
		stepDetails.put("stepStartTimestamp", new Date(System.currentTimeMillis()));
		stepDetails.put("stepSLA", getSla());
		stepDetails.put("stepTransactionStatus", "Fail");
		stepCollector.add(stepDetails);
	}

	// sets the values for the end of the transaction and adds the values to the
	// step collector
	public void endTransaction(String step, Long time) throws Exception {
		Object stepDesription = "";
		Object stepStartTimestamp = "";
		String status = "";
		if (time != null) {
			if (time > this.getSla()) {
				status = "Fail";
			} else {
				status = "Pass";
			}
		}

		int initialSize = stepCollector.size();
		int finalSize = 0;
		for (Object object : stepCollector) {
			HashMap<String, Object> obj = (HashMap<String, Object>) object;

			if (obj.get("step").equals(step)) {
				stepDesription = obj.get("stepDescription");
				stepStartTimestamp = obj.get("stepStartTimestamp");
				stepCollector.remove(obj);
				finalSize = stepCollector.size();
				break;
			}
		}

		if (initialSize == finalSize) {
			throw new Exception("Transaction Not Found");
		} else {

			HashMap<String, Object> stepDetails = new HashMap<String, Object>();
			stepDetails.put("step", step);
			stepDetails.put("stepStatus", status);
			stepDetails.put("stepDescription", stepDesription);
			stepDetails.put("stepTimer", time);
			stepDetails.put("stepStartTimestamp", stepStartTimestamp);
			stepDetails.put("stepEndTimestamp", new Date(System.currentTimeMillis()));
			stepDetails.put("stepSLA", getSla());
			stepDetails.put("stepTransactionStatus", "Pass");
			stepCollector.add(stepDetails);
		}
	}

	// override to allow setting of the SLA
	public void endTransaction(long sla, String step, Long time) throws Exception {
		setSla(sla);
		endTransaction(step, time);
	}

}
