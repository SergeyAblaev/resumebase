package ru.javawebinar.basejava.storage;

import javafx.util.Pair;
import ru.javawebinar.basejava.model.Resume;

import java.util.*;


// TODO create new MapStorage with search key not uuid -- не сделал!!
public class MapResumeStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {  //uuid
      return map.get(uuid);
   //    return map.get(uuid).getUuid();
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        doSave(r, searchKey);
    }

    @Override
    protected boolean isExist(Object resume) {
        return (resume != null);
    }

    @Override
    protected void doSave(Resume r, Object resume) {
      map.put(r.getUuid(), r);
     //   map.put(r, r.getUuid());
    }

    @Override
    protected Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void doDelete(Object searchKey) {
         map.remove( searchKey);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected Pair<Resume[], String> getAll() {
        Resume[] resumes = map.values().toArray(new Resume[map.size()]);
        return new Pair<>(resumes, getClass().getName());
    }

    @Override
    public int size() {
        return map.size();
    }
}
