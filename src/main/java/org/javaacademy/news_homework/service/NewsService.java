package org.javaacademy.news_homework.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.javaacademy.news_homework.dto.CategoryDto;
import org.javaacademy.news_homework.dto.NewsDto;
import org.javaacademy.news_homework.dto.NewsDtoRq;
import org.javaacademy.news_homework.entity.Category;
import org.javaacademy.news_homework.entity.News;
import org.javaacademy.news_homework.mapper.CategoryMapper;
import org.javaacademy.news_homework.mapper.NewsMapper;
import org.javaacademy.news_homework.repository.CategoryRepository;
import org.javaacademy.news_homework.repository.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final NewsMapper newsMapper;
    private final CategoryMapper categoryMapper;


    public void createNewsTest() {
        createNews(NewsDtoRq.builder()
                .header("test header")
                .categoryName("sport")
                .date(LocalDate.now())
                .text("test text")
                .build());
        createNews(NewsDtoRq.builder()
                .header("big trees")
                .categoryName("nature")
                .date(LocalDate.now())
                .text("big trees were found")
                .build());
        createNews(NewsDtoRq.builder()
                .header("hat-trick from Orel")
                .categoryName("sport")
                .date(LocalDate.now())
                .text("today blah-blah")
                .build());
        createNews(NewsDtoRq.builder()
                .header("man drinks 4 whiskey bottles ")
                .categoryName("health")
                .date(LocalDate.of(2024, 11, 22))
                .text("it is amazing")
                .build());
    }

        /*@PostConstruct
        public void init() {
            createNewsTest();
            List<Category> todayNews = findTodayNews();
            System.out.println(todayNews);
        }*/



    @Transactional
    public void createNews(NewsDtoRq dto) {
        News news = newsMapper.convertToEntity(dto);
        try {
            Category category =
                    categoryRepository.findCategoryByName(dto.getCategoryName()).orElseThrow();
            news.setCategory(category);
        } catch (Exception e) {
            Category category = new Category(dto.getCategoryName());
            categoryRepository.save(category);
            news.setCategory(category);
        }
        newsRepository.save(news);
        System.out.println(news);
    }

    @Transactional(readOnly = true)
    public String findTodayNews() {
        List<News> todayNews =
                newsRepository.findNewsByDate(LocalDate.now()).orElseThrow();
        List<Category> categoryList = getActualCategoriesForToday(todayNews);
        StringBuilder builder = new StringBuilder();
        builder.append(parseDate(LocalDate.now()));
        return getNewsSortedByCategory(todayNews, categoryList, builder);
    }

    @Transactional(readOnly = true)
    public String findNewsByDateAndCategory(LocalDate date, String categoryName) {
        Category category = categoryRepository.findCategoryByName(categoryName).orElseThrow();
        List<News> news = newsRepository.findNewsByDateAndCategory(date,
                category).orElseThrow();
        StringBuilder builder = new StringBuilder();
        builder.append(parseDate(date));
        builder.append(categoryName).append("\n");
        news.forEach(story -> builder.append(story).append("\n"));
        return builder.toString();
    }

    private String parseDate(LocalDate date) {
        return "%s.%s.%s\n".formatted(
                date.getDayOfMonth(),
                date.getMonth().getValue(),
                date.getYear());
    }

    private String getNewsSortedByCategory(List<News> todayNews,
                                           List<Category> categories,
                                           StringBuilder builder) {
        for (int i = 0; i < categories.size(); i++) {
            final Integer iteration = i;
            builder.append(categories.get(iteration) + "\n");
            todayNews.stream()
                    .filter(news -> news.getCategory().equals(categories.get(iteration)))
                    .forEach(news -> builder.append(news + "\n"));
        }
        return builder.toString();
    }

    private List<Category> getActualCategoriesForToday(List<News> todayNews) {
        return todayNews.stream()
                .map(News::getCategory)
                .toList();
    }
}
