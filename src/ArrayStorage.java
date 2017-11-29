import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    //добавлю переменную - в которой буду хранить колич значимых элементов в массиве (храниим от 0 до size)
    private int size = 0;  // меняем только из методов!

    void clear() {
        //for (int i = 0; i< size; i++) storage[i] = null;
        Arrays.fill(storage, 0,size,null);
        size = 0;
    }

    void save(Resume r) {
        if (size > storage.length) {
            System.out.println("база данных заполнена. невозможно записать!");
         return;
        }
        storage[size] = r;  // r - ссылка на новый элемент класса Resume содержащий резюме (поле uuid)
        size++;
    }
    void update(int i, Resume rNew){
        storage[i]=rNew;
    }

    int getInt(String uuid) {
        //ищем знач массива, если нет - вернем минус один ?
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
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
            storage[index] = storage[size -1];
            --size;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] returnResume = new Resume[size];   // ВОПРОС при каждом вызове создавать массив через new - правильно?
        System.arraycopy(storage, 0, returnResume, 0, size);
        return returnResume;
    }

    int size() {
        return size; //тут просто )
    }

}
