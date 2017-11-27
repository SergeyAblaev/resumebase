/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[100];  // 10000 пока уменьшил )

    //добавлю переменную - в которой буду хранить колич значимых элементов в массиве (храниим от 0 до count)
    private int count = 0;  // меняем только из методов!
    void clear() {
    }

    void save(Resume r) {
        // r - ссылка на новый элемент класса Resume содержащий резюме (поле uuid)
        // тут не пойму как добавлять в массив  - в какой индекс писать?
        storage[count]=r;
        count++;
    }

    Resume get(String uuid) {
       // НАПИШИ МЕТОД ПОИСКА ЭЛЕМЕНТА В МАССИВЕ ПО ЕГО ГУИДУ
        return null;
    }

    void delete(String uuid) {
        // тут напиши сжатие - при удалении элемента сдвигаем все элементы от текущего до 'count'
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        //return new Resume[0];  инициализировать через new надо?!
        // напиши выборку из массива от 0 до count - где содержимое != null
        Resume[] returnResume = new Resume[count];   // init array
/*
        for (int i=0; i<count; i++) { returnResume[i]=storage[i]; }  //я написал так но умная IDEA предложила метод arraycopy (ниже)
 */
        System.arraycopy(storage, 0, returnResume, 0, count);  // ВОПРОС! этот метод быстрее ручного копирования?
        return returnResume;
    }

    int size() {
        return 0;
    }

}
