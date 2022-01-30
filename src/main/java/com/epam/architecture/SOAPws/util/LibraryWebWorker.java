package com.epam.architecture.SOAPws.util;

import com.epam.architecture.userinterface.LibraryService;

public class LibraryWebWorker {
    private static LibraryService libraryService;

    public static LibraryService takeLibraryService() {
        if (libraryService == null) {
            synchronized (LibraryService.class) {
                if (libraryService == null) {
                    startWorking();
                }
            }
        }
        return libraryService;
    }

    public static void startWorking() {
        libraryService = new LibraryService();
        libraryService.requestDeserializeData();
    }
}
