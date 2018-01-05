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
    Boolean checkIndex(Object index) {
        Resume r = storage.get((String) index);
        if (r == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    void setElement(Resume r, int index) {
        doSave(r, r.getUuid());
    }

    @Override
    void doSave(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }

    @Override
    Resume getResume(int index) {
        return null;
    }

    @Override
    void deleteResume(int index) {

    }
}
