package com.quanvanstorebackend.controller.admin;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.BannerImageEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.BannerImageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/adminBannerImageController")
@RequestMapping("/api/admin/banner")
public class BannerImageController extends BaseController<BannerImageService> {

    @PostMapping("/")
    public ServerResponse uploadBannerImage(@RequestBody BannerImageEntity bannerImage) {
        try {
            BannerImageEntity bannerImageEntity = service.uploadBanner(bannerImage);
            return ServerResponse.success("Upload banner thành công!", bannerImageEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception upload banner image!");
        }
    }

    @GetMapping("/list-all")
    public ServerResponse getAll() {
        try {
            List<BannerImageEntity> bannerImageEntity = service.getAllBanner();
            return ServerResponse.success("Upload banner thành công!", bannerImageEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception upload banner image!");
        }
    }

    @PutMapping("/")
    public ServerResponse updateBanner(@RequestParam Long bannerId) {
        try {
            service.updateBanner(bannerId);
            return ServerResponse.success("Update banner thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception update banner!");
        }
    }


}
