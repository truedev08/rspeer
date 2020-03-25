package script.data;

public enum Items {
    STAFF_OF_WATER("Staff of water"),
    COINS("Coins"),
    COSMIC_RUNE("Cosmic rune"),
    SAPPHIRE_RING("Sapphire ring"),
    RING_OF_RECOIL("Ring of recoil"),

    ;

    private String name;

    Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
