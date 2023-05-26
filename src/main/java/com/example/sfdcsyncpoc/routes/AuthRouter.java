package com.example.sfdcsyncpoc.routes;

import com.example.sfdcsyncpoc.models.AccessResponseToken;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthRouter extends RouteBuilder {
    @Value("${sfdc.oauth.client_id}")
    private String clientId;

    @Value("${sfdc.oauth.client_secret}")
    private String clientSecret;

    @Override
    public void configure() throws Exception {
        from("direct:auth").tracing()
                .setHeader("CamelHttpMethod")
                .simple("POST")
                .setHeader("Content-Type")
                .simple("application/x-www-form-urlencoded")
                .setHeader("Accept")
                .simple("application/json")
                .setBody()
                .constant("grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret)
                .toD("https://testcompany227-dev-ed.develop.my.salesforce.com/services/oauth2/token")
                .convertBodyTo(String.class)
                .log("response from API: " + body())
                .choice()
                .when().simple("${header.CamelHttpResponseCode} == 200")
                .unmarshal().json(JsonLibrary.Jackson, AccessResponseToken.class)
                .process(exchange -> {
                    exchange.setProperty("ACCESS_TOKEN", exchange.getIn().getBody(AccessResponseToken.class).access_token());
                })
                .to("direct:dataSync")
                .otherwise()
                .log("Auth failed!!!")
                .end();
    }
}
