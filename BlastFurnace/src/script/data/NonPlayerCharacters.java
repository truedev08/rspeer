package script.data;

public enum NonPlayerCharacters {
    FOREMAN("Blast Furnace Foreman"),


    ;

    private String name;

    NonPlayerCharacters(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
