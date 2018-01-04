package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    // public Collection<Resume> storage = new ArrayList<>();  - Collection<Resume> - часть методов не работает! (indexOf)
    public ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume r) {
        int index = (int) getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertElement(r, index);
        }

    }

    @Override
    public Resume[] getAll() {
        Resume[] a = new Resume[storage.size()];
        return storage.toArray(a);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }


    @Override
    protected void setElement(Resume r, int index){
            storage.set(index, r);      // надо вставку с заменой!
    }

    @Override
    void insertElement(Resume r, int index) {
        storage.add(r);
    }

    @Override
    void deleteResume(int index){
        storage.remove(index);
    }

    @Override
    Resume getResume(int index){
        return storage.get(index);
    }


}
