package com.epam.architecture.datasource;

import com.epam.architecture.App;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HTwoDataSourceService<T extends Serializable> implements LibraryDAO<T> {
    private final EntityTypes sourceType;
    SessionFactory sessionFactory = App.sessionFactory;

    public HTwoDataSourceService(EntityTypes sourceType) {
        this.sourceType = sourceType;
    }

    @SafeVarargs
    @Override
    public final void saveData(T... data) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Arrays.stream(data).forEach(session::save);
            transaction.commit();
        }
    }

    @SafeVarargs
    @Override
    public final void deleteData(T... data) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Arrays.stream(data).forEach(session::delete);
            transaction.commit();
        }
    }

    @Override
    public List<T> restoreData() {
        List<T> resultList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Query<T> query = session.createQuery("from " + sourceType.getType().getSimpleName());
            resultList = query.list();
        }
        return resultList;
    }
}
