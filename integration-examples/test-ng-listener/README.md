<h1>Examples of Integration</h1>

Examples of a wrapper, required properties, test case, and listener integration can be found in the integration-code folder. These are incomplete code examples, will contain possible syntax errors, missing imports, etc.

The intention of these code examples is to simply show some integration possibilities. It is on you as the maintainer of your automation framework to properly integrate the Splunk Connector Library provided into your code.

<h2>Integration Points within <integration-examples> </h2>

<h3>"test-ng-listener"</h3> 
This has the main integration data related to the connector.  The Connector Library is spun up utilizing Thread Local to ensure it is thread safe so the main point to make about this project is to ensure on both TestStart and SuiteStart you are calling SplunkHelper.setSplunk(); so you have integration points everywhere necessary.  Depending on your implmentation you may wish to define this in even more places.