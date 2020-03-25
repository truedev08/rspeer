package script.data;

public enum NonPlayerCharacters {

    SOLIHIB("Solihib"),

    ;

    private String name;

    NonPlayerCharacters(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
