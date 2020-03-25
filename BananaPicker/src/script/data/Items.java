package script.data;

public enum Items {
    BANANA("Banana"),
    COINS("Coins"),

    ;

    private String name;

    Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
