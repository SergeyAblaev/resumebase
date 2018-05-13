package ru.javawebinar.basejava.storage;

public class SerializableStorageContext {
    public AbstractStorage storageStrategy;

    public void setStorageStrategy(AbstractStorage storageStrategy) {
        this.storageStrategy = storageStrategy; // создал возможностью выбора стратегии в классе "ObjectStreamStorage"      // сюда подадим лиюо AbstractPathStorage либо AbstractFileStorage
    }
     //   storageStrategy.save();
  //  public
}
