package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Array;
import java.util.Map;

public class MapStorage extends AbstractCollectionStorage {
    public MapStorage (){
        super(new Map<String,Resume>());
    }
}
