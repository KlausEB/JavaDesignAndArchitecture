package com.epam.architecture.endpoint;

import com.epam.architecture.endpoint.filter.AdminStatusNeededFilter;
import com.epam.architecture.endpoint.filter.UserStatusNeededFilter;
import com.epam.architecture.endpoint.service.AdminController;
import com.epam.architecture.endpoint.service.BookController;
import com.epam.architecture.endpoint.service.SearchController;
import com.epam.architecture.endpoint.service.UserController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class RestApplication extends Application {

    @Override
    public Set<Object> getSingletons() {
        Set<Object> set = new HashSet<>();
        set.add(new AdminController());
        set.add(new BookController());
        set.add(new SearchController());
        set.add(new UserController());
        set.add(new UserStatusNeededFilter());
        set.add(new AdminStatusNeededFilter());
        return set;
    }

}
