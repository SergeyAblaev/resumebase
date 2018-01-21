package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;


// TODO create new MapStorage with search key not uuid -- не сделал!!
public class MapFullNameStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String fullName) {  //uuid
        return fullName;
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
        map.put((String) searchKey, r); // searchKey ==  fullName
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
    public List<Resume> getAllSorted() {        //TODO  наворотил, посмотри как из мапы достать коллекцию значений

//first var: through array with comparator
        Resume[] resumes = map.values().toArray(new Resume[map.size()]);
        Arrays.sort(resumes, RESUME_COMPARATOR);
//      return Arrays.asList(resumes);

//next variant through TreeMap:
        TreeMap<String,Resume> mySortedMap = new TreeMap<>(map);
        return new ArrayList<>(mySortedMap.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
