package com.quanvanstorebackend.repository;

import com.quanvanstorebackend.entity.ImagesEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends BaseRepository<ImagesEntity> {
    List<ImagesEntity> findBySelectedTrueAndDeletedFalse();
}
