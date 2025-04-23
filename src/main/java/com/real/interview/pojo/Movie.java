package com.real.interview.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Movie {
    private Integer id;
    private String title;
    private String releaseYear;
}
