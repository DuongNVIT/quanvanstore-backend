package com.quanvanstorebackend.service;

import com.quanvanstorebackend.dto.OrderItemDTO;
import com.quanvanstorebackend.entity.OrderItemEntity;
import com.quanvanstorebackend.entity.ProductEntity;
import com.quanvanstorebackend.repository.OrderItemRepository;
import com.quanvanstorebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderItemService extends BaseService<OrderItemRepository, OrderItemEntity> {

    @Autowired
    private ProductRepository productRepository;

    public List<OrderItemDTO> getAllOrders() {
        List<OrderItemEntity> orderItemEntities = repository.findByDeletedFalse();
        List<Long> productIds = orderItemEntities.stream().map(e -> e.getProductId()).collect(Collectors.toList());
        List<ProductEntity> productEntities = productRepository.findByIdInAndDeletedFalse(productIds);
        Map<Long, ProductEntity> mapProductEntityById = productEntities
                .stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));
        List<OrderItemDTO> orderItemDTOS = orderItemEntities.stream()
                .map(OrderItemDTO::new)
                .map(orderItemDTO -> {
                    orderItemDTO.setProduct(mapProductEntityById.get(orderItemDTO.getProductId()));
                    return orderItemDTO;
                })
                .collect(Collectors.toList());
        return orderItemDTOS;
    }
}
