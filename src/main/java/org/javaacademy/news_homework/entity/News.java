package org.javaacademy.news_homework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String header;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private LocalDate date;

    @Column
    private String text;
}
