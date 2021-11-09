package com.epam.designAndArchitecture.entities;

import com.epam.designAndArchitecture.SavableObject;

public class Author implements SavableObject {
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
