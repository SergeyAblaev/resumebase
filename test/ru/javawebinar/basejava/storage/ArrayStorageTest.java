package ru.javawebinar.basejava.storage;

import org.junit.Before;

import static org.junit.Assert.*;

public class ArrayStorageTest extends AbstractArrayStorageTest {
    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
}