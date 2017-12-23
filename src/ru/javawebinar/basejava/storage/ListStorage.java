package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collection;

public class ListStorage extends AbstractCollectionStorage {
    public ListStorage() {
        super(new ArrayList<Resume>());
    }

}
