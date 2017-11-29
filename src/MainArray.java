import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Test for com.urise.webapp.storage.ArrayStorage
 */
public class MainArray {
    private final static ArrayStorage ARRAY_STORAGE = new ArrayStorage();  // создаем новый storaje

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;   // обьявили переменную, тип=класс resume
        while (true) {
            System.out.print("Введите одну из команд - (list | save uuid | delete uuid | get uuid | update uuid | clear | exit ): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(ARRAY_STORAGE.size());
                    break;
                case "save":
                    r = new Resume();
                    r.uuid = uuid;
                    ARRAY_STORAGE.save(r);
                    printAll();
                    break;
                case "update":
                    // найдем элемент для обновлениия
                    int i = ARRAY_STORAGE.getInt(uuid);
                    if (i != -1) {
                       //если найден - то запросим новый УУИД у пользователя и обновим
                        System.out.print("Введите новый uuid | пробел - отмена обновления ): ");
                        params = reader.readLine().trim().toLowerCase().split(" ");
                        if (params.length != 1) {
                            System.out.println("Неверная команда.");
                            break;
                        } else if (params[0].equals("")) {
                            break;
                        } else {
                            //  uuid = params[1].intern();
                            // uuidNew
                            r = new Resume();
                            r.uuid = params[0];
                          ARRAY_STORAGE.update(i, r );
                        }
                    }
                    else {
                            System.out.println("uuid не найден");
                        }
                    break;
                case "delete":
                    ARRAY_STORAGE.delete(uuid);
                    printAll();
                    break;
                case "get":
                    System.out.println(ARRAY_STORAGE.get(uuid));
                    break;
                case "clear":
                    ARRAY_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        Resume[] all = ARRAY_STORAGE.getAll();
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }

}