//package ru.javawebinar.basejava.storage;

//import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{
    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);  //копия метода из несортированного  массива
        size = 0;
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());  //копия метода из несортированного массива
        if (index == -1) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;  // не понимаю смысла перезаписывать uuid самим собой?! если бы был новый ууид - то
                                 // после надо было бы передвинуть элемент чтобы сохраниить сортировку! А тут просто копия метода из несортированного массива
        }
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            int pointOfInsert=-index-1;
            // двигаем массив
            for (int i=size;i>pointOfInsert;i--) {
             storage[i]=storage[i-1];
            }
            storage[pointOfInsert] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0 ) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            //сдвигаем массив для сохранения сортировки
            for (int i=index; i<size; i++) {
                storage[i] = storage[i+1];
            }
            storage[size - 1] = null;
             size--;
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);  // копия метода из несортированного массива
      //  return new Resume[0];
    }

    // Вопрос: методы: update/clear/getAll скопированы "один в один из класса" - ArrayStorage, не пониимаю почему нельзя в Абстрактном родителе это прописать статично?

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}