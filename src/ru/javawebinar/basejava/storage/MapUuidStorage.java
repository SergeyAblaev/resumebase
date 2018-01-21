package ru.javawebinar.basejava.storage;

import javafx.collections.transformation.SortedList;
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
    public List<Resume> getAllSorted() {        //TODO  наворотил, посмотри как из мапы достать коллекцию значений

//first var: through array with comparator
        Resume[] resumes = map.values().toArray(new Resume[map.size()]);
        Arrays.sort(resumes, RESUME_COMPARATOR);
//      return Arrays.asList(resumes);

//next variant through TreeMap:
        TreeMap<String,Resume> mySortedMap = new TreeMap<>(map);
        return new ArrayList<>(mySortedMap.values());


/*// how was:
        List<Resume> resumes = new ArrayList<>();
        String[] uuides = map.keySet().toArray(new String[map.size()]);  //
        Arrays.sort(uuides);

        for (int i = 0; i < uuides.length; i++) {
         //   resumes[i] = map.get(uuides[i]);
            resumes.add(map.get(uuides[i]));
        }
        return resumes;*/
    }

    @Override
    public int size() {
        return map.size();
    }
}
