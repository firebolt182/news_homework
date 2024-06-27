package org.javaacademy.news_homework.controller;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.javaacademy.news_homework.dto.NewsDtoRq;
import org.javaacademy.news_homework.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @PostMapping
    public ResponseEntity createNews(@RequestBody NewsDtoRq dto) {
        newsService.createNews(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<String> findTodayNews() {
        return ResponseEntity.status(HttpStatus.OK).body(newsService.findTodayNews());
    }

    @GetMapping("/find")
    public ResponseEntity<String> findNewsByDateAndCategory(@RequestParam LocalDate date,
                                                            @RequestParam String categoryName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(newsService.findNewsByDateAndCategory(date, categoryName));
    }
}
