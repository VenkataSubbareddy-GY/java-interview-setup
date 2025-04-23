package com.real.interview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String releaseYear;
}
