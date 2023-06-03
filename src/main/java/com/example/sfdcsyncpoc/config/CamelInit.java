package com.example.sfdcsyncpoc.config;

import com.example.sfdcsyncpoc.routes.SalesforceRouteBuilder;
import jakarta.annotation.PostConstruct;
import org.apache.camel.main.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelInit {

    @Autowired
    SalesforceRouteBuilder salesforceRouteBuilder;

    @PostConstruct
    private void initCamel() throws Exception {
        Main camelMain = new Main();
        camelMain.configure().addRoutesBuilder(salesforceRouteBuilder);
        camelMain.run();
    }
}
