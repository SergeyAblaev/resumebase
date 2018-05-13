package ru.javawebinar.basejava.storage;

public class SerializableStorageContext {
    public AbstractStorage storageStrategy;

    public void setStorageStrategy(AbstractStorage storageStrategy) {
        this.storageStrategy = storageStrategy;  // сюда подадим лиюо AbstractPathStorage либо AbstractFileStorage
    }
     //   storageStrategy.save();
  //  public
}
