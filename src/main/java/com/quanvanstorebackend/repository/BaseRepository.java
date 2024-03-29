package com.quanvanstorebackend.repository;

import com.quanvanstorebackend.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    // Deleted false
    T findByIdAndDeletedFalse(Long id);
    T findByCodeAndDeletedFalse(String code);
    List<T> findByIdInAndDeletedFalse(List<Long> ids);
    List<T> findByCodeInAndDeletedFalse(List<String> codes);
    Page<T> findAll(Pageable pageable);
    List<T> findByDeletedFalse(Pageable pageable);
    List<T> findByDeletedFalse();
    Page<T> findByDeletedFalse(Specification<T> specification, Pageable pageable);
    Page<T> findAll(Specification<T> specification, Pageable pageable);

    // Deleted true;
}
