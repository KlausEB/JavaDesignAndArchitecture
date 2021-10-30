package com.epam.designAndArchitecture.DBservices;

import com.epam.designAndArchitecture.SerializableObject;

import java.io.IOException;

public interface DBService {
    void saveData(SerializableObject[] objects) throws IOException;

    SerializableObject[] restoreData() throws IOException;
}
