package org.javaacademy.news_homework.mapper;

import org.javaacademy.news_homework.dto.NewsDto;
import org.javaacademy.news_homework.dto.NewsDtoRq;
import org.javaacademy.news_homework.entity.News;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    News convertToEntity(NewsDtoRq dto);

    List<NewsDto> convertToDto(List<News> news);

}
