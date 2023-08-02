package com.quanvanstorebackend.controller.web;

import com.quanvanstorebackend.dto.OrderItemDTO;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bill")
public class BillController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/list-all")
    public ServerResponse getAllBill() {
        try {
            List<OrderItemDTO> orderItemDTOS = orderItemService.getAllOrders();
            return ServerResponse.success("Lấy danh sách thành công!", orderItemDTOS);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get all bill!");
        }
    }
}
