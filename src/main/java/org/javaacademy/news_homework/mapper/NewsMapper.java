package org.javaacademy.news_homework.mapper;

import org.javaacademy.news_homework.dto.NewsDto;
import org.javaacademy.news_homework.dto.NewsDtoRq;
import org.javaacademy.news_homework.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface NewsMapper {
    News convertToEntity(NewsDtoRq dto);
    List<NewsDto> convertToDto(List<News> news);
    @Mapping(target = "categoryDto", source = "category")
    NewsDto convertToDto(News news);
    NewsDto convertToDto(NewsDtoRq newsDtoRq);

}
