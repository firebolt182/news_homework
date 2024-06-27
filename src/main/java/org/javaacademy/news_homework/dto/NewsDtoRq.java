package org.javaacademy.news_homework.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class NewsDtoRq {
    private String header;
    private String categoryName;
    private LocalDate date;
    private String text;
}
