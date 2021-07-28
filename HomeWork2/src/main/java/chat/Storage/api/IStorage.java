package chat.Storage.api;

/**
 * Интерфейс для реализции хранилища
 * @version 1.1
 */
public interface IStorage {
    public Object getValue(String atrName);
    public void setValue(String objName, Object obj);
}
