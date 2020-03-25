package script.data;

public enum NonPlayerCharacters {
    DOMMIK("Dommik"),

    ;

    private String name;

    NonPlayerCharacters(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
