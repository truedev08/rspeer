package script.data;

public enum Items {
    GRIMY_RANARR_WEED("Grimy Ranarr weed"),
    RANARR_WEED("Ranarr weed"),
    RANARR_POTION_UNF("Ranarr potion (unf)"),
    VIAL_OF_WATER("Vial of water"),

    ;

    private String name;

    Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
