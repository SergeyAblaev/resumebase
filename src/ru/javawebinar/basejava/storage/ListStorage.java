package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.*;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    // public Collection<Resume> collection = new ArrayList<>();  - Collection<Resume> - часть методов не работает! (indexOf)
    public ArrayList<Resume> collection = new ArrayList<>();

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
     //       collection.add(r);
        }

    }

    @Override
    public Resume[] getAll() {
        Resume[] a = new Resume[collection.size()];
        return collection.toArray(a);
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < collection.size(); i++) {
            if (uuid.equals(collection.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElement(Resume r, int index){
        if (index== -1){
            collection.add(r);
        } else {
            collection.set(index, r);
        }

    }

    @Override
    void deleteResume(int index){
        collection.remove(index);
    }

    @Override
    Resume getResume(int index){
        return collection.get(index);
    }


}
