package com.quanvanstorebackend.service;

import com.quanvanstorebackend.dto.RatingDTO;
import com.quanvanstorebackend.entity.RatingEntity;
import com.quanvanstorebackend.entity.UserEntity;
import com.quanvanstorebackend.repository.RatingRepository;
import com.quanvanstorebackend.repository.UserRepository;
import com.quanvanstorebackend.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RatingService extends BaseService<RatingRepository, RatingEntity> {

    @Autowired
    private UserRepository userRepository;

    public RatingEntity rate(RatingEntity ratingEntity) {
        UserEntity currUser = CurrentUserUtils.getCurrentUser();
        ratingEntity.setUserId(currUser.getId());
        return save(ratingEntity);
    }

    public List<RatingDTO> getAllRating(Long productId) {
        List<RatingEntity> ratingEntities = repository.findByProductId(productId);
        List<Long> userIds = ratingEntities.stream().map(e->e.getUserId()).collect(Collectors.toList());
        List<UserEntity> userEntities = userRepository.findByIdInAndDeletedFalse(userIds);
        Map<Long, UserEntity> mapUserEntityByUserId = userEntities
                .stream()
                .collect(Collectors.toMap(UserEntity::getId, Function.identity()));
        List<RatingDTO> ratingDTOS = ratingEntities.stream()
                .map(RatingDTO::new)
                .map(e -> {
                    e.setUsername(mapUserEntityByUserId.get(e.getUserId()).getUsername());
                    return e;
                })
                .collect(Collectors.toList());
        return ratingDTOS;
    }
}
