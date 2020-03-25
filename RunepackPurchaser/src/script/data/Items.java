package script.data;

public enum Items {
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
