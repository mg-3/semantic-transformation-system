package com.data2discovery.sts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by mike on 10/16/15.
 */
@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class SemanticTransformationSystem {

    public static void main(String[] args) {
        try {
            ApplicationContext appCtx = SpringApplication.run(SemanticTransformationSystem.class, args);
        } catch (Exception e) {
            log.error("*** Unable to start STS ***", e);
            System.exit(9);
        }
    }
}
