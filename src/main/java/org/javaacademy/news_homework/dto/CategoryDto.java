package org.javaacademy.news_homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaacademy.news_homework.entity.News;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CategoryDto {
    private String name;
    private List<NewsDto> news = new ArrayList<>();

    @Override
    public String toString() {
        return "%s: \n%s".formatted(name, news.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", ""));
    }
}
