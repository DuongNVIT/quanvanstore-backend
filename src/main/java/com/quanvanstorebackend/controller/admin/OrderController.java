package com.quanvanstorebackend.controller.admin;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.OrderDTO;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminOrderController")
@RequestMapping("/api/admin/order")
public class OrderController extends BaseController<OrderService> {

    @GetMapping("/")
    public ServerResponse getAllForAdmin() {
        try {
            List<OrderDTO> orderDTOList = service.getAllForAdmin();
            return ServerResponse.success("Lấy danh sách đơn hàng thành công!", orderDTOList);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get all orders for admin!");
        }
    }
    @PutMapping("/")
    public ServerResponse updateStatus(@RequestParam Long orderItemId, Integer status) {
        try {
            service.updateStatus(orderItemId, status);
            return ServerResponse.success("Lấy danh sách đơn hàng thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get all orders for admin!");
        }
    }
}
