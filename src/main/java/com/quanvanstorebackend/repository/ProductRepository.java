package com.quanvanstorebackend.repository;

import com.quanvanstorebackend.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity> {

    List<ProductEntity> findTop5ByCategoryIdAndDeletedFalseAndIdNot(Long categoryId, Long productId);

}
