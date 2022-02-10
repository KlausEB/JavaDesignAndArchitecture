package com.epam.architecture.RESTws;

import com.epam.architecture.RESTws.filter.AdminStatusNeededFilter;
import com.epam.architecture.RESTws.filter.UserStatusNeededFilter;
import com.epam.architecture.RESTws.service.AdminController;
import com.epam.architecture.RESTws.service.BookController;
import com.epam.architecture.RESTws.service.SearchController;
import com.epam.architecture.RESTws.service.UserController;

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
