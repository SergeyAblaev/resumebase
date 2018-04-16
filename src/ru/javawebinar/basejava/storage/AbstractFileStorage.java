package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * gkislin
 * 22.07.2016
 */
public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;


    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();  // допустим что в каталоге только файлы для удаления!
        if (files != null) {
            for (File file : files) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        try {
            return (int) directory.listFiles().length;
        } catch (SecurityException e) {
            throw new StorageException("Get size error", directory.toString(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            doWrite(r, file);
/*
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.getName()));
            out.writeObject(r);
            out.flush();
            out.close();
 */
        } catch (IOException e) {
            throw new StorageException("FileOutputStream error", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    @Override
    protected Resume doGet(File file) {
    return doRead(file);
    }

    protected Resume doRead (File file) throws StorageException {
        try {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getName()));
        Resume r = (Resume) in.readObject();
        in.close();
        return r;
        } catch ( ClassNotFoundException | IOException e) {
            throw new StorageException("FileStorage exception", "", e);
        }

    }

    @Override
    protected void doDelete(File file) {
            if (!file.delete()) {
                throw new StorageException("File delete exception", "");
            }
    }

    @Override
    protected List<Resume> doCopyAll() {
        //      return null;
        List<Resume> resumeList = new ArrayList<>();
        File[] files = directory.listFiles();
        for (File file : files) {
            resumeList.add(doGet(file));
        }
        return resumeList;
    }
}