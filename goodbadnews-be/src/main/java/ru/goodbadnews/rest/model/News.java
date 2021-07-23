package ru.goodbadnews.rest.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;


@Data
public class News {
    @Transient
    public static final String ID = "id";

    @Id
    private long id;
    private String title;
    private String goodText;
    private String badText;

}
