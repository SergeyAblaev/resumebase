package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> collection = new HashMap<>();

    @Override
    public void clear() {
    collection.clear();
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
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
    int getIndex(String uuid) {
        for (Map.Entry<String, Resume> entry : collection.entrySet()) {
            if (uuid.equals(entry.getValue().getUuid())) {
                return -1;  // что тут возвращать - нет индекса у ХэшМапа?
            }
        }
        return -1;
    }

    @Override
    void insertElement(Resume r, int index) {
      //  collection.put(String(index), r)
    }

    @Override
    Resume getResume(int index) {
        return null;
    }

    @Override
    void deleteResume(int index) {

    }
}
