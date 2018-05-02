package ru.javawebinar.basejava.storage;

public class SerializableStorageContext {
    public AbstractStorage storageStrategy;

    public void setStorageStrategy(AbstractStorage storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

  //  public
}
