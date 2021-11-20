package com.epam.architecture.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "AUTHOR", schema = "HOME_LIBRARY")
public class Author implements Serializable {
    @Id
    @Column(name = "NAME")
    private String authorName;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Author() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return authorName.equals(author.authorName);
    }

    @Override
    public int hashCode() {
        return authorName.hashCode();
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorName='" + authorName + '\'' +
                '}';
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
