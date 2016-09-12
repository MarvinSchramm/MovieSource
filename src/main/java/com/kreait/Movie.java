package com.kreait;

import javax.persistence.*;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private String dateOfRelease;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }
}