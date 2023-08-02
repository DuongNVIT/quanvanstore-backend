package com.quanvanstorebackend.controller.web;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.dto.filter.ProductFilterSearch;
import com.quanvanstorebackend.entity.ProductEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.CartService;
import com.quanvanstorebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseController<ProductService> {

    @Autowired
    private CartService cartService;

    @GetMapping("/list-all")
    public ServerResponse getListAllProducts(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             ProductFilterSearch productFilterSearch) {
        try {
            System.out.println("Vào đây");
            Pageable pageable = PageRequest.of(page, size);
            List<ProductEntity> listProducts = service.getAllProducts(productFilterSearch, pageable);
//            List<CartDTO> cartDTOS = cartService.getCart();
            return ServerResponse.success("Lấy danh sách sản phẩm thành công!", listProducts);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Unknown exception get all products!");
        }
    }

    @GetMapping("/list")
    public ServerResponse getListAll(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        try {
            return ServerResponse.success("Thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get list all!");
        }
    }

    @GetMapping("/")
    public ServerResponse getProduct(@RequestParam Long productId) {
        try {
            ProductEntity productEntity = service.getProduct(productId);
            return ServerResponse.success("Lấy thông tin sản phẩm thành công!", productEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Unknown exception get product!");
        }

    }

}
