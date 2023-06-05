package com.example.sfdcsyncpoc.routes;

import com.example.sfdcsyncpoc.services.DataSyncService;
import com.example.sfdcsyncpoc.util.ApplicationShutdownManager;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataSyncRouteBuilder extends RouteBuilder {

    @Autowired
    ApplicationShutdownManager applicationShutdownManager;

    @Override
    public void configure() throws Exception {
        from("direct:sync").tracing()
                .multicast()
                .parallelProcessing()
                .to("direct:gw")
                .to("direct:sf")
                .end()
                .log("Data has been synchronized successfully")
                .bean(applicationShutdownManager, "exit");
    }
}
