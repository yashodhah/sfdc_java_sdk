package com.example.sfdcsyncpoc.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SalesforceRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("salesforce:subscribe:{{salesforce.topic}}")
                .unmarshal().json()
                .log(">>> ${body}");
    }
}
