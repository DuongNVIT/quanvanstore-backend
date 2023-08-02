package com.quanvanstorebackend.controller.admin;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.ProductEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController("adminProductController")
@RequestMapping("/api/admin/product")
public class ProductController extends BaseController<ProductService> {

    @PostMapping("/")
    public ServerResponse addNewProduct(@RequestBody ProductEntity productEntity) {
        try {
            ProductEntity product = service.addNewProduct(productEntity);
            return ServerResponse.success("Thêm sản phẩm thành công!", product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Unknown exception add new product!");
        }
    }

    @DeleteMapping("/")
    public ServerResponse deleteProduct(@RequestParam("productId") Long productId) {
        try {
            service.deleteProduct(productId);
            return ServerResponse.success("Xóa sản phẩm thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Unknown exception delete product!");
        }
    }

}
