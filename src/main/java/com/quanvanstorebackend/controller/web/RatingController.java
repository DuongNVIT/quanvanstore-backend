package com.quanvanstorebackend.controller.web;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.RatingDTO;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.ImagesEntity;
import com.quanvanstorebackend.entity.RatingEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.RatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController extends BaseController<RatingService> {

    @PostMapping("/")
    public ServerResponse rate(@RequestBody RatingEntity ratingEntity) {
        try {
            RatingEntity ratingEntity1 = service.rate(ratingEntity);
            return ServerResponse.success("Thêm đánh giá", ratingEntity1);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get all image!");
        }
    }

    @GetMapping("/list-all")
    public ServerResponse getAll(@RequestParam Long productId) {
        try {
            List<RatingDTO> entityList = service.getAllRating(productId);
            return ServerResponse.success("Danh sách đánh giá", entityList);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get all image!");
        }
    }
}
