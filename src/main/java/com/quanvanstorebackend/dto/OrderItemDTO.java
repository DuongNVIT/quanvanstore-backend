package com.quanvanstorebackend.dto;

import com.quanvanstorebackend.entity.OrderItemEntity;
import com.quanvanstorebackend.entity.OrdersEntity;
import com.quanvanstorebackend.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO extends BaseDTO{
    private int quantity;
    private Long userId;
    private Long productId;
    private ProductEntity product;

    public OrderItemDTO(OrderItemEntity orderItemEntity) {
        this.id = orderItemEntity.getId();
        this.code = orderItemEntity.getCode();
        this.createdBy = orderItemEntity.getCreatedBy();
        this.createdDate = orderItemEntity.getCreatedDate();
        this.modifiedBy = orderItemEntity.getModifiedBy();
        this.modifiedDate = orderItemEntity.getModifiedDate();
        this.deleted = orderItemEntity.getDeleted();

        this.quantity = orderItemEntity.getQuantity();
        this.userId = orderItemEntity.getUserId();
        this.productId = orderItemEntity.getProductId();

    }

}
