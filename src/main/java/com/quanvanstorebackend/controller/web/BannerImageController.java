package com.quanvanstorebackend.controller.web;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.BannerImageEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.BannerImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/banner")
public class BannerImageController extends BaseController<BannerImageService> {

    @GetMapping("/")
    public ServerResponse getCurrentBannner() {
        try {
            BannerImageEntity currentBanner = service.getCurrentBanner();
            return ServerResponse.success("Lấy banner hiện tại!", currentBanner);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("unknown exception get current banner!");
        }
    }
}
