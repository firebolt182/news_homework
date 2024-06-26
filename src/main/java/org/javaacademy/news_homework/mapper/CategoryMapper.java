package org.javaacademy.news_homework.mapper;

import org.javaacademy.news_homework.dto.CategoryDto;
import org.javaacademy.news_homework.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto convertToDto(Category category);
}
