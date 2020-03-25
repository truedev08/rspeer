package script.data;

public enum Items {
    BRONZE_PLATE_LEGS("Bronze platelegs"),
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
