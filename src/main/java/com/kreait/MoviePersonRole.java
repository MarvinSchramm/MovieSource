package com.kreait;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MPR")
public class MoviePersonRole {

    @EmbeddedId
    private CompositeKey id;

    public Movie getMovie() {
        return id.movie;
    }

    public Person getPerson() {
        return id.person;
    }

    public String getRole() {
        return id.role;
    }

    @Embeddable
    public static class CompositeKey implements Serializable {

        @ManyToOne
        @JoinColumn(name = "MOVIE_ID", insertable = false, updatable = false, referencedColumnName = "id")
        private Movie movie;

        @ManyToOne
        @JoinColumn(name = "PERSON_ID", insertable = false, updatable = false, referencedColumnName = "id")
        private Person person;

        @Column(name = "role")
        private String role;

    }
}
