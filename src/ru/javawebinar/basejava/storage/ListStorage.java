package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collection;

public class ListStorage extends AbstractStorage {
    protected Collection<Resume> collection = new ArrayList<>();

    @Override
    public void clear() {
    collection.clear();
    }

    @Override
    public void update(Resume r) {  // todo do method! update

    }

    @Override
    public void save(Resume r) {   // todo do method! где можно не проверять на наличие в МАП или в ЛИСТ?
        collection.add(r);
    }

    @Override
    public Resume get(String uuid) {  // todo do method! get
        return null;
    }

    @Override
    public void delete(String uuid) {   // todo do method! delete

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];   // todo do method getAll!
    }

    @Override
    public int size() {
        return 0;           // todo do method! size
    }

    protected int getIndex(Resume r){
        boolean rrr = collection.contains(r);  // todo correct method! getIndex
        return Int(rrr);
    }
}
