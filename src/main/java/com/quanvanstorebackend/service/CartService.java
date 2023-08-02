package com.quanvanstorebackend.service;

import com.quanvanstorebackend.dto.CartDTO;
import com.quanvanstorebackend.entity.CartEntity;
import com.quanvanstorebackend.entity.ProductEntity;
import com.quanvanstorebackend.entity.UserEntity;
import com.quanvanstorebackend.repository.CartRepository;
import com.quanvanstorebackend.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CartService extends BaseService<CartRepository, CartEntity> {

    @Autowired
    private ProductService productService;

    public List<CartDTO> getCart() {
        UserEntity currentUser = CurrentUserUtils.getCurrentUser();
        List<CartEntity> cartEntities = repository.findByUserIdAndDeletedFalse(currentUser.getId());
        List<Long> productIds = cartEntities.stream().map(e -> e.getProductId()).collect(Collectors.toList());
        System.out.println(productIds);
        List<ProductEntity> listProducts = productService.findByIdIn(productIds);
        System.out.println(listProducts);
        Map<Long, ProductEntity> mapProductEntityById = listProducts.stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));
        List<CartDTO> cartDTOS = cartEntities
                .stream()
                .map(CartDTO::new)
                .map(cartDTO -> {
                    cartDTO.setProduct(mapProductEntityById.get(cartDTO.getProductId()));
                    return cartDTO;
                })
                .collect(Collectors.toList());
        System.out.println(listProducts);
        return cartDTOS;
    }

    public void decrease(Long cartItemId) {
        CartEntity cartEntity = repository.findByIdAndDeletedFalse(cartItemId);
        cartEntity.setQuantity(cartEntity.getQuantity() - 1);
        update(cartEntity);
    }

    public void increase(Long cartItemId) {
        CartEntity cartEntity = repository.findByIdAndDeletedFalse(cartItemId);
        cartEntity.setQuantity(cartEntity.getQuantity() + 1);
        update(cartEntity);
    }

    public void deleteCartItem(Long cartItemId) {
        delete(cartItemId);
    }

    public CartEntity addToCart(Long productId, Integer quantity) {
        UserEntity currentUser = CurrentUserUtils.getCurrentUser();
        CartEntity cartEntity = repository.findByUserIdAndProductIdAndDeletedFalse(currentUser.getId(), productId);
        if(cartEntity == null) {
            cartEntity = new CartEntity();
            cartEntity.setQuantity(quantity == null ? 1 : quantity);
            cartEntity.setUserId(currentUser.getId());
            cartEntity.setProductId(productId);
            return save(cartEntity);
        } else {
            cartEntity.setQuantity(cartEntity.getQuantity() + ((quantity == null) ? 1 : quantity));
            update(cartEntity);
            return cartEntity;
        }
    }
}
