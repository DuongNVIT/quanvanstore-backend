package com.quanvanstorebackend.repository;

import com.quanvanstorebackend.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {
    UserEntity findByUsername(String username);
    int countDistinctByDeletedFalse();
    @Query(value = "select count(*) from user " +
            "where created_date > now() - interval :n day " +
            "and created_date < now() - interval :m day " +
            "and deleted = false " +
            "and role_id = 2", nativeQuery = true)
    int countNewUserOverTheLastNDays(@Param("n") int day1, @Param("m") int day2);

    UserEntity findByCode(String userCode);

    List<UserEntity> findByRoleIdAndDeletedFalse(long l);

    List<UserEntity> findByRoleId(long l);
}
