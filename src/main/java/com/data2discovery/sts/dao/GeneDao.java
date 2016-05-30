package com.data2discovery.sts.dao;

import com.data2discovery.sts.data.model.GeneEntity;
import com.data2discovery.sts.repository.GeneRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.jena.ext.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mike on 11/20/15.
 */
@Slf4j
@Component
public class GeneDao {

    @Autowired
    private GeneRepository geneRepository;

    public List<GeneEntity> findAllGenes() {
        return Lists.newArrayList(geneRepository.findAll());
    }

    public GeneEntity findGeneById(String geneId) {
        return geneRepository.findOne(geneId);
    }

    @Transactional
    public GeneEntity putContent(GeneEntity gene) {
        return geneRepository.save(gene);
    }

    public List<GeneEntity> findGeneByIds(List<String> ids) {
        return Lists.newArrayList(geneRepository.findAll(ids));
    }
}
