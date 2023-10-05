package com.quanvanstorebackend.controller.admin;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.ImagesEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.ImagesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminImagesController")
@RequestMapping("/api/admin/images")
public class ImagesController extends BaseController<ImagesService> {

    @GetMapping("/list-all")
    public ServerResponse getAll() {
        try {
            List<ImagesEntity> imagesEntityList = service.getAll();
            return ServerResponse.success("Lấy danh sách ảnh!", imagesEntityList);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception upload image to galary!");
        }
    }

    @PostMapping("/")
    public ServerResponse uploadImageToGalary(@RequestBody ImagesEntity imageToAdd) {
        try {
            ImagesEntity addedImage = service.uploadImage(imageToAdd);
            return ServerResponse.success("Upload ảnh vào thư viện", addedImage);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception upload image to galary!");
        }
    }

    @PutMapping("/{imageId}")
    public ServerResponse uploadImageToGalary(@PathVariable Long imageId) {
        try {
            service.updateSelected(imageId);
            return ServerResponse.success("Upload ảnh vào thư viện");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception upload image to galary!");
        }
    }

    @DeleteMapping("/{imageId}")
    public ServerResponse deleteImage(@PathVariable Long imageId) {
        try {
            service.deleteImage(imageId);
            return ServerResponse.success("Xóa ảnh khỏi thư viện");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception delete image!");
        }
    }


}
