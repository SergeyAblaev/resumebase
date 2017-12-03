package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    void saveElement(Resume r, int index){
        int pointOfInsert = -index - 1;
        // двигаем массив
        for (int i = size; i > pointOfInsert; i--) {
            storage[i] = storage[i - 1];
        }
        storage[pointOfInsert] = r;
        size++;
    }

    void deleteElement(int index){ //sort
        //сдвигаем массив для сохранения сортировки
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
        }
        storage[size - 1] = null;
        size--;
    }

    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}