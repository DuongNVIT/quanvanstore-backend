package com.quanvanstorebackend.repository;

import com.quanvanstorebackend.entity.GeneralInforEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralInforRepository extends BaseRepository<GeneralInforEntity> {
    GeneralInforEntity findFirstByDeletedFalse();
}
