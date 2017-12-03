package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = availabilityCheck(r.getUuid());
        if (index < 0) {
            int pointOfInsert = -index - 1;
            // двигаем массив
            for (int i = size; i > pointOfInsert; i--) {
                storage[i] = storage[i - 1];
            }
            storage[pointOfInsert] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = verificationOfAbsence(uuid);
        if (index >=0){
            //сдвигаем массив для сохранения сортировки
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size - 1] = null;
            size--;
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}