/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    //добавлю переменную - в которой буду хранить колич значимых элементов в массиве (храниим от 0 до count)
    private int count = 0;  // меняем только из методов!

    void clear() {
        for (int i=0;i<count;i++) storage[i] = null;
        count = 0;
    }

    void save(Resume r) {
        storage[count] = r;  // r - ссылка на новый элемент класса Resume содержащий резюме (поле uuid)
        count++;
    }

    private int getInt(String uuid) {
        //ищем знач массива, если нет - вернем минус один ?
        for (int i = 0; i < count; i++) {
            if (storage[i].uuid == uuid) {
                return i;
            }
        }
        return -1;
    }

    Resume get(String uuid) {
        int i = getInt(uuid);
        return (i==-1)?null:storage[i];
    }

    void delete(String uuid) {
        // при удалении элемента заменяем стираемую ссылку элементом с вершины данных
        int index = getInt(uuid);
        if (index == -1) {
            // элемент не найден
        } else {
            storage[index] = storage[count-1];
            --count;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] returnResume = new Resume[count];   // ВОПРОС при каждом вызове создавать массив через new - правильно?
        System.arraycopy(storage, 0, returnResume, 0, count);
        return returnResume;
    }

    int size() {
        return count; //тут просто )
    }

}
