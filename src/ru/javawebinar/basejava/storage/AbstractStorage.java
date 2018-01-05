package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractStorage implements Storage {

    public abstract void clear();

    public void update(Resume r) {
        String uuid = r.getUuid();
        Object index = checkGetIndex(uuid);
            updateResume(r, index);
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        Object index = getIndex(uuid);  //(Integer/String)
        if (notExistIndex(index)) {
            doSave(r, index);
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    public Resume get(String uuid) {
        Object index = checkGetIndex(uuid);
        return getResume(index);
    }

    public void delete(String uuid) {
        Object index = checkGetIndex(uuid);
        deleteResume(index);
    }

    private Object checkGetIndex(String uuid){
        Object index = getIndex(uuid);
        if (notExistIndex(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    public abstract Resume[] getAll();

    public abstract int size();

    abstract Object getIndex(String uuid);

    abstract Boolean notExistIndex(Object index);

    abstract void updateResume(Resume r, Object index);

    abstract void doSave(Resume r, Object index);

    abstract Resume getResume(Object index);

    abstract void deleteResume(Object index);
}