package script.data;

public enum Items {

    COAL("Coal"),
    IRON_ORE("Iron ore"),
    STAMINA_POTION("Stamina potion(4)"),
    RING_OF_WEALTH("Ring of wealth"),
    STEEL_BAR("Steel bar"),
    IRON_BAR("Iron bar"),
    COINS("Coins"),
    VARROCK_TELEPORT("Varrock teleport"),
    BUCKET_OF_WATER("Bucket of water"),

    ;

    private String name;

    Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
