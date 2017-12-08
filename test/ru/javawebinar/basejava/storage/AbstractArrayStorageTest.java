package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage = new ArrayStorage();

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String DUMMY = "dummy";

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
        //      storage.clear();
        //    Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        //      storage.update(storage.get(UUID_2));
    }
    /*
        @Test (expected = NotExistStorageException.class)
        public void notExistUpdate() throws Exception {
            storage.update(storage.get(DUMMY));
        }
    */
    @Test
    public void getAll() throws Exception {
        //   Resume resume[] = storage.getAll();
        //   Assert.assertEquals(3,resume.length);
    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void delete() throws Exception {
        // storage.delete(UUID_3);
        //     Resume resume[] = storage.getAll();
        //     Assert.assertEquals(2,resume.length);
    }

    @Test
    public void get() throws Exception {
        //  Assert.assertEquals(storage.get(UUID_2).getUuid(),UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(DUMMY);
    }
/*
    @Test(expected = NotExistStorageException.class)
    public void overflow() throws Exception {
        for (int i=1;i<=10001;i++){
            {
              Resume r=new Resume();
              storage.save(r);
            }
        }
    }
    */
}
