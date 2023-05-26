package com.example.sfdcsyncpoc.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class InitRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        from("timer://runOnce?repeatCount=1&delay=5000")
                .to("direct:authService")
                .end();
    }
}
