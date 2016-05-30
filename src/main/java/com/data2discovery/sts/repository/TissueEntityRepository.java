package com.data2discovery.sts.repository;

import com.data2discovery.sts.data.model.TissueEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mike on 11/22/15.
 */
@Repository
public interface TissueEntityRepository extends CrudRepository<TissueEntity, Integer> {
}
