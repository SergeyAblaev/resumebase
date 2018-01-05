package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
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
    String getIndex(String uuid) {
        return uuid;
    }

    @Override
    Boolean notExistIndex(Object index) {
        return ((storage.get((String) index))== null);
    }

    @Override
    void updateResume(Resume r, Object index) {
        doSave(r, index);
    }

    @Override
    void doSave(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }

    @Override
    Resume getResume(Object index) {
        return null; // (String) index
    }

    @Override
    void deleteResume(Object index) {

    }
}
