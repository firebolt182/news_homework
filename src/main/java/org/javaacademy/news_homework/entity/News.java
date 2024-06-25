package org.javaacademy.news_homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column
    private String header;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    @JsonIgnore
    private Category category;

    @Column
    @JsonIgnore
    private LocalDate date;

    @Column
    private String text;

    @Override
    public String toString() {
        return "  --Заголовок: %s: Текст: %s.\n".formatted(header, text);
    }
}
