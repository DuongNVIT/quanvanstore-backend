package com.quanvanstorebackend.controller.admin;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.GeneralInforEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.GeneralInforService;
import org.springframework.web.bind.annotation.*;

@RestController("adminGeneralInfor")
@RequestMapping("/api/admin/infor")
public class GeneralInforController extends BaseController<GeneralInforService> {

    @PostMapping("/")
    public ServerResponse uploadInfor(@RequestBody GeneralInforEntity generalInforEntity) {
        try {
            GeneralInforEntity addedInfor = service.uploadInfor(generalInforEntity);
            return ServerResponse.success("Upload infor thành công!", addedInfor);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception upload infor!");
        }
    }

    @PutMapping("/")
    public ServerResponse updateInfor(@RequestBody GeneralInforEntity generalInforEntity) {
        try {
            service.updateInfor(generalInforEntity);
            return ServerResponse.success("Upload infor thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception upload infor!");
        }
    }
}
