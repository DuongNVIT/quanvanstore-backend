package com.quanvanstorebackend.controller.web;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.ImagesEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.ImagesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImagesController extends BaseController<ImagesService> {

    @GetMapping("/list-all")
    public ServerResponse getAllImage() {
        try {
            List<ImagesEntity> imagesEntityList = service.getAllSelectedImage();
            return ServerResponse.success("Lấy danh sách thư viện ảnh", imagesEntityList);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get all image!");
        }
    }
}
