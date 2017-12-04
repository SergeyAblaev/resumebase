package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    void saveElement(Resume r, int index) {
        int pointOfInsert = -index - 1;
        // двигаем массив
        System.arraycopy(storage, pointOfInsert, storage, pointOfInsert + 1, size - pointOfInsert);
        storage[pointOfInsert] = r;
    }

    void deleteElement(int index) { //sort
        //сдвигаем массив для сохранения сортировки
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}