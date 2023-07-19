<h1>Examples of Integration</h1>

Examples of a wrapper, required properties, test case, and listener integration can be found in the integration-code folder. These are incomplete code examples, will contain possible syntax errors, missing imports, etc.

The intention of these code examples is to simply show some integration possibilities. It is on you as the maintainer of your automation framework to properly integrate the Splunk Connector Library provided into your code.

<h2>Integration Points within <integration-examples> </h2>

<h3>"wrapper"</h3>  
The SplunkHelper class has everything you need to set an instance of the Splunk connector for your framework. The idea is when you generate your instance of the Splunk Connector Library you store it in a passble object propery that you can consume within your framework, again the way you eventually implement this is up to you.  You will also see the consumption of the Splunk Connector Library properties which are required to make a connection with Splunk itself.