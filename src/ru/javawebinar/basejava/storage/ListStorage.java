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
    - проверяем индекс // boolean checkIndex
    - если невалидный кидаем искл
    - если валидный то пытаемся сохранить // doSave
*checkIndex, для list/array - >=0, для map - !=null
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
    Boolean checkIndex(Object index) {
        if ((Integer)index >= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void setElement(Resume r, int index){
            storage.set(index, r);      // надо вставку с заменой!
    }

    @Override
    void doSave(Resume r, Object index) {
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
