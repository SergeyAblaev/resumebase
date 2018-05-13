package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.List;

public class ObjectStreamStorage  {   //extends AbstractStorage
    private AbstractStorage storageStrategy;

    public void setStorageStrategy(AbstractStorage storageStrategy) {
        this.storageStrategy = storageStrategy;  // сюда подадим лиюо AbstractPathStorage либо AbstractFileStorage
    }

    public void update(Resume r) {
        storageStrategy.update(r);
    }

    public void save(Resume r) {
        storageStrategy.save(r);
    }

    public void delete(String uuid) {
        storageStrategy.delete(uuid);
    }

    public Resume get(String uuid) {
        return storageStrategy.get(uuid);
    }

    public List<Resume> getAllSorted() {
        return storageStrategy.getAllSorted();
    }

    public void clear() {
        storageStrategy.clear();
    }

    public int size() {
        return storageStrategy.size();
    }

/*    public void method1 (){
     size();
     //storageStrategy.save();
 }*/
}