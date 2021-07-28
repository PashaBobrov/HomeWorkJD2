package sessionOrCookies.utils.api;

/**
 * перечиcление видов хранилищ
 * COOKIES и SESSION
 */
public enum TypeStorage {
    COOKIES("COOKIES"),
    SESSION("SESSION");
    private String type;

    TypeStorage(String type) {
        this.type = type;
    }
    public static TypeStorage getType(String type) {
        TypeStorage[] items = TypeStorage.values();
        for (TypeStorage item : items) {
            if (item.type.equals(type)) {
                return item;
            }
        }
        return null;
    }

}
