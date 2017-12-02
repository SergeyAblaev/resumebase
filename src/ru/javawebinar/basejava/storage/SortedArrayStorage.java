package ru.javawebinar.basejava.storage;


import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
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
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            //сдвигаем массив для сохранения сортировки
            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            storage[size - 1] = null;
            size--;
        }
    }

    // Вопрос: методы: update/clear/getAll скопированы "один в один из класса" - ru.javawebinar.basejava.storage.ArrayStorage, не пониимаю почему нельзя в Абстрактном родителе это прописать статично?

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}