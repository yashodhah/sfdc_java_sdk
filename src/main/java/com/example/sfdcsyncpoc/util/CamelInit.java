package com.example.sfdcsyncpoc.util;

import jakarta.annotation.PostConstruct;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelInit {
    @Autowired
    private FluentProducerTemplate fluentProducerTemplate;

    @PostConstruct
    private void initCamel() {
//        fluentProducerTemplate.to("direct:sync").send();
    }
}
