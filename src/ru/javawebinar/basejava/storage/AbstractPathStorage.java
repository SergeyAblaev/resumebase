package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

    protected abstract Resume doRead(InputStream is) throws IOException;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("Directory read error", null, e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        Path file = Files.createFile(directory, new Arrays.asList(uuid));  //??

        @Override
    protected void doUpdate(Resume r, Path Path) {
        try {
            doWrite(r, new BufferedOutputStream(new PathOutputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path Path) {
        return Path.exists();
    }

    @Override
    protected void doSave(Resume r, Path Path) {
        try {
            Path.createNewPath();
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + Path.getAbsolutePath(), Path.getName(), e);
        }
        doUpdate(r, Path);
    }

    @Override
    protected Resume doGet(Path Path) {
        try {
            return doRead(new BufferedInputStream(new PathInputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", Path.getName(), e);
        }
    }

    @Override
    protected void doDelete(Path Path) {
        if (!Path.delete()) {
            throw new StorageException("Path delete error", Path.getName());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        Path[] Paths = directory.listPaths();
        if (Paths == null) {
            throw new StorageException("Directory read error", null);
        }
        List<Resume> list = new ArrayList<>(Paths.length);
        for (Path Path : Paths) {
            list.add(doGet(Path));
        }
        return list;
    }

