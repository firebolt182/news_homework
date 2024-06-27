package org.javaacademy.news_homework.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class CategoryDto {
    private String name;
    private List<NewsDto> news = new ArrayList<>();

    public CategoryDto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
