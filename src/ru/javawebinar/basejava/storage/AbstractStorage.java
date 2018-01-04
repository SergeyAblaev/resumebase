package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractStorage implements Storage {

    public abstract void clear();

    public void update(Resume r) {
        int index = (int) getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            setElement(r, index);
        }
    }

    public abstract void save(Resume r);

    public Resume get(String uuid) {
        int index = (int) getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    public void delete(String uuid) {
        int index = (int) getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(index);
        }
    }

    public abstract Resume[] getAll();

    public abstract int size();

    abstract Object getIndex(String uuid);

    abstract void setElement(Resume r, int index); //

    abstract void insertElement(Resume r, int index); //

    abstract Resume getResume(int index);

    abstract void deleteResume(int index);
}