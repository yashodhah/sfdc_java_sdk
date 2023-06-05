package com.example.sfdcsyncpoc.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class GatewayRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:gw")
                .log("GW sync started...")
                .delay(2000)
                .log("Gateway data synced...")
                .to("direct:gw_db")
                .end();

        from("direct:gw_db").tracing()
                .log("Getting GW data from DB")
                .delay(2000)
                .log("DB data retrieved")
                .to("direct:gw_sync")
                .end();

        from("direct:gw_sync")
                .log("Performing GW data synchronization")
                .delay(2000)
                .log("GW Synchronization completed")
                .end();
    }
}
