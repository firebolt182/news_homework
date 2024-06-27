package org.javaacademy.news_homework.repository;

import org.javaacademy.news_homework.entity.Category;
import org.javaacademy.news_homework.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<List<News>> findNewsByDate(LocalDate date);
    Optional<List<News>> findNewsByDateAndCategory(LocalDate date, Category category);


}
