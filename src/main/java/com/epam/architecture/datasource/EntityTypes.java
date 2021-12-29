package com.epam.architecture.datasource;

import com.epam.architecture.entities.*;

public enum EntityTypes {
    ACCOUNT(User.class),
    AUTHOR(Author.class),
    BOOK(Book.class),
    BOOKMARK(Bookmark.class);

    private final Class type;

    EntityTypes(Class type){
        this.type = type;
    }

    public Class getType() {
        return type;
    }
}
