package com.epam.architecture.util;

import com.epam.architecture.userinterface.LibraryService;

public class LibraryWebWorker {
    private static LibraryService libraryService;

    public static LibraryService takeLibraryService() {
        if (libraryService == null) {
            startWorking();
        }
        System.out.println(libraryService);
        return libraryService;
    }

    public static void startWorking() {
        libraryService = new LibraryService();
        libraryService.requestDeserializeData();
    }
}
