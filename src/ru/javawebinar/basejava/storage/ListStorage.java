package ru.javawebinar.basejava.storage;

import com.sun.org.apache.regexp.internal.RE;
import javafx.util.Pair;
import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();
 //   private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());


    @Override
    protected Integer getSearchKey(String fullName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFullName().equals(fullName)) {  //getUuid
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        list.set((Integer) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        list.add(r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((Integer) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        list.remove(((Integer) searchKey).intValue());
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    protected Pair<Resume[], String> getAll() {  //не использую - но пришлось добавить - т.к. он заявлен как абстрактный.
        Resume[] key = list.toArray(new Resume[0]);
        return new Pair<>(key,getClass().getName());
    }

    @Override
    public int size() {
        return list.size();
    }
}