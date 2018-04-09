package ru.javawebinar.basejava.storage;

//import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1,"name1");
//  Начинаю заполнение резюме  RESUME_1  данными:
      // EDUCATION
        List<Titles> titlesList = new LinkedList<>();
        titlesList.add(new Titles(LocalDate.parse("1993-09-01"),LocalDate.parse("1996-07-01"),"Аспирантура (программист С, С++)"));
        titlesList.add(new Titles(LocalDate.parse("1993-09-01"),LocalDate.parse("1996-07-01"),"Аспирантура (программист С, С++)"));
        Organization organisation = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики","http://www.ifmo.ru/",titlesList,"");
        List<Organization> organizations = new LinkedList<>();
        organizations.add(organisation);
        OrganizationSection organizationSection = new OrganizationSection(organizations);
        RESUME_1.setSection(SectionType.EDUCATION, organizationSection);  // установили организацию в резюме1
        // даллее так же сделай заполнение других типов секций!!

     // EXPERIENCE
        titlesList.clear();
        titlesList.add(new Titles(LocalDate.parse("2013-10-01"),(LocalDate) null,"Автор проекта|Создание, организация и проведение Java онлайн проектов и стажировок."));
        organisation = new Organization("Java Online Projects","http://javaops.ru/",titlesList,"");
        organizations.clear();
        organizations.add(organisation);
        organizationSection = new OrganizationSection(organizations);
        RESUME_1.setSection(SectionType.EXPERIENCE, organizationSection);  //

     // ContactType
        RESUME_1.setContact(ContactType.MOBILE,"+7(921) 855-0482");
        RESUME_1.setContact(ContactType.SKYPE,"grigory.kislin");
        RESUME_1.setContact(ContactType.MAIL,"gkislin@yandex.ru");
        RESUME_1.setContact(ContactType.LINKEDIN,"https://www.linkedin.com/in/gkislin");
        RESUME_1.setContact(ContactType.GITHUB,"https://github.com/gkislin");
        RESUME_1.setContact(ContactType.STATCKOVERFLOW,"https://stackoverflow.com/users/548473");


        RESUME_2 = new Resume(UUID_2,"name2");
        RESUME_3 = new Resume(UUID_3,"name3");
        RESUME_4 = new Resume(UUID_4,"name4");
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_3);
        storage.save(RESUME_2);

    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1,"NewNameUUID1");
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(RESUME_1, list.get(0));
        assertEquals(RESUME_2, list.get(1));
        assertEquals(RESUME_3, list.get(2));
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        // check caller class:
        String field = storage.getClass().getName();
        if (!field.contains("Array")) {
          throw new StorageException("This exception is correct!","");
        }
        // only for Arrays implementations:
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume(String.valueOf(i)));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume(""));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));  //getUuid
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}