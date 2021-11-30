package com.epam.architecture.datasource;

import java.io.Serializable;
import java.util.List;

public interface LibraryDAO<T extends Serializable> {
    void saveData(T... data);

    void deleteData(T... data);

    List<T> restoreData();
}
