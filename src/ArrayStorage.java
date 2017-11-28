/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[100];  // 10000 пока уменьшил )

    //добавлю переменную - в которой буду хранить колич значимых элементов в массиве (храниим от 0 до count)
    private int count = 0;  // меняем только из методов!

    void clear() {
    count = 0; //тут просто )
    }

    void save(Resume r) {
        storage[count] = r;  // r - ссылка на новый элемент класса Resume содержащий резюме (поле uuid)
        count++;
    }

    private int getInt(String uuid) {
        //ищем знач массива, если нет - вернем минус один ?
        for (int i=0; i<count; i++){
            if (storage[i].uuid==uuid) {
                return i;
            }
        }
        return -1;
    }

    Resume get(String uuid) {
        Resume foundElement;
        int i = getInt(uuid);
        if (i == -1) {
            foundElement = null;   // элемент не найден
        } else {
            foundElement = storage[i];
        }
        return foundElement;
    }

    void delete(String uuid) {
        // при удалении элемента сдвигаем все элементы от текущего до 'count'
        int index = getInt(uuid);
        if (index == -1) {
            // элемент не найден
        } else {
            storage[index]=null; //удаляем: ВОПРОС - стоит ли так явно прописывать стирание элем. для удобства понимания программы?
                                 // ведь нииже я все равно затру эту ячейку при уплотнении
            // начинаем переносить элементы из i+1 в i пока i+1<=count !!
            for (int i=index; i<count; i++){
                storage[i]=storage[i+1];
            }
            storage[count] = null;
            --count;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] returnResume = new Resume[count];   // ВОПРОС при каждом вызове создавать массив через new - правильно?
/*
        for (int i=0; i<count; i++) { returnResume[i]=storage[i]; }  //я написал так но умная IDEA предложила метод arraycopy (ниже)
 */
        System.arraycopy(storage, 0, returnResume, 0, count);  // ВОПРОС! этот метод быстрее ручного копирования?
        return returnResume;
    }

    int size() {
        return count; //тут просто )
    }

}
