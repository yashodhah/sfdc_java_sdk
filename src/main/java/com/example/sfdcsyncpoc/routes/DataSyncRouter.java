package com.example.sfdcsyncpoc.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DataSyncRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:dataSync").tracing()
                .process(exchange -> isAuthenticated(exchange))
                .choice()
                .when(simple("${body}"))
                .to("direct:sfdc")
                .to("direct:dbSource")
                .otherwise()
                .log("Access denied. Missing or invalid ACCESS_TOKEN.")
                .to("direct:auth")
                .end();

        from("direct:sfdc").tracing()
                .removeHeaders("*")
                .setHeader("Authorization")
                .simple("Bearer ${exchangeProperty.ACCESS_TOKEN}")
                .setHeader("CamelHttpMethod")
                .simple("GET")
                .log("Headers: ${headers}")
                .toD("https://testcompany227-dev-ed.develop.my.salesforce.com/services/data/v57.0/sobjects")
                .convertBodyTo(String.class)
                .log("response from API: " + body())
                .end();

        from("direct:dbSource").tracing()
                .log("response from DB")
                .end();
    }


    private boolean isAuthenticated(Exchange exchange) {
        String accessToken = exchange.getIn().getHeader("ACCESS_TOKEN", String.class);
        return accessToken != null && !accessToken.isEmpty();
    }
}
