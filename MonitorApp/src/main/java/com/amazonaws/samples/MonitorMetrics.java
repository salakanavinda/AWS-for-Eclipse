package com.amazonaws.samples;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.cloudwatch.model.ListMetricsRequest;
import com.amazonaws.services.cloudwatch.model.ListMetricsResult;
import com.amazonaws.services.cloudwatch.model.Metric;

public class MonitorMetrics {

	public static void main(String[] args) {
		
		    final AmazonCloudWatch cw =
			    AmazonCloudWatchClientBuilder.defaultClient();
			ListMetricsRequest request = new ListMetricsRequest()
			        .withMetricName("CPUUtilization")
			        .withNamespace("AWS/EC2");

			boolean done = false;

			while(!done) {
			    ListMetricsResult response = cw.listMetrics(request);

			    for(Metric metric : response.getMetrics()) {
			        System.out.printf(
			            "Retrieved metric %s", metric.getMetricName());
			    }

			    request.setNextToken(response.getNextToken());

			    if(response.getNextToken() == null) {
			        done = true;
			    }
			}


	}

}
