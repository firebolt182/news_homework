package org.javaacademy.news_homework.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.javaacademy.news_homework.entity.Category;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class NewsDto {
    private String header;
    @JsonIgnore
    private CategoryDto categoryDto;
    @JsonIgnore
    private LocalDate date;
    private String text;

    @Override
    public String toString() {
        return "  --Заголовок: %s: Текст: %s\n".formatted(header, text);
    }
}
