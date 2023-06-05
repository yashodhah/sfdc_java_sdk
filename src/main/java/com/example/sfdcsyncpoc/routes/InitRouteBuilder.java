package com.example.sfdcsyncpoc.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class InitRouteBuilder extends RouteBuilder {

    //TODO: Replace with FluentProducerTemplate
    @Override
    public void configure() throws Exception {
        from("timer://runOnce?repeatCount=1&delay=2000")
                .to("direct:sync")
                .end();
    }
}
