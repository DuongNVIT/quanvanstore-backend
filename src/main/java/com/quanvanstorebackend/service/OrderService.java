package com.quanvanstorebackend.service;

import com.quanvanstorebackend.dto.OrderDTO;
import com.quanvanstorebackend.entity.OrderItemEntity;
import com.quanvanstorebackend.entity.OrdersEntity;
import com.quanvanstorebackend.entity.ProductEntity;
import com.quanvanstorebackend.entity.UserEntity;
import com.quanvanstorebackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService extends BaseService<OrderRepository, OrdersEntity> {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemService orderItemService;

    public List<OrderDTO> getAllForAdmin() {
        List<OrderItemEntity> ordersEntities = orderItemService.findAllAndDeletedFalse();
        List<Long> productIds = ordersEntities.stream().map(e->e.getProductId()).collect(Collectors.toList());
        List<ProductEntity> productEntities = productService.findByIdIn(productIds);
        Map<Long, ProductEntity> mapProductEntityByProductId = productEntities
                .stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));
        List<Long> userIds = ordersEntities.stream().map(e->e.getUserId()).collect(Collectors.toList());
        List<UserEntity> userEntities = userService.findByIdIn(userIds);
        Map<Long, UserEntity> mapUserEntityByUserId = userEntities
                .stream()
                .collect(Collectors.toMap(UserEntity::getId, Function.identity()));
        List<OrderDTO> orderDTOList = ordersEntities.stream()
                .map(OrderDTO::new)
                .map(orderDTO -> {
                    orderDTO.setProduct(mapProductEntityByProductId.get(orderDTO.getProductId()));
                    orderDTO.setUser(mapUserEntityByUserId.get(orderDTO.getUserId()));
                    return orderDTO;
                })
                .collect(Collectors.toList());
        return orderDTOList;
    }
}
