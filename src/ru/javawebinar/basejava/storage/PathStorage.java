package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private StorageStrategy strategy;

 //   protected abstract void doWrite(Resume r, OutputStream os) throws IOException;

 //   protected abstract Resume doRead(InputStream is) throws IOException;

    protected PathStorage(String dir, StorageStrategy strategy) {
        this.strategy = strategy;
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
        return Paths.get(directory.toString(), uuid);                            // TEST This!!
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            strategy.doWrite(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path file) {
        if (Files.isDirectory(file)){
            return Files.exists(file);
        }
        return false;
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + path.toString(), path.getFileName().toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return strategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.toString());
        }
    }


    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> resumeList = new ArrayList<>(directory.getNameCount());
        try {
//            Files.list(directory).map((s) -> s + "_1"); // .collect(Collectors.toList())
            List<Path> directoryList = Files.list(directory).collect(toList());
            for (Path path : directoryList) {
                resumeList.add(doGet(path));
            }
        } catch (IOException e) {
            throw new StorageException("Directory read error", null, e);
        }
        return resumeList;
    }
}

