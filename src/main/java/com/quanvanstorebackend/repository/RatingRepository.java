package com.quanvanstorebackend.repository;

import com.quanvanstorebackend.entity.RatingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends BaseRepository<RatingEntity> {
    List<RatingEntity> findByProductId(Long productId);
}
