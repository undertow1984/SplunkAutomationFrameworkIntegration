<h1>Examples of Integration</h1>

Examples of a wrapper, required properties, test case, and listener integration can be found in the integration-code folder. These are incomplete code examples, will contain possible syntax errors, missing imports, etc.

The intention of these code examples is to simply show some integration possibilities. It is on you as the maintainer of your automation framework to properly integrate the Splunk Connector Library provided into your code.

<h2>Integration Points within <integration-examples> </h2>

<h3>"splunk-configuration</h1>
This project utilizes the Splunk HTTP Event Collector api interface and delivers a json payload.  By default the http event collector is not normally enabled.  Additionally JSON field extraction isn't always setup already on the Splunk instance you are working on.  The field extraction is necessary for writing the queries necessary to populate the Splunk Dashboard.  

You will find all the details here on how to configure Splunk.  You will need to point your Splunk administrator to this information.  NOTE: if using a hosted Splunk instance (hosted by Splunk themselves) you will need to submit a ticket to the Splunk Support Department to have the HTTP Event Collector enabled for you as it is not possible for your administrator to do so.

<h3>Creating the HTTP Event Collector</h3>

Select settings and then Data Inputs  <br>
![1](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/76c11179-231a-41fe-804d-44f0131229db)


Select HTTP Event Collector <br>
![2](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/6ef12cac-5bd9-4ac6-94d4-688a769040f0)



Select New Token <br>
![3](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/9af7512f-c808-471f-b418-579ad0ce7950)



Enter a data collector name and click next <br>
![4](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/78245bea-ab09-4ef9-83ec-2a6df926c411)



Add an index you wish for the HEC to use to the selected items list and click review <br>
![5](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/b0f151b6-db39-4efa-85cd-f71ca66a2887)


Ensure the HTTP Event Collector is now enabled. <br>
![6](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/8228d279-5d07-49ee-ba1e-6d6b20e0e2e0)

<h3>Enable tokens</h3>

If you have an icon in the top right indicating all tokens are disabled, click Global Settings <br>
![7](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/17172362-43ce-4747-86ae-6dc52594814e)


Select enabled and then click Save. <br>
![8](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/b3474508-10a5-4c79-947c-fe8d1d047a03)

<h3>Configure the HEC for input</h3>

Select Edit on the Data Input you created <br>
![9](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/3c06dba1-6d4f-474f-baeb-ffa20b23471b)


Enter a Source name <br>
![10](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/f53917d8-1e0f-4df6-b475-80f429251b4c)

Select Source Type as custom source type you created by following the process here <br>

Ensure the index you created in in the selected index list <br>

Finally click save <br>

<h3>Increase the event data truncate limit</h3>

By default Splunk limits messages to 10,000 bytes (characters). You can increase this limit in the Splunk properties files. Depending on the size of your Json records this may or may not need to be modified. <br> <br>

Navigate to your Splunk directory and open the props.conf file in \etc\system\default <br> <br>

Modify the TRUNCATE property under the default section at the top of the file to change the maximum characters for a message. In my case I’ve increased to 1,000,000. <br> <br>

After the settings has been changed, restart your Splunk instance.


