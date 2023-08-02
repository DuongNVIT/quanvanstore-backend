package com.quanvanstorebackend.dto;

import com.quanvanstorebackend.entity.CartEntity;
import com.quanvanstorebackend.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CartDTO extends BaseDTO{

    private Integer quantity;
    private Long userId;
    private Long productId;
    private ProductEntity product;

    public CartDTO(CartEntity cartEntity) {
        this.id = cartEntity.getId();
        this.code = cartEntity.getCode();
        this.createdDate = cartEntity.getCreatedDate();
        this.createdBy = cartEntity.getCreatedBy();
        this.modifiedDate = cartEntity.getModifiedDate();
        this.modifiedBy = cartEntity.getModifiedBy();
        this.deleted = cartEntity.getDeleted();

        this.quantity = cartEntity.getQuantity();
        this.userId = cartEntity.getUserId();
        this.productId = cartEntity.getProductId();

    }

}
