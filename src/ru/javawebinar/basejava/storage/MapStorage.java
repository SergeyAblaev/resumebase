package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;

public class MapStorage extends AbstractStorage {
    public MapStorage collection = new MapStorage(); //Resume

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    int getIndex(String uuid) {
        return 0;
    }

    @Override
    void insertElement(Resume r, int index) {

    }

    @Override
    Resume getResume(int index) {
        return null;
    }

    @Override
    void deleteResume(int index) {

    }
}
