package com.example.sfdcsyncpoc.services;

import com.example.sfdcsyncpoc.models.DBData;
import com.example.sfdcsyncpoc.models.SFData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class DataSyncService {
    private static final Logger logger = LoggerFactory.getLogger(DataSyncService.class);

    public void syncData(SFData sfData, DBData dbData) {
        // Compare sfData and dbData
        boolean isEqual = compareData(sfData, dbData);

        // Simulate data differences
        if (!isEqual) {
            logger.info("Updating DB. Sync data diff with SFDC");
        } else {
            logger.info("No data diff found");
        }
    }

    private boolean compareData(SFData sfData, DBData dbData) {
        Random random = new Random();
        boolean isEqual = random.nextBoolean();

        return isEqual;
    }

}
