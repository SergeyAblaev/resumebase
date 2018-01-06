package ru.javawebinar.basejava.storage;

import static org.junit.Assert.*;

public class ListStorageTest extends AbstractArrayStorageTest{

    public ListStorageTest() {
        super(new ListStorage());
    }
}