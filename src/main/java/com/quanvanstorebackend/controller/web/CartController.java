package com.quanvanstorebackend.controller.web;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.CartDTO;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.CartEntity;
import com.quanvanstorebackend.entity.ProductEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.CartService;
import com.quanvanstorebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController extends BaseController<CartService> {

    @Autowired
    private ProductService productService;

    @GetMapping("/list-all")
    public ServerResponse getCart() {
        try {
            List<CartDTO> cartDTOS = service.getCart();
            return ServerResponse.success("Lấy danh sách giỏ hàng thành công!", cartDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Unknown exception get cart!");
        }
    }

    @PutMapping("/decrease")
    public ServerResponse decrease(@RequestParam Long cartItemId) {
        try {
            service.decrease(cartItemId);
            return ServerResponse.success("Cập nhật giỏ hàng thành công!");
        } catch (Exception e) {
            e.printStackTrace();;
            throw new UnknowException("Unknow exception decrease!");
        }
    }

    @PutMapping("/increase")
    public ServerResponse increase(@RequestParam Long cartItemId) {
        try {
            service.increase(cartItemId);
            return ServerResponse.success("Cập nhật giỏ hàng thành công!");
        } catch (Exception e) {
            e.printStackTrace();;
            throw new UnknowException("Unknow exception decrease!");
        }
    }

    @DeleteMapping("/")
    public ServerResponse delete(@RequestParam Long cartItemId) {
        try {
            service.deleteCartItem(cartItemId);
            return ServerResponse.success("Xóa sản phẩm khỏi giỏ hàng thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception delete cart item!");
        }
    }

    @PostMapping("/")
    public ServerResponse addToCart(@RequestParam Long productId,
                                    @RequestParam(required = false) Integer quantity) {
        try {
            CartEntity cartEntity = service.addToCart(productId, quantity);
            return ServerResponse.success("Thêm sản phẩm vào giỏ hàng thành công!", cartEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception add to cart!");
        }
    }
}
