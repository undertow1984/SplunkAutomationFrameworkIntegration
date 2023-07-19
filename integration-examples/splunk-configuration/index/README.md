<h1>Examples of Integration</h1>

Examples of a wrapper, required properties, test case, and listener integration can be found in the integration-code folder. These are incomplete code examples, will contain possible syntax errors, missing imports, etc.

The intention of these code examples is to simply show some integration possibilities. It is on you as the maintainer of your automation framework to properly integrate the Splunk Connector Library provided into your code.

<h2>Integration Points within <integration-examples> </h2>

<h3>"splunk-configuration</h1>
This project utilizes the Splunk HTTP Event Collector api interface and delivers a json payload.  By default the http event collector is not normally enabled.  Additionally JSON field extraction isn't always setup already on the Splunk instance you are working on.  The field extraction is necessary for writing the queries necessary to populate the Splunk Dashboard.  

You will find all the details here on how to configure Splunk.  You will need to point your Splunk administrator to this information.  NOTE: if using a hosted Splunk instance (hosted by Splunk themselves) you will need to submit a ticket to the Splunk Support Department to have the HTTP Event Collector enabled for you as it is not possible for your administrator to do so.

<h4>Creating and Configuring Index</h4>

Select Settings and then Indexes.
![splunkindex1](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/9ea3a21d-8794-44d0-8aad-e05d2c6fec42)


Select the new index.
![splunkindex2](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/9c3deed6-420a-4025-a559-1f63347598ee)


Enter an Index Name and select Search & Reporting from App section and click save
![splunkindex3](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/a803a092-67ac-49d2-9f81-f78ec06ad39b)


Before you can see the data added to the index, you must ensure your user has access to the index.
![splunkindex4](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/55e7a12a-b865-40dd-878f-6db1126fc01f)


Select settings and then Access Controls
![splunkindex5](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/2c36b818-e9b4-471e-9a66-2f69337ca88a)

Select Roles
![splunkindex6](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/f4e1251e-ea40-4229-89a3-97fbf23475f6)

Select the role you wish to modify
![splunkindex7](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/81e9431a-c927-4eb5-8cf6-b116072256c0)

Scroll to indexes searched by default and the indexes section at bottom of page. From here add your newly created index to the selected indexes section then click save.
![splunkindex8](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/037a290a-d835-44f4-b29c-58ac7a470958)

dsfsdfsd
![splunkindex9](https://github.com/undertow1984/SplunkAutomationFrameworkIntegration/assets/12835715/abafe9a1-050e-428d-b8ea-f36bdb029dc7)


