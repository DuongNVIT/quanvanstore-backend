package com.quanvanstorebackend.controller.admin;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.NewsEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminNewsController")
@RequestMapping("/api/admin/news")
public class NewsController extends BaseController<NewsService> {

    @PostMapping("/")
    public ServerResponse addNews(@RequestBody NewsEntity newsEntityToAdd) {
        try {
            NewsEntity addedNews = service.addNews(newsEntityToAdd);
            return ServerResponse.success("Thêm tin tức thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception addNews!");
        }
    }

    @GetMapping("/list-all")
    public ServerResponse getAll() {
        try {
            List<NewsEntity> newsEntityList = service.getAllNews();
            return ServerResponse.success("Thêm tin tức thành công!", newsEntityList);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception addNews!");
        }
    }

    @DeleteMapping("/{newsId}")
    public ServerResponse getAll(@PathVariable Long newsId) {
        try {
            service.deleteNews(newsId);
            return ServerResponse.success("Xóa tin tức thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception addNews!");
        }
    }

    @PutMapping("/")
    public ServerResponse updateNews(@RequestBody NewsEntity newsEntity) {
        try {
            service.updateNews(newsEntity);
            return ServerResponse.success("Cập nhật tin tức thành công!");
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception addNews!");
        }
    }


}
