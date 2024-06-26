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
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
            findNews();
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
        StringBuilder builder = new StringBuilder(parseDate(LocalDate.now()));

        List<NewsDto> todayNews = newsMapper.convertToDto(
                newsRepository.findNewsByDate(LocalDate.now()).orElseThrow());
        Set<CategoryDto> categorySet = getActualCategoriesForToday(todayNews);
        return getNewsSortedByCategory(todayNews, categorySet, builder);
    }

    @Transactional(readOnly = true)
    public String findNewsByDateAndCategory(LocalDate date, String categoryName) {
        Category category = categoryRepository.findCategoryByName(categoryName).orElseThrow();
        List<NewsDto> news = newsMapper.convertToDto(newsRepository.findNewsByDateAndCategory(date,
                category).orElseThrow());
        StringBuilder builder = new StringBuilder(parseDate(date));
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

    private String getNewsSortedByCategory(List<NewsDto> todayNews,
                                           Set<CategoryDto> categories,
                                           StringBuilder builder) {
            for (CategoryDto category : categories) {
                builder.append(category).append("\n");
                todayNews.stream()
                        .filter(news -> news.getCategoryDto().getName().equals(category.getName()))
                        .forEach(builder::append);
            }
        return builder.toString();
    }

    private Set<CategoryDto> getActualCategoriesForToday(List<NewsDto> todayNews) {
        return todayNews.stream()
                .map(NewsDto::getCategoryDto)
                .collect(Collectors.toSet());
    }
}
