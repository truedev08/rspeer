package script.data;

public enum Objects {
    BANANA_TREE("Banana tree"),
    BANK_DEPOSIT_BOX("Bank deposit box"),
    GANGPLANK("Gangplank"),

    ;

    private String name;

    Objects(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
