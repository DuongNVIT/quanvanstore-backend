package com.quanvanstorebackend.controller.web;

import com.quanvanstorebackend.dto.PaymenRequestDTO;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.VNPayService;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/")
    public ServerResponse payment(@RequestBody PaymenRequestDTO paymenRequestDTO) {
        try {
            System.out.println(paymenRequestDTO.getProducts());
            System.out.println(paymenRequestDTO.getQuantity());
            Object res = vnPayService.pay(paymenRequestDTO);
            return ServerResponse.success("Chuyển đến trang thanh toán!", res);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception payment!");
        }
    }

    @GetMapping("/save-order")
    public RedirectView saveOrder(@RequestParam(name = "vnp_OrderInfo") String orders) {
        try {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("http://localhost:3000/profile/bill");
            String products = (orders.split("\\."))[2].substring(1, ((orders.split("\\."))[2]).length() - 1);
            List<Long> productIds = Arrays.stream(products.split(", ")).map(Long::parseLong).collect(Collectors.toList());
            Long userId = Long.parseLong(orders.split("\\.")[4]);
            String quantity = (orders.split("\\."))[3].substring(1, ((orders.split("\\."))[3]).length() - 1);
            List<Integer> quantityList = Arrays.stream(quantity.split(", ")).map(Integer::parseInt).collect(Collectors.toList());
            vnPayService.saveOrder(productIds, quantityList, userId);
            return redirectView;
//            return ServerResponse.success("Lưu hóa đơn!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception save order!");
        }
    }


}
