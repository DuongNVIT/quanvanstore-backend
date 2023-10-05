package com.quanvanstorebackend.dto;

import com.quanvanstorebackend.entity.RatingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO extends BaseDTO{
    private Long userId;
    private Long productId;
    private Integer rate;
    private String content;
    private String username;

    public RatingDTO(RatingEntity ratingEntity) {
        this.id = ratingEntity.getId();
        this.code = ratingEntity.getCode();
        this.createdDate = ratingEntity.getCreatedDate();
        this.createdBy = ratingEntity.getCreatedBy();
        this.modifiedDate = ratingEntity.getModifiedDate();
        this.modifiedBy = ratingEntity.getModifiedBy();
        this.deleted = ratingEntity.getDeleted();

        this.userId =  ratingEntity.getUserId();
        this.productId = ratingEntity.getProductId();
        this.rate = ratingEntity.getRate();
        this.content = ratingEntity.getContent();

    }
}
