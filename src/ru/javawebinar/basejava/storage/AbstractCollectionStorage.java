package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Collection;

public abstract class AbstractCollectionStorage implements Storage {
    protected Collection<Resume> collection;  //  = new ArrayList<>();

    protected AbstractCollectionStorage(Collection collection){
        this.collection = collection;
    }

    @Override
    public void clear() {
    collection.clear();
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {
        collection.add(r);
    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }

    protected int getIndex(Resume r){
        boolean rrr = collection.contains(r);
        return Int(rrr);
    }
}
