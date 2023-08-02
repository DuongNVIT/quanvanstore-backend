package com.quanvanstorebackend.service;

import com.quanvanstorebackend.entity.CategoryEntity;
import com.quanvanstorebackend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends BaseService<CategoryRepository, CategoryEntity> {

    public List<CategoryEntity> getAllCategory() {
        return repository.findByDeletedFalse();
    }
}
