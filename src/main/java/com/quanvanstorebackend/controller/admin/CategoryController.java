package com.quanvanstorebackend.controller.admin;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.CategoryEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("adminCategoryController")
@RequestMapping("/api/admin/category")
public class CategoryController extends BaseController<CategoryService> {

    @PostMapping("/")
    public ServerResponse createCategory(@RequestBody CategoryEntity categoryEntity) {
        try {
            CategoryEntity categoryEntity1 = service.addNewCategory(categoryEntity);
            return ServerResponse.success("Thêm mới cateogry!", categoryEntity1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknowException("Unknown exception create new category!");
        }
    }
}
