package com.quanvanstorebackend.controller.admin;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.OrderDTO;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
