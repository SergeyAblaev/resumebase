package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractStorage implements Storage {

    public abstract void clear();

    public void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isNotExist(searchKey)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            doUpdate(r, searchKey);
        }
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        Object searchKey = getSearchKey(uuid);  //(Integer/String)
        if (isNotExist(searchKey)) {
            doSave(r, searchKey);
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isNotExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isNotExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(searchKey);
        }
    }

    public abstract Resume[] getAll();

    public abstract int size();

    abstract Object getSearchKey(String uuid);

    abstract Boolean isNotExist(Object index);

    abstract void doUpdate(Resume r, Object index);

    abstract void doSave(Resume r, Object index);

    abstract Resume getResume(Object index);

    abstract void deleteResume(Object index);
}