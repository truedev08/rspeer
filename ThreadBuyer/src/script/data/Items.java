package script.data;

public enum Items {
    THREAD("Thread"),
    COINS("Coins"),
    NEEDLE("Needle"),

    ;

    private String name;

    Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
