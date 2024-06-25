package org.javaacademy.news_homework.repository;

import org.javaacademy.news_homework.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByName(String name);
}
