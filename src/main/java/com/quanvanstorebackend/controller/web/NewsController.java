package com.quanvanstorebackend.controller.web;

import com.quanvanstorebackend.controller.BaseController;
import com.quanvanstorebackend.dto.ServerResponse;
import com.quanvanstorebackend.entity.NewsEntity;
import com.quanvanstorebackend.exception.UnknowException;
import com.quanvanstorebackend.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController extends BaseController<NewsService> {

    @GetMapping("/list-all")
    public ServerResponse getAllNews() {
        try {
            List<NewsEntity> listNews = service.getAllNews();
            return ServerResponse.success("Lấy danh sách tin tức!", listNews);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknownexception get all news!");
        }
    }

    @GetMapping("/{newsId}")
    public ServerResponse getOneNews(@PathVariable Long newsId) {
        try {
            NewsEntity newsEntity = service.getOneNews(newsId);
            return ServerResponse.success("Lấy thông tin tin tức!", newsEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnknowException("Unknown exception get one news!");
        }
    }
}
