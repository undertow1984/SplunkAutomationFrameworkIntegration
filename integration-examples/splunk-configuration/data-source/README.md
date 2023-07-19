
<h1>Examples of Integration</h1>

Examples of a wrapper, required properties, test case, and listener integration can be found in the integration-code folder. These are incomplete code examples, will contain possible syntax errors, missing imports, etc.

The intention of these code examples is to simply show some integration possibilities. It is on you as the maintainer of your automation framework to properly integrate the Splunk Connector Library provided into your code.

<h2>Integration Points within <integration-examples> </h2>

<h3>"splunk-configuration"</h1>
This project utilizes the Splunk HTTP Event Collector api interface and delivers a json payload.  By default the http event collector is not normally enabled.  Additionally JSON field extraction isn't always setup already on the Splunk instance you are working on.  The field extraction is necessary for writing the queries necessary to populate the Splunk Dashboard.  

You will find all the details here on how to configure Splunk.  You will need to point your Splunk administrator to this information.  NOTE: if using a hosted Splunk instance (hosted by Splunk themselves) you will need to submit a ticket to the Splunk Support Department to have the HTTP Event Collector enabled for you as it is not possible for your administrator to do so.

<h3>Creating your Data Source</h3>

Click Settings and then Source types.<br>

Click New Source Type.<br>

Set the Name and Description of the Source Type and change the Index Extractions to json.<br>



Select Advanced and then add New Settings.<br>



Add 3 new settings that match the ones in the following table.<br><br>

<table>
<tr>
<td>Name</td>
<td>Value</td>
</tr>
  <tr></tr>
<td>EXTRACT-QUOTED-KVPS</td>
<td>(?:\\r\\n)?(?:\\n)?(?:\\t)?(?<_KEY_1>[a-zA-Z0-9._]+)=\\\"(?<_VAL_1>[A-Z0-9_\s:;!@#$%^&*()\/[\]{}|+.~,'\-]+)\\\</td>
</tr>
<tr>
<td>EXTRACT-UNQUOTED-KVPS</td>
<td>(?:\\r\\n)?(?:\\n)?(?:\\t)?(?<_KEY_2>[a-zA-Z0-9._]+)=(?<_VAL_2>[a-zA-Z0-9_:;!@#$%^&*()\/[\]{}|+.~'\-]+)</td>
</tr>
<tr>
<td>KV_MODE</td>
<td>json</td>
</tr>
</table><br>

Click Save.<br>

