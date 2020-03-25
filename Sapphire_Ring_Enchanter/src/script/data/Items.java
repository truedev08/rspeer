package script.data;

public enum Items {

    SAPPHIRE_RING(1, "Sapphire ring"),
    COSMIC_RUNE(1, "Cosmic rune"),
    WATER_STAFF(1, "Water staff"),
    COINS(1, "Coins"),
    RING_OF_RECOIL(1, "Ring of recoil")
    ;

    private int level;
    private String itemName;

    Items(int level, String itemName) {
        this.level = level;
        this.itemName = itemName;
    }

    public int getLevel() {
        return level;
    }

    public String getItemName() {
        return itemName;
    }
}
