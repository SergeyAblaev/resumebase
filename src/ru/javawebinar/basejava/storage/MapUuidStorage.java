package ru.javawebinar.basejava.storage;

import javafx.collections.transformation.SortedList;
import ru.javawebinar.basejava.model.Resume;

import java.util.*;

// TODO implement
// TODO create new MapStorage with search key not uuid
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
    public List<Resume> getAllSorted() {
/*
       Resume[] resumes = map.values().toArray(new Resume[map.size()]);  // так не работает: Геннадий убрал implements Comparable из класса Resume
        Arrays.sort(resumes);
*/

        List<Resume> resumes = new ArrayList<>();
        String[] uuides = map.keySet().toArray(new String[map.size()]);  //
        Arrays.sort(uuides);

        for (int i = 0; i < uuides.length; i++) {
         //   resumes[i] = map.get(uuides[i]);
            resumes.add(map.get(uuides[i]));
        }
        return resumes;
    }

    @Override
    public int size() {
        return map.size();
    }
}
