package com.data2discovery.sts.repository;

import com.data2discovery.sts.data.model.GeneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mike on 11/16/15.
 */
@Repository
public interface GeneRepository extends CrudRepository<GeneEntity, String> {

    List<GeneEntity> findByGeneId(String id);

}