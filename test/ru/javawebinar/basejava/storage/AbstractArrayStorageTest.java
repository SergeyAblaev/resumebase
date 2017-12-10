package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String DUMMY = "dummy";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume r=storage.get(UUID_2);
        if (r.getUuid().equals(UUID_2)){
            storage.update(r);
        }
    }


    @Test(expected = NotExistStorageException.class)
    public void notExistUpdate() throws Exception {
        storage.update(storage.get(DUMMY));
    }

    @Test
    public void getAll() throws Exception {
        Resume resume[] = storage.getAll();
        Assert.assertEquals(3, resume.length);
    }

    @Test
    public void save() throws Exception {
        Resume r = new Resume();
        storage.save(r);
        Resume resume[] = storage.getAll();
        Assert.assertEquals(4, resume.length);
        Assert.assertEquals(storage.get(r.getUuid()),r); //save можно еще проверить, что 4-ое действительно добавилось,
        // а не просто размер на 1 увеличился   - а по моему тут "масло масляное" получилось )
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        Resume resume[] = storage.getAll();
        Assert.assertEquals(2, resume.length);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(storage.get(UUID_2).getUuid(), UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(DUMMY);
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() throws Exception {
        Resume r;
        try {
            for (int i = 1; i <= (10000 - 3); i++) {
                r = new Resume();
                storage.save(r);
            }
        } catch (Exception e) {
            Assert.fail("size=" + storage.size() + "  " + e.toString());
            //throw new IndexOutOfBoundsException();
        }
        r = new Resume();
        storage.save(r);  // and this is for a perceived mistake
    }

}