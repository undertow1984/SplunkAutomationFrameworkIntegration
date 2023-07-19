
	public void beforeStep(String stepName, int stepIndex, String stepDescription) {
				
		String msg = "BEGIN STEP: " + stepName;
		try {
			SplunkHelper.testStepStart("Step" + stepIndex, stepDescription);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConsoleUtils.logInfoBlocks(msg, ConsoleUtils.lower_block + " ", 10);
	}


	public void afterStep(String method, String stepName, int stepIndex) {

		
			long starttime = getStartTime(method);
			long endtime = now();
			Number duration = starttime - endtime;
			try {
				SplunkHelper.testStepEnd(getBundle().getLong("splunk.globalSLA"),
						"Step" + getNextStepIndex(endtime), Long.parseLong(duration.toString()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logInfo(msg + " Execution Time: " + duration, ConsoleUtils.lower_block + " ", 10);
		}

	}