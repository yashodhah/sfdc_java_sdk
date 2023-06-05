package com.example.sfdcsyncpoc.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SalesforceRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("direct:sf")
                .log("Salesforce camel started")
                .to("salesforce:query?sObjectQuery=SELECT name, industry FROM Account")
                .log("Received Salesforce response: ${body}")
                .to("direct:sf_db")
                .end();

        from("direct:sf_db").tracing()
                .log("Getting SF data from DB")
                .delay(2000)
                .log("DB data retrieved")
                .to("direct:sf_sync")
                .end();

        from("direct:sf_sync")
                .log("Performing SF synchronization based on data")
                .delay(2000)
                .log("SF Synchronization completed")
                .end();
    }
}
