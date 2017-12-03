package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index=verificationOfAbsence(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        }
    }

    public void save(Resume r) { //not sorted
        int index = availabilityCheck(r.getUuid());
        if (index < 0) {
            saveElement( r,  index);
        }
    }

    public Resume get(String uuid) {
        int index = verificationOfAbsence(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = verificationOfAbsence(uuid);
        if (index >=0){
        deleteElement(index);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    abstract void saveElement(Resume r, int index);
    abstract void deleteElement(int index);

    int availabilityCheck(String uuid){
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Resume " + uuid + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        }
        return index;
    }

    int verificationOfAbsence(String uuid){
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        }
        return index;
    }

    protected abstract int getIndex(String uuid);
}