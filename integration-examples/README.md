<h1>Examples of Integration</h1>

Examples of a wrapper, required properties, test case, and listener integration can be found in the integration-code folder. These are incomplete code examples, will contain possible syntax errors, missing imports, etc.

The intention of these code examples is to simply show some integration possibilities. It is on you as the maintainer of your automation framework to properly integrate the Splunk Connector Library provided into your code.

<h2>Integration Points within <integration-examples> </h2>

<h3>"wrapper"</h3>  
The SplunkHelper class has everything you need to set an instance of the Splunk Connector Library for your framework. The idea is when you generate your instance of the Splunk Connector Library you store it in a passble object propery that you can consume within your framework, again the way you eventually implement this is up to you.  You will also see the consumption of the Splunk Connector Library properties which are required to make a connection with Splunk itself.

<h3>"properties"</h3> 
This is a properties file containing all needed properties for the Splunk Connector Library requires to create a connection to Splunk.  If you are unsure about a property specific to Splunk please reach out to a Splunk adiminstrator.  Splunk Channel is optional, your admin will know if this is required or not.

<h3>"test-case"</h3>  
An example showing how to pass step name, step description, and start/end times to the Splunk Connector Library

<h3>"test-ng-listener"</h3> 
This has the main integration data related to the connector.  The Connector Library is spun up utilizing Thread Local to ensure it is thread safe so the main point to make about this project is to ensure on both TestStart and SuiteStart you are calling SplunkHelper.setSplunk(); so you have integration points everywhere necessary.  Depending on your implmentation you may wish to define this in even more places.

<h3>"splunk-dashboard"</h1>
The Splunk Dashboard directory contains both the XML markup to integrate directly into Splunks dashboard interface as well as screenshot examples of each dashboard.  Note the Dashboards themselves are currently a work in progress and may contain bugs.  If unsure how to utilize please work with your Splunk administrator for implementation.

<h3>"splunk-configuration</h1>
This project utilizes the Splunk HTTP Event Collector api interface and delivers a json payload.  By default the http event collector is not normally enabled.  Additionally JSON field extraction isn't always setup already on the Splunk instance you are working on.  The field extraction is necessary for writing the queries necessary to populate the Splunk Dashboard.  

You will find all the details here on how to configure Splunk.  You will need to point your Splunk administrator to this information.  NOTE: if using a hosted Splunk instance (hosted by Splunk themselves) you will need to submit a ticket to the Splunk Support Department to have the HTTP Event Collector enabled for you as it is not possible for your administrator to do so.

<h3>"splunk-payload"</h3>
An example of the json payload that is fed to Splunk via the Splunk Connector Library 