package ru.javawebinar.basejava.storage;

import javafx.util.Pair;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getFullName().compareTo(o2.getFullName());

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume[] getAll();

    public void update(Resume r) {
      Object searchKey = getExistedSearchKey(r.getUuid());
    //    Object searchKey = getExistedSearchKey(r.getFullName());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
      Object searchKey = getNotExistedSearchKey(r.getUuid());
     //   Object searchKey = getNotExistedSearchKey(r.getFullName());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
    public List<Resume> getAllSorted() {
        Resume[] resume = getAll();
        Arrays.sort(resume,RESUME_COMPARATOR);
        return Arrays.asList(resume);
    }
}