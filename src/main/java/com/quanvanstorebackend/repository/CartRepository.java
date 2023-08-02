package com.quanvanstorebackend.repository;

import com.quanvanstorebackend.entity.CartEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends BaseRepository<CartEntity> {
    List<CartEntity> findByUserId(Long id);

    List<CartEntity> findByUserIdAndDeletedFalse(Long id);

    CartEntity findByUserIdAndProductIdAndDeletedFalse(Long id, Long productId);
}
