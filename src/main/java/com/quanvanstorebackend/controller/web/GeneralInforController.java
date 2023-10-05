package com.quanvanstorebackend.controller.web;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.GeneralInforEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.GeneralInforService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/infor")
public class GeneralInforController extends BaseController<GeneralInforService> {

    @GetMapping("/")
    public ServerResponse getInfor() {
        try {
            GeneralInforEntity generalInfor = service.getInfor();
            return ServerResponse.success("Upload infor thành công!", generalInfor);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception upload infor!");
        }
    }

}
