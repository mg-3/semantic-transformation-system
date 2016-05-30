package com.data2discovery.sts.dao;

import com.data2discovery.sts.data.model.TissueEntity;
import com.data2discovery.sts.repository.TissueEntityRepository;
import org.apache.jena.ext.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mike on 11/20/15.
 */
@Component
public class TissueDao {

    @Autowired
    private TissueEntityRepository tissueEntityRepository;

    @Transactional
    public TissueEntity storeTissue(TissueEntity tissue) {
        return tissueEntityRepository.save(tissue);
    }

    public List<TissueEntity> findTissueByIds(String tids) {
        return Lists.newArrayList(tissueEntityRepository.findAll());
    }
}
