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
    public void save(Resume r) {
        if (storage.get(r.getUuid()) != null) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertElement(r, -1);
        }
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
    void setElement(Resume r, int index) {
        insertElement(r, index);
    }

    @Override
    void insertElement(Resume r, int index) {
        storage.put(r.getUuid(), r);
    }

    @Override
    Resume getResume(int index) {
        return null;
    }

    @Override
    void deleteResume(int index) {

    }
}
