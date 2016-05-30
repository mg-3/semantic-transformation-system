package com.data2discovery.sts.dao;

import com.data2discovery.sts.SemanticTransformationSystem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mgarcia on 5/25/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SemanticTransformationSystem.class})
@WebAppConfiguration
@IntegrationTest("server.port=9090")
@Slf4j
public class BiologicalGene {

    @Autowired
    private ApplicationContext appCtx;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void biologicalGene() {
        String query = "select " +
                "s.refStsId, " +
                "s.stsShrtNm, " +
                "s.stsDescTxt " +
                "from RefStsEntity as s";
        assertNotNull(appCtx);
        assertNotNull(entityManager);
        Query queryObject = entityManager.createQuery(query);
        List results = queryObject.getResultList();
        assertNotNull(results);
        assertFalse(results.isEmpty());
        log.info("Found {} rows", results.size());


    }
}
