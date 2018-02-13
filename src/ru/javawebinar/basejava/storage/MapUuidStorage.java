package ru.javawebinar.basejava.storage;

//import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
import ru.javawebinar.basejava.model.Resume;

import java.util.*;


// TODO create new MapStorage with search key not uuid -- не сделал!!
public class MapUuidStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        doSave(r, searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return ((map.get((String) searchKey)) != null);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put((String) searchKey, r); // searchKey ==  uuid
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected Resume[] getAll() {
        return map.values().toArray(new Resume[map.size()]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
