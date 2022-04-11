package com.epam.architecture.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "AUTHOR", schema = "HOME_LIBRARY")
public class Author implements Serializable {
    @Id
    @Column(name = "NAME")
    private String authorName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHORNAME")
    private Set<Book> books;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author() {
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorName='" + authorName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return Objects.equals(authorName, author.authorName);
    }

    @Override
    public int hashCode() {
        return authorName != null ? authorName.hashCode() : 0;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
