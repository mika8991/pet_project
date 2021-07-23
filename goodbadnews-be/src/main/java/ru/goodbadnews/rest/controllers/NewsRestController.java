package ru.goodbadnews.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.goodbadnews.rest.Services.NewsService;
import ru.goodbadnews.rest.model.News;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewsRestController {

    @Autowired
    private NewsService newsServices;

    @GetMapping("/news")
    public List getAllNews() {
        return newsServices.getAllNews();
    }

    @PostMapping("/news")
    public void addNewNews(@RequestBody News newNews) {
        newsServices.insertNews(newNews);
    }

    @GetMapping("/news/{id}")
    public News getNewsById(@PathVariable @RequestBody Long id) {
        return newsServices.getNewsById(id);
    }

    @DeleteMapping("/news")
    public void deleteNewsById(@RequestBody Long id) {
        newsServices.deleteNewsById(id);
    }

    @PutMapping("/news/{id}")
    public void findAndModifyNewsById(@RequestBody News newNews, @PathVariable Long id) {
        newsServices.findAndModifyNewsById(newNews, id);
    }
}
