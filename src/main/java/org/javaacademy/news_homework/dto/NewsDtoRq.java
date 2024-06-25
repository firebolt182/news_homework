package org.javaacademy.news_homework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class NewsDtoRq {
    private String header;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String categoryName;
    private LocalDate date;
    private String text;
}
