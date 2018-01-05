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

    /*
    ок, на примере save
    - получаем индекс //Object getIndex
    - проверяем индекс // boolean notExistIndex
    - если невалидный кидаем искл
    - если валидный то пытаемся сохранить // doSave
*notExistIndex, для list/array - >=0, для map - !=null
*doSave сам подумай
     */

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
    Boolean notExistIndex(Object index) {
        return ((Integer)index < 0);
    }

    @Override
    protected void updateResume(Resume r, Object index){
            storage.set((Integer) index, r);      // надо вставку с заменой!
    }

    @Override
    void doSave(Resume r, Object index) {
        storage.add(r);
    }

    @Override
    void deleteResume(Object index){
        storage.remove((Integer) index);
    }

    @Override
    Resume getResume(Object index){
        return storage.get((Integer) index);
    }


}
