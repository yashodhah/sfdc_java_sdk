package com.example.sfdcsyncpoc.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SalesforceRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
//        from("salesforce:subscribe:{{salesforce.topic}}")
//                .unmarshal().json()
//                .log(">>> ${body}");

        from("direct:processData")
                .log("Salesforce camel started")
                .to("salesforce:query?sObjectQuery=SELECT Name FROM Account")
                .log("Received Salesforce response: ${body}");
    }
}
