package com.quanvanstorebackend.repository;

import com.quanvanstorebackend.entity.BannerImageEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerImageRepository extends BaseRepository<BannerImageEntity> {
    BannerImageEntity findBySelectedTrueAndDeletedFalse();
}
