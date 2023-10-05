package com.quanvanstorebackend.dto;

import com.quanvanstorebackend.entity.OrderItemEntity;
import com.quanvanstorebackend.entity.ProductEntity;
import com.quanvanstorebackend.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO extends BaseDTO{
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Integer status;
    private UserEntity user;
    private ProductEntity product;

    public OrderDTO(OrderItemEntity orderItemEntity) {
        this.id = orderItemEntity.getId();
        this.code = orderItemEntity.getCode();
        this.createdDate = orderItemEntity.getCreatedDate();
        this.createdBy = orderItemEntity.getCreatedBy();
        this.modifiedDate = orderItemEntity.getModifiedDate();
        this.modifiedBy = orderItemEntity.getModifiedBy();
        this.deleted = orderItemEntity.getDeleted();

        this.userId = orderItemEntity.getUserId();
        this.productId = orderItemEntity.getProductId();
        this.quantity = orderItemEntity.getQuantity();
        this.status = orderItemEntity.getStatus();
    }
}
