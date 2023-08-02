package com.quanvanstorebackend.controller.web;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.CategoryEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends BaseController<CategoryService> {

    @GetMapping("/list-all")
    public ServerResponse getAllCategory() {
        try {
            List<CategoryEntity> categoryEntities = service.getAllCategory();
            return ServerResponse.success("Lấy danh sách danh mục thảnh công!", categoryEntities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Unknown exception get all category!");
        }
    }
}
