package org.javaacademy.news_homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<News> news = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    /*@Override
    public String toString() {
        return "%s: \n%s".formatted(name, news.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", ""));
    }*/

    @Override
    public String toString() {
        return name;
    }
}
