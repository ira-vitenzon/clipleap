package com.joyfulshark.clipleap.common;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectsScores {
    private static final ObjectsScores ourInstance = new ObjectsScores();

    private static Map<String, Map<SceneType, Float>> objectsMap;

    private ObjectsScores() {
        objectsMap = new HashMap<>();
    }

    public static ObjectsScores getInstance() {
        return ourInstance;
    }

    public static Map<String, Map<SceneType, Float>> getObjectsMap() {
        return objectsMap;
    }

    public void build(Reader fileReader) {
        if (objectsMap.isEmpty()) {
            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withSkipLines(1)
                    .build();

            try {
                List<String[]> lines = csvReader.readAll();
                for (String[] row : lines) {
                    Map<SceneType, Float> objMap = new HashMap<>();
                    objMap.put(SceneType.CITY, Float.valueOf(row[1]));
                    objMap.put(SceneType.NATURE, Float.valueOf(row[2]));
                    objectsMap.put(row[0], objMap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
